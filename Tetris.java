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

        //Call Game and SideInfo
        Game game = new Game();
        SideInfo sideInfo = new SideInfo(game);
        //Connect SideIndo to Game
        game.setSideInfo(sideInfo);

        add(game, BorderLayout.CENTER);
        add(sideInfo, BorderLayout.EAST);
        
        
        

        //Set the frame to visible
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Tetris();
    }
}
