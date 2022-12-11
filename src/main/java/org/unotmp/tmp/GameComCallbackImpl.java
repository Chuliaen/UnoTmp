package org.unotmp.tmp;

import java.util.Arrays;

public class GameComCallbackImpl implements GameComCallback {

    @Override
    public void sendMessageToClient(String[] username, String[] message, byte[] data) {
        System.out.println(Arrays.toString(username));
        System.out.println(Arrays.toString(message));
    }
}
