package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Logique de recommandation intelligente (SAF-55 et SAF-57).
 */
public class IA_Recommandation {
    private List<Plat> cataloguePlats;

    public IA_Recommandation() {
        cataloguePlats = new ArrayList<>();
        // Simulation d'une base de données de plats
        cataloguePlats.add(new Plat("Salade César", "Healthy", 15.5));
        cataloguePlats.add(new Plat("Burger Gourmet", "Fast-food", 22.0));
        cataloguePlats.add(new Plat("Pizza Margherita", "Italien", 18.0));
        cataloguePlats.add(new Plat("Bol de Quinoa", "Healthy", 14.0));
        cataloguePlats.add(new Plat("Pâtes Carbonara", "Italien", 20.0));
    }

    /**
     * Analyse les préférences et retourne des suggestions filtrées (Critères
     * d'acceptation).
     */
    public List<Plat> genererSuggestions(String preference) {
        return cataloguePlats.stream()
                .filter(p -> p.getCategorie().equalsIgnoreCase(preference))
                .collect(Collectors.toList());
    }
}
