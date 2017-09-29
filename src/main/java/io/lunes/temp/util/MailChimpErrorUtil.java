package io.lunes.temp.util;


import org.apache.commons.lang3.StringUtils;

/**
 * @author wandyersilva
 *
 * Utilitário para gerenciar mensagens de erro da API da MailChimp e retornar uma mensagem customizada.
 */
class MailChimpErrorUtil {

    /**
     * @param errorMessage mensagem de erro da API da MailChimp
     * @param language a linguagem da página que o usuíaro está
     * @return a mensagem customizada
     */
    static String checkError(String errorMessage, String language) {
        if (StringUtils.containsIgnoreCase(errorMessage, "is already a list member")) {
            if (StringUtils.containsIgnoreCase(language, "en")) {
                return "Email already registered!";
            } else {
                return "Email já registrado!";
            }
        } else if (StringUtils.containsIgnoreCase(errorMessage ,"looks fake or invalid")) {
            if (StringUtils.containsIgnoreCase(language, "en")) {
                return "This email looks fake or invalid, please enter a real email address.";
            } else {
                return "Este email parece ser falso ou inválido. Por favor, insira um endereço de email verdadeiro.";
            }
        } else {
            if (StringUtils.containsIgnoreCase(language, "en")) {
                return "Error!";
            } else {
                return "Erro!";
            }
        }
    }
}
