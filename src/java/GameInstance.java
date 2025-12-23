import map.Board;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class GameInstance {
    private static GameInstance instance;
    private volatile boolean running = false;
    private Thread inputThread;
    private static final AtomicReference<String> input = new AtomicReference<>(null);
    private static final int delta = 500;
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

        if (cmd != null) {
            switch (cmd.toLowerCase().charAt(0)) {

            }
        }
    }


}
