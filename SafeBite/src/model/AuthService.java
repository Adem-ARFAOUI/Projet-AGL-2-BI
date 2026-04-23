/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import service.AdminService;
import service.CompteService;
import service.RestaurantService;

public class AuthService {

    private final CompteService compteService;
    private final RestaurantService restaurantService;
    private final AdminService adminService;
    private Object utilisateurConnecte; // garde qui est connecté

    public AuthService(CompteService compteService,
            RestaurantService restaurantService,
            AdminService adminService) {
        this.compteService = compteService;
        this.restaurantService = restaurantService;
        this.adminService = adminService;
    }

    public boolean connecter(String email, String motDePasse) {

        // Vérifier champs vides
        if (email.isEmpty() || motDePasse.isEmpty()) {
            System.out.println("❌ Erreur : email et mot de passe obligatoires.");
            return false;
        }

        // Chercher dans les clients
        ArrayList<Client> clients = compteService.getUtilisateurs();
        for (Client u : clients) {
            if (u.getEmail().equals(email) &&
                    u.getMotDePasse().equals(motDePasse)) {
                utilisateurConnecte = u;
                return true;
            }
        }

        // Chercher dans les admins
        if (adminService != null) {
            for (Utilisateur u : adminService.getUsers()) {
                if (u.getEmail().equals(email) && u.getMotDePasse().equals(motDePasse)) {
                    utilisateurConnecte = u;
                    return true;
                }
            }
        }

        // Chercher dans les restaurants
        ArrayList<Restaurant> restos = restaurantService.getRestaurants();
        for (Restaurant r : restos) {
            if (r.getEmail().equals(email) &&
                    r.getMotDePasse().equals(motDePasse)) {
                // SAF-43 : vérifier statut
                if (r.getStatut().equals("en attente")) {
                    System.out.println("⚠️ Votre compte restaurant est en attente d'approbation.");
                    return false;
                }
                utilisateurConnecte = r;
                return true;
            }
        }

        System.out.println("❌ Email ou mot de passe incorrect.");
        return false;
    }

    public Object getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public void deconnecter() {
        utilisateurConnecte = null;
        System.out.println("👋 Déconnexion réussie.");
    }

}
