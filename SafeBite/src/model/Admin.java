package model;

/**
 * Représente un administrateur SafeBite.
 * Peut valider des restaurants, suspendre/supprimer des utilisateurs.
 * Correspond aux actions dans AdminService et AdminUI.
 */
public class Admin extends Utilisateur {

    private String niveauAcces; // ex: "SUPER_ADMIN", "MODERATEUR"

    public Admin(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.niveauAcces = "ADMIN";
    }

    public Admin(String nom, String prenom, String email, String motDePasse, String niveauAcces) {
        super(nom, prenom, email, motDePasse);
        this.niveauAcces = niveauAcces;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    public String getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(String niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + niveauAcces + "]";
    }
}