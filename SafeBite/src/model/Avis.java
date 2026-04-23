package model;
 
/**
 * Représente un avis laissé par un client (SAF-50).
 */
public class Avis {
 
    private String client;
    private int note;           // 1 à 5
    private String commentaire;
 
    public Avis(String client, int note, String commentaire) {
        this.client      = client;
        this.note        = note > 5 ? 5 : (note < 1 ? 1 : note);
        this.commentaire = commentaire;
    }
 
    public String getClient()      { return client; }
    public int    getNote()        { return note; }
    public String getCommentaire() { return commentaire; }
 
    @Override
    public String toString() {
        String etoiles = "★".repeat(note) + "☆".repeat(5 - note);
        return String.format("[%s] %s : %s", etoiles, client, commentaire);
    }
}
 