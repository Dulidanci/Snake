package snake;

public class TurboSnake extends Snake {
    public int multiplier;

    public TurboSnake(int position, int length, int multiplier) {
        super(position, length);
        this.multiplier = multiplier;
    }

    public TurboSnake(int length, int multiplier) {
        super(length);
        this.multiplier = multiplier;
    }

    @Override
    public void step() {
        while (length > 0) {
            length--;
            position += multiplier;
        }
    }
}
