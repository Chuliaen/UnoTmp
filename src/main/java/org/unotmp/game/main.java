package org.unotmp.game;

import org.unotmp.card.Card;
import org.unotmp.card.CardColor;
import org.unotmp.card.CardDeckGenerator;
import org.unotmp.card.CardNumber;

import java.util.stream.Stream;

public class main {

    public static void main(String[] args) {
        CardDeckGenerator cd = new CardDeckGenerator();

        DrawPile dp = new DrawPile(cd.getCards());

        GameCreator gc = new GameCreator("test");
        gc.join("test2");
        gc.join("test3");
        gc.join("test4");

        PlayerIterator pi = gc.start(dp);


        System.out.println(pi.getCurrentPlayer().getName().equals("test"));

//        System.out.println(pi.getCurrentPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//
//        pi.changeDirection();
//        System.out.println();
//
//        System.out.println(pi.getCurrentPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());
//        System.out.println(pi.getNextPlayer().getName());

//        var pc = pi.getCurrentPlayer().getCards();
//        pi.getCurrentPlayer().addCard(new CardNumber(1, CardColor.BLUE));
//        pi.getCurrentPlayer().addCard(new CardNumber(1, CardColor.BLUE));
//
//        for (Card card : pc) {
//            System.out.println(card.toString());
//        }
//
//        System.out.println("-----------------------------------------------");
//
//        pi.getCurrentPlayer().removeCard(new CardNumber(1, CardColor.BLUE));
//
//        for (Card card : pc) {
//            System.out.println(card.toString());
//        }


//        System.out.println(dp.getSize());
//        dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();dp.drawCard();


//        Card c = dp.drawCard();
//        System.out.println(c.getCardType());
//        System.out.println(c.getCardColor());
    }

}
