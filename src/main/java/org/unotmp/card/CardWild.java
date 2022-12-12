package org.unotmp.card;

public class CardWild extends CardAbstract {

    public CardWild(CardType type) {
        super(type, null);
    }

    public CardWild(CardType type, CardColor color) {
        super(type, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return getCardType() == ((CardWild) o).getCardType() && getCardColor() == ((CardWild) o).getCardColor();
    }

    @Override
    public String toString() {
        return "" + getCardType() + ", " + getCardColor() + "";
    }
}
