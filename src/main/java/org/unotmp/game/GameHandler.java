package org.unotmp.game;

import org.unotmp.card.Card;
import org.unotmp.card.CardDeckGenerator;

//todo: delete
import org.unotmp.card.CardNumber;
import org.unotmp.card.CardType;
import org.unotmp.player.Player;
import org.unotmp.tmp.GameCom.*;
import org.unotmp.tmp.GameComCallback;
import org.unotmp.tmp.GameComCallbackImpl;
import org.unotmp.tmp.GameConnect.*;

import java.util.ArrayList;
import java.util.List;


public class GameHandler {

    private Game[] games = new Game[10];
    private GameCreator[] gameCreators = new GameCreator[10];


    public ReturnCreateGame createGame(String rules, String username) {
        int gameId = 0;

        while (games[gameId] != null && gameCreators[gameId] != null) {
            gameId++;
            if (gameId >= 10) {
                return ReturnCreateGame.MAX_NUMBER_OF_BOARDS;
            }
        }

        //todo: add rules support
        GameCreator gc = new GameCreator(username);
        gameCreators[gameId] = gc;
        //todo: send id to server?!? and callback gameid

        return ReturnCreateGame.SUCCESS;
    }

    public ReturnJoinGame joinGame(int gameId, String username) {
        if (gameId < 0 || gameId > 9) {
            return ReturnJoinGame.FAILURE;
        }
        if (gameCreators[gameId] == null) {
            return ReturnJoinGame.FAILURE;
        }
        if (gameCreators[gameId] == null && games[gameId] != null) {
            return ReturnJoinGame.GAME_STATE_PLAYING;
        }

        if (!gameCreators[gameId].join(username)) {
            return ReturnJoinGame.MAX_NUMBER_OF_PLAYERS;
        }
        return ReturnJoinGame.SUCCESS;
    }

    public ReturnLeaveGame leaveGame() {
        //todo:
        return null;
    }

    public ReturnStartGame startGame(int gameId) {
        if (gameId < 0 || gameId > 9) {
            return ReturnStartGame.FAILURE;
        }

        GameCreator activeGameCreator = gameCreators[gameId];
        if (activeGameCreator.getCurrentPlayers() < 2) {
            return ReturnStartGame.NOT_ENOUGH_PLAYERS;
        }

        CardDeckGenerator cardDeckGenerator = new CardDeckGenerator();
        DrawPile drawPile = new DrawPile(cardDeckGenerator.getCards());
        PlayerIterator playerIterator = activeGameCreator.start(drawPile);
        Game game = new Game(drawPile, playerIterator);
        games[gameId] = game;
        gameCreators[gameId] = null;

        //todo: send gamestate and cards to everyone
        for (Player player : playerIterator.getPlayers()) {
            sendCallback(player.getName(), player.getCards().toString(), null);
        }

        return ReturnStartGame.SUCCESS;
    }


    public ReturnPlayCard playCard(Card card, int gameId, String username) {
        if (gameId < 0 || gameId > 9) {
            return ReturnPlayCard.FAILURE;
        }
        if (!games[gameId].getPlayers().contains(username)) {
            return ReturnPlayCard.FAILURE;
        }


        //todo: maybe change order? because update clients only if card is accepted
        ReturnPlayCard returnPlayCard = games[gameId].playCard(username, card);
        if (returnPlayCard == ReturnPlayCard.NOT_YOUR_TURN) {
            return ReturnPlayCard.NOT_YOUR_TURN;
        }
        if (returnPlayCard == ReturnPlayCard.CARD_NOT_PLAYABLE) {
            return ReturnPlayCard.CARD_NOT_PLAYABLE;
        }

        //todo: send gamestate and cards to everyone
        for (String player : games[gameId].getPlayers()) {
            if (card.getCardType() == CardType.NUMBER) {
                int value = ((CardNumber) card).getValue();
                sendCallback(player, "CARD{" + value + ", " + card.getCardColor().toString() + "}", null);
            } else {
                sendCallback(player, "CARD{" + card.getCardType() + ", " + card.getCardColor().toString() + "}", null);
            }
        }


        return returnPlayCard;
    }


    //todo: send gamestate and cards to everyone
    public ReturnDrawCard drawCard(int gameId, String username) {
        if (gameId < 0 || gameId > 9) {
            return ReturnDrawCard.FAILURE;
        }
        if (!games[gameId].getPlayers().contains(username)) {
            return ReturnDrawCard.FAILURE;
        }

        return games[gameId].drawCard(username);
    }

    public ReturnSayUno sayUno(int gameId, String username) {
        if (gameId < 0 || gameId > 9) {
            return ReturnSayUno.FAILURE;
        }
        if (!games[gameId].getPlayers().contains(username)) {
            return ReturnSayUno.FAILURE;
        }

        return games[gameId].sayUno(username);
    }

    /**
     * Callback logic
     */
    List<GameComCallback> gameComCallbacks = new ArrayList<>();

    public void registerGameCallback(GameComCallback gameComCallback) {
        gameComCallbacks.add(gameComCallback);
    }

    public void sendCallback(String username, String message, byte[] data) {
        for (GameComCallback gameComCallback : gameComCallbacks) {
            gameComCallback.sendMessageToClient(username, message, data);
        }
    }

}
