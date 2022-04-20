package model.game.board.mat.element;

import java.util.ArrayList;

import lombok.Data;
import model.game.card.programming.*;
import model.game.Player;
import model.game.card.Card;

import java.util.Collections;

@Data
public class ProgrammingDeck {

    private Player player;
    private ArrayList<Card> cards;
    public static final int NUMBER_OF_CARDS_DRAWN_IN_EACH_ROUND = 9;
    private static final int NUMBER_OF_CARDAGAIN = 2;
    private static final int NUMBER_OF_CARDMOVE1 = 6;
    private static final int NUMBER_OF_CARDMOVE2 = 4;
    private static final int NUMBER_OF_CARDMOVE3 = 2;
    private static final int NUMBER_OF_CARDTURNRIGHT = 6;
    private static final int NUMBER_OF_CARDTURNLEFT = 6;
    private static final int NUMBER_OF_CARDUTURN = 2;
    private static final int NUMBER_OF_CARDBACKUP = 2;
    private static final int NUMBER_OF_CARDPOWERUP = 2;


    public ProgrammingDeck(Player player) {
        this.player = player;
        this.init();
        this.shuffle(this.cards);
    }

    private void init() {
        this.cards = new ArrayList<>() {
            {
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDAGAIN; i++)
                    add(new CardAgain());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDMOVE1; i++)
                    add(new CardMove1());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDMOVE2; i++)
                    add(new CardMove2());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDMOVE3; i++)
                    add(new CardMove3());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDTURNRIGHT; i++)
                    add(new CardTurnRight());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDTURNLEFT; i++)
                    add(new CardTurnLeft());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDUTURN; i++)
                    add(new CardUTurn());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDBACKUP; i++)
                    add(new CardBackUp());
                for (int i = 0; i < ProgrammingDeck.NUMBER_OF_CARDPOWERUP; i++)
                    add(new CardPowerUp());
            }
        };
    }

    public void shuffle(ArrayList<Card> cards) {
        Collections.shuffle(cards);
    }


    public static void main(String[] args) {
//        ProgrammingDeck pd = new ProgrammingDeck(new Player());
//        for (Card c : pd.getCards()) {
//            System.out.println(c.getClass().getSimpleName());
//        }
//        System.out.println("=================================");
//        pd.shuffle(pd.getCards());
//        for (Card c : pd.getCards()) {
//            System.out.println(c.getClass().getSimpleName());
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("four");

        System.out.println(strings);   // prints [one, two, three, four]

//        strings.subList(1, 3);
//
//        System.out.println(strings.subList(1, 3));
//        System.out.println(strings);
//
//        System.out.println("=========================");
//
//        strings.subList(1, 3).clear();
//
//        System.out.println(strings);

        strings.subList(1, 3);

        System.out.println(strings.subList(1, 3));
        System.out.println(strings);

        System.out.println("=========================");

        strings.subList(1, 3).clear();

        System.out.println(strings);

//        ArrayList<Card> a = new ArrayList<>();
//        System.out.println(a.size());
//
//        ArrayList<Card> b = null;
//        System.out.println(b.size()); //NullPointerException
    }
}
