/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sissi
 */
/* interface login client et restau */
import java.util.Scanner;

public class FormulaireLogin {

    private final AuthService authService;
    private final Scanner scanner;

    public FormulaireLogin(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void afficherFormulaire() {
        System.out.println("\n===== 🔐 CONNEXION =====");
        System.out.print("Email      : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        boolean succes = authService.connecter(email, motDePasse);

        if (succes) {
            // SAF-38 : redirection après connexion
            Object connecte = authService.getUtilisateurConnecte();
            System.out.println("\n✅ Connexion réussie !");
            if (connecte instanceof Admin) {
                Admin a = (Admin) connecte;
                System.out
                        .println("   Bienvenue " + a.getPrenom() + " " + a.getNom() + " [" + a.getNiveauAcces() + "]");
                System.out.println("   ➡️  Redirection vers : Tableau de bord Admin");
            } else if (connecte instanceof Restaurateur) {
                Restaurateur rtr = (Restaurateur) connecte;
                System.out.println("   Bienvenue " + rtr.getPrenom() + " " + rtr.getNom());
                System.out.println("   ➡️  Redirection vers : Tableau de bord Restaurateur");
            } else if (connecte instanceof Utilisateur) {
                Utilisateur u = (Utilisateur) connecte;
                System.out.println("   Bienvenue " + u.getPrenom() + " " + u.getNom());
                System.out.println("   ➡️  Redirection vers : Tableau de bord Client");
            } else if (connecte instanceof Restaurant) {
                Restaurant r = (Restaurant) connecte;
                System.out.println("   Bienvenue " + r.getNom());
                System.out.println("   ➡️  Redirection vers : Tableau de bord Restaurant");
            }
        } else {
            System.out.println("\n⚠️  Connexion échouée. Veuillez réessayer.");
        }
        System.out.println("========================\n");
    }
}