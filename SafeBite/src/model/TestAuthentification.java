/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import service.AdminService;
import service.CompteService;
import service.RestaurantService;

/* teste authen client et restau */
public class TestAuthentification {

    public static void tester(CompteService compteService,
            RestaurantService restaurantService,
            AdminService adminService) {
        System.out.println("\n===== 🧪 TEST AUTHENTIFICATION =====");
        AuthService auth = new AuthService(compteService, restaurantService, adminService);

        // Test 1 : mauvais mot de passe
        System.out.println("\n[Test 1] Mauvais mot de passe :");
        auth.connecter("test@test.com", "mauvais");

        // Test 2 : email inexistant
        System.out.println("\n[Test 2] Email inexistant :");
        auth.connecter("inconnu@test.com", "123456");

        // Test 3 : connexion correcte client
        System.out.println("\n[Test 3] Connexion client valide :");
        if (!compteService.getUtilisateurs().isEmpty()) {
            Utilisateur u = compteService.getUtilisateurs().get(0);
            boolean result = auth.connecter(u.getEmail(), u.getMotDePasse());
            System.out.println("   Résultat : " + (result ? "✅ Succès" : "❌ Échec"));
        } else {
            System.out.println("   Aucun client enregistré pour tester.");
        }
        System.out.println("=====================================\n");
    }

}
