package org.unotmp.card;

public abstract class CardAbstract implements Card {
    private final CardType cardType;
    private final CardColor cardColor;

    protected CardAbstract(CardType type, CardColor color) {
        this.cardType = type;
        this.cardColor = color;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public CardColor getCardColor() {
        return cardColor;
    }
}
