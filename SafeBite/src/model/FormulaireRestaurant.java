/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/* interface restau*/
import java.util.Scanner;
import service.AdminService;
import service.RestaurantService;

public class FormulaireRestaurant {

    private final RestaurantService service;
    private final AdminService adminService;
    private final Scanner scanner;

    public FormulaireRestaurant(RestaurantService service, AdminService adminService) {
        this.service = service;
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }

    public void afficherFormulaire() {
        System.out.println("\n===== 🍽️ INSCRIPTION RESTAURANT =====");
        System.out.print("Nom du restaurant : ");
        String nom = scanner.nextLine();

        System.out.print("Adresse           : ");
        String adresse = scanner.nextLine();

        System.out.print("Email             : ");
        String email = scanner.nextLine();

        System.out.print("Téléphone         : ");
        String telephone = scanner.nextLine();

        System.out.print("Mot de passe (min 6 caractères) : ");
        String motDePasse = scanner.nextLine();

        boolean succes = service.inscrireRestaurant(nom, adresse, email,
                telephone, motDePasse);

        if (succes) {
            System.out.println("\n✅ Demande d'inscription envoyée avec succès !");
            System.out.println("   Votre compte est actuellement : EN ATTENTE");
            System.out.println("   Vous serez notifié par email : " + email);

            // Ajouter la demande dans AdminService pour validation
            if (adminService != null) {
                // retrouver le restaurant ajouté dans RestaurantService
                for (model.Restaurant r : service.getRestaurants()) {
                    if (r.getEmail().equals(email)) {
                        adminService.addRestaurant(r);
                        break;
                    }
                }
            }

            // SAF-44 : Notification admin
            notifierAdmin(nom, email);
        } else {
            System.out.println("\n⚠️ L'inscription a échoué. Veuillez réessayer.");
        }
        System.out.println("======================================\n");
    }

    // SAF-44 : notification admin
    private void notifierAdmin(String nomRestaurant, String emailRestaurant) {
        System.out.println("\n📧 [NOTIFICATION ADMIN]");
        System.out.println("   Nouveau restaurant en attente d'approbation :");
        System.out.println("   Nom   : " + nomRestaurant);
        System.out.println("   Email : " + emailRestaurant);
        System.out.println("   Action requise : approuver ou refuser le compte.");
    }
}
