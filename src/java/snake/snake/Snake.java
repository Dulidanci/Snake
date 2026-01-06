package snake.snake;

import snake.map.Board;
import snake.map.Cells;

import java.util.ArrayList;

public class Snake {
    public final Board board;
    public final ArrayList<SnakePart> snake = new ArrayList<>();

    public Snake(Board board, Position head, Direction direction, int length) {
        this.board = board;

        for (int i = 0; i <= Math.max(length, 0); i++) {
            if (board.getCell(head.add(direction.getOpposite().getVector().multiply(i))) != Cells.EMPTY) {
                break;
            }
            this.snake.add(new SnakePart(head.add(direction.getOpposite().getVector().multiply(i)), direction));
        }
    }

    public void step(Direction direction) {
        if (this.snake.isEmpty()) {
            this.board.death();
            return;
        }
        Direction nextDirection = (direction.getOpposite() != this.snake.get(0).direction() && direction != Direction.NULL)
                ? direction
                : this.snake.get(0).direction();

        Cells nextCell = board.getCell(this.snake.get(0).position().add(nextDirection.getVector()));
        switch (nextCell) {
            case EMPTY -> {
                moveHead(nextDirection);
                moveTail();
            }
            case APPLE -> {
                moveHead(nextDirection);
                this.board.removeApple(this.snake.get(0).position());
            }
            default -> board.death();
        }
    }

    public void moveHead(Direction nextDirection) {
        // replace old head Cell
        this.board.setCell(this.snake.get(0).position(), Cells.getSnakePart(nextDirection, this.snake.get(0).direction().getOpposite()));
        // add new head position
        this.snake.add(0, new SnakePart(this.snake.get(0).position().add(nextDirection.getVector()), nextDirection));
        // replace new head Cell
        this.board.setCell(this.snake.get(0).position(), Cells.SNAKE_HEAD);
    }

    public void moveTail() {
        // replace tail Cell
        this.board.setCell(this.snake.get(this.snake.size() - 1).position(), Cells.EMPTY);
        // remove Tail
        this.snake.remove(this.snake.size() - 1);
    }
}
