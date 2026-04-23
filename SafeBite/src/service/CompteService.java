/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sissi
 */
/* l'ajout d'un client */
package service;
import java.util.ArrayList;
import model.*;

public class CompteService {

    // La "table utilisateurs" en mémoire
    private final ArrayList<Client> utilisateurs = new ArrayList<>();

    // Créer un compte
    public boolean inscrireUtilisateur(String nom, String prenom,
            String email, String motDePasse) {
        // Vérification champs vides
        if (nom.isEmpty() || prenom.isEmpty() ||
                email.isEmpty() || motDePasse.isEmpty()) {
            System.out.println("❌ Erreur : tous les champs sont obligatoires.");
            return false;
        }

        // Vérification format email basique
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("❌ Erreur : format email invalide.");
            return false;
        }

        // Vérification mot de passe (min 6 caractères)
        if (motDePasse.length() < 6) {
            System.out.println("❌ Erreur : mot de passe trop court (min 6 caractères).");
            return false;
        }

        // Création et ajout dans la "table"
        Client nouvelUtilisateur = new Client(nom, prenom, email, motDePasse);
        utilisateurs.add(nouvelUtilisateur);
        return true;
    }

    // Récupérer tous les utilisateurs (pour vérification)
    public ArrayList<Client> getUtilisateurs() {
        return utilisateurs;
    }
}