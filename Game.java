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
    private int[][] board = new int[rows][columns];
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
    public Game() {
        //Set Background
        setBackground(darkPink);
        setFocusable(true);

        //Add Key Listener for movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;

                switch (e.getKeyCode()) {
                    //Arrow key pad
                    case KeyEvent.VK_LEFT -> moveLeft();
                    case KeyEvent.VK_RIGHT -> moveRight();
                    case KeyEvent.VK_UP -> rotatePiece();
                    case KeyEvent.VK_DOWN -> fastDrop();
                    //awsd
                    case KeyEvent.VK_A -> moveLeft();
                    case KeyEvent.VK_D -> moveRight();
                    case KeyEvent.VK_W -> rotatePiece();
                    case KeyEvent.VK_S -> fastDrop();
                }
                repaint();
            }
        });

        //Add timer to game
        Timer timer = new Timer(500, e -> {
            //Stop timer and game when game over is reached
            if (gameOver) {
                ((Timer) e.getSource()).stop();
                repaint();
                return;
            }

            if (!collidesBelow()) {
                pieceY++;
            } else {
                lockPiece();
                clearLines();
                spawnNewBlock();
            }
            repaint();
        });
        timer.start();

        spawnNewBlock(); // First piece
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
                    g.fillRect((pieceX + col) * tileSize, (pieceY + row) * tileSize, tileSize, tileSize);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createGrid(g);
        createBoard(g);
        createPiece(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", 20, getHeight() / 2);
        }
    }

    //Functions-----------------------------------------------------------------------------
    //Create Piece
    public void spawnNewBlock() {
        if (nextPiece == null) {
            nextPiece = shapes[(int) (Math.random() * shapes.length)];
            nextPieceColor = colours[(int) (Math.random() * colours.length)];
        }

        piece = nextPiece;
        pieceColor = nextPieceColor;
        pieceX = columns / 2 - piece[0].length / 2;
        pieceY = 0;

        if (collides(piece, pieceX, pieceY)) gameOver = true;

        nextPiece = shapes[(int) (Math.random() * shapes.length)];
        nextPieceColor = colours[(int) (Math.random() * colours.length)];
    }
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

    //Detect when piece reaches the bottom
    private boolean collidesBelow() {
        return collides(piece, pieceX, pieceY + 1);
    }
    //Detect when the piece collides with a side wall or other piece
    private boolean collides(int[][] testPiece, int x, int y) {
        for (int row = 0; row < testPiece.length; row++)
            for (int col = 0; col < testPiece[row].length; col++)
                if (testPiece[row][col] == 1) {
                    int newX = x + col;
                    int newY = y + row;
                    if (newX < 0 || newX >= columns || newY < 0 || newY >= rows || board[newY][newX] != 0)
                        return true;
                }
        return false;
    }

    //Lock a piece into place when it touched the bottom 
    private void lockPiece() {
        for (int row = 0; row < piece.length; row++)
            for (int col = 0; col < piece[row].length; col++)
                if (piece[row][col] == 1)
                    board[pieceY + row][pieceX + col] = 1;
    }

    //Detect and clear a line that has been fininshed 
    private void clearLines() {
        for (int row = rows - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0; col < columns; col++)
                if (board[row][col] == 0) {
                    full = false;
                    break;
                }

            if (full) {
                removeLine(row);
                linesCleared++;
                score += 100;
                if (linesCleared % 10 == 0) level++;
                row++; // Recheck same row after shift
            }
        }
    }
    //remove the finished line
    private void removeLine(int row) {
        for (int r = row; r > 0; r--)
            board[r] = board[r - 1].clone();
        board[0] = new int[columns];
    }

    //Fast Drop a piece into place
    private void fastDrop() {
        while (!collidesBelow()) pieceY++;
        lockPiece();
        clearLines();
        spawnNewBlock();
    }

    //Getters for Side Panel----------
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getLinesCleared() { return linesCleared; }
    public int[][] getNextPiece() { return nextPiece; }
    public Color getNextPieceColor() { return nextPieceColor; }

}