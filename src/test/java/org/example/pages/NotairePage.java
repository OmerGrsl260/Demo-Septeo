package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page object pour les pages liées au métier de Notaire
 */
public class NotairePage extends BasePage {
    
    // Localisateurs pour les éléments spécifiques à la page Notaire
    private static final By PAGE_TITLE = By.cssSelector("h1, .page-title, .main-title");
    private static final By NOTAIRE_CONTENT = By.xpath("//*[contains(text(), 'notaire') or contains(text(), 'Notaire')]");
    private static final By PAGE_CONTENT = By.cssSelector(".content, .page-content, main");
    
    // Éléments de navigation
    @FindBy(css = ".navigation, .breadcrumb, .nav-links")
    private WebElement navigation;
    
    // Éléments de contenu
    @FindBy(css = ".content-section, .main-content, article")
    private WebElement contentSection;
    
    // Boutons d'action
    @FindBy(css = ".cta-button, .action-button, .btn-primary")
    private List<WebElement> actionButtons;
    
    public NotairePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Vérifie si la page actuelle est une page Notaire
     * @return true si c'est une page Notaire, false sinon
     */
    public boolean isNotairePage() {
        try {
            // Vérifier le titre de la page
            String pageTitle = driver.getTitle();
            boolean titleContainsNotaire = pageTitle.toLowerCase().contains("notaire");
            
            // Vérifier le contenu de la page
            List<WebElement> notaireElements = driver.findElements(NOTAIRE_CONTENT);
            boolean contentContainsNotaire = !notaireElements.isEmpty();
            
            return titleContainsNotaire || contentContainsNotaire;
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la page Notaire: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Vérifie si le contenu principal de la page est affiché
     * @return true si le contenu est affiché, false sinon
     */
    public boolean isContentDisplayed() {
        try {
            WebElement content = driver.findElement(PAGE_CONTENT);
            return content.isDisplayed();
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification du contenu: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Attend que la page soit complètement chargée
     */
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
        wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_CONTENT));
    }
    
    /**
     * Clique sur un bouton d'action par son texte
     * @param buttonText Le texte du bouton
     * @return true si le clic a réussi, false sinon
     */
    public boolean clickActionButton(String buttonText) {
        try {
            for (WebElement button : actionButtons) {
                if (button.getText().contains(buttonText)) {
                    button.click();
                    return true;
                }
            }
            
            // Si aucun bouton n'a été trouvé par le texte, essayer avec XPath
            WebElement button = driver.findElement(
                By.xpath("//button[contains(text(),'" + buttonText + "')] | " +
                         "//a[contains(@class,'btn') and contains(text(),'" + buttonText + "')]")
            );
            button.click();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors du clic sur le bouton '" + buttonText + "': " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Récupère le titre de la page
     * @return Le titre de la page
     */
    public String getPageTitle() {
        try {
            WebElement titleElement = driver.findElement(PAGE_TITLE);
            return titleElement.getText();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du titre: " + e.getMessage());
            return "";
        }
    }
}
