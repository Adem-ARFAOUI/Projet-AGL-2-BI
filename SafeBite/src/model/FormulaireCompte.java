/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;
import service.AdminService;
import service.CompteService;

public class FormulaireCompte {

    private final CompteService service;
    private final AdminService adminService;
    private final Scanner scanner;

    public FormulaireCompte(CompteService service, AdminService adminService) {
        this.service = service;
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }

    public void afficherFormulaire() {
        System.out.println("\n===== 📝 CRÉATION DE COMPTE CLIENT =====");
        System.out.print("Nom       : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom    : ");
        String prenom = scanner.nextLine();

        System.out.print("Email     : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe (min 6 caractères) : ");
        String motDePasse = scanner.nextLine();

        // Envoyer au service
        boolean succes = service.inscrireUtilisateur(nom, prenom, email, motDePasse);

        // SAF-34 : message de confirmation
        if (succes) {
            System.out.println("\n✅ Compte créé avec succès !");
            System.out.println("   Bienvenue sur SafeBite, " + prenom + " " + nom + " !");
            System.out.println("   Un email de confirmation a été envoyé à : " + email);

            // Ajouter l'utilisateur aux utilisateurs gérés par l'admin
            if (adminService != null) {
                for (model.Client c : service.getUtilisateurs()) {
                    if (c.getEmail().equals(email)) {
                        adminService.addUser(c);
                        break;
                    }
                }
            }
        } else {
            System.out.println("\n⚠️  La création du compte a échoué. Veuillez réessayer.");
        }
        System.out.println("=========================================\n");
    }
}