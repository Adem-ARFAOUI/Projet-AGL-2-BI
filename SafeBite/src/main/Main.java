package main;

import java.util.List;
import java.util.Scanner;
import model.*;
import service.*;
import ui.AdminUI;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ SAFEBITE – Point d'entrée commun ║
 * ║ Authentification | Visiteur | Restaurant | Client | Admin ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * Comment exécuter :
 * javac -d out -sourcepath src src/com/safebite/Main.java
 * java -cp out Main
 */
public class Main {

    // ── Services partagés ─────────────────────────────────────────────────────
    private static final CompteService compteService = new CompteService();
    private static final RestaurantService restaurantService = new RestaurantService();
    private static final AdminService adminService = new AdminService();
    private static final AuthService authService = new AuthService(compteService, restaurantService, adminService);

    private static final MenuService menuService = new MenuService();
    private static final CommandeService commandeService = new CommandeService();
    private static final GestionnaireAvis gestionnaireAvisPartage = new GestionnaireAvis();

    private static final AdminUI adminUI = new AdminUI(adminService);

    private static final Scanner scanner = new Scanner(System.in);

    // ── Données de démonstration ──────────────────────────────────────────────
    static {
        // Pré-charger un client de test
        compteService.inscrireUtilisateur("Ben Ali", "Sonia", "sonia@test.com", "123456");
        // Ajouter le client pré-chargé à la liste gérée par l'admin
        for (model.Client c : compteService.getUtilisateurs()) {
            if (c.getEmail().equals("sonia@test.com")) {
                adminService.addUser(c);
                break;
            }
        }

        // Pré-charger un restaurant approuvé
        restaurantService.inscrireRestaurant(
                "Le SafSaf", "La Marsa", "safesaf@resto.com", "71000000", "resto123");
        restaurantService.getRestaurants().get(0).setStatut("approuvé");

        // Pré-charger des plats dans le menu
        menuService.ajouterPlat("Couscous Royal", 15.0, "couscous.jpg");
        menuService.ajouterPlat("Pizza Napolitaine", 12.5, "pizza.jpg");
        menuService.ajouterPlat("Salade César", 9.0, "salade.jpg");

        // Pré-charger un admin dans AdminService
        adminService.addUser(new model.Admin("Admin", "Principal", "admin@safebite.com", "admin123", "SUPER_ADMIN"));
        adminService.addRestaurant(
                new model.Restaurant("Bistro Test", "2 Rue de la Paix", "bistro@test.com", "bistro123", "en attente"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      🍽️   BIENVENUE SUR SAFEBITE            ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            afficherMenuPrincipal();
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> moduleAuthentification();
                case "2" -> moduleVisiteur();
                case "3" -> moduleRestaurant();
                case "4" -> moduleClient();
                case "5" -> moduleAdmin();
                case "6" -> moduleTests();
                case "0" -> {
                    running = false;
                    System.out.println("👋 À bientôt !");
                }
                default -> System.out.println("❌ Choix invalide.");
            }
        }
        scanner.close();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MENU PRINCIPAL
    // ══════════════════════════════════════════════════════════════════════════
    private static void afficherMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           MENU PRINCIPAL SAFEBITE            ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1 - 🔐 Authentification (Connexion / Inscription) ║");
        System.out.println("║  2 - 👁️  Visiteur        (Catalogue & Recherche)    ║");
        System.out.println("║  3 - 🍽️  Restaurant      (Gestion menu)             ║");
        System.out.println("║  4 - 🛒 Client           (Commander un plat)        ║");
        System.out.println("║  5 - 🛡️  Admin           (Validation & Gestion)     ║");
        System.out.println("║  6 - 🧪 Tests            (Tests automatiques)       ║");
        System.out.println("║  0 - Quitter                                        ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Votre choix : ");
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 1 – AUTHENTIFICATION
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleAuthentification() {
        System.out.println("\n=== 🔐 MODULE AUTHENTIFICATION ===");
        System.out.println("1 - Créer un compte client");
        System.out.println("2 - Inscription restaurant");
        System.out.println("3 - Se connecter");
        System.out.println("4 - Se déconnecter");
        System.out.print("Choix : ");
        String choix = scanner.nextLine().trim();

        switch (choix) {
            case "1" -> new FormulaireCompte(compteService, adminService).afficherFormulaire();
            case "2" -> new FormulaireRestaurant(restaurantService, adminService).afficherFormulaire();
            case "3" -> new FormulaireLogin(authService).afficherFormulaire();
            case "4" -> authService.deconnecter();
            default -> System.out.println("❌ Choix invalide.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 2 – VISITEUR (Catalogue, Recherche, Avis)
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleVisiteur() {
        System.out.println("\n=== 👁️ MODULE VISITEUR ===");
        System.out.println("1 - Afficher tous les plats");
        System.out.println("2 - Filtrer par catégorie");
        System.out.println("3 - Rechercher un restaurant");
        System.out.println("4 - Avis publics"); 
        System.out.print("Choix : ");
        String choix = scanner.nextLine().trim();

        ServiceSafeBite svc = new ServiceSafeBite();

        switch (choix) {
            case "1" -> {
                System.out.println("\n--- 🍴 CATALOGUE COMPLET ---");
                svc.getTousLesPlats().forEach(
                        p -> System.out.println("  " + p.getNom() + " – " + p.getPrix() + " DT | " + p.getCategorie()));
            }
            case "2" -> {
                System.out.print("Catégorie (Healthy / Italien / Tunisien / Fast-food) : ");
                String cat = scanner.nextLine().trim();
                List<model.Plat> res = svc.filtrerPlats(cat);
                if (res.isEmpty())
                    System.out.println("Aucun plat trouvé.");
                else
                    res.forEach(p -> System.out.println("  " + p));
            }
            case "3" -> {
                System.out.print("Mot-clé : ");
                String mc = scanner.nextLine().trim();
                svc.chercherRestaurant(mc).forEach(r -> System.out.println("  " + r));
            }
            case "4" -> gestionnaireAvisPartage.afficherAvisPublics();
            default -> System.out.println("❌ Choix invalide.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 3 – RESTAURANT (Gestion du menu)
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleRestaurant() {
        System.out.println("\n=== 🍽️ MODULE RESTAURANT ===");
        System.out.println("1 - Voir le menu");
        System.out.println("2 - Ajouter un plat");
        System.out.println("3 - Modifier un plat");
        System.out.println("4 - Supprimer un plat");
        System.out.println("5 - Voir les commandes");
        System.out.print("Choix : ");
        String choix = scanner.nextLine().trim();

        switch (choix) {
            case "1" -> {
                System.out.println("\n--- MENU ACTUEL ---");
                if (menuService.getMenu().isEmpty())
                    System.out.println("Aucun plat.");
                else
                    menuService.getMenu().forEach(p -> System.out.println("  " + p));
            }
            case "2" -> {
                System.out.print("Nom du plat    : ");
                String nom = scanner.nextLine();
                System.out.print("Prix (DT)      : ");
                double prix = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Image (fichier): ");
                String img = scanner.nextLine();
                System.out.println(menuService.ajouterPlat(nom, prix, img));
            }
            case "3" -> {
                System.out.print("ID du plat à modifier : ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Nouveau nom           : ");
                String nom = scanner.nextLine();
                System.out.print("Nouveau prix (DT)     : ");
                double prix = Double.parseDouble(scanner.nextLine().trim());
                System.out.println(menuService.modifierPlat(id, nom, prix) ? "✅ Plat modifié." : "❌ Plat introuvable.");
            }
            case "4" -> {
                System.out.print("ID du plat à supprimer : ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                System.out.println(menuService.supprimerPlat(id) ? "✅ Plat supprimé." : "❌ Plat introuvable.");
            }
            case "5" -> {
                System.out.println("\n--- HISTORIQUE COMMANDES ---");
                if (commandeService.getHistorique().isEmpty())
                    System.out.println("Aucune commande.");
                else
                    commandeService.getHistorique().forEach(c -> System.out.println("  " + c));
            }
            default -> System.out.println("❌ Choix invalide.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 4 – CLIENT
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleClient() {
        System.out.println("\n=== 🛒 MODULE CLIENT ===");
        // L'IA est maintenant gérée à l'intérieur de SafeBiteConsole
        SafeBiteConsole console = new SafeBiteConsole(gestionnaireAvisPartage);
        console.demarrer();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 5 – ADMIN
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleAdmin() {
        System.out.println("\n=== 🛡️ MODULE ADMIN ===");
        System.out.println("1 - Valider / Refuser des restaurants");
        System.out.println("2 - Gérer les utilisateurs");
        System.out.print("Choix : ");
        String choix = scanner.nextLine().trim();

        switch (choix) {
            case "1" -> adminUI.showRestaurantValidation();
            case "2" -> adminUI.showUserManagement();
            default -> System.out.println("❌ Choix invalide.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MODULE 6 – TESTS AUTOMATIQUES
    // ══════════════════════════════════════════════════════════════════════════
    private static void moduleTests() {
        System.out.println("\n=== 🧪 TESTS AUTOMATIQUES ===");
        System.out.println("1 - Test authentification");
        System.out.println("2 - Test menu service");
        System.out.println("3 - Test commande service");
        System.out.print("Choix : ");
        String choix = scanner.nextLine().trim();

        switch (choix) {
            case "1" -> TestAuthentification.tester(compteService, restaurantService, adminService);
            case "2" -> {
                System.out.println("\n--- Test MenuService ---");
                MenuService ms = new MenuService();
                System.out.println(ms.ajouterPlat("Test Plat", 10.0, "test.jpg"));
                System.out.println("Menu : " + ms.getMenu());
                System.out.println(ms.modifierPlat(1, "Plat Modifié", 12.0) ? "✅ Modifié" : "❌ Erreur");
                System.out.println(ms.supprimerPlat(1) ? "✅ Supprimé" : "❌ Erreur");
            }
            case "3" -> {
                System.out.println("\n--- Test CommandeService ---");
                CommandeService cs = new CommandeService();
                List<model.Plat> plats = new java.util.ArrayList<>();
                plats.add(new model.Plat(1, "Couscous", 15.0, "c.jpg"));
                cs.enregistrerCommande(plats);
                System.out.println("Commandes : " + cs.getHistorique().size());
                System.out.println(cs.changerStatut(1, "Livré") ? "✅ Statut changé" : "❌ Erreur");
            }
            default -> System.out.println("❌ Choix invalide.");
        }
    }
}