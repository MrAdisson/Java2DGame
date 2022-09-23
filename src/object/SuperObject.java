package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    protected BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int worldX = 0, worldY = 0;
    protected boolean pickable = false;

    protected Rectangle collisionArea = new Rectangle(0,0,48,48); //todo : remplacer 48 par valeur dynamique
    protected int collisionAreaDefaultX = 0, collisionAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = this.worldX - gp.getPlayer().getWorldX()  + gp.getPlayer().getScreenX();
        int screenY = this.worldY - gp.getPlayer().getWorldY()  + gp.getPlayer().getScreenY();

        if (worldX > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() - gp.getTileSize()
                && worldX < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() + gp.getTileSize()
                && worldY > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() - gp.getTileSize()
                && worldY < gp.getPlayer().getWorldY() +  gp.getPlayer().getScreenY() + gp.getTileSize()){

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    public SuperObject setWorldX(int worldX) {
        this.worldX = worldX;
        return this;
    }

    public SuperObject setWorldY(int worldY) {
        this.worldY = worldY;
        return this;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

    public int getCollisionAreaDefaultX() {
        return collisionAreaDefaultX;
    }

    public int getCollisionAreaDefaultY() {
        return collisionAreaDefaultY;
    }

    public boolean isPickable() {
        return pickable;
    }
}
