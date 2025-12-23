public class Snake {
    public int position;
    public int length;
    private static char k = '[';

    public Snake(int position, int length) {
        this.position = position;
        this.length = length;
    }

    public Snake(int length) {
        this.position = 0;
        this.length = length;
    }

    public void step() {
        while (length > 0) {
            length--;
            position++;
        }
    }

    public int result() {
        if (length > 0) {
            this.step();
        }
        return position;
    }

    public static char getK() {
        return k;
    }

    public static void setK(char k) {
        Snake.k = k;
    }
}
