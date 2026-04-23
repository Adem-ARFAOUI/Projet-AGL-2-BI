package model;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * Représente un client de la plateforme SafeBite.
 * Peut consulter des plats, passer des commandes et laisser des avis.
 */
public class Client extends Utilisateur {
 
    private List<Commande> historiqueCommandes;
    private List<String> preferences; // ex: "Healthy", "Italien"
 
    public Client(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.historiqueCommandes = new ArrayList<>();
        this.preferences = new ArrayList<>();
    }
 
    @Override
    public String getRole() {
        return "CLIENT";
    }
 
    public void ajouterCommande(Commande commande) {
        historiqueCommandes.add(commande);
    }
 
    public List<Commande> getHistoriqueCommandes() {
        return historiqueCommandes;
    }
 
    public void ajouterPreference(String preference) {
        if (!preferences.contains(preference)) {
            preferences.add(preference);
        }
    }
 
    public List<String> getPreferences() {
        return preferences;
    }
}