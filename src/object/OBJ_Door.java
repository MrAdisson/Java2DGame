package object;

import entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject implements Pickable{

    public OBJ_Door(){

        this.name = "Door";
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        this.collision = true;
        this.pickable = true;
    }

    @Override
    public SuperObject pickupAction(Player p) {
        System.out.println(p.getKeys());

        if (p.getKeys() > 0){
            p.setKeys(p.getKeys() - 1);
            p.getGamePanel().playSoundEffect(3);
            p.getGamePanel().getUi().showMessage("Porte dévérouillée !");
            return null;
        }
        else{
            p.getGamePanel().getUi().showMessage("Vous avez besoin d'une clé.");
            return this;
        }
    }
}
