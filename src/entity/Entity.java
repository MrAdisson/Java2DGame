package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    protected int worldX, worldY;
//    protected int x, y;
    protected int speed;

    protected BufferedImage up1, up2, right1, right2, down1, down2, left1, left2, idleD, idleR, idleU, idleL;
    protected String direction;
    protected String lastDirection;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    protected Rectangle collisionArea;
    protected int collisionAreaDefaultX, collisionAreaDefaultY;
    protected boolean collisionOn = false;

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public String getDirection() {
        return direction;
    }

    public int getCollisionAreaDefaultX() {
        return collisionAreaDefaultX;
    }

    public int getCollisionAreaDefaultY() {
        return collisionAreaDefaultY;
    }
}
