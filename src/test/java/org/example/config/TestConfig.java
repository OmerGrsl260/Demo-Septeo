package org.example.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configuration centralisée pour les tests
 */
public class TestConfig {
    
    private static final Map<String, String> URLS = new HashMap<>();
    
    static {
        // Configuration des URLs
        URLS.put("SEPTEO_BASE_URL", "https://www.septeo.com/fr");
        URLS.put("SEPTEO_ABOUT_URL", "https://www.septeo.com/fr/qui-sommes-nous");
        // Ajouter d'autres URLs au besoin
    }
    
    /**
     * Récupère l'URL correspondant à la clé spécifiée
     * @param key Clé de l'URL
     * @return URL correspondante ou null si la clé n'existe pas
     */
    public static String getUrl(String key) {
        return URLS.get(key);
    }
    
    /**
     * Récupère l'URL de base de Septeo
     * @return URL de base de Septeo
     */
    public static String getSepteoBaseUrl() {
        return URLS.get("SEPTEO_BASE_URL");
    }
}
