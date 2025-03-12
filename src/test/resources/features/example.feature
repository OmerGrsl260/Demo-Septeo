# language: en
@all
Feature: Navigation sur le site de Septeo
  En tant qu'utilisateur
  Je veux pouvoir naviguer sur le site de Septeo
  Afin de découvrir les produits et services proposés

  @all
  Scenario: Accéder à la page d'accueil
    Given j'ouvre le navigateur
    When je me rends sur le site de Septeo
    And j'accepte les cookies
    Then je devrais voir la page d'accueil de Septeo

  @all
  Scenario: Explorer toutes les pages de Notaire
    Given j'ouvre le navigateur
    When je me rends sur le site de Septeo
    And j'accepte les cookies
    Then je devrais voir la page d'accueil de Septeo
    When je sélectionne le métier de Notaire
    And je sélectionne le premier besoin
    Then je devrais voir la première page de Notaire

  @all
  Scenario: Explorer la deuxième page de Notaire
    Given j'ouvre le navigateur
    When je me rends sur le site de Septeo
    And j'accepte les cookies
    Then je devrais voir la page d'accueil de Septeo
    When je sélectionne le métier de Notaire
    And je sélectionne le deuxième besoin
    Then je devrais voir la deuxième page de Notaire
