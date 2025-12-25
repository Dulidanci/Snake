package snake.map;

import snake.GameInstance;
import snake.snake.Direction;
import snake.snake.Position;
import snake.snake.Snake;
import snake.snake.SnakePart;

public class Board {
    private final Cells[][] grid;
    public final int height;
    public final int width;
    public Snake snake;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cells[height][width];
        prepareBoard();
    }

    public void prepareBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = Cells.EMPTY;
            }
        }
        for (int x = 0; x < width; x++) {
            grid[0][x] = Cells.BORDER_TOP;
            grid[height-1][x] = Cells.BORDER_BOTTOM;
        }
        for (int y = 1; y < height-1; y++) {
            grid[y][0] = Cells.BORDER_LEFT_OR_RIGHT;
            grid[y][width-1] = Cells.BORDER_LEFT_OR_RIGHT;
        }
        this.snake = new Snake(this, new Position(10, 6), Direction.RIGHT, 2);
        registerSnake(snake);
    }

    public void printBoard() {
        StringBuilder output = new StringBuilder();
        output.append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.append((char) grid[i][j].getChar_dec_value());
            }
            output.append('\n');
        }
        System.out.println(output);
    }

    public Cells getCell(Position position) {
        if (position.x() < 0 || position.y() < 0 || position.x() >= width || position.y() >= height) {
            return null;
        }
        return grid[position.y()][position.x()];
    }

    public void setCell(Position position, Cells cell) {
        if (position.x() < 0 || position.y() < 0 || position.x() >= width || position.y() >= height) {
            return;
        }
        grid[position.y()][position.x()] = cell;
    }

    public void registerSnake(Snake snake) {
        int length = snake.snake.size();
        if (length > 0) {
            setCell(snake.snake.getFirst().position(), Cells.SNAKE_HEAD);
        }
        for (int i = 1; i < length-1; i++) {
            setCell(snake.snake.get(i).position(), Cells.getSnakePart(snake.snake.get(i).direction(), snake.snake.get(i + 1).direction().getOpposite()));
        }
        setCell(snake.snake.getLast().position(), Cells.getSnakePart(snake.snake.getLast().direction(), snake.snake.getLast().direction().getOpposite()));
    }

    public void death() {
        for (SnakePart snakePart : this.snake.snake) {
            setCell(snakePart.position(), Cells.SNAKE_DEAD);
        }
        GameInstance.getInstance().stop();
    }
}
