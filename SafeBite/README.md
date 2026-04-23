"# SafeBite - Plateforme de Restauration en Ligne

## 🚀 Démarrage Rapide

### Compilation

```bash
cd "c:\Users\adema\OneDrive\Bureau\SafeBite\SafeBite"
javac -d out -sourcepath src src/main/Main.java
```

### Exécution

```bash
java -cp out main.Main
```

---

## 📊 Architecture Complètement Intégrée

### ✅ Inscription Restaurant → Admin Validation

**Flux:**

1. Client remplit formulaire d'inscription restaurant
2. `FormulaireRestaurant` enregistre dans `RestaurantService`
3. `FormulaireRestaurant` ajoute également dans `AdminService.pendingRestaurants`
4. Admin accède à Module Admin > Valider Restaurants
5. Admin voit la demande et accepte/refuse

**Fichiers impliqués:**

- `FormulaireRestaurant.java` (collecte + enregistrement)
- `AdminService.java` (stockage demandes)
- `AdminUI.java` (affichage + actions)

### ✅ Inscription Client → Admin Management

**Flux:**

1. Client remplit formulaire de création de compte
2. `FormulaireCompte` enregistre dans `CompteService`
3. `FormulaireCompte` ajoute également dans `AdminService.users`
4. Admin accède à Module Admin > Gérer Utilisateurs
5. Admin voit le client et peut suspendre/supprimer

**Fichiers impliqués:**

- `FormulaireCompte.java` (collecte + enregistrement)
- `AdminService.java` (stockage utilisateurs)
- `AdminUI.java` (affichage + actions)

### ✅ Authentification Multi-Acteurs

**Flux:**

1. Utilisateur remplit formulaire de connexion
2. `AuthService` cherche dans cet ordre :
   - Clients (CompteService)
   - Admins (AdminService)
   - Restaurants approuvés (RestaurantService)
3. Redirection vers tableau de bord approprié

**Fichiers impliqués:**

- `FormulaireLogin.java`
- `AuthService.java`
- Tous les services de données

---

## 🎯 Données Préchargées

### 👤 Clients

- **Sonia Ben Ali** | sonia@test.com | 123456

### 🍽️ Restaurants

- **Le SafSaf** (approuvé) | safesaf@resto.com | resto123
- **Bistro Test** (en attente) | bistro@test.com | bistro123

### 🛡️ Admins

- **Admin Principal** | admin@safebite.com | admin123

---

## 📋 Modules de l'Application

| Module              | Description             | Statut |
| ------------------- | ----------------------- | ------ |
| 1️⃣ Authentification | Inscription & Connexion | ✅     |
| 2️⃣ Visiteur         | Catalogue & Recherche   | ✅     |
| 3️⃣ Restaurant       | Gestion Menu            | ✅     |
| 4️⃣ Client           | Commande Interactive    | ✅     |
| 5️⃣ Admin            | Validation & Gestion    | ✅     |
| 6️⃣ Tests            | Tests Automatiques      | ✅     |

---

## 🔄 Cas d'Usage Complets

### Cas 1: Inscription Restaurant + Validation Admin

```
1. Menu Principal > 1 > 2 (Authentification > Inscription restaurant)
   ├─ Remplir: nom, adresse, email, téléphone, mot de passe
   └─ ✅ Demande enregistrée

2. Menu Principal > 5 > 1 (Admin > Valider Restaurants)
   ├─ Voir la demande du restaurant
   └─ ✅ Accepter ou refuser
```

### Cas 2: Inscription Client + Gestion Admin

```
1. Menu Principal > 1 > 1 (Authentification > Créer compte client)
   ├─ Remplir: nom, prénom, email, mot de passe
   └─ ✅ Client enregistré

2. Menu Principal > 5 > 2 (Admin > Gérer Utilisateurs)
   ├─ Voir le client dans la liste
   └─ ✅ Suspendre ou supprimer
```

### Cas 3: Admin Se Connecte

```
1. Menu Principal > 1 > 3 (Authentification > Se connecter)
   ├─ Email: admin@safebite.com
   ├─ Mot de passe: admin123
   └─ ✅ Redirection Admin Dashboard

2. Module Admin > Valider Restaurants / Gérer Utilisateurs
   └─ ✅ Admin gère les demandes
```

