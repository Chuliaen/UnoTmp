package org.unotmp.player;

import org.unotmp.card.Card;
import org.unotmp.card.CardType;
import org.unotmp.card.CardWild;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardList {

    private final List<Card> playerCards = new ArrayList<>();

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public boolean removeCard(Card card) {
        if (playerCards.contains(card)) {
            playerCards.remove(card);
            return true;
        } else if (card.getCardType().equals(CardType.WILD)) {
            playerCards.remove(new CardWild(CardType.WILD, null));
            return true;
        } else if ( card.getCardType().equals(CardType.WILD_DRAW_FOUR)){
            playerCards.remove(new CardWild(CardType.WILD_DRAW_FOUR, null));
            return true;
        }
        return false;
    }

    public List<Card> getCards() {
        return playerCards;
    }

    public int size() {
        return playerCards.size();
    }

}