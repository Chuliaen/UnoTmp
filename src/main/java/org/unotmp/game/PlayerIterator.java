package org.unotmp.game;

import org.unotmp.player.Player;

public class PlayerIterator {

    private final Player[] players;
    private int current = 0;
    private GameDirection gameDirection = GameDirection.CLOCKWISE;

    public PlayerIterator(Player[] players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return players[current];
    }

    public void changeDirection() {
        if (gameDirection == GameDirection.CLOCKWISE) {
            gameDirection = GameDirection.COUNTER_CLOCKWISE;
        } else {
            gameDirection = GameDirection.CLOCKWISE;
        }
    }

    public Player getNextPlayer() {
        if (gameDirection == GameDirection.CLOCKWISE) {
            current = (current + 1) % players.length;
        } else {
            current = (current - 1 + players.length) % players.length;
        }
        return getCurrentPlayer();
    }

    public Player[] getPlayers() {
        return players;
    }

    //todo: remove player
}