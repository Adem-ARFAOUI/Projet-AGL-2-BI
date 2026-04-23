package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Logique de gestion des avis (SAF-51 et SAF-53).
 */
public class GestionnaireAvis {
    private List<Avis> baseDeDonneesAvis = new ArrayList<>();

    public void ajouterAvis(Avis avis) {
        baseDeDonneesAvis.add(avis);
        System.out.println("> Avis enregistré avec succès.");
    }

    public void afficherAvisPublics() {
        if (baseDeDonneesAvis.isEmpty()) {
            System.out.println("> Aucun avis disponible pour ce restaurant.");
            return;
        }
        System.out.println("\n--- AVIS PUBLICS ---");
        for (Avis avis : baseDeDonneesAvis) {
            System.out.println(avis.toString());
        }
        System.out.println("--------------------\n");
    }
}
