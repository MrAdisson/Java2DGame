package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;

    }

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for (int i = 0; i < gp.getObjectsArray().length ; i++) {

            if (gp.getObjectsArray()[i] != null) {
                SuperObject obj = gp.getObjectsArray()[i];
                //get entity's collisionArea position
                entity.getCollisionArea().x = entity.getWorldX() + entity.getCollisionArea().x;
                entity.getCollisionArea().y = entity.getWorldY() + entity.getCollisionArea().y;
                //get object's collisionArea position
                obj.getCollisionArea().x = obj.getWorldX() + obj.getCollisionArea().x;
                obj.getCollisionArea().y = obj.getWorldY() + obj.getCollisionArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getCollisionArea().y -= entity.getSpeed();
                        if (entity.getCollisionArea().intersects(obj.getCollisionArea())) {
                            System.out.println("INTERSECTION " + obj.getName() + " up ");
                            if (obj.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getCollisionArea().y += entity.getSpeed();
                        if (entity.getCollisionArea().intersects(obj.getCollisionArea())) {
                            System.out.println("INTERSECTION " + obj.getName() + " down ");
                            if (obj.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getCollisionArea().x -= entity.getSpeed();
                        if (entity.getCollisionArea().intersects(obj.getCollisionArea())) {
                            System.out.println("INTERSECTION " + obj.getName() + " left ");
                            if (obj.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getCollisionArea().x += entity.getSpeed();
                        if (entity.getCollisionArea().intersects(obj.getCollisionArea())) {
                            System.out.println("INTERSECTION " + obj.getName() + " right ");
                            if (obj.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "idle":
                        if (entity.getCollisionArea().intersects(obj.getCollisionArea())) {
                            System.out.println("INTERSECTION " + obj.getName() + " idle ");
//                            if (obj.isCollision()){
//                                entity.setCollisionOn(true);
//                            }
                            if (player){
                                index = i;
                            }
                        }
                }

                entity.getCollisionArea().x = entity.getCollisionAreaDefaultX();
                entity.getCollisionArea().y = entity.getCollisionAreaDefaultY();
                obj.getCollisionArea().x = obj.getCollisionAreaDefaultX();
                obj.getCollisionArea().y = obj.getCollisionAreaDefaultY();

            }
        }
        return index;

    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.getWorldX() + entity.getCollisionArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getCollisionArea().x + entity.getCollisionArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getCollisionArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getCollisionArea().y + entity.getCollisionArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTile()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTile()[entityRightCol][entityTopRow];
                if(gp.getTileManager().getTile()[tileNum1].collision || gp.getTileManager().getTile()[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 =  gp.getTileManager().getMapTile()[entityRightCol][entityTopRow];
                tileNum2 =  gp.getTileManager().getMapTile()[entityRightCol][entityBottomRow];
                if(gp.getTileManager().getTile()[tileNum1].collision || gp.getTileManager().getTile()[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTile()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTile()[entityRightCol][entityBottomRow];
                if(gp.getTileManager().getTile()[tileNum1].collision || gp.getTileManager().getTile()[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 =  gp.getTileManager().getMapTile()[entityLeftCol][entityTopRow];
                tileNum2 =  gp.getTileManager().getMapTile()[entityLeftCol][entityBottomRow];
                if(gp.getTileManager().getTile()[tileNum1].collision || gp.getTileManager().getTile()[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;

        }

    }

}

