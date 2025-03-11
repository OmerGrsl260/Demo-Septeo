package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverManager {
    
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }
    
    public static void initDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser", "chrome");
        
        switch (browser.toLowerCase()) {
            case "firefox":
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                // Ajouter des options pour accélérer la fermeture du navigateur
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driverThreadLocal.set(driver);
    }
    
    public static void quitDriver() {
        System.out.println("WebDriverManager: Fermeture du driver...");
        try {
            if (driverThreadLocal.get() != null) {
                WebDriver driver = driverThreadLocal.get();
                
                // Fermer toutes les fenêtres et onglets
                try {
                    driver.close();
                } catch (Exception e) {
                    System.out.println("Erreur lors de la fermeture des fenêtres: " + e.getMessage());
                }
                
                // Quitter le driver
                try {
                    driver.quit();
                } catch (Exception e) {
                    System.out.println("Erreur lors du quit du driver: " + e.getMessage());
                }
                
                // Nettoyer la référence
                driverThreadLocal.remove();
                System.out.println("WebDriverManager: Driver fermé avec succès");
                
                // Forcer le garbage collector pour libérer les ressources
                System.gc();
            } else {
                System.out.println("WebDriverManager: Aucun driver à fermer");
            }
        } catch (Exception e) {
            System.out.println("WebDriverManager: Erreur lors de la fermeture du driver: " + e.getMessage());
        }
    }
}
