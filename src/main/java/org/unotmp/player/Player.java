package org.unotmp.player;

import org.unotmp.card.Card;

import java.util.stream.Stream;

public class Player {

    private final String name;
    private final CardList cardList;

    public Player(String name, CardList cardList) {
        this.name = name;
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public Stream<Card> getCards() {
        return this.cardList.getCards();
    }

    public void addCard(Card card) {
        cardList.addCard(card);
    }

    public boolean removeCard(Card card) {
        return cardList.removeCard(card);
    }

    public int getCardsNumber() {
        return cardList.size();
    }
}
