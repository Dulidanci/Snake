package snake.map;

import snake.snake.Direction;

public enum Cells {
    EMPTY(' '),
    DEBUG('¤'),
    BORDER_TOP(9604),
    BORDER_BOTTOM(9600),
    BORDER_LEFT_OR_RIGHT(9608),      //left:9612 and right:9616
    SNAKE_LEFT_RIGHT(9552),
    SNAKE_TOP_BOTTOM(9553),
    SNAKE_BOTTOM_RIGHT(9556),
    SNAKE_BOTTOM_LEFT(9559),
    SNAKE_TOP_RIGHT(9562),
    SNAKE_TOP_LEFT(9565),
    SNAKE_HEAD(9632),
    SNAKE_DEAD('#'),
    APPLE('@');

    private final int char_dec_value;

    Cells(int char_dec_value) {
        this.char_dec_value = char_dec_value;
    }

    public int getChar_dec_value() {
        return char_dec_value;
    }

    public static Cells getSnakePart(Direction first, Direction second) {
        if (first == null || second == null) {
            return DEBUG;
        }
        return switch (first) {
            case UP -> switch (second) {
                case RIGHT -> SNAKE_TOP_RIGHT;
                case DOWN -> SNAKE_TOP_BOTTOM;
                case LEFT -> SNAKE_TOP_LEFT;
                default -> DEBUG;
            };
            case RIGHT -> switch (second) {
                case DOWN -> SNAKE_BOTTOM_RIGHT;
                case LEFT -> SNAKE_LEFT_RIGHT;
                case UP -> SNAKE_TOP_RIGHT;
                default -> DEBUG;
            };
            case DOWN -> switch (second) {
                case LEFT -> SNAKE_BOTTOM_LEFT;
                case UP -> SNAKE_TOP_BOTTOM;
                case RIGHT -> SNAKE_BOTTOM_RIGHT;
                default -> DEBUG;
            };
            case LEFT -> switch (second) {
                case UP -> SNAKE_TOP_LEFT;
                case RIGHT -> SNAKE_LEFT_RIGHT;
                case DOWN -> SNAKE_BOTTOM_LEFT;
                default -> DEBUG;
            };
            default -> DEBUG;
        };
    }
}
