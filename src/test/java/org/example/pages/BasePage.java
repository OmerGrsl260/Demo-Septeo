package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Classe de base pour tous les objets de page
 */
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Attend que l'élément soit cliquable et clique dessus
     * @param locator Localisateur de l'élément
     */
    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    
    /**
     * Attend que l'élément soit visible
     * @param locator Localisateur de l'élément
     * @return L'élément web
     */
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Vérifie si un élément est présent sur la page
     * @param locator Localisateur de l'élément
     * @return true si l'élément est présent, false sinon
     */
    protected boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Attend que le titre de la page contienne le texte spécifié
     * @param text Texte à rechercher dans le titre
     * @return true si le titre contient le texte, false sinon
     */
    protected boolean waitForTitleContains(String text) {
        try {
            return wait.until(ExpectedConditions.titleContains(text));
        } catch (Exception e) {
            return false;
        }
    }
}
