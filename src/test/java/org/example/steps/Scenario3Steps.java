package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.example.context.TestContext;
import org.junit.Assert;

/**
 * Classe de définition des étapes pour le troisième scénario :
 * "Explorer la deuxième page de Notaire"
 */
public class Scenario3Steps extends BaseSteps {
    
    private Scenario scenario;
    
    public Scenario3Steps(TestContext testContext) {
        super(testContext);
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
    
    @Then("je devrais voir la deuxième page de Notaire")
    public void jeDevraisVoirLaDeuxiemePageDeNotaire() {
        System.out.println("Vérification de la deuxième page de Notaire");
        
        // Vérifier que l'URL ou le titre contient "notaire"
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String pageTitle = driver.getTitle().toLowerCase();
        
        boolean urlContainsNotaire = currentUrl.contains("notaire");
        boolean titleContainsNotaire = pageTitle.contains("notaire");
        
        Assert.assertTrue(
            "La page Notaire n'est pas affichée correctement",
            urlContainsNotaire || titleContainsNotaire
        );
    }
}
