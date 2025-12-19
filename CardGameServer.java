import java.io.*;
import java.net.*;
import java.util.*;

public class CardGameServer {
    private static ArrayList<Cards> deck = new ArrayList<>();
    private static ArrayList<ObjectOutputStream> clients = new ArrayList<>();
    private static HashMap<ObjectOutputStream, Cards> currentRound = new HashMap<>();
    private static final boolean DEBUG_MODE = true; // toggle this for testing

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        System.out.println("Server started on port 12345");

        // Build and shuffle deck
        Cards.createDeck();
        deck.addAll(Cards.Deck);
        Cards.shuffleDeck();

        while (true) {
            Socket socket = server.accept();
            System.out.println("Client connected: " + socket);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            clients.add(out);

            new Thread(() -> handleClient(in, out)).start();
        }
    }

    @SuppressWarnings("unused")
    private static void handleClient(ObjectInputStream in, ObjectOutputStream out) {
        try {
            while (true) {
                String command = (String) in.readObject();
                if (command.equals("DRAW")) {
                    if (!deck.isEmpty()) {
                        Cards playerCard = deck.remove(0);
                        currentRound.put(out, playerCard);

                        if (DEBUG_MODE && clients.size() == 1) {
                            // Simulate AI opponent
                            Cards aiCard = deck.remove(0);
                            String result = compareCards(playerCard, aiCard, "Player", "AI Opponent");

                            out.writeObject(result);
                            out.flush();

                            currentRound.clear();
                        } else if (currentRound.size() == 2) {
                            // Normal multiplayer mode
                            Iterator<Map.Entry<ObjectOutputStream, Cards>> it = currentRound.entrySet().iterator();
                            Map.Entry<ObjectOutputStream, Cards> p1 = it.next();
                            Map.Entry<ObjectOutputStream, Cards> p2 = it.next();

                            String result = compareCards(p1.getValue(), p2.getValue(), "Player 1", "Player 2");

                            for (ObjectOutputStream client : clients) {
                                client.writeObject(result);
                                client.flush();
                            }
                            currentRound.clear();
                        }
                    } else {
                        out.writeObject("No more cards in deck!");
                        out.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Client disconnected.");
        }
    }

    private static String compareCards(Cards c1, Cards c2, String name1, String name2) {
        int val1 = getCardValue(c1.value);
        int val2 = getCardValue(c2.value);

        if (val1 > val2) {
            return name1 + " wins! (" + c1 + " vs " + c2 + ")";
        } else if (val2 > val1) {
            return name2 + " wins! (" + c1 + " vs " + c2 + ")";
        } else {
            return "It's a tie! (" + c1 + " vs " + c2 + ")";
        }
    }

    private static int getCardValue(String value) {
        switch (value) {
            case "Ace": return 14;
            case "King": return 13;
            case "Queen": return 12;
            case "Jack": return 11;
            default: return Integer.parseInt(value);
        }
    }
}
