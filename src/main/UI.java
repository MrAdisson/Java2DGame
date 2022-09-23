package main;

import object.OBJ_Key;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    double playtime = 0;
    DecimalFormat dformat = new DecimalFormat("#0.00");
    Font font = new Font("Arial", Font.PLAIN, 40);
    OBJ_Key key = new OBJ_Key();
    String notificationMessage = "";
    boolean notificationOn = false;
    int notificationCounter = 0;
    final int notificationTime = 2;

    public UI(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawImage(key.getImage(), 30,50 ,gp.tileSize,gp.tileSize, null);
        g2.drawString(" : " + gp.player.getKeys(), 70, 90);
        playtime += (double)1/gp.FPS;
//        g2.drawString("Time : " + dformat.format(playtime), 30, 150);

        if (notificationOn){
            g2.setFont(font.deriveFont(20f));
            g2.setColor(Color.MAGENTA);
            g2.drawString("[INFO] : " + notificationMessage, 30, gp.screenHeight/2);
            notificationCounter++;
            if (notificationCounter > notificationTime * gp.FPS){
                notificationOn = false;
                notificationCounter = 0;
            }
        }

    }

    public String showMessage (String message){

        notificationMessage = message;
        notificationOn = true;
        return message;
    }

}
