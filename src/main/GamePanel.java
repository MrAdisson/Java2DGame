package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    protected final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    //WORLD SETTINGS
    protected final int maxWorldCol = 50;
    protected final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //SOUND
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    //MAP
    TileManager tileManager = new TileManager(this);
    protected AssetSetter assetSetter = new AssetSetter(this);
    //UI
    UI ui = new UI(this);
    //SYSTEM
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //FPS
    int FPS = 60;

    //PLAYER and Objeects
    Player player = new Player(this, keyH);
    protected SuperObject[] objectsArray = new SuperObject[10];
    CollisionChecker collisionChecker = new CollisionChecker(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void setupGame(){
        assetSetter.setObjects();
    }


    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
        playMusic(0);
    }

    @Override
    public void run() {
        System.out.println("Game is running");

        long lastTime = System.nanoTime();
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // GAME LOOP (DELTA)
        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (timer >= 1000000000){
                System.out.println("FPS = " + drawCount);
                System.out.println("Coordinate -> X : " + player.getWorldX() / tileSize + " | Y : " + player.getWorldY() / tileSize);
                drawCount = 0;
                timer = 0;
            }

            if (delta >= 1) {
                //update infos like character position
                update();
                //draw on screen
                repaint();
                //DELTA RESET
                delta--;
                drawCount++;
            }


        }
    }

    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        for (int i = 0; i < objectsArray.length; i++) {
            if (objectsArray[i] != null){
                objectsArray[i].draw(g2, this);
            }

        }
        player.draw(g2);
        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }



    //GETTERS SETTERS

    public int getTileSize() {
        return tileSize;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public SuperObject[] getObjectsArray() {
        return objectsArray;
    }

    public UI getUi() {
        return ui;
    }
}
