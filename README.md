# 🥗 SafeBite

---

## 🌍 Présentation du projet

**SafeBite** est une application web/mobile innovante conçue pour aider les personnes atteintes de la **maladie cœliaque** à trouver et commander facilement des repas sans gluten sûrs.

Les personnes souffrant de cette maladie doivent éviter strictement le gluten, ce qui rend le choix des repas et les sorties au restaurant difficiles.

Cette plateforme vise à connecter les **restaurants et les consommateurs sans gluten**, en offrant une expérience alimentaire sûre, intelligente et agréable.

---

## 💡 Idée principale

✔ Trouver facilement des plats sans gluten  
✔ Découvrir des restaurants certifiés  
✔ Commander des repas en toute sécurité  
✔ Noter et commenter les plats  
✔ Recevoir des recommandations personnalisées  

---

## 👥 Équipe du projet

| Membre | Rôle Scrum | Fonctionnalité | Branche Git |
|--------|------------|----------------|--------------|
| Syrine Hafsia | Product Owner & Développeuse | Authentification & Inscription | `feature/authentification` |
| Adem Arfaoui | Scrum Master & Développeur | Administration (gestion utilisateurs, validation restaurants, modération) | `adem/feature-admin` |
| Imen Abed | Développeuse | Espace Visiteur (consultation plats & restaurants) | `feature/visiteur` |
| Maram Ayari | Développeuse | Gestion Restaurant (menus, commandes) | `feature/restaurant-management` |
| Elyes Jridi | Développeur | Espace Client (commande, avis, recommandations) | `feature/client-space` |

---

# 🧩 Backlog du projet

## 🟧 EPIC 1 — Expérience Visiteur

### 🧾 US1 – Consulter les plats
- Créer la table des plats en base de données  
- Développer l’API GET /plats  
- Créer l’interface de liste des plats  
- Ajouter un filtre par catégorie  
- Tester l’affichage des plats  

### 🧾 US2 – Rechercher des restaurants
- Créer la table restaurants  
- Développer l’API de recherche  
- Ajouter une barre de recherche  
- Ajouter des filtres (localisation / note)  
- Tester la fonctionnalité de recherche  

---

## 🟩 EPIC 2 — Authentification

### 🧾 US3 – Créer un compte client
- Créer la table utilisateurs  
- Développer l’API d’inscription  
- Créer le formulaire frontend  
- Vérifier l’unicité de l’email  
- Afficher un message de confirmation  

### 🧾 US4 – Connexion
- Implémenter l’API de connexion  
- Vérifier email et mot de passe  
- Gérer les sessions (JWT)  
- Redirection après connexion  
- Tester l’authentification  

### 🧾 US13 – Inscription restaurant
- Créer la table restaurant  
- Formulaire d’inscription restaurant  
- Statut "en attente"  
- Système de notification admin  

---

## 🟨 EPIC 3 — Espace Client

### 🧾 US5 – Commander des plats
- Créer la table commandes  
- Implémenter le panier  
- API de création de commande  
- Interface de validation de commande  
- Affichage de confirmation  

### 🧾 US6 – Noter et commenter
- Créer la table avis  
- API d’ajout d’avis  
- Interface de notation (étoiles)  
- Affichage des commentaires  
- Tests fonctionnels  

### 🧾 US7 – Recommandations
- Définir la logique de recommandation  
- Collecter les préférences utilisateurs  
- Développer l’API de recommandation  
- Afficher les suggestions  
- Tester le système  

---

## 🟧 EPIC 4 — Espace Restaurant

### 🧾 US8 – Gestion des menus
- Créer la table plats  
- API CRUD des plats  
- Interface de gestion des menus  
- Upload d’images  
- Tester les opérations CRUD  

### 🧾 US9 – Gestion des commandes
- API de récupération des commandes  
- Interface de liste des commandes  
- Mise à jour du statut des commandes  
- Historique des commandes  
- Tester le workflow  

---

## 🟥 EPIC 5 — Administration

### 🧾 US10 – Gestion des utilisateurs
- API de liste des utilisateurs  
- Interface admin  
- Suspension / suppression utilisateur  
- Tests de sécurité  

### 🧾 US11 – Validation des restaurants
- API des restaurants en attente  
- Interface de validation  
- Accepter / refuser les restaurants  
- Système de notification  
- Tests de validation  

### 🧾 US12 – Modération du contenu
- Système de signalement  
- API de suppression de contenu  
- Interface admin de modération  
- Historique des actions  

---

# ⚙️ Stack technique

- Frontend : HTML / CSS / JS  
- Backend : Java Natif 
- Base de données : MySQL 
- Authentification : JWT  
- Outils : GitHub, Postman, Figma  

---

# 🚀 Objectif du projet

Créer une solution réelle permettant de :
- Améliorer la vie des personnes atteintes de la maladie cœliaque  
- Connecter les utilisateurs à des options alimentaires sûres  
- Aider les restaurants à proposer des menus sans gluten  
- Offrir une expérience de commande fluide et moderne  

---
# Les liens des maquettes UI : 
 - https://www.figma.com/make/xwd6k8vwUCOfglCTGzWRLG/SafeBite?t=If4A5h3hV60gOAvE-1 (Partie Admin)
 - https://www.figma.com/make/EOxM46z7AePXGQq43tyz1z/Cr%C3%A9er-maquettes-pour-fonctionnalit%C3%A9s?fullscreen=1&t=42obIaMxk9Q2gRYV-1&preview-route=%2Forder%2Fmenu (partie client)
 - https://www.figma.com/make/TS5rttoXQSaVxCaEIdWXZ3/Maquette-d-%C3%A9cran?t=6WEt2TLjZINftg8M-1 (Partie Visiteur)
 -  https://www.figma.com/make/6INi9X7eL9QBDsEuwsKKAs/Cr%C3%A9er-maquettes-pour-fonctionnalit%C3%A9s?t=jbpXCWMAFHkYX4le-1&preview-route=%2Ffonctionnalite-1 (partie restaurant)
https://www.figma.com/make/a8j9eFgjFEl5hVdc5Po2ng/login-client?t=DLkWCv63uUsqL4mi-1( login client)
https://www.figma.com/make/LzjuSwcjofRXvAG7Lfpq8A/login-restaurent?t=POSrF64wLeJJNKpM-1(login réstau)
https://www.figma.com/make/U5cx9qP8NRwCH9K1m41Utm/cr%C3%A9ation-compte-client?t=9lrFKVCSA3N8Zlz2-1(création compte client)
https://www.figma.com/make/Kxw14UnNR21L56s7p38SaC/cr%C3%A9ation-compte-r%C3%A9staurent?t=VDGdzvjVbeZhpnuv-1( création compte réstau)
# 🧠 Conclusion

Ce projet combine **santé, technologie et innovation alimentaire** pour résoudre un problème réel de société.

---

> 💙 Développé avec travail d’équipe, innovation et passion
