package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Page object pour la page de sélection de métier et de besoin
 */
public class SelectionPage extends BasePage {

    private static final By FIRST_SELECT = By.cssSelector("#first-select");
    private static final By SECOND_SELECT = By.cssSelector("#second-select");
    private static final By JS_BUTTON = By.cssSelector("#jsbutton");

    public SelectionPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Sélectionne le métier de Notaire
     * @return true si la sélection a réussi, false sinon
     */
    public boolean selectMetierNotaire() {
        try {
            System.out.println("Sélection du métier de Notaire");
            
            // Cliquer sur le premier select
            WebElement firstSelect = driver.findElement(FIRST_SELECT);
            firstSelect.click();
            
            // Appuyer sur la flèche du bas une fois
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            
            // Appuyer sur Entrée pour sélectionner
            actions.sendKeys(Keys.ENTER).perform();
            
            System.out.println("Métier de Notaire sélectionné avec succès");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la sélection du métier de Notaire: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Sélectionne un besoin par son index
     * @param index L'index du besoin à sélectionner (1 ou 2)
     * @return true si la sélection a réussi, false sinon
     */
    public boolean selectBesoin(int index) {
        try {
            System.out.println("Sélection du besoin à l'index: " + index);
            
            // Cliquer sur le deuxième select
            WebElement secondSelect = driver.findElement(SECOND_SELECT);
            secondSelect.click();
            
            // Appuyer sur la flèche du bas autant de fois que nécessaire
            Actions actions = new Actions(driver);
            for (int i = 0; i < index; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
            }
            
            // Appuyer sur Entrée pour sélectionner
            actions.sendKeys(Keys.ENTER).perform();
            
            // Cliquer sur le bouton JS
            WebElement jsButton = driver.findElement(JS_BUTTON);
            jsButton.click();
            
            System.out.println("Besoin à l'index " + index + " sélectionné avec succès");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la sélection du besoin à l'index " + index + ": " + e.getMessage());
            return false;
        }
    }
}
