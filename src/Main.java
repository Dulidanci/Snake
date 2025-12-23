public class Main {
    public static void main(String[] args) {
        System.out.print("Hello and welcome!");

        for (int i = 1; i <= 8; i++) {
            System.out.println("i = " + i);
        }

        Snake kigyo = new Snake(1, 4);
        Snake kigyo2 = new Snake(-10, 1);

        TurboSnake kigyoTurbo = new TurboSnake(5, 3);
        kigyoTurbo.multiplier = 5;

        System.out.println(kigyoTurbo.result());

        if (kigyo instanceof TurboSnake turboSnake) {
            turboSnake.multiplier = 5;
        }


        Snake.setK('{');
        System.out.println(Snake.getK());

        kigyo.step();

        System.out.println(kigyo2.result());
    }
}