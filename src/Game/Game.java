package Game;

import Display.Display;
import Game.Level.Level;
import IO.Input;
import Utils.Time;

import java.awt.*;
import Graphics.*;

/**
 * Created by Mocki on 02.11.2016.
 */

public class Game implements Runnable{

    public static final int         WIDTH           = 800;
    public static final int         HEIGHT          = 592;
    public static final String      TITLE           = "Tanks";
    public static final int         CLEAR_COLOR     = 0xff000000;
    public static final int         NUM_BUFFERS     = 3;

    public static final float       UPDATE_RATE     = 60.0f;
    public static final float       UDPATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long        IDLE_TIME       = 1;
    public static final String      ATLAS_FILE_NAME = "texture_atlas.png";

    private boolean                 running;
    private Thread                  gameThread;
    private Graphics2D              graphics2D;
    private Input                   input;
    private TextureAtlas   			atlas;
	private Player					player;
	private Level					lvl;

    public Game() {
        running = false;
        Display.created(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics2D = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
		player = new Player(300, 300, 2, 3, atlas);
		lvl = new Level(atlas);
    }

    public synchronized void statr() {

        if (running)
            return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    public synchronized void stop() {

        if (!running)
            return;

        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();

    }

    private void update() {
		player.update(input);
		lvl.update();
    }

    private void render() {

        Display.clear();
		lvl.render(graphics2D);
		player.render(graphics2D);
        lvl.renderGrass(graphics2D);
        Display.swapBuffers();

    }

    private void cleanUp() {
        Display.destroy();
    }

    @Override
    public void run() {

        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.getSecond();
        while (running) {
            long now = Time.getSecond();
            long elapsTime = now - lastTime;
            lastTime = now;

            count += elapsTime;

            boolean render = false;
            delta += ( elapsTime / UDPATE_INTERVAL );
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render)
                    updl++;
                else
                render = true;
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || fps: " + fps + " | upd: " + upd + " | updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }
        }
    }
}

