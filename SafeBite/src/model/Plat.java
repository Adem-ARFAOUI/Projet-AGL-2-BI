package model;

/**
 * Représente un plat sur la plateforme SafeBite.
 *
 * Compatible avec :
 * - MenuService (getId, getNom, getPrix, setNom, setPrix)
 * - CommandeService (constructeur avec id + List<Plat>)
 * - ServiceSafeBite (getNom, getPrix, getCategorie)
 * - IA_Recommandation (getNom, getCategorie, getPrix)
 */
public class Plat {

    private int id;
    private String nom;
    private double prix;
    private String image;
    private String categorie;

    // ─── Constructeurs ────────────────────────────────────────────────────────

    /** Constructeur complet (MenuService, Restaurant) */
    public Plat(int id, String nom, double prix, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.categorie = "Général";
    }

    /** Constructeur avec catégorie (ServiceSafeBite, IA_Recommandation) */
    public Plat(String nom, double prix, String image, String categorie) {
        this.id = 0;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.categorie = categorie;
    }

    /** Constructeur IA_Recommandation (nom, categorie, prix) */
    public Plat(String nom, String categorie, double prix) {
        this.id = 0;
        this.nom = nom;
        this.prix = prix;
        this.image = "";
        this.categorie = categorie;
    }

    // ─── Getters ──────────────────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public String getCategorie() {
        return categorie;
    }

    // ─── Setters ──────────────────────────────────────────────────────────────

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategorie(String cat) {
        this.categorie = cat;
    }

    @Override
    public String toString() {
        return String.format("[%d] %-25s | %.2f DT | %s", id, nom, prix, categorie);
    }
}