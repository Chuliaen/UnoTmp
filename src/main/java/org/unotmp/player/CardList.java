package org.unotmp.player;

import org.unotmp.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CardList {
    private final List<Card> playerCards = new ArrayList<>();

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public boolean removeCard(Card card) {
        if (playerCards.contains(card)){
            playerCards.remove(card);
            return true;
        }
        return false;
    }

    public Stream<Card> getCards () {
        return playerCards.stream();
    }

    public int size() {
        return playerCards.size();
    }

}
