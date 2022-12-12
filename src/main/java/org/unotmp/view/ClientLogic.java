package org.unotmp.view;

import org.unotmp.card.Card;
import org.unotmp.game.Game;
import org.unotmp.game.GameHandler;


public class ClientLogic {

    private GameHandler gameHandler;
    private Uno uno;
    private int gameId;

    public ClientLogic(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void createGame(String username) {
        gameHandler.createGame("", username);
    }

    public void createBoard(String username, int gameId) {
        this.gameId = gameId;
    }

    public void joinGame(int gameId, String username) {
        gameHandler.joinGame(gameId, username);
    }

    public void joinBoard(String[] username, int gameId) {
        this.gameId = gameId;
    }

    public void startGame() {
        gameHandler.startGame(this.gameId);
    }

    public void updateBoard(Game game) {
        var playerList = game.getPlayerIterator().getPlayers();
        var activePlayerID = game.getPlayerIterator().getCurrent();
        var gameDirection = game.getPlayerIterator().getGameDirection();
        var topCard = game.getTopCard();
        uno.showBoard(playerList, activePlayerID, gameDirection, topCard);
    }

    public void playCard(Card card, String username) {
        gameHandler.playCard(card, this.gameId, username);
    }

    public void drawCard(String username) {
        gameHandler.drawCard(this.gameId, username);
    }

    public void sayUno(String username) {
        gameHandler.sayUno(this.gameId, username);
    }

    public int getGameId(){
        return this.gameId;
    }



    public void setUno(Uno uno) {
        this.uno = uno;
    }



//    for later use and servercommunication
//    public void sendMessageToClient(String test){
//        switch(test){
//            case "HALLO" -> {
//
//            }
//        }
//    }
}