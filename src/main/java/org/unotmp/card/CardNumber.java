package org.unotmp.card;

public class CardNumber extends CardAbstract {
    private final int value;

    public CardNumber(int value, CardColor color) {
        super(CardType.NUMBER, color);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
