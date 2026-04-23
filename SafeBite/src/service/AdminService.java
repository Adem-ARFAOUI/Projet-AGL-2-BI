package service;

import java.util.ArrayList;
import java.util.List;
import model.Restaurant;
import model.Utilisateur;

public class AdminService {

    private List<Restaurant> pendingRestaurants = new ArrayList<>();
    private List<Utilisateur> users = new ArrayList<>();

    // === RESTAURANTS ===

    // 1- API liste restaurants en attente
    public List<Restaurant> getPendingRestaurants() {
        return pendingRestaurants;
    }

    public void addRestaurant(Restaurant r) {
        pendingRestaurants.add(r);
    }

    // 3- Accepter
    public void approveRestaurant(Restaurant r) {
        r.setValidated(true);
        pendingRestaurants.remove(r);
        sendNotification(r, "Restaurant validé ✅");
    }

    // 3- Refuser
    public void rejectRestaurant(Restaurant r) {
        pendingRestaurants.remove(r);
        sendNotification(r, "Restaurant refusé ❌");
    }

    // 4- Notification
    private void sendNotification(Restaurant r, String message) {
        System.out.println("Notification à " + r.getName() + " : " + message);
    }

    // === USERS ===

    // 1- API liste utilisateurs
    public List<Utilisateur> getUsers() {
        return users;
    }

    public void addUser(Utilisateur u) {
        users.add(u);
    }

    // 3- Suspendre utilisateur
    public void suspendUser(Utilisateur u) {
        u.suspend();
        System.out.println("Utilisateur suspendu : " + u.getName());
    }

    // 4- Supprimer utilisateur
    public void deleteUser(Utilisateur u) {
        users.remove(u);
        System.out.println("Utilisateur supprimé : " + u.getName());
    }
}
