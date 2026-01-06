package snake.map;

import snake.GameInstance;
import snake.snake.Direction;
import snake.snake.Position;
import snake.snake.Snake;
import snake.snake.SnakePart;

import java.util.ArrayList;

public class Board {
    private final Cells[][] grid;
    public final int height;
    public final int width;
    public final int maxApples;
    public Snake snake;
    public ArrayList<Position> apples = new ArrayList<>();

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cells[height][width];
        this.maxApples = 3;
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
        checkApples();
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
            setCell(snake.snake.get(0).position(), Cells.SNAKE_HEAD);
        }
        for (int i = 1; i < length-1; i++) {
            setCell(snake.snake.get(i).position(), Cells.getSnakePart(snake.snake.get(i).direction(), snake.snake.get(i + 1).direction().getOpposite()));
        }
        setCell(snake.snake.get(snake.snake.size() - 1).position(), Cells.getSnakePart(snake.snake.get(snake.snake.size() - 1).direction(), snake.snake.get(snake.snake.size() - 1).direction().getOpposite()));
    }

    public void addApple(int count) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = (int) (Math.random() * (this.width - 2) + 1);
                y = (int) (Math.random() * (this.height - 2) + 1);
            } while (this.getCell(new Position(x, y)) != Cells.EMPTY);
            apples.add(new Position(x, y));
            this.setCell(new Position(x, y), Cells.APPLE);
        }
    }

    public void removeApple(Position position) {
        apples.remove(position);
    }

    public void checkApples() {
        if (apples.size() < maxApples) {
            addApple(maxApples - apples.size());
        }
    }

    public void death() {
        for (SnakePart snakePart : this.snake.snake) {
            setCell(snakePart.position(), Cells.SNAKE_DEAD);
        }
        GameInstance.getInstance().stop();
    }
}
