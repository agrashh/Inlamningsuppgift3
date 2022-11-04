package game;

import javax.swing.*;
import java.awt.*;

public class BoardController {
    private final Board board;
    private final BoardView view;
    private TileButton emptyButton;
    private int moves;

    /**
     * Constructor, initiates new game button and test button with listeners.
     * */
    public BoardController(Board board, BoardView view) {
        this.board = board;
        this.view = view;
        initiateMenuButtons();
    }
    /**
     * Sets
     * */
    private void initiateMenuButtons() {
        view.getNewGameButton().addActionListener(e -> newGame());
        view.getTestButton().addActionListener(e -> testGame());
    }
    /**
     * Sets the layout of the board panel
     * Adds actionListener to 'New Game' button
     * Removes any existing components
     * Makes buttons from board given to constructor.
     * */
    public void initiateController() {
        view.getBoardPanel().setLayout(new GridLayout(board.getRows()-2, board.getCols()-2));
        resetBoardPanel(); // remove components
        makeButtonsFromBoard(); // make new buttons
        view.getBoardPanel().revalidate();
        view.getBoardPanel().repaint();
    }
    /**
     * Removes buttons from board panel.
     * */
    private void resetBoardPanel() {
        for (Component c : view.getBoardPanel().getComponents()) {
            view.getBoardPanel().remove(c);
        }
    }
    /**
     * Generates buttons from board given in constructor arguments.
     * Does not create buttons of borders (-1).
     * Saves empty button.
     * Adds actionListeners to buttons so they swap when pressed.
     * Sets size of frame and location after buttons are added.
     * */
    private void makeButtonsFromBoard() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.getTileAt(row, col) != -1) { // border
                    int temp = board.getTileAt(row, col);
                    TileButton button = new TileButton(String.valueOf(temp));
                    if (temp == 0) {
                    emptyButton = button;
                    }
                    int finalRow = row;
                    int finalCol = col;
                    button.addActionListener(e -> swap(finalRow, finalCol, button));
                    view.getBoardPanel().add(button);
                }
            }
        }
        view.getFrame().setSize(800,600);
        view.getFrame().setLocationRelativeTo(null);
    }
    /**
     * Increment the amount of moves player has done.
     * */
    private void incrementMoves() {
        moves++;
        view.getMovesLabel().setText(String.valueOf(moves));
    }
    /**
     * Returns true if every button is in the right order [1,2,3,n,n+1,..,0]
     * */
    private boolean gameWon() {
        boolean gameWon = false;
        for (int i = 0; i < board.getSize()-1; i++) {
            TileButton b = (TileButton) view.getBoardPanel().getComponent(i);
            if (b.getText().equals(String.valueOf(i + 1))) {
                gameWon = true;
            } else {
                gameWon = false;
                break;
            }
        }
        return gameWon;
    }
    /**
     * Creates a new board, initiates the buttons and sets the moves to 0.
     * */
    private void newGame() {
        board.newBoard(); // create new board
        initiateController(); // initiate board with new buttons and listeners
        moves = 0; // set moves to 0
        view.getMovesLabel().setText(String.valueOf(moves)); // set move label to 0
        board.print(); // model-view validation
    }
    private void testGame() {
        board.newWinBoard();
        initiateController();
        moves = 0;
        view.getMovesLabel().setText(String.valueOf(moves));
        board.print();
    }

    /**
     * Every time a button is pressed, this function is called with the tiles position and the button pressed.
     * Checks if the tile pressed is a neighbour to the empty tile.
     * If it is a neighbour they swap on the model, then the buttons swap appearances.
     * */
    private void swap(int row, int col, TileButton button) {
        if(board.isNeighbour(row, col)) {
            incrementMoves();
            board.swap(row, col); // swap board model

            // Set the pressed button to the empty button
            String temp = button.getText(); // save pressed button text
            button.setText("0");
            button.setBackground(Color.WHITE);
            button.setForeground(Color.WHITE);

            // Set the empty button to the pressed button
            emptyButton.setText(temp);
            emptyButton.setForeground(Color.WHITE);
            emptyButton.setBackground((new Color(120, 10,10)));
            emptyButton = button;
            board.print(); // model-view validation

            // If game is won
            if (gameWon()) {
                JOptionPane.showMessageDialog(view.getFrame(), "YOU WON! MOVES: "+ view.getMovesLabel().getText());

                // Ask user if it wants to play again
                int input = JOptionPane.showConfirmDialog(view.getFrame(), "Play again?");
                switch (input) {
                    case (JOptionPane.YES_OPTION) -> newGame();
                    case (JOptionPane.NO_OPTION), (JOptionPane.CANCEL_OPTION), (JOptionPane.CLOSED_OPTION) ->
                            System.exit(0);
                }
            }
        }

    }
}
