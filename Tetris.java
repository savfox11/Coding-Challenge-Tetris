//imports
import java.awt.*;
import javax.swing.*;

//Main Class
public class Tetris extends JFrame {
    public Tetris() {
        //Set Frame Title
        setTitle("Coding Challenge: Tetris");
        //Close on exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set Frame Size
        setSize(600, 650);
        //Load Frame in center of screen
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Call Game
        Game game = new Game();
        add(game, BorderLayout.CENTER);

        //call the side info panel
        SidePanel sidePanel = new SidePanel(gamePanel);
        add(sidePanel, BorderLayout.EAST);

        //Set the frame to visible
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Tetris();
    }
}
