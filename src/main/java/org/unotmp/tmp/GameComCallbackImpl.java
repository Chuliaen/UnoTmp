package org.unotmp.tmp;

public class GameComCallbackImpl implements GameComCallback {
    @Override
    public void sendMessageToClient(String username, String message, byte[] data) {
        System.out.println("User called callback:" + username);
        System.out.println("With message:" + message);
    }
}
