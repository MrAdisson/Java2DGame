package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects(){
        gp.getObjectsArray()[0] = new OBJ_Key();
        gp.getObjectsArray()[0].setWorldX(1 * gp.getTileSize()).setWorldY(5 * gp.getTileSize());

        gp.getObjectsArray()[1] = new OBJ_Key();
        gp.getObjectsArray()[1].setWorldX(1 * gp.getTileSize()).setWorldY(8 * gp.getTileSize());

        gp.getObjectsArray()[2] = new OBJ_Chest();
        gp.getObjectsArray()[2].setWorldX(21 * gp.getTileSize()).setWorldY(12 * gp.getTileSize());

        gp.getObjectsArray()[3] = new OBJ_Door();
        gp.getObjectsArray()[3].setWorldX(17 * gp.getTileSize()).setWorldY(8 * gp.getTileSize());

        gp.getObjectsArray()[4] = new OBJ_Door();
        gp.getObjectsArray()[4].setWorldX(26 * gp.getTileSize()).setWorldY(13 * gp.getTileSize());
        gp.getObjectsArray()[5] = new OBJ_Door();
        gp.getObjectsArray()[5].setWorldX(27 * gp.getTileSize()).setWorldY(13 * gp.getTileSize());

    }
}