---

## 🏗️ Points d'Intégration Clés

### Main.java

```java
private static final AdminService adminService = new AdminService();
private static final AuthService authService =
    new AuthService(compteService, restaurantService, adminService);
```

### FormulaireRestaurant.java

```java
if (adminService != null) {
    for (Restaurant r : service.getRestaurants()) {
        if (r.getEmail().equals(email)) {
            adminService.addRestaurant(r);  // ← Clé d'intégration
            break;
        }
    }
}
```

### FormulaireCompte.java

```java
if (adminService != null) {
    for (Client c : service.getUtilisateurs()) {
        if (c.getEmail().equals(email)) {
            adminService.addUser(c);  // ← Clé d'intégration
            break;
        }
    }
}
```

### AuthService.java

```java
// Cherche les admins aussi
for (Utilisateur u : adminService.getUsers()) {
    if (u.getEmail().equals(email) && u.getMotDePasse().equals(motDePasse)) {
        utilisateurConnecte = u;
        return true;
    }
}
```

---

## 📚 Structure Projet

```
SafeBite/
├── src/
│   ├── main/
│   │   └── Main.java          ← Point d'entrée
│   ├── model/
│   │   ├── Utilisateur.java   ← Classe de base (Admin, Client)
│   │   ├── Admin.java
│   │   ├── Client.java
│   │   ├── Restaurant.java
│   │   ├── Commande.java
│   │   ├── Plat.java
│   │   ├── AuthService.java   ← Authentification
│   │   ├── FormulaireLogin.java
│   │   ├── FormulaireCompte.java   ← Intégration AdminService
│   │   ├── FormulaireRestaurant.java ← Intégration AdminService
│   │   └── ...autres fichiers
│   ├── service/
│   │   ├── AdminService.java  ← Données admins
│   │   ├── CompteService.java
│   │   ├── RestaurantService.java
│   │   ├── MenuService.java
│   │   ├── CommandeService.java
│   │   └── SafeBiteConsole.java
│   └── ui/
│       └── AdminUI.java       ← Interface admin
├── out/                        ← Classes compilées
└── README.md
```

---

## ✅ Fonctionnalités Intégrées

| Fonctionnalité                          | Implémentation                             | Status |
| --------------------------------------- | ------------------------------------------ | ------ |
| Restaurant inscrit → Admin voit demande | FormulaireRestaurant + AdminService        | ✅     |
| Client inscrit → Admin voit utilisateur | FormulaireCompte + AdminService            | ✅     |
| Admin valide restaurant                 | AdminUI + AdminService.approveRestaurant() | ✅     |
| Admin refuse restaurant                 | AdminUI + AdminService.rejectRestaurant()  | ✅     |
| Admin suspend client                    | AdminUI + AdminService.suspendUser()       | ✅     |
| Admin supprime client                   | AdminUI + AdminService.deleteUser()        | ✅     |
| Authentification multi-acteurs          | AuthService (clients + admins + restos)    | ✅     |
| Redirection post-connexion              | FormulaireLogin                            | ✅     |
| Tests automatiques                      | TestAuthentification                       | ✅     |

---

## 🔐 Hiérarchie Utilisateurs

```
Utilisateur (abstract)
├── Client
│   └── Géré par: AdminService.users
├── Admin
│   └── Créé dans: AdminService.users
│   └── Authentifié via: AuthService
└── Restaurateur (extends Utilisateur)
    └── Associé à: Restaurant
```

---

## 🎓 Notes de Développement

- **Pas de base de données** : Données stockées en mémoire
- **Pas d'email réel** : Notifications affichées en console
- **Pas de pages web** : Application console
- **Héritages respectés** : Admin, Client, Restaurateur héritent de Utilisateur
- **Intégration complète** : Tous les modules interagissent via AdminService

---

## 📝 À Faire (Optionnel)

- [ ] Ajouter persistance base de données
- [ ] Implémenter notifications email réelles
- [ ] Ajouter pagination pour grandes listes
- [ ] Ajouter recherche/filtrage utilisateurs
- [ ] Historique validations
- [ ] Interface graphique web

---

## 📖 Documentation Complète

Voir `INTEGRATION_GUIDE.md` pour détails techniques complets."
