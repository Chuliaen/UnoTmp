package org.unotmp.game;

import org.unotmp.card.*;
import org.unotmp.tmp.GameComCallback;
import org.unotmp.tmp.GameComCallbackImpl;

import java.util.stream.Stream;

public class main {

    public static void main(String[] args) {
        GameHandler gameHandler = new GameHandler();

        /**
         *  Callback logic
         */

        GameComCallback gameComCallback = new GameComCallbackImpl();
        gameHandler.registerGameCallback(gameComCallback);
        gameHandler.testCallback();

        /**
         *

        gameHandler.createGame("", "user1");
        gameHandler.joinGame(0, "user2");
        gameHandler.joinGame(0, "user3");

        gameHandler.startGame(0);

        System.out.println(gameHandler.playCard(new CardNumber(1, CardColor.RED), 0, "user1"));
        System.out.println(gameHandler.playCard(new CardNumber(1, CardColor.RED), 0, "user2"));

         */


//        CardDeckGenerator cd = new CardDeckGenerator();
//
//        DrawPile dp = new DrawPile(cd.getCards());
//
//
//        GameCreator gc = new GameCreator("test");
//        gc.join("test2");
//        gc.join("test3");
//        gc.join("test4");
//        PlayerIterator pi = gc.start(dp);
//
//        Game game = new Game(dp, pi);
//
//        pi.getCurrentPlayer().addCard(new CardNumber(2, CardColor.RED));
//
//        game.playCard("test", pi.getCurrentPlayer().getCards().get(7));
//
//        System.out.println(pi.getCurrentPlayer().getName());
//        System.out.println("Play +2");
//
//        pi.getCurrentPlayer().addCard(new CardAction(CardType.DRAW_TWO, CardColor.RED));
//
//        game.playCard("test2", pi.getCurrentPlayer().getCards().get(7));
//
//        System.out.println(pi.getCurrentPlayer().getName());
//        System.out.println("After +2");


//        CardDeckGenerator cd = new CardDeckGenerator();
//
//        DrawPile dp = new DrawPile(cd.getCards());
//
//        GameCreator gc = new GameCreator("test");
//        gc.join("test2");
//        gc.join("test3");
//        gc.join("test4");
//
//        PlayerIterator pi = gc.start(dp);
//
//
//        System.out.println(pi.getCurrentPlayer().getName().equals("test"));

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
