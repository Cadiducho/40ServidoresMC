package com.cadiducho.cservidoresmc.api;

import com.cadiducho.cservidoresmc.ApiClient;
import com.cadiducho.cservidoresmc.Updater;
import com.cadiducho.cservidoresmc.config.CSConfiguration;

public interface CSPlugin {

    String TAG = "&8[&b40ServidoresMC&8]";

    void log(String text);
    void logError(String text);

    default boolean isDebug() {
        return getCSConfiguration().getBoolean("debug");
    }

    default void debugLog(String s) {
        if (isDebug()){
            log("[Debug] " + s);
        }
    }

    /**
     * Registrar los comandos en la plataforma deseada
     */
    void registerCommands();

    /**
     * Datos de configuración del plugin, con implementación para cada tipo de servidor
     * @return config
     */
    CSConfiguration getCSConfiguration();

    /**
     * Comprobar si la configuración tiene una clave válida
     */
    default void checkDefaultKey() {
        if (getCSConfiguration().getString("clave", "key").equalsIgnoreCase("key")) {
            logError("¡Atención! La clave del servidor no está correctamente configurada");
            logError("Accede a la configuración y modifica 'clave' con el valor correcto obtenido en la página web.");
            log("Este error hará que el plugin no funcione correctamente.");
        }
    }

    /**
     * Instancia del cliente HTTP para la API
     * @return API client
     */
    ApiClient getApiClient();

    /**
     * Instancia del actualizador
     * @return updater
     */
    Updater getUpdater();

    /**
     * La versión del plugin en String, por ejemplo "3.0"
     * @return versión
     */
    String getPluginVersion();

    /**
     * Ejecutar un comando deseado por la consola del servidor
     * @param command El comando deseado
     */
    void dispatchCommand(String command);

    /**
     * Enviar un mensaje a todos los usuarios
     * @param message el mensaje
     */
    void broadcastMessage(String message);
}
