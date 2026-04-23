package model;

/**
 * Classe de base représentant un utilisateur du système SafeBite.
 * Héritée par Admin, Client et Restaurateur.
 */
public abstract class Utilisateur {

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private boolean suspendu;

    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.suspendu = false;
    }

    // Constructeur simplifié (pour SafeBiteConsole)
    public Utilisateur(String nom) {
        this.nom = nom;
        this.prenom = "";
        this.email = "";
        this.motDePasse = "";
        this.suspendu = false;
    }

    // Méthode abstraite : chaque type d'utilisateur définit son rôle
    public abstract String getRole();

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public boolean isSuspendu() {
        return suspendu;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String mdp) {
        this.motDePasse = mdp;
    }

    // Actions communes
    public void suspend() {
        this.suspendu = true;
    }

    public void unsuspend() {
        this.suspendu = false;
    }

    // Alias pour compatibilité AdminService (getName)
    public String getName() {
        return nom + " " + prenom;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s <%s>%s",
                getRole(), prenom, nom, email, suspendu ? " (suspendu)" : "");
    }
}