package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un restaurant sur la plateforme SafeBite.
 *
 * Compatible avec :
 * - AuthService (getEmail, getMotDePasse, getStatut)
 * - AdminService (getName, isValidated, setValidated)
 * - FormulaireRestaurant (getNom, getEmail, getStatut)
 * - ServiceSafeBite (getNom, getLocalisation, getNote)
 * - SafeBiteConsole (getNom, getMenu)
 */
public class Restaurant {

    private String nom;
    private String adresse; // localisation
    private String email;
    private String telephone;
    private String motDePasse;
    private String statut; // "en attente" | "approuvé" | "refusé"
    private boolean validated;
    private double note;
    private List<Plat> menu;

    // ─── Constructeurs ────────────────────────────────────────────────────────

    /** Constructeur complet (inscription restaurant) */
    public Restaurant(String nom, String adresse, String email,
            String telephone, String motDePasse) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.statut = "en attente";
        this.validated = false;
        this.note = 0.0;
        this.menu = new ArrayList<>();
    }

    /** Constructeur simplifié pour ServiceSafeBite (nom, localisation, note) */
    public Restaurant(String nom, String adresse, double note) {
        this(nom, adresse, "", "", "");
        this.note = note;
    }

    /** Constructeur minimal pour SafeBiteConsole (nom seul) */
    public Restaurant(String nom) {
        this(nom, "", "", "", "");
        // Menu par défaut pour la démo console
        this.menu.add(new Plat(1, "Couscous Royal", 15.0, "couscous.jpg"));
        this.menu.add(new Plat(2, "Brick à l'œuf", 8.5, "brick.jpg"));
        this.menu.add(new Plat(3, "Pizza Margherita", 12.0, "pizza.jpg"));
        this.menu.add(new Plat(4, "Salade César", 9.0, "salade.jpg"));
        this.menu.add(new Plat(5, "Burger Gourmet", 11.5, "burger.jpg"));
        this.menu.add(new Plat(6, "Pâtes Carbonara", 13.0, "pates.jpg"));
    }

    // ─── Getters ──────────────────────────────────────────────────────────────

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLocalisation() {
        return adresse;
    } // alias ServiceSafeBite

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getStatut() {
        return statut;
    }

    public boolean isValidated() {
        return validated;
    }

    public double getNote() {
        return note;
    }

    public List<Plat> getMenuPlats() {
        return menu;
    }

    /**
     * Retourne les noms des plats sous forme de liste de String (SafeBiteConsole)
     */
    public List<String> getMenu() {
        List<String> noms = new ArrayList<>();
        for (Plat p : menu) {
            noms.add(String.format("%-25s %.2f DT", p.getNom(), p.getPrix()));
        }
        return noms;
    }

    /** Alias getName() pour compatibilité AdminService */
    public String getName() {
        return nom;
    }

    // ─── Setters ──────────────────────────────────────────────────────────────

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String tel) {
        this.telephone = tel;
    }

    public void setMotDePasse(String mdp) {
        this.motDePasse = mdp;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setStatut(String statut) {
        this.statut = statut;
        this.validated = statut.equals("approuvé");
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
        this.statut = validated ? "approuvé" : "refusé";
    }

    // ─── Gestion du menu ──────────────────────────────────────────────────────

    public void ajouterPlat(Plat plat) {
        menu.add(plat);
    }

    public boolean supprimerPlat(int id) {
        return menu.removeIf(p -> p.getId() == id);
    }

    @Override
    public String toString() {
        return String.format("🍽️  %s | %s | Note: %.1f | Statut: %s",
                nom, adresse, note, statut);
    }
}