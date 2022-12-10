package org.unotmp.card;

public class CardValidator {

    public static void validateColor(CardColor color) {
        if (!(color == CardColor.BLUE || color == CardColor.GREEN || color == CardColor.RED || color == CardColor.YELLOW)) {
            System.err.println("CardColor is null");
        }
    }

    public static void validateNumber(int number) {
        if (number > 9 || number < 0) {
            System.err.println("Number is less then 0 or greater then 9");
        }
    }

    public static void validateAction(CardType type) {
        if (!(type == CardType.SKIP || type == CardType.REVERSE || type == CardType.DRAW_TWO)) {
            System.err.println("Number is no Action card");
        }
    }

    public static boolean validateWildCard(Card card) {
        if (card.getCardType() == CardType.WILD || card.getCardType() == CardType.WILD_DRAW_FOUR) {
            return true;
        }
        return false;
    }

}
