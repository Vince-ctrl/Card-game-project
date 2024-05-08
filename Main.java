public class Main {
    public static void main(String[] args) {
        Cards.createDeck(0, 0);
        Cards.shuffleDeck();
        System.out.println(Cards.Deck);
        Cards.dealHands();
        System.out.println(Cards.hand1);
        System.out.println(Cards.hand2);
    }
} 