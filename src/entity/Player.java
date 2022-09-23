package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Pickable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    private GamePanel gp;
    private KeyHandler kh;
    private BufferedImage image;

    protected final int screenX;
    protected final int screenY;

    protected int keys = 0;


    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        collisionArea = new Rectangle(8, 16, gp.getTileSize()-16, gp.getTileSize()-16);
        this.collisionAreaDefaultX = collisionArea.x;
        this.collisionAreaDefaultY = collisionArea.y;
        setDefaultValues();

        this.screenX = gp.getScreenWidth()/2 - gp.getTileSize()/2;
        this.screenY = gp.getScreenHeight()/2 - gp.getTileSize()/2;

        getPlayerImages();

    }

    public void getPlayerImages() {

        try{
            up1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite5.png")));
            up2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite6.png")));
            left1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite7.png")));
            left2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite8.png")));
            down1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite1.png")));
            down2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite2.png")));
            right1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite3.png")));
            right2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite4.png")));
            idleD = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite9.png")));
            idleR = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite10.png")));
            idleU = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite11.png")));
            idleL = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/RPG_Sprite12.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setDefaultValues(){
        this.worldX = 1* gp.getTileSize();
        this.worldY = 1*gp.getTileSize();
        this.direction = "idle";
        this.lastDirection = "down";
        this.speed = 4;
    }

    public void pickObject(int i){
        if (i != 999){
            if (gp.getObjectsArray()[i].isPickable()){
                gp.getObjectsArray()[i] = ((Pickable) gp.getObjectsArray()[i]).pickupAction(this);

            }

        }
    }
    public void update(){

        if(this.kh.isUpPressed()){
            this.direction = "up";
        } else if (this.kh.isDownPressed()) {
            this.direction = "down";
        } else if (this.kh.isLeftPressed()) {
            this.direction = "left";
        } else if (this.kh.isRightPressed()) {
            this.direction = "right";
        }

        collisionOn = false;
        gp.getCollisionChecker().checkTile(this);
        int objIntersectIndex = gp.getCollisionChecker().checkObject(this, true);
        pickObject(objIntersectIndex);

        if (!collisionOn){
            switch (direction){
                case "up":
                    this.worldY -= speed;
                    break;
                case "down":
                    this.worldY += speed;
                    break;
                case "left":
                    this.worldX -= speed;
                    break;
                case "right":
                    this.worldX += speed;
                    break;
            }
        }

        //ANIMATOR :
        if (this.kh.isRightPressed() ||
                this.kh.isLeftPressed() ||
                this.kh.isUpPressed() ||
                this.kh.isDownPressed()) {

            spriteCounter++;

            if(spriteCounter > 15){   // NOMBRE DE FRAMES PAR SPRITE
                if (spriteNum == 1){
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else if (!direction.equals("idle")){
            lastDirection = direction;
            direction = "idle";
        }

    }

    public void draw(Graphics2D g2) {

        switch(direction){
            case "up":
                if (this.spriteNum == 1){
                    image = up1;
                }
                if (this.spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (this.spriteNum == 1){
                    image = down1;
                }
                if (this.spriteNum == 2){
                    image = down2;
                }
                break;
            case "right":
                if (this.spriteNum == 1){
                    image = right1;
                }
                if (this.spriteNum == 2){
                    image = right2;
                }
                break;
            case "left":
                if (this.spriteNum == 1){
                    image = left1;
                }
                if (this.spriteNum == 2){
                    image = left2;
                }
                break;
            case "idle":
                if (this.lastDirection.equals("up")){
                    image = idleU;
                }
                if (this.lastDirection.equals("right")){
                    image = idleR;
                }
                if (this.lastDirection.equals("left")){
                    image = idleL;
                }
                if (this.lastDirection.equals("down") || this.lastDirection.equals("idle")){
                    image = idleD;
                }
        }
        g2.drawImage(image, this.screenX, this.screenY, gp.getTileSize(), gp.getTileSize(), null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public GamePanel getGamePanel() {
        return gp;
    }
}
