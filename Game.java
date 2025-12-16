//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


//Game Class
class Game extends JPanel {
    //All objetcs------------------------------------------------------------------------------
    //Game table------------------------
    public static final int tileSize = 30;
    public static final int rows = 20;
    public static final int columns = 10;
    //Game items------------------------
    //Colors added
    Color pastelGreen = new Color(119, 221, 119); 
    Color pastelLavender = new Color(236, 227, 252); 
    Color pastelYellow = new Color(250, 248, 223); 
    Color pastelPurple = new Color(131, 174, 242);
    Color darkPink = new Color(148, 33, 106);
    //Colours to be used for pieces
    private Color[] colours = {
            Color.cyan, pastelLavender, Color.orange, pastelGreen, Color.red, pastelYellow, pastelPurple
    };
    //Create the shape for peices
    private int[][][] shapes = {
            // O
            {{1, 1}, {1, 1}},
            // I
            {{1, 1, 1, 1}},
            // T
            {{0, 1, 0}, {1, 1, 1}},
            // L
            {{1, 0}, {1, 0}, {1, 1}},
            // J
            {{0, 1}, {0, 1}, {1, 1}},
            // S
            {{0, 1, 1}, {1, 1, 0}},
            // Z
            {{1, 1, 0}, {0, 1, 1}}
    };
    //Game metrics----------------------
    private int score = 0;
    private int level = 1;
    private int linesCleared = 0;

    //Constucter----------------------------------------------------------------------------


    //Game Visuals--------------------------------------------------------------------------
    //Board
    private void drawBoard(Graphics g) {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                if (board[row][col] != 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                }
    }
    //Grid on board
    private void drawGrid(Graphics g) {
        g.setColor(Color.magenta);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                g.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
    }

    //pieces
    private void drawPiece(Graphics g) {
        g.setColor(pieceColor);
        for (int row = 0; row < piece.length; row++)
            for (int col = 0; col < piece[row].length; col++)
                if (piece[row][col] == 1)
                    g.fillRect((pieceX + col) * TILE_SIZE, (pieceY + row) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    //Functions-----------------------------------------------------------------------------






}