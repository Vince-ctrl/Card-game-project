import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Today, we will be playing a card game. You will pick a card and it must be the highest of all of the ones picked by the opponents. Aces are the lowest, Kings the highest.");
        Cards.createDeck(0, 0);
        System.out.println(Cards.Deck);
        System.out.println("Here is your deck.");
        System.out.println("Shuffling...");
        Cards.shuffleDeck();
        System.out.println("Dealing hands...");
        Cards.dealHands();
        System.out.println("Your hand: " + Cards.hand1);

        // Display user hand with indices
        for (int i = 0; i < Cards.hand1.size(); i++) {
            System.out.println((i + 1) + ": " + Cards.hand1.get(i));
        }

        // User selects a card
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select the number of the card you want to play: ");
        int selectedIndex = scanner.nextInt() - 1;
        Cards userCard = Cards.hand1.get(selectedIndex);
        System.out.println("Your selected card: " + userCard);
        scanner.close();

        // Pick a random card from hands 2-4
        Cards card2 = Cards.getRandomCard(Cards.hand2);
        Cards card3 = Cards.getRandomCard(Cards.hand3);
        Cards card4 = Cards.getRandomCard(Cards.hand4);

        // Print the randomly picked cards
        System.out.println("Opponent 1's card: " + card2);
        System.out.println("Opponent 2's card: " + card3);
        System.out.println("Opponent 3's card: " + card4);

        // Compare cards
        boolean userWins = true;
        if (Cards.compareCards(userCard, card2) <= 0) userWins = false;
        if (Cards.compareCards(userCard, card3) <= 0) userWins = false;
        if (Cards.compareCards(userCard, card4) <= 0) userWins = false;

        if (userWins) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("Sorry! You lose.");
        }
    }
}
