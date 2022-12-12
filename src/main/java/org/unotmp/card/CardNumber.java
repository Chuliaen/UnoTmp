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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return value == ((CardNumber) o).value && getCardColor() == ((CardNumber) o).getCardColor();
    }

    @Override
    public String toString() {
        return "" + value + ", " + getCardColor() + "";
    }
}
