package org.example.context;

import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 * Classe qui gère le contexte partagé entre les différentes étapes des tests
 */
public class TestContext {
    
    private final WebDriver driver;
    
    public TestContext() {
        // Initialiser le driver au début du scénario
        this.driver = WebDriverManager.getDriver();
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void closeDriver() {
        WebDriverManager.quitDriver();
    }
}
