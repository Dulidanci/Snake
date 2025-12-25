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
        Direction nextDirection = (direction.getOpposite() != this.snake.getFirst().direction() && direction != Direction.NULL)
                ? direction
                : this.snake.getFirst().direction();

        Cells nextCell = board.getCell(this.snake.getFirst().position().add(nextDirection.getVector()));
        switch (nextCell) {
            case EMPTY -> {
                // replace old head Cell
                this.board.setCell(this.snake.getFirst().position(), Cells.getSnakePart(nextDirection, this.snake.getFirst().direction().getOpposite()));
                // add new head position
                this.snake.addFirst(new SnakePart(this.snake.getFirst().position().add(nextDirection.getVector()), nextDirection));
                // replace new head Cell
                this.board.setCell(this.snake.getFirst().position(), Cells.SNAKE_HEAD);
                // replace tail Cell
                this.board.setCell(this.snake.getLast().position(), Cells.EMPTY);
                // remove Tail
                this.snake.removeLast();
            }
            case APPLE -> {
                // replace old head Cell
                this.board.setCell(this.snake.getFirst().position(), Cells.getSnakePart(nextDirection, this.snake.getFirst().direction().getOpposite()));
                // add new head position
                this.snake.addFirst(new SnakePart(this.snake.getFirst().position().add(nextDirection.getVector()), nextDirection));
                // replace new head Cell
                this.board.setCell(this.snake.getFirst().position(), Cells.SNAKE_HEAD);
            }
            default -> board.death();
        }
    }
}
