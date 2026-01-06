package snake;

import snake.map.Board;
import snake.snake.Direction;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class GameInstance {
    private static GameInstance instance;
    private volatile boolean running = false;
    private Thread inputThread;
    private static final AtomicReference<String> input = new AtomicReference<>(null);
    private static final int delta = 750;
    public final int mapHeight;
    public final int mapWidth;
    public final Board board;

    private GameInstance() {
        instance = this;
        this.mapHeight = 12;
        this.mapWidth = 30;
        this.board = new Board(mapHeight, mapWidth);
    }

    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }

    public void start() {
        running = true;
        startInputThread();
        startGameLoop();
    }

    public void stop() {
        running = false;
        if (inputThread != null) {
            inputThread.interrupt();
        }
        deathScreen();
    }

    public void startInputThread() {
        inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (running) {
                String line = scanner.nextLine();
                input.set(line);
            }
        }, "inputThread");

        inputThread.setDaemon(true);
        inputThread.start();
    }

    public void startGameLoop() {
        board.printBoard();
        try {
            Thread.sleep(3 * delta);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        while (running) {
            update();
            board.printBoard();

            try {
                Thread.sleep(delta);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void update() {
        String cmd = input.getAndSet(null);

        this.board.snake.step(Direction.fromChar(
                cmd != null && !cmd.isEmpty() ? cmd.charAt(0) : 0));

        this.board.checkApples();
    }

    public void deathScreen() {
        System.out.println("Well, you died! Your score is " + (this.board.snake.snake.size() - 3) + "! GG");
    }
}
