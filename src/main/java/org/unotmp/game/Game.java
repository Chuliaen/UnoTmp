package org.unotmp.game;

import org.unotmp.tmp.GameCom.*;
//todo: delete


import org.unotmp.card.*;
import org.unotmp.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private PlayerIterator playerIterator;
    private DrawPile drawPile;
    private Stack<Card> discardPile = new Stack<>();

    private Player winner = null;

    private List<String> playerNames = new ArrayList<>();

    public Game(DrawPile drawPile, PlayerIterator players) {
        this.drawPile = drawPile;
        this.playerIterator = players;

        for (Player player : playerIterator.getPlayers()) {
            playerNames.add(player.getName());
        }


        //todo: change
        Card firstCard = new CardNumber(1, CardColor.RED);
        this.discardPile.add(firstCard);
        playerIterator.getCurrentPlayer().addCard(new CardNumber(1, CardColor.RED));
    }


    /**
     * Methods for access and interface
     */

    public ReturnPlayCard playCard(String playerName, Card playedCard) {
        if (!playerIterator.getCurrentPlayer().getName().equals(playerName)) {
            return ReturnPlayCard.NOT_YOUR_TURN;
        }

        if (!isPlayable(playedCard)) {
            return ReturnPlayCard.FAILURE;
        }

        if (winner != null) {
            //todo::
            throw new IllegalStateException("Game is over");
        }

        return ReturnPlayCard.SUCCESS;
    }

    public Player getWinner() {
        return winner;
    }

    public List<String> getPlayers() {
        return playerNames;
    }

    public ReturnDrawCard drawCard(String playerName) {
        if (!playerIterator.getCurrentPlayer().getName().equals(playerName)) {
            return ReturnDrawCard.NOT_YOUR_TURN;
        }

        playerIterator.getCurrentPlayer().setSaidUno(false);
        drawCards(1);
        playerIterator.getNextPlayer();
        return ReturnDrawCard.SUCCESS;
    }

    public ReturnSayUno sayUno(String playerName) {
        if (!playerIterator.getCurrentPlayer().getName().equals(playerName)) {
            return ReturnSayUno.NOT_YOUR_TURN;
        }

        if (playerIterator.getCurrentPlayer().getSaidUno()) {
            return ReturnSayUno.FAILURE;
        }
        playerIterator.getCurrentPlayer().setSaidUno(true);
        return ReturnSayUno.SUCCESS;
    }


    /**
     * Private methods for game logic
     */

    private boolean isPlayable(Card playedCard) {
        var topCard = discardPile.peek();

        switch (playedCard.getCardType()) {
            case NUMBER -> {
                if (checkNumberCard(topCard, (CardNumber) playedCard)) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.getNextPlayer();
                }
            }
            case SKIP -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.getNextPlayer();
                    playerIterator.getNextPlayer();
                }
            }
            case REVERSE -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.changeDirection();
                    playerIterator.getNextPlayer();
                }
            }
            case DRAW_TWO -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.getNextPlayer();
                    drawCards(2);
                    playerIterator.getNextPlayer();
                }
            }
            case WILD -> {
                if (playedCard.getCardColor() != null) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.getNextPlayer();
                }


            }
            case WILD_DRAW_FOUR -> {
                if (playedCard.getCardColor() != null) {
                    if (!placeCard(playedCard)) {
                        return false;
                    }

                    playerIterator.getNextPlayer();
                    drawCards(4);
                    playerIterator.getNextPlayer();
                }
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    private void drawCards(int total) {
        int drawPileSize = drawPile.getSize();
        playerIterator.getCurrentPlayer().setSaidUno(false);

        if (total < drawPileSize) {
            for (int i = 0; i < total; i++) {
                playerIterator.getCurrentPlayer().addCard(drawPile.drawCard());
            }
        } else {
            total = total - drawPileSize;
            for (int i = 0; i < drawPileSize; i++) {
                playerIterator.getCurrentPlayer().addCard(drawPile.drawCard());
            }

            Card firstCard = discardPile.pop();
            drawPile = new DrawPile(discardPile);
            discardPile.clear();
            discardPile.add(firstCard);

            for (int i = 0; i < total; i++) {
                playerIterator.getCurrentPlayer().addCard(drawPile.drawCard());
            }
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean placeCard(Card playedCard) {
        if (!playerIterator.getCurrentPlayer().removeCard(playedCard)) {
            return false;
        }
        discardPile.add(playedCard);

        int remainingCards = playerIterator.getCurrentPlayer().getCardsAmount();
        if (remainingCards == 1 && !playerIterator.getCurrentPlayer().getSaidUno()) {
            drawCards(2);
        } else if (remainingCards == 0) {
            winner = playerIterator.getCurrentPlayer();
        }
        return true;
    }

    private boolean checkNumberCard(Card topCard, CardNumber playedCard) {
        if (topCard.getCardColor() == playedCard.getCardColor()) {
            return true;
        }

        if (topCard.getCardType() == CardType.NUMBER) {
            return ((CardNumber) topCard).getValue() == playedCard.getValue();
        }

        return false;
    }


    private boolean checkActionCard(Card topCard, CardAction playedCard) {
        if (topCard.getCardColor() == playedCard.getCardColor()) {
            return true;
        }

        return topCard.getCardType() == playedCard.getCardType();
    }

}
