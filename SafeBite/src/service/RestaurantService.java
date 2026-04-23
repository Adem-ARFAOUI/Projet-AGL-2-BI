package service;

import model.Restaurant;
import java.util.ArrayList;

/**
 * Gère l'inscription et la liste des restaurants.
 * Utilisé par AuthService, FormulaireRestaurant, AdminService.
 */
public class RestaurantService {

    private final ArrayList<Restaurant> restaurants = new ArrayList<>();

    public boolean inscrireRestaurant(String nom, String adresse, String email,
            String telephone, String motDePasse) {
        if (nom.isEmpty() || adresse.isEmpty() || email.isEmpty() ||
                telephone.isEmpty() || motDePasse.isEmpty()) {
            System.out.println("❌ Erreur : tous les champs sont obligatoires.");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("❌ Erreur : format email invalide.");
            return false;
        }
        if (motDePasse.length() < 6) {
            System.out.println("❌ Erreur : mot de passe trop court (min 6 caractères).");
            return false;
        }
        restaurants.add(new Restaurant(nom, adresse, email, telephone, motDePasse));
        return true;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
