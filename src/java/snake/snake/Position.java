package snake.snake;

public record Position(int x, int y) {
    public Position add(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public Position multiply(int constant) {
        return new Position(x * constant, y * constant);
    }
}
