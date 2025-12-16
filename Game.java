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
    private int[][] board = new int[ROWS][COLS];
    //Game pieces------------------------
    private int[][] piece;
    private Color pieceColor;
    private int pieceX, pieceY;
    private int[][] nextPiece;
    private Color nextPieceColor;
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
            //The different shapes
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
    private boolean gameOver = false;


    //Constucter----------------------------------------------------------------------------
    public GamePanel() {
        //Set Background
        setBackground(darkPink);
        setFocusable(true);

        //Add Key Listener


    }

    //Game Visuals--------------------------------------------------------------------------
    //Board
    private void createBoard(Graphics g) {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                if (board[row][col] != 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                }
    }
    //Grid on board
    private void createGrid(Graphics g) {
        g.setColor(Color.magenta);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                g.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
    }

    //pieces
    private void createPiece(Graphics g) {
        g.setColor(pieceColor);
        for (int row = 0; row < piece.length; row++)
            for (int col = 0; col < piece[row].length; col++)
                if (piece[row][col] == 1)
                    g.fillRect((pieceX + col) * TILE_SIZE, (pieceY + row) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    //Functions-----------------------------------------------------------------------------
    //Piece Movement
    //Left 
    private void moveLeft() {
        if (!collides(piece, pieceX - 1, pieceY)) pieceX--;
    }
    //Right
    private void moveRight() {
        if (!collides(piece, pieceX + 1, pieceY)) pieceX++;
    }
    //Rotate piece
    private void rotatePiece() {
        int rows = piece.length;
        int cols = piece[0].length;
        int[][] rotated = new int[cols][rows];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                rotated[col][rows - 1 - row] = piece[row][col];

        if (!collides(rotated, pieceX, pieceY)) piece = rotated;
    }




}