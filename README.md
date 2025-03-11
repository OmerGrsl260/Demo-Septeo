# 🚀 Septeo E2E Testing Framework

<div align="center">
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" alt="Cucumber" />
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Allure-2E5783?style=for-the-badge&logo=allure&logoColor=white" alt="Allure" />
</div>

<div align="center">
  <p><i>Un framework d'automatisation de tests end-to-end pour le site Septeo, utilisant Cucumber, Selenium WebDriver et Allure Reports.</i></p>
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

### Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/Septeo_E2E.git
   cd Septeo_E2E
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

---

## 🔍 Scénarios de Test

<div style="background-color: #f5fffa; padding: 15px; border-left: 5px solid #3cb371; margin-bottom: 20px;">
  <p>Les scénarios de test sont définis en langage Gherkin dans les fichiers .feature.</p>
</div>

### Exemple de Scénario

```gherkin
Fonctionnalité: Exploration du site Septeo

  Scénario: Visiter la page d'accueil de Septeo
    Étant donné j'ouvre le navigateur
    Quand je me rends sur le site de Septeo
    Et j'accepte les cookies
    Alors je devrais voir la page d'accueil de Septeo

  Scénario: Explorer la première page de Notaire
    Étant donné j'ouvre le navigateur
    Quand je me rends sur le site de Septeo
    Et j'accepte les cookies
    Et je sélectionne le métier de Notaire
    Et je sélectionne le premier besoin
    Alors je devrais voir la première page de Notaire
```

---

## 👨‍💻 Bonnes Pratiques

<div style="background-color: #f8f8ff; padding: 15px; border-left: 5px solid #6a5acd; margin-bottom: 20px;">
  <p>Le framework suit plusieurs bonnes pratiques pour assurer la qualité et la maintenabilité du code.</p>
</div>

### 🧩 Principes SOLID
- **S**ingle Responsibility : Chaque classe a une seule responsabilité
- **O**pen/Closed : Les classes sont ouvertes à l'extension mais fermées à la modification
- **L**iskov Substitution : Les sous-classes peuvent remplacer leurs classes parentes
- **I**nterface Segregation : Interfaces spécifiques plutôt que génériques
- **D**ependency Inversion : Dépendance envers les abstractions, pas les implémentations

### 📏 Autres Pratiques
- **DRY (Don't Repeat Yourself)** : Éviter la duplication de code
- **KISS (Keep It Simple, Stupid)** : Garder le code simple et direct
- **Page Object Model** : Séparation de la logique de test et de l'interaction avec l'UI
- **Explicit Waits** : Attentes explicites plutôt qu'implicites
- **Logging** : Journalisation détaillée pour faciliter le débogage

---

<div align="center">
  <p>Développé avec ❤️ pour Septeo</p>
</div>
