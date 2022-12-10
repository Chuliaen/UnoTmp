package org.unotmp.card;

public class CardValidator {
    public void validateColor(CardColor color) {
        if (color == null) {
            System.err.println("CardColor is null");
        }
    }

    public void validateNumber(int number) {
        if (number > 9 || number < 0) {
            System.err.println("Number is less then 0 or greater then 9");
        }
    }

    public boolean validateWildCard(Card card) {
        if (card.getCardType() == ) {
            System.err.println("Number is less then 0 or greater then 9");
        }
    }

}
