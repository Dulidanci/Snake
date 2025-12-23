import map.Board;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class GameInstance {
    private static GameInstance instance;
    private volatile boolean running = false;
    private Thread inputThread;
    private static final AtomicReference<String> input = new AtomicReference<>(null);
    public final int mapHeight;
    public final int mapWidth;
    public final Board board;

    private GameInstance() {
        instance = this;
        this.mapHeight = 40;
        this.mapWidth = 80;
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
}
