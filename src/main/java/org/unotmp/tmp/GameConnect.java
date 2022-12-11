package org.unotmp.tmp;


import org.unotmp.card.Card;

public interface GameConnect {

    int[] availableGames();

    /**
     * Get all gameIDs from join-able games
     *
     * @return int array with gameIDs
     */
    int[] availableGamesList();

    /**
     * Create a new game with a set of rules
     *
     * @param rules Set of rules
     * @return (@link ReturnCreateGame)
     */
    ReturnCreateGame createGame(String rules);

    enum ReturnCreateGame {
        MAX_NUMBER_OF_BOARDS,
        SUCCESS,
        FAILURE;
    }

    /**
     * Join the game based of gameId
     *
     * @param gameId gameId of game user wants to join
     * @return (@link ReturnJoinGame)
     */
    ReturnJoinGame joinGame(int gameId, String username);

    enum ReturnJoinGame {
        MAX_NUMBER_OF_PLAYERS,
        GAME_STATE_PLAYING,
        GAME_STATE_PAUSED,
        SUCCESS,
        FAILURE;
    }

    /**
     * Start the game with specific gameId
     *
     * @param gameId gameId of game host wants to start
     * @return (@link ReturnStartGame)
     */
    ReturnStartGame startGame(int gameId);

    enum ReturnStartGame {
        NOT_ENOUGH_PLAYERS,
        SUCCESS,
        FAILURE;
    }
}
