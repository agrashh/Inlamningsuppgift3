package game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The board class is a 2 dimensional array of integers with added row and column as borders.
 *  [-1, -1, -1, -1, -1, -1]
 *  [-1, x,  x,   x,  x, -1]
 *  [-1, x,  x,   x,  x, -1]         <-- A 4x4 board
 *  [-1, x,  x,   x,  x, -1]
 *  [-1, x,  x,   x,  x, -1]
 *  [-1, -1, -1, -1, -1, -1]
 */
public class Board {
    private final static int PADDING = 2;
    private final int rows;
    private final int cols;
    private final int size;
    private int[][] tiles;
    private ArrayList<Integer> numbers;
    private int emptyRow;
    private int emptyCol;

    /**
     * Default constructor.
     * Padding is added to create a border around the actual tiles
     * to prevent index out of bounds when searching for neighbours.
     */
    public Board(int rows, int cols) {
        size = rows * cols;
        this.rows = rows + PADDING; // padding
        this.cols = cols + PADDING; // padding

        newBoard();
        print(); // to validate that the buttons represent the actual board
    }

    /**
     * Generates a new board from generated numbers.
     * Shuffles number list.
     */
    public void newBoard() {
        generateNumbers();
        Collections.shuffle(numbers);
        generateTiles();
    }

    /**
     * Generates an easily winnable board
     */
    public void newWinBoard() {
        generateWin();
        generateTiles();
    }

    /**
     * Generates an ArrayList of numbers in range [0-size)
     */
    private void generateNumbers() {
        numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
    }
    /**
     * Adds numbers in order except last two tiles.
     * For creating easy win.
     */
    private void generateWin() {
        numbers = new ArrayList<>();
        for (int i = 1; i < size - 1; i++) {
            numbers.add(i);
        }
        numbers.add(0);
        numbers.add(size - 1);
    }

    /**
     * Creates tiles from list of numbers.
     * Makes border tiles to -1
     * Saves index of the empty tile (emptyRow, emptyCol)
     */
    private void generateTiles() {
        tiles = new int[rows][cols];
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) { // border
                    tiles[row][col] = -1;
                } else {
                    if (numbers.get(index) == 0) {
                        emptyRow = row;
                        emptyCol = col;
                    }
                    tiles[row][col] = numbers.get(index);
                    index++;
                }
            }
        }
    }

    /**
     * Checks if tile at (row, col) is a neighbour to the empty tile
     */
    public boolean isNeighbour(int row, int col) {
        return getTileAt(row + 1, col) == 0 || getTileAt(row - 1, col) == 0 ||
                getTileAt(row, col + 1) == 0 || getTileAt(row, col - 1) == 0;
    }

    /**
     * Swap position of two tiles.
     */
    public void swap(int row, int col) {
        int temp = getTileAt(row, col);

        setTileAt(emptyRow, emptyCol, temp);
        setTileAt(row, col, 0);
        emptyRow = row;
        emptyCol = col;
    }

    /**
     * For model-view relationship validation.
     */
    public void print() {
        for (int row = 1; row < rows - 1; row++) {
            System.out.println();
            for (int col = 1; col < cols - 1; col++) {
                System.out.print("(" + tiles[row][col] + ")");
            }
        }
        System.out.println();
    }

    /**
     * Getters and setters.
     */
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getSize() {
        return size;
    }

    public int getTileAt(int row, int col) {
        return tiles[row][col];
    }

    public void setTileAt(int row, int col, int number) {
        tiles[row][col] = number;
    }
}
