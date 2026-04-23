package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Représente une commande sur la plateforme SafeBite.
 *
 * Compatible avec :
 * - CommandeService (getId int, List<Plat>, setStatut)
 * - SafeBiteConsole (getId String, List<String>, confirmer, getStatut)
 */
public class Commande {

    // ─── Champs ───────────────────────────────────────────────────────────────

    private int idInt; // usage CommandeService
    private String idStr; // usage SafeBiteConsole (UUID court)
    private List<Plat> plats;
    private List<String> platsNoms; // usage SafeBiteConsole
    private String statut;
    private LocalDateTime dateCommande;
    private Client client;

    // ─── Constructeurs ────────────────────────────────────────────────────────

    /** Constructeur CommandeService : (int id, List<Plat>) */
    public Commande(int id, List<Plat> plats) {
        this.idInt = id;
        this.idStr = String.valueOf(id);
        this.plats = plats;
        this.platsNoms = new ArrayList<>();
        for (Plat p : plats)
            platsNoms.add(p.getNom());
        this.statut = "En attente";
        this.dateCommande = LocalDateTime.now();
    }

    /** Constructeur SafeBiteConsole : (String id, List<String>) */
    public Commande(String id, List<String> platsNoms) {
        this.idStr = id;
        this.idInt = 0;
        this.platsNoms = platsNoms;
        this.plats = new ArrayList<>();
        this.statut = "En attente";
        this.dateCommande = LocalDateTime.now();
    }

    // ─── Méthodes ─────────────────────────────────────────────────────────────

    /** Confirme la commande (SafeBiteConsole) */
    public void confirmer() {
        this.statut = "Confirmée ✅";
    }

    /** Getters */
    public int getId() {
        return idInt;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getStatut() {
        return statut;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public List<String> getPlatsNoms() {
        return platsNoms;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    /** Setters */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        String date = dateCommande.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return String.format("Commande #%s | %s | %s | %d article(s)",
                idStr, statut, date, platsNoms.isEmpty() ? plats.size() : platsNoms.size());
    }
}