package com.cadiducho.cservidoresmc.api;

public interface CSCommandSender {

    void sendMessage(String string);

    default void sendMessageWithTag(String string) {
        sendMessage(CSPlugin.TAG + " " + string);
    }

    default void sendNotVotedTodayLink(String message, String web) {
        sendMessageWithTag(message + web);
    }

    String getName();

    default boolean isConsole() {
        return false;
    }

    boolean hasPermission(String string);
}
