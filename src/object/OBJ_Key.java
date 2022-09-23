package object;

import entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject implements Pickable{

    public OBJ_Key(){

        this.name = "Key";
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.pickable = true;
    }

    @Override
    public OBJ_Key pickupAction(Player p) {
        p.setKeys(p.getKeys()+1);
        System.out.println("+ Clé x1");
        p.getGamePanel().playSoundEffect(1);
        p.getGamePanel().getUi().showMessage("Clé obtenue !");
        return null;
    }
}
