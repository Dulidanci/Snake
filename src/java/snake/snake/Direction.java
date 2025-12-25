package snake.snake;

public enum Direction {
    UP(new Position(0, -1)),
    DOWN(new Position(0, 1)),
    LEFT(new Position(-1, 0)),
    RIGHT(new Position(1, 0)),
    NULL(new Position(0, 0));

    private final Position vector;

    Direction(Position vector) {
        this.vector = vector;
    }

    public Position getVector() {
        return vector;
    }

    public static Direction fromChar(int char_value) {
        return switch (char_value) {
            case 'w' -> UP;
            case 'a' -> LEFT;
            case 's' -> DOWN;
            case 'd' -> RIGHT;
            default -> NULL;
        };
    }

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case LEFT -> RIGHT;
            case DOWN -> UP;
            case RIGHT -> LEFT;
            default -> NULL;
        };
    }
}
