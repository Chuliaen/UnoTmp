package org.unotmp.game;

import edu.unibw.se.uno.server.game.GameCom.*;
import org.unotmp.card.Card;
import org.unotmp.card.CardAction;
import org.unotmp.card.CardNumber;
import org.unotmp.card.CardType;

import java.util.Stack;

public class Game {

    private PlayerIterator playerIterator;
    private DrawPile drawPile;
    private Stack<Card> discardPile = new Stack<Card>();

    public Game(DrawPile drawPile, PlayerIterator players) {
        this.drawPile = drawPile;
        this.playerIterator = players;
    }

    /**
     * games[id].playcard(username, card)
     * GH -> Game -> gameid.playCard(username, card)
     */

    public ReturnPlayCard playCard(String playerName, Card playedCard) {
        if (!playerIterator.getCurrentPlayer().getName().equals(playerName)) {
            return ReturnPlayCard.NOT_YOUR_TURN;
        }

        if (isPlayable(playedCard)) {
            return null;
        }

        if (!playerIterator.getCurrentPlayer().removeCard(playedCard)) {
            return ReturnPlayCard.FAILURE;
        }


        //checkHasWon();
        return ReturnPlayCard.SUCCESS;
    }

    public boolean isPlayable(Card playedCard) {
        var topCard = discardPile.peek();

        switch (playedCard.getCardType()) {
            case NUMBER -> {
                if (checkNumberCard(topCard, (CardNumber) playedCard)) {
                    placeCard(playedCard);

                    //todo:
                }
            }
            case SKIP -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    //todo:
                }
            }
            case REVERSE -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    //todo:
                }
            }
            case DRAW_TWO -> {
                if (checkActionCard(topCard, (CardAction) playedCard)) {
                    //todo:
                }
            }
            case WILD -> {
            }
            case WILD_DRAW_FOUR -> {
            }
        }
        return false;
    }

    private void placeCard(Card playedCard) {

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
