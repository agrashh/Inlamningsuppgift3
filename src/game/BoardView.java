package game;

import javax.swing.*;
import java.awt.*;

public class BoardView {
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JButton newGameButton;
    private final JButton testButton;
    private final JLabel movesLabel;

    /**
     * View of the game, one panel for the board and one panel for the new game button and moves-counter.
     * */
    public BoardView() {
        // board panel
        boardPanel = new JPanel();

        // menu panel
        JPanel menuPanel = new JPanel();
        newGameButton = new JButton("New Game");
        menuPanel.add(newGameButton);
        testButton = new JButton("Test");
        menuPanel.add(testButton);
        menuPanel.add(new JLabel("Moves: "));
        movesLabel = new JLabel("");
        menuPanel.add(movesLabel);

        // frame
        frame = new JFrame("Puzzle");
        frame.add(boardPanel);
        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }
    public JLabel getMovesLabel() {
        return movesLabel;
    }

    public JButton getTestButton() {
        return testButton;
    }
}
