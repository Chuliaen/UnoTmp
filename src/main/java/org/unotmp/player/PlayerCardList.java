package org.unotmp.player;

import org.unotmp.card.Card;

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
        }
        return false;
    }

//    public boolean hasCard(Card card) {
//        return true;
//    }

    public List<Card> getCards() {
        return playerCards;
    }

    public int size() {
        return playerCards.size();
    }

}