import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cards {
    String suit;
    String value;
    static ArrayList<Cards> Deck = new ArrayList<Cards>();
    static ArrayList<Cards> hand1 = new ArrayList<Cards>();
    static ArrayList<Cards> hand2 = new ArrayList<Cards>();
    static ArrayList<Cards> hand3 = new ArrayList<Cards>();
    static ArrayList<Cards> hand4 = new ArrayList<Cards>();

    public Cards(int suit, int value) {
        if (suit < 4) {
            switch (suit) {
                case 0 -> this.suit = "Hearts";
                case 1 -> this.suit = "Diamonds";
                case 2 -> this.suit = "Clubs";
                case 3 -> this.suit = "Spades";
                default -> throw new IllegalArgumentException("Unexpected value: " + suit);
            }
        } else {
            System.err.println("Out of bounds suits error");
        }
        if (value < 13) {
            switch (value) {
                case 0 -> this.value = "Ace";
                case 1 -> this.value = "2";
                case 2 -> this.value = "3";
                case 3 -> this.value = "4";
                case 4 -> this.value = "5";
                case 5 -> this.value = "6";
                case 6 -> this.value = "7";
                case 7 -> this.value = "8";
                case 8 -> this.value = "9";
                case 9 -> this.value = "10";
                case 10 -> this.value = "Jack";
                case 11 -> this.value = "Queen";
                case 12 -> this.value = "King";
                default -> throw new IllegalArgumentException("Unexpected value: " + value);
            }
        } else {
            System.err.println("Out of bounds value error");
        }
    }

    public String toString() {
        return value + " of " + suit;
    }

    public static void createDeck(int tempSuit, int tempValue) {
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

    public static void shuffleDeck() {
        Collections.shuffle(Deck);
    }

    public static void dealHands() {
        int cardIndex = 0;
        int handIndex = 0;
        while (cardIndex < 52) {
            while (handIndex == 0 && cardIndex < 13) {
                hand1.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 13) {
                    handIndex++;
                }
            }
            while (handIndex == 1 && cardIndex < 26) {
                hand2.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 26) {
                    handIndex++;
                }
            }
            while (handIndex == 2 && cardIndex < 39) {
                hand3.add(Deck.get(cardIndex));
                cardIndex++;
                if (cardIndex == 39) {
                    handIndex++;
                }
            }
            while (handIndex == 3 && cardIndex < 52) {
                hand4.add(Deck.get(cardIndex));
                cardIndex++;
            }
        }
    }

    public static Cards getRandomCard(ArrayList<Cards> hand) {
        Random rand = new Random();
        return hand.get(rand.nextInt(hand.size()));
    }

    public static int compareCards(Cards card1, Cards card2) {
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int card1Value = 0, card2Value = 0;
        for (int i = 0; i < values.length; i++) {
            if (card1.value.equals(values[i])) card1Value = i;
            if (card2.value.equals(values[i])) card2Value = i;
        }
        return Integer.compare(card1Value, card2Value);
    }
}
