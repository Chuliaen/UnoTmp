package org.unotmp.player;

import org.unotmp.card.Card;

import java.util.List;

public class Player {

    private final String name;
    private final PlayerCardList cardList;
    private boolean saidUno = false;

    public Player(String name, PlayerCardList cardList) {
        this.name = name;
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return this.cardList.getCards();
    }

    public void addCard(Card card) {
        cardList.addCard(card);
    }

    public boolean removeCard(Card card) {
        return cardList.removeCard(card);
    }

    public int getCardsAmount() {
        return cardList.size();
    }

    public boolean getSaidUno() {
        return saidUno;
    }

    public void setSaidUno(boolean saidUno) {
        this.saidUno = saidUno;
    }
}