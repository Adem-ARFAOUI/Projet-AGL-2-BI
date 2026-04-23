package ui;

import java.util.List;
import java.util.Scanner;
import model.Restaurant;
import model.Utilisateur;
import service.AdminService;

public class AdminUI {

    private final AdminService service;
    private final Scanner scanner = new Scanner(System.in);

    public AdminUI(AdminService service) {
        this.service = service;
    }

    // Interface validation restaurants
    public void showRestaurantValidation() {
        List<Restaurant> list = service.getPendingRestaurants();

        System.out.println("=== Restaurants en attente ===");

        if (list.isEmpty()) {
            System.out.println("Aucun restaurant en attente.");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " - " + list.get(i).getName());
        }

        try {
            System.out.println("Choisir index : ");
            int choice = scanner.nextInt();

            if (choice < 0 || choice >= list.size()) {
                System.out.println("Index invalide.");
                return;
            }

            System.out.println("1-Accepter / 2-Refuser");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    service.approveRestaurant(list.get(choice));
                    break;
                case 2:
                    service.rejectRestaurant(list.get(choice));
                    break;
                default:
                    System.out.println("Action invalide.");
                    break;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez saisir un nombre.");
            scanner.nextLine();
        }
    }

    // Interface gestion utilisateurs
    public void showUserManagement() {
        List<Utilisateur> users = service.getUsers();

        System.out.println("=== Liste utilisateurs ===");

        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur disponible.");
            return;
        }

        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + " - " + users.get(i).getName());
        }

        try {
            System.out.println("Choisir index : ");
            int choice = scanner.nextInt();

            if (choice < 0 || choice >= users.size()) {
                System.out.println("Index invalide.");
                return;
            }

            System.out.println("1-Suspendre / 2-Supprimer");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    service.suspendUser(users.get(choice));
                    break;
                case 2:
                    service.deleteUser(users.get(choice));
                    break;
                default:
                    System.out.println("Action invalide.");
                    break;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez saisir un nombre.");
            scanner.nextLine();
        }
    }
}
