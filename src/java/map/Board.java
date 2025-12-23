package map;

public class Board {
    public Cells[][] grid;
    public final int height;
    public final int width;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cells[height][width];
        prepareBoard();
    }

    public void prepareBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = Cells.EMPTY;
            }
        }
        for (int i = 0; i < width; i++) {
            grid[0][i] = Cells.BORDER_TOP;
            grid[height-1][i] = Cells.BORDER_BOTTOM;
        }
        for (int i = 1; i < height-1; i++) {
            grid[i][0] = Cells.BORDER_LEFT_OR_RIGHT;
            grid[i][width-1] = Cells.BORDER_LEFT_OR_RIGHT;
        }

        grid[5][4] = Cells.APPLE;
        grid[5][5] = Cells.SNAKE_HEAD;
        grid[5][6] = Cells.SNAKE_LEFT_RIGHT;
        grid[5][7] = Cells.SNAKE_BOTTOM_LEFT;
        grid[6][7] = Cells.SNAKE_TOP_RIGHT;
        grid[6][8] = Cells.SNAKE_TOP_LEFT;
        grid[5][8] = Cells.SNAKE_TOP_BOTTOM;
        grid[4][8] = Cells.SNAKE_BOTTOM_RIGHT;

        printBoard();
    }

    public String printBoard() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.append((char) grid[i][j].getChar_dec_value());
            }
            output.append('\n');
        }
        System.out.println(output);
        return output.toString();
    }
}
