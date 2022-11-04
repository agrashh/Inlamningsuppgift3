package game;
/**
 * Starts a new game with given ROWS, COLS.
 * */
public class Game {
    private final static int ROWS = 4;
    private final static int COLS = 4;

    public static void main(String[] args) {
        Board board = new Board(ROWS, COLS);
        BoardView view = new BoardView();
        BoardController controller = new BoardController(board, view);
        controller.initiateController();
    }
}
