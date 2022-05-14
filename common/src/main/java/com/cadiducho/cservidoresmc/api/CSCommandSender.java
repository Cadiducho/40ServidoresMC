package com.cadiducho.cservidoresmc.api;

public interface CSCommandSender {

    String TAG();

    void sendMessage(String string);

    default void sendMessageWithTag(String string) {
        sendMessage(TAG() + " " + string);
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
