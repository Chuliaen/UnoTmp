package org.unotmp.tmp;

public interface GameComCallback {


    /**
     * @param username array with one or more usernames to identify the client(s)
     * @param message Array of Strings to send to client
     * @param data Can be used if Objects instead of Strings are needed
     */
    void sendMessageToClient(String username, String message, byte[] data);
}