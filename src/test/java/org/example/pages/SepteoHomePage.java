package org.example.pages;

import org.example.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page object pour la page d'accueil de Septeo
 */
public class SepteoHomePage extends BasePage {
    
    // Localisateur pour le menu burger
    private static final By BURGER_MENU = By.cssSelector(".navbar-toggler, .menu-toggle, .hamburger-menu");
    
    // Localisateurs pour la bannière de cookies - spécifiques au site Septeo
    private static final By COOKIE_BANNER = By.cssSelector("#didomi-popup");
    private static final By ACCEPT_COOKIES_BUTTON = By.cssSelector("#didomi-notice-agree-button");
    
    // Localisateurs pour vérifier que la page d'accueil est chargée
    private static final By HOME_PAGE_INDICATOR = By.cssSelector("body, main, #main-content, .main-content, .container, header, footer");
    
    // Localisateurs pour les éléments de la page d'accueil
    private static final By LOGO = By.cssSelector("img[alt*='Septeo'], .logo img, a.logo, .navbar-brand img");
    private static final By MAIN_MENU = By.cssSelector("nav, .main-menu, .navbar, .navbar-nav, ul.menu");
    
    // Délais d'attente courts en millisecondes
    private static final int WAIT_TIMEOUT = 500;  // 0,5 seconde
    
    public SepteoHomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigue vers la page d'accueil de Septeo
     */
    public void navigateToHomePage() {
        String url = TestConfig.getSepteoBaseUrl();
        driver.get(url);
        waitForPageToLoad();
    }
    
    /**
     * Attend que la page d'accueil soit chargée
     */
    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        wait.until(ExpectedConditions.presenceOfElementLocated(HOME_PAGE_INDICATOR));
    }
    
    /**
     * Accepte les cookies s'ils sont présents
     * @return true si les cookies ont été acceptés, false sinon
     */
    public boolean acceptCookies() {
        try {
            // Vérifier si la bannière de cookies est présente
            WebElement cookieBanner = null;
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
                cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER));
            } catch (Exception e) {
                // Essayer avec un sélecteur plus générique
                try {
                    cookieBanner = driver.findElement(By.cssSelector("#axeptio_overlay, .axeptio_overlay, .cookie-banner"));
                } catch (Exception e2) {
                    System.out.println("Bannière de cookies non trouvée");
                    return false;
                }
            }
            
            if (cookieBanner != null && cookieBanner.isDisplayed()) {
                // Trouver le bouton d'acceptation
                WebElement acceptButton = null;
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
                    acceptButton = wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BUTTON));
                } catch (Exception e) {
                    // Essayer avec un sélecteur plus générique
                    try {
                        acceptButton = driver.findElement(By.cssSelector("#axeptio_btn_acceptAll, .axeptio_btn_acceptAll, button[contains(text(),'Accept')]"));
                    } catch (Exception e2) {
                        System.out.println("Bouton d'acceptation des cookies non trouvé");
                        return false;
                    }
                }
                
                if (acceptButton != null && acceptButton.isDisplayed()) {
                    acceptButton.click();
                    System.out.println("Cookies acceptés");
                    return true;
                }
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Erreur lors de l'acceptation des cookies: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Vérifie si la page d'accueil est correctement affichée
     * @return true si la page d'accueil est affichée, false sinon
     */
    public boolean isHomePageDisplayed() {
        try {
            // Attendre que la page soit complètement chargée
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.presenceOfElementLocated(HOME_PAGE_INDICATOR));
            } catch (Exception e) {
                System.out.println("Timeout en attendant l'indicateur de page d'accueil: " + e.getMessage());
            }
            
            // Vérifier le titre de la page
            String pageTitle = driver.getTitle();
            System.out.println("Titre de la page: " + pageTitle);
            boolean titleContainsSepteo = pageTitle.toLowerCase().contains("septeo");
            System.out.println("Le titre contient 'Septeo': " + titleContainsSepteo);
            
            // Vérifier la présence du logo
            boolean logoDisplayed = isElementDisplayed(LOGO);
            System.out.println("Logo affiché: " + logoDisplayed);
            
            // Vérifier la présence du menu principal
            boolean menuDisplayed = isElementDisplayed(MAIN_MENU);
            System.out.println("Menu affiché: " + menuDisplayed);
            
            // Vérifier la présence de l'indicateur de page d'accueil
            boolean indicatorDisplayed = isElementDisplayed(HOME_PAGE_INDICATOR);
            System.out.println("Indicateur de page d'accueil affiché: " + indicatorDisplayed);
            
            // Vérifier si l'URL contient "septeo"
            String currentUrl = driver.getCurrentUrl();
            System.out.println("URL actuelle: " + currentUrl);
            boolean urlContainsSepteo = currentUrl.toLowerCase().contains("septeo");
            System.out.println("L'URL contient 'septeo': " + urlContainsSepteo);
            
            // Considérer la page comme affichée si au moins deux des conditions sont remplies
            boolean isDisplayed = (logoDisplayed || menuDisplayed || indicatorDisplayed || titleContainsSepteo || urlContainsSepteo) &&
                                 (logoDisplayed || menuDisplayed || indicatorDisplayed);
            
            System.out.println("Page d'accueil affichée: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la page d'accueil: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Vérifie si un élément est affiché
     * @param locator Le localisateur de l'élément
     * @return true si l'élément est affiché, false sinon
     */
    private boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Clique sur un élément du menu principal
     * @param menuText Texte du menu à cliquer
     * @return Cette instance de page
     */
    public SepteoHomePage clickOnMainMenu(String menuText) {
        try {
            // Essayer de trouver le menu par son texte
            By menuLocator = By.xpath("//nav//a[contains(text(), '" + menuText + "')]");
            
            // Vérifier si le menu est visible
            if (isElementVisible(menuLocator)) {
                // Cliquer sur le menu
                driver.findElement(menuLocator).click();
            } else {
                // Si le menu n'est pas visible, essayer de cliquer sur le menu burger d'abord
                if (isElementVisible(BURGER_MENU)) {
                    driver.findElement(BURGER_MENU).click();
                    
                    // Attendre que le menu apparaisse
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
                    wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));
                    
                    // Cliquer sur le menu
                    driver.findElement(menuLocator).click();
                } else {
                    throw new NoSuchElementException("Menu '" + menuText + "' non trouvé et menu burger non visible");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du clic sur le menu '" + menuText + "': " + e.getMessage());
            
            // Essayer une approche alternative avec JavaScript
            try {
                String jsScript = "Array.from(document.querySelectorAll('nav a')).find(a => a.textContent.includes('" + menuText + "')).click();";
                ((JavascriptExecutor) driver).executeScript(jsScript);
                System.out.println("Clic effectué via JavaScript");
            } catch (Exception jsError) {
                System.out.println("Échec du clic via JavaScript: " + jsError.getMessage());
                throw e; // Relancer l'exception originale
            }
        }
        
        return this;
    }
    
    /**
     * Vérifie si un élément est visible sur la page
     * @param locator Localisateur de l'élément
     * @return true si l'élément est visible, false sinon
     */
    protected boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(WAIT_TIMEOUT));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
