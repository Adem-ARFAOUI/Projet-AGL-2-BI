package service;

import java.util.*;
import model.*;

/**
 * Classe SafeBiteConsole - Application console pour SafeBite
 * Gère la fonctionnalité "Commander un plat" 100% en terminal
 */
public class SafeBiteConsole {
    private Client client;
    private Restaurant resto;
    private Map<String, Integer> panier;
    private Scanner scanner;
    private IA_Recommandation iaService; // Instance de l'IA
    private GestionnaireAvis gestionnaireAvis;

    public SafeBiteConsole(GestionnaireAvis gestionnairePartage) {
        this.client = new Client("Jridi", "Ilyes", "ilyes.jridi@example.com", "password123");
        this.resto = new Restaurant("SafeBite Cantine");
        this.panier = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
        this.iaService = new IA_Recommandation(); // Initialisation
        this.gestionnaireAvis = gestionnairePartage; // Initialisation
    }

    /**
     * Lance l'application console
     */
    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1" -> consulterPlats();
                case "2" -> ajouterPlat();
                case "3" -> afficherPanier();
                case "4" -> validerCommande();
                case "5" -> viderPanier();
                case "6" -> demanderRecommandationIA();
                case "7" -> laisserUnAvis(); // Nouvelle fonctionnalité
                case "8" -> {
                    continuer = false;
                    afficherAuRevoir();
                }
                default -> System.out.println("❌ Choix invalide.");
            }
            if (continuer)
                scanner.nextLine();
        }
    }

    /**
     * SOUS-TÂCHE 1: Consulter les plats
     */
    private void consulterPlats() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     📋 MENU DISPONIBLE                 ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        List<String> plats = resto.getMenu();
        for (int i = 0; i < plats.size(); i++) {
            System.out.printf("  %d. %-35s%n", (i + 1), plats.get(i));
        }

        System.out.println("\n💡 Tapez un numéro pour ajouter un plat au panier :");
    }

    /**
     * SOUS-TÂCHE 2: Ajouter au panier
     */
    private void ajouterPlat() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     🛒 AJOUTER UN PLAT AU PANIER      ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        consulterPlats();

        System.out.print("\n✍️  Entrez le numéro du plat (ou 0 pour annuler): ");
        String choix = scanner.nextLine().trim();

        if (choix.equals("0")) {
            System.out.println("❌ Opération annulée");
            return;
        }

        try {
            int numero = Integer.parseInt(choix);
            List<String> plats = resto.getMenu();

            if (numero < 1 || numero > plats.size()) {
                System.out.println("❌ Numéro invalide");
                return;
            }

            String platChoisi = plats.get(numero - 1);

            System.out.print("Quantité (par défaut 1): ");
            String qtyStr = scanner.nextLine().trim();
            int quantite = qtyStr.isEmpty() ? 1 : Integer.parseInt(qtyStr);

            if (quantite <= 0) {
                System.out.println("❌ Quantité invalide");
                return;
            }

            panier.put(platChoisi, panier.getOrDefault(platChoisi, 0) + quantite);

            System.out.println("✓ " + quantite + "x " + platChoisi + " ajouté(s) au panier");

        } catch (NumberFormatException e) {
            System.out.println("❌ Veuillez entrer un nombre valide");
        }
    }

    /**
     * Afficher le contenu du panier
     */
    private void afficherPanier() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     🛒 VOTRE PANIER                    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        if (panier.isEmpty()) {
            System.out.println("  ❌ Le panier est vide\n");
            return;
        }

        int index = 1;
        int totalArticles = 0;
        for (Map.Entry<String, Integer> entry : panier.entrySet()) {
            System.out.printf("  %d. %-30s | Quantité: %d%n", index, entry.getKey(), entry.getValue());
            totalArticles += entry.getValue();
            index++;
        }

        System.out.println("\n  ─────────────────────────────────────────");
        System.out.println("  📊 Total articles: " + totalArticles);
    }

    /**
     * SOUS-TÂCHE 3: Valider la commande
     */
    private void validerCommande() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     ✅ VALIDER LA COMMANDE             ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        if (panier.isEmpty()) {
            System.out.println("❌ Le panier est vide! Impossible de valider.");
            return;
        }

        afficherPanier();

        System.out.print("\n❓ Confirmer la commande? (oui/non): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("oui") && !confirmation.equals("o")) {
            System.out.println("❌ Commande annulée");
            return;
        }

        // Créer la commande
        String uniqueID = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        List<String> selection = new ArrayList<>(panier.keySet());

        Commande maCommande = new Commande(uniqueID, selection);
        maCommande.confirmer();

        // Afficher les détails de la commande
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     🎉 COMMANDE VALIDÉE!               ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.println("  ID Commande:     " + maCommande.getId());
        System.out.println("  Statut:          " + maCommande.getStatut());
        System.out.println("  Client:          " + client.getNom());
        System.out.println("  Restaurant:      " + resto.getNom());

        int totalArticles = panier.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("  Nombre articles: " + totalArticles);

        System.out.println("\n  📦 Détails de la commande:");
        int index = 1;
        for (Map.Entry<String, Integer> entry : panier.entrySet()) {
            System.out.println("     " + index + ". " + entry.getKey() + " (x" + entry.getValue() + ")");
            index++;
        }

        System.out.println("\n  ─────────────────────────────────────────");
        System.out.println("  ✓ Commande en cours de préparation...");
        System.out.println("  ✓ Vous recevrez une notification quand c'est prêt!");

        // Vider le panier après validation
        panier.clear();
    }

    /**
     * Vider le panier
     */
    private void viderPanier() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     🗑️  VIDER LE PANIER                ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        if (panier.isEmpty()) {
            System.out.println("❌ Le panier est déjà vide");
            return;
        }

        System.out.print("Êtes-vous sûr? (oui/non): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("oui") || confirmation.equals("o")) {
            panier.clear();
            System.out.println("✓ Panier vidé avec succès");
        } else {
            System.out.println("❌ Opération annulée");
        }
    }

    /**
     * Afficher le header
     */
    private void afficherHeader() {
        effacerEcran();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  🍽️  SAFEBITE - MODULE DE COMMANDE    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n  Bienvenue " + client.getNom() + "!");
        System.out.println("  Restaurant: " + resto.getNom());
        System.out.println("\n");
    }

    /**
     * Afficher le menu principal
     */
    private void afficherMenu() {
        System.out.println("  1️⃣  Consulter les plats");
        System.out.println("  2️⃣  Ajouter un plat au panier");
        System.out.println("  3️⃣  Afficher mon panier");
        System.out.println("  4️⃣  Valider ma commande");
        System.out.println("  5️⃣  Vider le panier");
        System.out.println("  6️⃣  ✨ Recommandations IA");
        System.out.println("  7️⃣  💬 Laisser un avis"); // Ajouté
        System.out.println("  8️⃣  Quitter"); // Décalé

        int totalArticles = panier.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("  📊 Articles dans le panier: " + totalArticles);
        System.out.println("  ─────────────────────────────────────────\n");

        System.out.print("✍️  Votre choix: ");
    }

    /**
     * NOUVELLE MÉTHODE : Permet au client de noter et commenter
     */
    private void laisserUnAvis() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     💬 LAISSER UN AVIS SUR LE RESTO    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        try {
            System.out.print("Note sur 5 (étoiles) : ");
            int note = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Votre commentaire : ");
            String commentaire = scanner.nextLine().trim();

            // Création de l'objet Avis
            Avis nouvelAvis = new Avis(client.getNom(), note, commentaire);

            // Enregistrement via le gestionnaire
            this.gestionnaireAvis.ajouterAvis(nouvelAvis);

            System.out.println("\n⭐ Merci pour votre retour !");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur : La note doit être un chiffre.");
        }
    }

    /**
     * NOUVELLE MÉTHODE : Recommandation accessible uniquement au client
     */
    private void demanderRecommandationIA() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     ✨ RECOMMANDATIONS IA PERSONNALISÉES ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.print("Quelle est votre envie actuelle ? (Healthy / Italien / Fast-food) : ");
        String pref = scanner.nextLine().trim();

        List<Plat> suggestions = iaService.genererSuggestions(pref);

        if (suggestions.isEmpty()) {
            System.out.println("❌ Désolé " + client.getNom() + ", aucune suggestion pour cette catégorie.");
        } else {
            System.out.println("Voici ce que notre IA suggère pour vous :");
            for (Plat p : suggestions) {
                System.out.println("  ⭐ " + p.getNom() + " — " + p.getPrix() + " DT");
            }
            System.out.println("\n💡 Vous pouvez maintenant ajouter ces plats via l'option 2.");
        }
    }

    /**
     * Afficher un message d'au revoir
     */
    private void afficherAuRevoir() {
        effacerEcran();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     👋 MERCI D'AVOIR UTILISÉ SAFEBITE  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n  À bientôt!");
    }

    /**
     * Effacer l'écran (simule)
     */
    private void effacerEcran() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
