package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTile[][];

    public TileManager(GamePanel gp){

      this.gp = gp;
      tile = new Tile[10];
      getTileImage();
      this.mapTile = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
      loadMap("world01");
    }

    private void getTileImage() {

        try{
            //DIRT
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/earth.png")));
            //GRASS
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grass.png")));
            //WALL
            tile[2] = new Tile();
            tile[2].collision = true;
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/wall.png")));
            //WATER
            tile[3] = new Tile();
            tile[3].collision = true;
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/water.png")));
            //SAND
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/sand.png")));
            //TREE
            tile[5] = new Tile();
            tile[5].collision = true;
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tree.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/" + mapName + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {

            String line = br.readLine();
            String numbers[] = line.split(" ");

                while (col < gp.getMaxWorldCol()){
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();

            }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()){

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX()  + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY()  + gp.getPlayer().getScreenY();

            if (worldX > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() - gp.getTileSize()
                    && worldX < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() + gp.getTileSize()
                    && worldY > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() - gp.getTileSize()
                    && worldY < gp.getPlayer().getWorldY() +  gp.getPlayer().getScreenY() + gp.getTileSize()){

            g2.drawImage(tile[mapTile[worldCol][worldRow]].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
            worldCol++;

            if (worldCol == gp.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }

        }

    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTile() {
        return mapTile;
    }
}
