import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    String suit;
    String value;
    static ArrayList<Cards> Deck = new ArrayList<>();
    static ArrayList<Cards> hand1 = new ArrayList<>();
    static ArrayList<Cards> hand2 = new ArrayList<>();
    static ArrayList<Cards> hand3 = new ArrayList<>();
    static ArrayList<Cards> hand4 = new ArrayList<>();

    // Constructor
    public Cards(int suit, int value) {
        switch (suit) {
            case 0: this.suit = "Hearts"; break;
            case 1: this.suit = "Diamonds"; break;
            case 2: this.suit = "Clubs"; break;
            case 3: this.suit = "Spades"; break;
            default: throw new IllegalArgumentException("Invalid suit index");
        }

        switch (value) {
            case 0: this.value = "Ace"; break;
            case 1: this.value = "2"; break;
            case 2: this.value = "3"; break;
            case 3: this.value = "4"; break;
            case 4: this.value = "5"; break;
            case 5: this.value = "6"; break;
            case 6: this.value = "7"; break;
            case 7: this.value = "8"; break;
            case 8: this.value = "9"; break;
            case 9: this.value = "10"; break;
            case 10: this.value = "Jack"; break;
            case 11: this.value = "Queen"; break;
            case 12: this.value = "King"; break;
            default: throw new IllegalArgumentException("Invalid value index");
        }
    }

    public String toString() {
        return value + " of " + suit;
    }

    // Build full deck
    public static void createDeck() {
        Deck.clear();
        for (int s = 0; s < 4; s++) {
            for (int v = 0; v < 13; v++) {
                Deck.add(new Cards(s, v));
            }
        }
    }

    // Shuffle deck
    public static void shuffleDeck() {
        Collections.shuffle(Deck);
    }

    // Deal evenly into 4 hands
    public static void dealHands() {
        hand1.clear();
        hand2.clear();
        hand3.clear();
        hand4.clear();

        for (int i = 0; i < Deck.size(); i++) {
            Cards card = Deck.get(i);
            switch (i % 4) {
                case 0: hand1.add(card); break;
                case 1: hand2.add(card); break;
                case 2: hand3.add(card); break;
                case 3: hand4.add(card); break;
            }
        }
    }

    // Quick test
    public static void main(String[] args) {
        createDeck();
        shuffleDeck();
        dealHands();

        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
        System.out.println("Hand 3: " + hand3);
        System.out.println("Hand 4: " + hand4);
    }
}
