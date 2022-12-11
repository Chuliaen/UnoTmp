package org.unotmp.tmp;


import org.unotmp.card.Card;

public interface GameCom {

    /**
     * Play card
     *
     * @param card card which to place
     * @param gameId gameId of game
     * @param username player who plays the card
     * @return (@link ReturnStartGame)
     */
    ReturnPlayCard playCard(Card card, int gameId, String username);

    enum ReturnPlayCard {
        CARD_NOT_PLAYABLE,
        NOT_YOUR_TURN,
        SUCCESS,
        FAILURE;
    }

    /**
     * Draw card from pile
     *
     * @param gameId gameId of game
     * @param username player who draws the card
     * @return (@link ReturnDrawCard)
     */
    ReturnDrawCard drawCard(int gameId, String username);

    enum ReturnDrawCard {
        NOT_YOUR_TURN,
        SUCCESS,
        FAILURE;
    }

    /**
     * Say uno
     *
     * @param gameId gameId of game
     * @param username player who wants to say uno
     * @return (@link ReturnSayUno)
     */
    ReturnSayUno sayUno(int gameId, String username);

    enum ReturnSayUno {
        NOT_YOUR_TURN,
        SUCCESS,
        FAILURE;
    }

    /**
     * Leave game
     *
     * @param gameId gameId of game
     * @param username player who leaves the game
     * @return (@link ReturnLeaveGame)
     */
    ReturnLeaveGame leaveGame(int gameId, String username);

    enum ReturnLeaveGame {
        SUCCESS,
        FAILURE;
    }

    /**
     * Send chat message in ingame chat
     *
     * @param gameId gameId of game
     * @param username player who sends message
     * @param message complete message
     * @return (@link ReturnLeaveGame)
     */
    ReturnSendChatMessage sendChatMessage(int gameId, String username, String message);

    enum ReturnSendChatMessage {
        SUCCESS,
        FAILURE;
    }

    /**
     * Pause the game
     *
     * @param gameId gameId of game
     * @return (@link ReturnLeaveGame)
     */
    ReturnPauseGame pauseGame(int gameId);

    enum ReturnPauseGame {
        SUCCESS,
        FAILURE;
    }
}
