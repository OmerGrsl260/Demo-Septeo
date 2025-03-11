package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.context.TestContext;
import org.example.pages.SepteoHomePage;
import org.junit.Assert;

/**
 * Classe de définition des étapes pour le premier scénario :
 * "Visiter la page d'accueil de Septeo"
 */
public class Scenario1Steps extends BaseSteps {
    
    private Scenario scenario;
    private SepteoHomePage homePage;
    
    public Scenario1Steps(TestContext testContext) {
        super(testContext);
        this.homePage = new SepteoHomePage(driver);
    }
    
    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
        System.out.println("Démarrage du scénario: " + scenario.getName());
    }
    
    @After
    public void tearDown() {
        if (scenario.isFailed()) {
            // Capture d'écran en cas d'échec
            captureFailureInfo();
        }
        
        // Fermeture du navigateur
        closeDriver();
    }
    
    @Given("j'ouvre le navigateur")
    public void jOuvreLeNavigateur() {
        System.out.println("Ouverture du navigateur");
    }
    
    @When("je me rends sur le site de Septeo")
    public void jeMeRendsSurLeSiteDeSepteo() {
        System.out.println("Navigation vers le site de Septeo");
        
        try {
            // Naviguer vers l'URL
            driver.get("https://www.septeo.fr");
            
            // Attendre que la page soit chargée
            homePage.waitForPageToLoad();
            
            System.out.println("Navigation vers le site effectuée avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la navigation vers le site de Septeo: " + e.getMessage());
            Assert.fail("Erreur lors de la navigation vers le site de Septeo: " + e.getMessage());
        }
    }
    
    @When("j'accepte les cookies")
    public void jAccepteLesCookies() {
        System.out.println("Acceptation des cookies");
        
        try {
            homePage.acceptCookies();
            System.out.println("Cookies acceptés avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'acceptation des cookies: " + e.getMessage());
            // Ne pas faire échouer le test si le bouton des cookies n'est pas présent
            System.out.println("Poursuite du test sans acceptation des cookies");
        }
    }
    
    @Then("je devrais voir la page d'accueil de Septeo")
    public void jeDevraisVoirLaPageDAccueilDeSepteo() {
        System.out.println("Vérification de la page d'accueil");
        
        try {
            // Vérifier que l'URL contient "septeo"
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            boolean isValidUrl = currentUrl.contains("septeo");
            
            Assert.assertTrue(
                "L'URL de la page ne correspond pas à Septeo",
                isValidUrl
            );
            
            System.out.println("Page d'accueil de Septeo vérifiée avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la page d'accueil: " + e.getMessage());
            Assert.fail("Erreur lors de la vérification de la page d'accueil: " + e.getMessage());
        }
    }
}
