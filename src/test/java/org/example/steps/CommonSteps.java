package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.example.context.TestContext;
import org.example.pages.SelectionPage;
import org.junit.Assert;

/**
 * Classe contenant les définitions de steps communes à plusieurs scénarios
 */
public class CommonSteps extends BaseSteps {
    
    private SelectionPage selectionPage;
    
    public CommonSteps(TestContext testContext) {
        super(testContext);
        this.selectionPage = new SelectionPage(driver);
    }
    
    @When("je sélectionne le métier de Notaire")
    public void jeSelectionneLeMetierDeNotaire() {
        System.out.println("Sélection du métier de Notaire");
        boolean success = selectionPage.selectMetierNotaire();
        Assert.assertTrue("Impossible de sélectionner le métier Notaire", success);
    }
    
    @And("je sélectionne le premier besoin")
    public void jeSelectionneLePremierbBesoin() {
        System.out.println("Sélection du premier besoin");
        boolean success = selectionPage.selectBesoin(1);
        Assert.assertTrue("Impossible de sélectionner le premier besoin", success);
    }
    
    @And("je sélectionne le deuxième besoin")
    public void jeSelectionneLeDeuxiemeBesoin() {
        System.out.println("Sélection du deuxième besoin");
        boolean success = selectionPage.selectBesoin(2);
        Assert.assertTrue("Impossible de sélectionner le deuxième besoin", success);
    }
}
