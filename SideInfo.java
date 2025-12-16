//Imports 
import java.awt.*;
import javax.swing.*;

//Side info panel to display all relavant info
class SideInfo extends JPanel {
    //Import Game class
    private Game game;

    Color pastelPink = new Color(230, 110, 178);

    //Create Side panel
    public SideInfo(Game game) {
        this.game = game;
        //Set size
        setPreferredSize(new Dimension(200, 600));
        //set colour
        setBackground(pastelPink);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        //System.out.println("--- Coding Challenge: ---");
        //System.out.println("--- Tetris ---");
        g.drawString("Score: " + game.getScore(), 20, 50);
        g.drawString("Level: " + game.getLevel(), 20, 100);
        g.drawString("Lines: " + game.getLinesCleared(), 20, 150);

        g.drawString("Next:", 20, 200);

        int[][] next = game.getNextPiece();
        if (next != null) {
            g.setColor(game.getNextPieceColor());
            int startX = 40;
            int startY = 220;
            int tileSize = 20;
            for (int row = 0; row < next.length; row++)
                for (int col = 0; col < next[row].length; col++)
                    if (next[row][col] == 1)
                        g.fillRect(startX + col * tileSize, startY + row * tileSize, tileSize, tileSize);
        }
    }
}


