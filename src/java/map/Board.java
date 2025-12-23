package map;

public class Board {
    public Cells[][] board;

    public Board(int height, int width) {
        this.board = new Cells[height][width];
    }
}
