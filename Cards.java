import java.util.ArrayList;
import java.util.Collections;
public class Cards {
    String suit;
    String value;
    static ArrayList<Cards> Deck = new ArrayList<Cards>();
    static ArrayList<Cards> hand1 = new ArrayList<Cards>();
    static ArrayList<Cards> hand2 = new ArrayList<Cards>();
    static ArrayList<Cards> hand3 = new ArrayList<Cards>();
    static ArrayList<Cards> hand4 = new ArrayList<Cards>();
    //constructor and a lot of values
    public Cards(int suit, int value){
        if (suit < 4) {
            if (suit == 0) {
                this.suit = "Hearts";
            }
            if (suit == 1) {
                this.suit = "Diamonds";
            }
            if (suit == 2) {
                this.suit = "Clubs";
            }
            if (suit == 3) {
                this.suit = "Spades";
            }
        } else {
            System.err.println("Out of bounds suits error");
        }
        if (value < 13) {
            if (value == 0) {
                this.value = "Ace";
            }
            if (value == 1) {
                this.value = "2";
            }
            if (value == 2) {
                this.value = "3";
            }
            if (value == 3) {
                this.value = "4";
            }
            if (value == 4) {
                this.value = "5";
            }
            if (value == 5) {
                this.value = "6";
            }
            if (value == 6) {
                this.value = "7";
            }
            if (value == 7) {
                this.value = "8";
            }
            if (value == 8) {
                this.value = "9";
            }
            if (value == 9) {
                this.value = "10";
            }
            if (value == 10) {
                this.value = "Jack";
            }
            if (value == 11) {
                this.value = "Queen";
            }
            if (value == 12) {
                this.value = "King";
            }
        } else {
            System.err.println("Out of bounds value error");
        }
    }
    public String toString(){
        return value + " of " + suit;
    }
    public static void createDeck(int tempSuit, int tempValue){
        tempSuit = 0;
        tempValue = 0;
        while (tempSuit < 4) {
            if (tempValue < 13) {
                Deck.add(new Cards(tempSuit, tempValue));
                tempValue++;
            } else {
                tempSuit++;
                tempValue = 0;
            }
        }
    }
    public static void shuffleDeck(){
        Collections.shuffle(Deck);
    }
    public static void dealHands(){
        int cardIndex = 0;
        int handIndex = 0;
        while (cardIndex > 52) {
            while (handIndex == 0) {
                hand1.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 12) {
                    handIndex++;
                }
            }
            while (handIndex == 1) {
                hand2.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 25) {
                    handIndex++;
                }
            }
            while (handIndex == 2) {
                hand3.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 38) {
                    handIndex++;
                }
            }
            while (handIndex == 3) {
                hand4.add(Deck.get(cardIndex));
                cardIndex++;
            }
        }
    }
}