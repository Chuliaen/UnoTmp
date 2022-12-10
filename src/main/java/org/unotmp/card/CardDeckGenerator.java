package org.unotmp.card;

import java.util.ArrayList;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Uno_(card_game)
 * 112 Karten, inklusive 3 Custom Karten und 1 "Wild Shuffle Hands" Karten
 * → 108 Karten bei uns
 * jede Farbe: 1x 0, 2x 1-9, 2x DrawTwo, 2x Reverse, 2x Skip
 * wild cards: 4x Farbe wählen, 4x Plus 4
 */
public class CardDeckGenerator {
    private final List<Card> cards;

    public CardDeckGenerator() {
        this.cards = new ArrayList<>(108);
        generateDeck();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void generateDeck() {
        for (CardColor color : CardColor.values()) {
            cards.add(new CardNumber(0, color));

            for (int i = 1; i < 10; i++) {
                cards.add(new CardNumber(i, color));
                cards.add(new CardNumber(i, color));
            }

            for (int i = 0; i < 2; i++) {
                cards.add(new CardAction(CardType.SKIP, color));
                cards.add(new CardAction(CardType.REVERSE, color));
                cards.add(new CardAction(CardType.DRAW_TWO, color));
            }
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new CardWild(CardType.WILD));
            cards.add(new CardWild(CardType.WILD_DRAW_FOUR));
        }
    }

}
