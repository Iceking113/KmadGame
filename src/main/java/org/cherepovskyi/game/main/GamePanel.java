package org.cherepovskyi.game.main;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.entity.Player;
import org.cherepovskyi.game.object.SuperObject;
import org.cherepovskyi.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32 px
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = /*fulScreenValues("Width")*/ tileSize * maxScreenCol;
    public final int screenHeight = /*fulScreenValues("Height")*/ tileSize * maxScreenRow;

    //WORLD SATTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssertSetter aSetter = new AssertSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT AND NPC
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[26];

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setOjgect();
        aSetter.setNPC();
        gameState = titleState;
    }
    public final int fulScreenValues(String wigthOrHight){
        int returnValue = 0;
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        if(Objects.equals(wigthOrHight, "Width")) returnValue = (int) size.getWidth();
        if(Objects.equals(wigthOrHight, "Height")) returnValue = (int) size.getHeight();
        return returnValue;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        /*double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

            }
        }*/
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;

            lastTime = currentTime;
            if(delta>1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if(gameState == playState){
            //PLAYER
            player.update();
            //NPC
            for(Entity npcIn : npc) {
                if(npcIn != null) {
                    npcIn.update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
        if(gameState == titleState){
            //nothing
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // TITLE SCRIN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            //TILE
            tileM.draw(g2);
            //OBJECT
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            //NPC
            for(Entity npcIn : npc) {
                if(npcIn != null) {
                    npcIn.draw(g2);
                }
            }

            //PLAYER
            player.draw(g2);

            //UI
            ui.draw(g2);
        }

        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
