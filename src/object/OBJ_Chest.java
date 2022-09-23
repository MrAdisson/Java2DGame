package object;

import entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject implements Pickable{

    protected boolean opened = false;
    public OBJ_Chest(){

        this.name = "Chest";
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.collision = true;
        this.pickable = true;
    }

    @Override
    public SuperObject pickupAction(Player p) {
        if (!opened) {
            try {
                this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest_opened.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.opened = true;
            p.getGamePanel().getUi().showMessage("Coffre ouvert !");
            p.getGamePanel().playSoundEffect(3);
        }
        return this;
    }
}
