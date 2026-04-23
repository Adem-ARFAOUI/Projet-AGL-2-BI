package service;
import model.Plat;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<Plat> menu = new ArrayList<>();

    public String ajouterPlat(String nom, double prix, String image) {
        if (nom.isEmpty() || prix <= 0) return "Erreur : Validation échouée.";
        int id = menu.size() + 1;
        menu.add(new Plat(id, nom, prix, image));
        return "Plat ajouté !";
    }

    public boolean supprimerPlat(int id) {
        return menu.removeIf(p -> p.getId() == id);
    }

    public boolean modifierPlat(int id, String nom, double prix) {
        for (Plat p : menu) {
            if (p.getId() == id) {
                p.setNom(nom);
                p.setPrix(prix);
                return true;
            }
        }
        return false;
    }

    public List<Plat> getMenu() { return menu; }

    public Plat trouverPlat(int id) {
        return menu.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}