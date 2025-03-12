# 🚀 Septeo E2E Testing Framework

<div align="center">
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" alt="Cucumber" />
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Allure-2E5783?style=for-the-badge&logo=allure&logoColor=white" alt="Allure" />
  <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white" alt="Jenkins" />
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />
</div>

<div align="center">
  <p><i>Un framework d'automatisation de tests end-to-end pour le site Septeo, utilisant Cucumber, Selenium WebDriver et Allure Reports.</i></p>
  <a href="https://github.com/OmerGrsl260/Demo-Septeo"><strong>Voir sur GitHub »</strong></a>
</div>

---

## 📋 Table des Matières

- [🌟 Présentation](#-présentation)
- [🏗️ Architecture](#️-architecture)
- [🛠️ Technologies Utilisées](#️-technologies-utilisées)
- [📁 Structure du Projet](#-structure-du-projet)
- [🚀 Démarrage Rapide](#-démarrage-rapide)
- [🧪 Exécution des Tests](#-exécution-des-tests)
- [📊 Rapports](#-rapports)
- [🔍 Scénarios de Test](#-scénarios-de-test)
- [👨‍💻 Bonnes Pratiques](#-bonnes-pratiques)
- [🔄 Intégration GitHub](#-intégration-github)
- [🔄 Intégration Jenkins](#-intégration-jenkins)

---

## 🌟 Présentation

<div style="background-color: #f0f8ff; padding: 15px; border-left: 5px solid #4682b4; margin-bottom: 20px;">
  <p>Ce projet implémente un framework d'automatisation de tests end-to-end pour le site web de Septeo. Il utilise une approche BDD (Behavior-Driven Development) avec Cucumber pour définir les scénarios de test en langage naturel, Selenium WebDriver pour l'interaction avec le navigateur, et Allure Reports pour la génération de rapports détaillés.</p>
</div>

Le framework est conçu pour être :
- 🔄 **Maintenable** : Structure claire et séparation des responsabilités
- 🚀 **Performant** : Interactions directes et optimisées avec le navigateur
- 📊 **Informatif** : Rapports détaillés avec captures d'écran et logs
- 🧩 **Extensible** : Facilement adaptable pour de nouveaux scénarios

---

## 🏗️ Architecture

<div style="background-color: #fff8e8; padding: 15px; border-left: 5px solid #ffd700; margin-bottom: 20px;">
  <p>Le framework suit le modèle <strong>Page Object Model (POM)</strong> combiné avec le pattern <strong>Step Definitions</strong> de Cucumber pour une séparation claire des responsabilités.</p>
</div>

```
                 ┌─────────────┐
                 │   Feature   │
                 │   Files     │
                 └──────┬──────┘
                        │
                        ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐
│    Step     │   │    Page     │   │   WebDriver  │
│ Definitions │◄──┤   Objects   │◄──┤   Manager    │
└─────────────┘   └─────────────┘   └─────────────┘
        │                │                 │
        │                │                 │
        ▼                ▼                 ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐
│   Test      │   │   Allure    │   │  Selenium   │
│  Context    │   │   Reports   │   │  WebDriver  │
└─────────────┘   └─────────────┘   └─────────────┘
```

---

## 🛠️ Technologies Utilisées

<div style="background-color: #f5f5f5; padding: 15px; border-left: 5px solid #696969; margin-bottom: 20px;">
  <p>Le projet utilise un ensemble de technologies modernes pour l'automatisation des tests.</p>
</div>

| Technologie | Version | Description |
|-------------|---------|-------------|
| **Java** | 17 | Langage de programmation principal |
| **Cucumber** | 7.14.0 | Framework BDD pour la définition des scénarios |
| **Selenium WebDriver** | 4.16.1 | Automatisation des interactions avec le navigateur |
| **WebDriverManager** | 5.6.2 | Gestion automatique des drivers de navigateur |
| **JUnit** | 4.13.2 | Framework de test unitaire |
| **Allure Reports** | 2.25.0 | Génération de rapports détaillés |
| **Maven** | 3.x | Gestion des dépendances et du build |
| **Jenkins** | 2.x | Intégration et déploiement continus |
| **Git** | - | Gestion de version |
| **GitHub** | - | Hébergement et partage du code source |
| **Selenium DevTools** | v120 | Support CDP pour le débogage avancé |

---

## 📁 Structure du Projet

<div style="background-color: #f0fff0; padding: 15px; border-left: 5px solid #2e8b57; margin-bottom: 20px;">
  <p>Le projet suit une structure organisée pour faciliter la maintenance et l'extension.</p>
</div>

```
src/
├── test/
│   ├── java/
│   │   └── org/
│   │       └── example/
│   │           ├── config/           # Configuration des tests
│   │           ├── context/          # Contexte partagé entre les steps
│   │           ├── pages/            # Page Objects
│   │           │   ├── BasePage.java
│   │           │   ├── SepteoHomePage.java
│   │           │   ├── SelectionPage.java
│   │           │   └── NotairePage.java
│   │           ├── steps/            # Step Definitions
│   │           │   ├── BaseSteps.java
│   │           │   ├── CommonSteps.java
│   │           │   ├── Scenario1Steps.java
│   │           │   ├── Scenario2Steps.java
│   │           │   └── Scenario3Steps.java
│   │           ├── utils/            # Utilitaires
│   │           └── RunCucumberTest.java  # Runner de test
│   └── resources/
│       ├── features/                 # Fichiers de scénarios Cucumber
│       │   └── example.feature
│       ├── allure.properties
│       └── cucumber.properties
```

---

## 🚀 Démarrage Rapide

<div style="background-color: #f0f0ff; padding: 15px; border-left: 5px solid #4169e1; margin-bottom: 20px;">
  <p>Pour commencer à utiliser ce framework, suivez ces étapes simples.</p>
</div>

### Prérequis

- Java JDK 17 ou supérieur
- Maven 3.x
- Un navigateur web (Chrome, Firefox, Edge)
- Git

### Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/OmerGrsl260/Demo-Septeo.git
   cd Demo-Septeo
   ```

2. Installez les dépendances :
   ```bash
   mvn clean install -DskipTests
   ```

---

## 🧪 Exécution des Tests

<div style="background-color: #fff0f5; padding: 15px; border-left: 5px solid #ff69b4; margin-bottom: 20px;">
  <p>Plusieurs options sont disponibles pour exécuter les tests.</p>
</div>

### Exécuter tous les tests

```bash
mvn clean test
```

### Exécuter un scénario spécifique par tag

```bash
mvn clean test -Dcucumber.filter.tags="@tag-name"
```

### Générer et afficher le rapport Allure

```bash
mvn allure:report
mvn allure:serve
```

---

## 📊 Rapports

<div style="background-color: #fffaf0; padding: 15px; border-left: 5px solid #ff8c00; margin-bottom: 20px;">
  <p>Les rapports Allure fournissent une visualisation détaillée des résultats des tests.</p>
</div>

Le framework génère automatiquement des rapports Allure après l'exécution des tests. Ces rapports incluent :

- 📈 **Tableaux de bord** : Vue d'ensemble des résultats
- 🔍 **Détails des scénarios** : Étapes exécutées avec statut
- 📷 **Captures d'écran** : En cas d'échec des tests
- 📝 **Logs** : Informations détaillées sur l'exécution

Pour visualiser le rapport :

```bash
mvn allure:serve
```

Les rapports sont également disponibles dans le dossier `target/site/allure-report`.

---

## 🔄 Intégration Jenkins

<div style="background-color: #fff5ee; padding: 15px; border-left: 5px solid #d24939; margin-bottom: 20px;">
  <p>Le projet est configuré pour une intégration continue avec Jenkins, permettant une exécution automatisée des tests.</p>
</div>

### Configuration Jenkins

Le pipeline Jenkins est configuré pour :

- 🔄 **Build Automatique** : Déclenché à chaque push sur la branche main
- 🧪 **Exécution des Tests** : Lance la suite de tests complète
- 📊 **Rapports Allure** : Génère et publie les rapports de test
- 📧 **Notifications** : Envoie des alertes en cas d'échec des tests

### Pipeline

Le pipeline Jenkins est défini dans le `Jenkinsfile` à la racine du projet et comprend les étapes suivantes :

1. **Checkout** : Récupération du code source
2. **Build** : Compilation et installation des dépendances
3. **Test** : Exécution des tests automatisés
4. **Report** : Génération et publication des rapports Allure
5. **Notify** : Notification des résultats

Pour accéder aux rapports Jenkins :
1. Connectez-vous à votre instance Jenkins
2. Naviguez vers le projet "Septeo-E2E-Tests"
3. Consultez les résultats de build et les rapports Allure

---

## 🔍 Scénarios de Test

<div style="background-color: #f0ffff; padding: 15px; border-left: 5px solid #20b2aa; margin-bottom: 20px;">
  <p>Les scénarios de test sont écrits en Gherkin, un langage naturel qui facilite la compréhension pour toutes les parties prenantes.</p>
</div>

### Exemples de Scénarios

```gherkin
Feature: Navigation sur le site Septeo

  @smoke
  Scenario: Accès à la page d'accueil
    Given l'utilisateur est sur la page d'accueil de Septeo
    When il accepte les cookies
    Then la page d'accueil est affichée correctement

  @regression
  Scenario: Sélection d'un métier
    Given l'utilisateur est sur la page de sélection des métiers
    When il sélectionne le métier "Notaire"
    Then il est redirigé vers la page des solutions pour notaires
```

### Organisation des Tests

Les tests sont organisés par :
- 🎯 **Fonctionnalité** : Regroupés par domaine fonctionnel
- 🏷️ **Tags** : Catégorisés pour une exécution ciblée
- 📊 **Priorité** : Identifiés par niveau d'importance

---

## 👨‍💻 Bonnes Pratiques

<div style="background-color: #f5fffa; padding: 15px; border-left: 5px solid #3cb371; margin-bottom: 20px;">
  <p>Le projet suit un ensemble de bonnes pratiques pour maintenir un code de qualité et faciliter la maintenance.</p>
</div>

### Conventions de Code

- 📝 **Nommage explicite** des classes, méthodes et variables
- 🔍 **Tests atomiques** et indépendants
- 📚 **Documentation** claire et à jour
- ♻️ **DRY** (Don't Repeat Yourself)
- 🎨 **SOLID** principles

### Gestion des Tests

- 🔄 **Isolation** des tests
- 🧹 **Nettoyage** des données de test
- 📸 **Screenshots** automatiques en cas d'échec
- 📝 **Logging** détaillé
- 🔍 **Assertions** explicites

---

## 🔄 Intégration GitHub

<div style="background-color: #f8f8ff; padding: 15px; border-left: 5px solid #181717; margin-bottom: 20px;">
  <p>Le projet utilise GitHub pour la gestion du code source et la collaboration.</p>
</div>

### Workflow Git

1. **Branches** :
   - `main` : Code stable et testé
   - `develop` : Développement en cours
   - `feature/*` : Nouvelles fonctionnalités
   - `bugfix/*` : Corrections de bugs

2. **Pull Requests** :
   - Revue de code obligatoire
   - Tests automatisés
   - Validation des critères de qualité

3. **Actions GitHub** :
   - Build automatique
   - Exécution des tests
   - Analyse de code
   - Publication des rapports

### Contribution

1. Créez une nouvelle branche depuis `develop`
2. Implémentez vos modifications
3. Testez localement
4. Soumettez une Pull Request
5. Attendez la revue et l'approbation

---

## ✨ Conclusion

<div style="background-color: #fff0f5; padding: 15px; border-left: 5px solid #9370db; margin-bottom: 20px;">
  <p>Ce framework de test E2E représente notre engagement envers la qualité et l'excellence technique. En combinant des outils modernes avec les meilleures pratiques du secteur, nous assurons la fiabilité et la maintenabilité de nos tests automatisés. Rejoignez-nous dans cette aventure pour créer des tests plus intelligents, plus robustes et plus efficaces.</p>
</div>

<div align="center">
  <i>🌟 Construisons ensemble l'avenir de l'automatisation des tests chez Septeo 🌟</i>
</div>
