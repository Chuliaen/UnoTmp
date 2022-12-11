package org.unotmp.game;

import org.unotmp.card.CardDeckGenerator;
import org.unotmp.player.PlayerCardList;
import org.unotmp.player.Player;

public class GameCreator {

    private String[] playerNames = new String[10];
    private int currentPlayers = 0;

    public GameCreator(String playerName) {
        playerNames[0] = playerName;
        currentPlayers = 1;
    }

    public PlayerIterator start(DrawPile drawPile) {
        Player[] players = new Player[currentPlayers];

        for (int i = 0; i < currentPlayers; i++) {
            PlayerCardList cards = new PlayerCardList();
            for (int j = 0; j < 7; j++) {
                cards.addCard(drawPile.drawCard());
            }

            players[i] = new Player(playerNames[i], cards);
        }

        return new PlayerIterator(players);
    }

    public boolean join(String playerName) {
        if (currentPlayers < 10) {
            playerNames[currentPlayers] = playerName;
            currentPlayers += 1;
            return true;
        }
        return false;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }
}