package org.unotmp.game;

import org.unotmp.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DrawPile {
    private final Stack<Card> cards = new Stack<>();

    public DrawPile(List<Card> cardList) {
        cards.addAll(shuffleCards(cardList));
    }

    public Card drawCard() {
        return cards.pop();
    }

    public int getSize() {
        return cards.size();
    }

    private List<Card> shuffleCards(List<Card> cardList){
        List<Card> shuffledCards = new ArrayList<>(cardList);
        Random random = new Random();

        for (int i = 0; i < shuffledCards.size() - 1; i++) {
            int randomCardIndex = i + random.nextInt(cardList.size() - i);
            swapCards(shuffledCards, i , randomCardIndex);
        }

        return shuffledCards;
    }

    private void swapCards(List<Card> cards, int currentCardNumber, int randomCardNumber) {
        Card randomCard = cards.get(randomCardNumber);
        cards.set(randomCardNumber, cards.get(currentCardNumber));
        cards.set(currentCardNumber, randomCard);
    }

}
