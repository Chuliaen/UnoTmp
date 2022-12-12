package org.unotmp.card;

public class CardAction extends CardAbstract {
    public CardAction(CardType type, CardColor color) {
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

        return getCardType() == ((CardAction) o).getCardType() && getCardColor() == ((CardAction) o).getCardColor();
    }

    @Override
    public String toString() {
        return "" + getCardType() + ", " + getCardColor() + "";
    }
}
