package com.cadiducho.cservidoresmc.api;

public interface CSCommandSender {

    /**
     * Get the tag of the plugin
     * @return The tag
     */
    String TAG();

    /**
     * Send a message to the sender
     * @param message The message
     */
    void sendMessage(String message);

    /**
     * Send a message to the sender with the plugin tag
     * @param message The message
     */
    default void sendMessageWithTag(String message) {
        sendMessage(TAG() + " " + message);
    }

    /**
     * Send a default message with the web link
     * @param message The message
     * @param web The web link
     */
    default void sendNotVotedTodayLink(String message, String web) {
        sendMessageWithTag(message + web);
    }

    /**
     * Get the name of the sender
     * @return The name of the sender
     */
    String getName();

    /**
     * Check if the sender is a console
     * @return true if the sender is a console
     */
    default boolean isConsole() {
        return false;
    }

    /**
     * Check if the sender has a permission
     * @param permission The permission
     * @return true if the sender has the permission
     */
    boolean hasPermission(String permission);
}
