package service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Plat;
import model.Restaurant;


/**
 * Logique métier pour les plats et les restaurants (SAF-21, SAF-23, SAF-26, SAF-28).
 */
public class ServiceSafeBite {
    private List<Plat> cataloguePlats = new ArrayList<>();
    private List<Restaurant> listeRestaurants = new ArrayList<>();

    public ServiceSafeBite() {
        // Données fictives Plats
        cataloguePlats.add(new Plat("Couscous", 15.0, "couscous.jpg", "Tunisien"));
        cataloguePlats.add(new Plat("Pizza", 12.5, "pizza.jpg", "Italien"));
        cataloguePlats.add(new Plat("Salade", 8.0, "salade.jpg", "Healthy"));

        // Données fictives Restaurants
        listeRestaurants.add(new Restaurant("Le SafSaf", "La Marsa", 4.5));
        listeRestaurants.add(new Restaurant("La Tavola", "Tunis", 4.2));
        listeRestaurants.add(new Restaurant("Fast Food Express", "Sousse", 3.5));
    }

    // --- Gestion des Plats ---
    public List<Plat> filtrerPlats(String categorie) {
        return cataloguePlats.stream()
                .filter(p -> p.getCategorie().equalsIgnoreCase(categorie))
                .collect(Collectors.toList());
    }

    public List<Plat> getTousLesPlats() { return cataloguePlats; }

    // --- Gestion des Restaurants ---
    public List<Restaurant> chercherRestaurant(String motCle) {
        return listeRestaurants.stream()
                .filter(r -> r.getNom().toLowerCase().contains(motCle.toLowerCase()) || 
                             r.getLocalisation().toLowerCase().contains(motCle.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Restaurant> filtrerParNote(List<Restaurant> liste, double noteMin) {
        return liste.stream().filter(r -> r.getNote() >= noteMin).collect(Collectors.toList());
    }
}
