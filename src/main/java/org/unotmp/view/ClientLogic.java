package org.unotmp.view;

import org.unotmp.card.Card;
import org.unotmp.game.Game;
import org.unotmp.game.GameHandler;


public class ClientLogic {

    private GameHandler gameHandler;
    private int gameId;

    public ClientLogic(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    //todo: abfragen für success usw (server communication)
    public void createGame(String username) {
        gameHandler.createGame("", username);
    }

    public void createBoard(String username, int gameId) {
        this.gameId = gameId;
        //update gui join board
    }

    public void joinGame(int gameId, String username) {
        gameHandler.joinGame(gameId, username);
    }

    public void joinBoard(String[] username, int gameId) {
        this.gameId = gameId;
        //an alle schicken auf dem Board
    }

    public void startGame() {
        gameHandler.startGame(this.gameId);
    }

    public void updateBoard(Game game) {
        var playerList = game.getPlayerIterator().getPlayers();
//       gui aufruf: placeCard(player, cards)
        var activePlayerID = game.getPlayerIterator().getCurrent();
        var gameDirection = game.getPlayerIterator().getGameDirection();
        var topCard = game.getTopCard();
    }

    public void playCard(Card card, String username) {
        gameHandler.playCard(card, this.gameId, username);
    }

    public void drawCard(String username) {
        gameHandler.drawCard(this.gameId, username);
    }

    //String -> Methoden

//    public void sendMessageToClient(String test){
//        switch(test){
//            case "HALLO" -> {
//
//            }
//        }
//    }
}