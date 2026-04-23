# SafeBite - Guide d'Intégration Complète

## Architecture des Interactions

### 1️⃣ Inscription Restaurant → Admin Validation

**Flux:**

```
FormulaireRestaurant.afficherFormulaire()
    ↓
RestaurantService.inscrireRestaurant()
    ↓
AdminService.addRestaurant() [NOUVEAU RESTAURANT EN ATTENTE]
    ↓
AdminUI.showRestaurantValidation()
    ↓
Admin approveRestaurant() / rejectRestaurant()
```

**Fichiers impliqués:**

- `FormulaireRestaurant.java` - Collecte données & enregistre dans AdminService
- `RestaurantService.java` - Crée l'objet Restaurant
- `AdminService.java` - Maintient `pendingRestaurants` pour validation
- `AdminUI.java` - Affiche liste pour validation

---

### 2️⃣ Inscription Client → Admin Management

**Flux:**

```
FormulaireCompte.afficherFormulaire()
    ↓
CompteService.inscrireUtilisateur()
    ↓
AdminService.addUser() [CLIENT AJOUTÉ AUX UTILISATEURS GÉRÉS]
    ↓
AdminUI.showUserManagement()
    ↓
Admin suspendUser() / deleteUser()
```

**Fichiers impliqués:**

- `FormulaireCompte.java` - Collecte données & enregistre dans AdminService
- `CompteService.java` - Crée l'objet Client
- `AdminService.java` - Maintient `users` pour gestion
- `AdminUI.java` - Affiche liste pour gestion

---

### 3️⃣ Authentification Complète

**Flux:**

```
FormulaireLogin.afficherFormulaire()
    ↓
AuthService.connecter()
    ↓ (cherche dans cet ordre)
    - Clients (CompteService)
    - Admins (AdminService)
    - Restaurants approuvés (RestaurantService)
    ↓
Redirection selon type utilisateur
```

**Fichiers impliqués:**

- `FormulaireLogin.java` - Interface de connexion
- `AuthService.java` - Valide identifiants & redirection
- `CompteService.java`, `AdminService.java`, `RestaurantService.java` - Sources de données

---

## Points d'Intégration Clés

### ✅ Main.java

- Initialise `AdminService` **AVANT** `AuthService`
- Passe `adminService` à `FormulaireCompte` et `FormulaireRestaurant`
- Pré-charge un admin, client, restaurant pour démo

### ✅ FormulaireRestaurant.java

```java
if (adminService != null) {
    for (model.Restaurant r : service.getRestaurants()) {
        if (r.getEmail().equals(email)) {
            adminService.addRestaurant(r);  // ← IMPORTANT
            break;
        }
    }
}
```

### ✅ FormulaireCompte.java

```java
if (adminService != null) {
    for (model.Client c : service.getUtilisateurs()) {
        if (c.getEmail().equals(email)) {
            adminService.addUser(c);  // ← IMPORTANT
            break;
        }
    }
}
```

### ✅ AdminService.java

- Maintient deux listes séparées :
  - `pendingRestaurants` - Demandes en attente de validation
  - `users` - Clients/Admins gérables

### ✅ AdminUI.java

- Affiche listes depuis AdminService
- Appelle méthodes d'approbation/rejet

---

## Étapes de Test Complet

### 📝 Test 1: Inscription Restaurant

1. Lancer: `java -cp out main.Main`
2. Choisir: **1 > 2** (Authentification > Inscription restaurant)
3. Remplir:
   - Nom: "La Pizzeria"
   - Adresse: "10 Rue Principale"
   - Email: "pizzeria@resto.com"
   - Téléphone: "71555555"
   - Mot de passe: "pizza123"
4. ✅ Message: "Demande d'inscription envoyée"

### 👤 Test 2: Inscription Client

1. Choisir: **1 > 1** (Authentification > Créer compte client)
2. Remplir:
   - Nom: "Dupont"
   - Prénom: "Marie"
   - Email: "marie@test.com"
   - Mot de passe: "marie123"
3. ✅ Message: "Compte créé avec succès"

### 🛡️ Test 3: Admin Valide Restaurant

1. Choisir: **5** (Module Admin)
2. Choisir: **1** (Valider/Refuser restaurants)
3. ✅ Voir "La Pizzeria" en attente
4. Choisir indice et action (1=Accepter / 2=Refuser)
5. ✅ Message de validation envoyé

### 👥 Test 4: Admin Gère Clients

1. Choisir: **5** (Module Admin)
2. Choisir: **2** (Gérer utilisateurs)
3. ✅ Voir "Marie Dupont" dans la liste
4. Choisir indice et action (1=Suspendre / 2=Supprimer)

---

## Dépendances et Ordre d'Initialisation

**IMPORTANT:** Respecter cet ordre dans Main.java:

```java
CompteService compteService = new CompteService();
RestaurantService restaurantService = new RestaurantService();
AdminService adminService = new AdminService();  // ← AVANT AuthService
AuthService authService = new AuthService(..., adminService);  // ← Reçoit adminService
```

---

## Fonctionnalités Intégrées

| Fonctionnalité                         | Fichiers                            | Statut |
| -------------------------------------- | ----------------------------------- | ------ |
| Inscription Restaurant → Admin Pending | FormulaireRestaurant + AdminService | ✅     |
| Inscription Client → Admin Users       | FormulaireCompte + AdminService     | ✅     |
| Admin Valide Restaurant                | AdminUI + AdminService              | ✅     |
| Admin Gère Client (suspend/delete)     | AdminUI + AdminService              | ✅     |
| Authentification multi-acteurs         | AuthService                         | ✅     |
| Redirection post-connexion             | FormulaireLogin                     | ✅     |
| Tests automatiques                     | TestAuthentification                | ✅     |

---

## À Améliorer (Optionnel)

- Persistance base de données (actuellement en mémoire)
- Notification par email réelle (actuellement console)
- Pagination pour grandes listes
- Recherche/filtrage utilisateurs
- Historique validations
