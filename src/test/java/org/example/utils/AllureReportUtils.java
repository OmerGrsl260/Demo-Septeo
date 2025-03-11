package org.example.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Classe utilitaire pour les fonctionnalités liées aux rapports Allure
 */
public class AllureReportUtils {

    /**
     * Prend une capture d'écran et l'attache au rapport Allure
     * @param driver WebDriver actif
     * @param name Nom de la capture d'écran
     * @return Tableau d'octets de l'image
     */
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver, String name) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    /**
     * Attache une capture d'écran au rapport Allure lors d'un échec de test
     * @param driver WebDriver actif
     * @param testName Nom du test
     */
    public static void attachScreenshotOnFailure(WebDriver driver, String testName) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(testName + " - Capture d'écran", "image/png", 
                    new ByteArrayInputStream(screenshot), "png");
            } catch (Exception e) {
                System.out.println("Impossible de prendre une capture d'écran: " + e.getMessage());
            }
        }
    }

    /**
     * Attache des informations HTML au rapport Allure
     * @param name Nom de la pièce jointe
     * @param html Contenu HTML
     */
    @Attachment(value = "{name}", type = "text/html")
    public static String attachHtml(String name, String html) {
        return html;
    }

    /**
     * Attache du texte au rapport Allure
     * @param name Nom de la pièce jointe
     * @param text Contenu texte
     */
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String text) {
        return text;
    }
}
