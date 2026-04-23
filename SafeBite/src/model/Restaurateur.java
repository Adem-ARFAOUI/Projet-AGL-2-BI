package model;

/**
 * Représente le propriétaire/gérant d'un restaurant sur SafeBite.
 * Associé à un objet Restaurant, peut gérer le menu et les commandes.
 */
public class Restaurateur extends Utilisateur {

    private Restaurant restaurant;

    public Restaurateur(String nom, String prenom, String email, String motDePasse,
            Restaurant restaurant) {
        super(nom, prenom, email, motDePasse);
        this.restaurant = restaurant;
    }

    @Override
    public String getRole() {
        return "RESTAURATEUR";
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return super.toString() + " → " + (restaurant != null ? restaurant.getNom() : "Sans restaurant");
    }
}
