import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("запускаем игру...");
        JFrame window = new JFrame( "Крестики нолики");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        tictac game = new tictac();
        window.add(game);
        window.setVisible(true);
        System.out.println("game over...");

    }
}


