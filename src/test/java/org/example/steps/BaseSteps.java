package org.example.steps;

import io.qameta.allure.Allure;
import org.example.context.TestContext;
import org.example.pages.SepteoHomePage;
import org.example.utils.AllureReportUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Classe utilitaire pour les définitions d'étapes Cucumber
 * Contient les méthodes communes et utilitaires
 */
public class BaseSteps {
    
    protected TestContext testContext;
    protected WebDriver driver;
    protected SepteoHomePage homePage;
    
    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
        this.driver = testContext.getDriver();
        this.homePage = new SepteoHomePage(driver);
    }
    
    /**
     * Méthode utilitaire pour prendre une capture d'écran
     * @param description Description de la capture d'écran
     */
    protected void takeScreenshot(String description) {
        if (driver != null) {
            AllureReportUtils.takeScreenshot(driver, description);
        }
    }
    
    /**
     * Méthode utilitaire pour capturer des informations en cas d'échec
     */
    protected void captureFailureInfo() {
        if (driver != null) {
            // Capture d'écran en cas d'échec
            AllureReportUtils.takeScreenshot(driver, "Échec du scénario");
            
            // Ajouter des informations supplémentaires au rapport
            Allure.addAttachment("URL actuelle", "text/plain", driver.getCurrentUrl());
            Allure.addAttachment("Titre de la page", "text/plain", driver.getTitle());
        }
    }
    
    /**
     * Méthode utilitaire pour fermer le navigateur
     */
    protected void closeDriver() {
        if (testContext != null) {
            testContext.closeDriver();
        }
    }
    
    /**
     * Méthode utilitaire pour gérer les popups
     * Cette méthode vérifie si un popup est présent et tente de le fermer
     */
    protected void handlePopupIfPresent() {
        try {
            System.out.println("Vérification de la présence du popup...");
            
            // Vérifier si le popup est présent
            WebElement popup = null;
            try {
                popup = new WebDriverWait(driver, Duration.ofMillis(500))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#hs-web-interactives-top-anchor")));
            } catch (Exception e) {
                System.out.println("Popup non trouvé avec le sélecteur #hs-web-interactives-top-anchor");
            }
            
            if (popup != null) {
                System.out.println("Popup trouvé, recherche du bouton de fermeture...");
                
                // Essayer de trouver le bouton de fermeture
                WebElement closeButton = null;
                try {
                    closeButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"interactive-close-button\"]")));
                } catch (Exception e) {
                    System.out.println("Bouton de fermeture non trouvé avec le sélecteur //*[@id=\"interactive-close-button\"]");
                }
                
                if (closeButton != null) {
                    System.out.println("Clic sur le bouton de fermeture du popup");
                    closeButton.click();
                    
                    // Capture d'écran après fermeture du popup
                    AllureReportUtils.takeScreenshot(driver, "Après fermeture du popup");
                } else {
                    // Essayer avec JavaScript si le bouton n'est pas trouvé
                    try {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript(
                            "var closeButtons = document.querySelectorAll('body > div > div.button-container, .close-button, .btn-close, button.close');" +
                            "if(closeButtons.length > 0) {" +
                            "  closeButtons[0].click();" +
                            "}"
                        );
                        System.out.println("Clic sur le bouton de fermeture via JavaScript");
                        
                        // Capture d'écran après fermeture du popup via JS
                        AllureReportUtils.takeScreenshot(driver, "Après fermeture du popup via JS");
                    } catch (Exception jsError) {
                        System.out.println("Erreur lors de la fermeture du popup via JavaScript: " + jsError.getMessage());
                    }
                }
            } else {
                System.out.println("Aucun popup détecté");
            }
        } catch (Exception popupError) {
            System.out.println("Erreur lors de la gestion du popup: " + popupError.getMessage());
        }
    }
    
    /**
     * Méthode utilitaire pour trouver un élément select par différentes méthodes
     * @param cssSelector Le sélecteur CSS principal à essayer
     * @param containsTexts Les textes à rechercher dans les attributs
     * @return L'élément trouvé ou null
     */
    protected WebElement findSelectElement(String cssSelector, String... containsTexts) {
        try {
            // Essayer d'abord avec le sélecteur CSS direct
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            return element;
        } catch (Exception e) {
            System.out.println("Élément non trouvé avec le sélecteur: " + cssSelector);
            
            // Essayer avec des sélecteurs plus génériques
            try {
                // Construire un sélecteur CSS plus générique basé sur les attributs
                StringBuilder selector = new StringBuilder("select");
                for (String text : containsTexts) {
                    selector.append(", select[name*=\"").append(text).append("\"]");
                    selector.append(", select[id*=\"").append(text).append("\"]");
                    selector.append(", select[class*=\"").append(text).append("\"]");
                    selector.append(", div[role=\"listbox\"][aria-label*=\"").append(text).append("\"]");
                }
                
                WebElement element = driver.findElement(By.cssSelector(selector.toString()));
                return element;
            } catch (Exception e2) {
                System.out.println("Élément non trouvé avec les sélecteurs génériques");
                return null;
            }
        }
    }
    
    /**
     * Méthode utilitaire pour trouver un élément bouton par différentes méthodes
     * @param cssSelector Le sélecteur CSS principal
     * @param keywords Mots-clés à rechercher dans les attributs et le texte
     * @return L'élément trouvé ou null si non trouvé
     */
    protected WebElement findButtonElement(String cssSelector, String... keywords) {
        try {
            // Essayer d'abord avec le sélecteur CSS direct
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            return element;
        } catch (Exception e) {
            System.out.println("Bouton non trouvé avec le sélecteur: " + cssSelector);
            
            // Essayer avec des sélecteurs plus génériques
            try {
                // Construire un sélecteur CSS plus générique basé sur les attributs
                StringBuilder selector = new StringBuilder("button");
                for (String keyword : keywords) {
                    selector.append(", button[name*=\"").append(keyword).append("\"]");
                    selector.append(", button[id*=\"").append(keyword).append("\"]");
                    selector.append(", button[class*=\"").append(keyword).append("\"]");
                    selector.append(", input[type=\"submit\"][value*=\"").append(keyword).append("\"]");
                    selector.append(", input[type=\"button\"][value*=\"").append(keyword).append("\"]");
                    selector.append(", a.btn[href*=\"").append(keyword).append("\"]");
                }
                
                WebElement element = driver.findElement(By.cssSelector(selector.toString()));
                return element;
            } catch (Exception e2) {
                System.out.println("Bouton non trouvé avec les sélecteurs génériques");
                
                // Essayer de trouver par texte
                try {
                    String xpathSelector = "//button[contains(text(),'" + keywords[0] + "')]";
                    for (int i = 1; i < keywords.length; i++) {
                        xpathSelector += " | //button[contains(text(),'" + keywords[i] + "')]";
                        xpathSelector += " | //input[@type='submit' and contains(@value,'" + keywords[i] + "')]";
                        xpathSelector += " | //input[@type='button' and contains(@value,'" + keywords[i] + "')]";
                        xpathSelector += " | //a[contains(@class,'btn') and contains(text(),'" + keywords[i] + "')]";
                    }
                    
                    WebElement element = driver.findElement(By.xpath(xpathSelector));
                    return element;
                } catch (Exception e3) {
                    System.out.println("Bouton non trouvé avec les sélecteurs XPath");
                    return null;
                }
            }
        }
    }
}
