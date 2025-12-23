package map;

public enum Cells {
    EMPTY(' '),
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
    APPLE('@');

    private final int char_dec_value;

    Cells(int char_dec_value) {
        this.char_dec_value = char_dec_value;
    }

    public int getChar_dec_value() {
        return char_dec_value;
    }
}
