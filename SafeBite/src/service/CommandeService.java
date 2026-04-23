package service;
import java.util.*;
import model.Commande;
import model.Plat;

public class CommandeService {
    private List<Commande> historique = new ArrayList<>();

    public void enregistrerCommande(List<Plat> plats) {
        int id = historique.size() + 1;
        historique.add(new Commande(id, plats));
    }

    public List<Commande> getHistorique() { return historique; }

    public boolean changerStatut(int id, String nouveauStatut) {
        for (Commande c : historique) {
            if (c.getId() == id) {
                c.setStatut(nouveauStatut);
                return true;
            }
        }
        return false;
    }
}