import java.io.*;
import java.net.*;
import javax.swing.*;

public class CardGameClient {
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public CardGameClient(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        JFrame frame = new JFrame("Online Card Game");
        JButton drawButton = new JButton("Draw Card");
        JLabel resultLabel = new JLabel("Waiting...", SwingConstants.CENTER);

        drawButton.addActionListener(e -> {
            try {
                out.writeObject("DRAW");
                out.flush();
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        // Thread to listen for server messages
        new Thread(() -> {
            try {
                while (true) {
                    String msg = (String) in.readObject();
                    resultLabel.setText(msg);
                }
            } catch (Exception e) {
                resultLabel.setText("Disconnected.");
            }
        }).start();

        frame.add(resultLabel, "Center");
        frame.add(drawButton, "South");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new CardGameClient("localhost", 12345);
    }
}
