package org.cherepovskyi.game.tile;

import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("TestMap2.txt", 0);
        loadMap("map1.txt", 1);
        loadMap("map2.txt", 2);
        //loadMap("TestMap.txt", 1);
    }
    public void getTileImage(){
        setup(0, "001.png", true);
        setup(1, "002.png", true);
        setup(2, "003.png", false);
        setup(3, "004.png", true);

        setup(4, "005.png", true);
        setup(5, "006.png", true);
        setup(6, "007.png", true);
        setup(7, "008.png", true);

        setup(8, "009.png", true);
        setup(9, "010.png", true);
        setup(10, "011.png", true);
        setup(11, "012.png", true);

        setup(12, "013.png", true);
        setup(13, "014.png", true);
        setup(14, "015.png", true);
        setup(15, "016.png", true);

        setup(16, "017.png", true);
        setup(17, "018.png", true);
        setup(18, "019.png", true);
        setup(19, "020.png", true);

        setup(20, "021.png", true);
        setup(21, "022.png", true);
        setup(22, "023.png", true);
        setup(23, "024.png", true);

        setup(24, "025.png", false);
        setup(25, "026.png", false);
        setup(26, "027.png", false);
        setup(27, "028.png", false);

        setup(28, "029.png", true);
        setup(29, "030.png", true);
        setup(30, "031.png", true);
        setup(31, "032.png", true);

        setup(32, "033.png", true);
        setup(33, "034.png", true);
        setup(34, "035.png", true);
        setup(35, "036.png", true);

        setup(36, "037.png", true);
        setup(37, "038.png", true);
        setup(38, "039.png", true);
        setup(39, "040.png", true);

        setup(40, "041.png", true);
        setup(41, "042.png", true);
        setup(42, "043.png", true);
        setup(43, "044.png", true);

        setup(44, "045.png", true);
        setup(45, "046.png", true);
        setup(46, "047.png", true);
        setup(47, "048.png", true);

        setup(48, "049.png", true);
        setup(49, "050.png", true);
        setup(50, "051.png", true);
        setup(51, "052.png", true);

        setup(52, "053.png", true);
        setup(53, "054.png", false);
        setup(54, "055.png", true);
        setup(55, "056.png", true);

        setup(56, "057.png", true);
        setup(57, "058.png", true);
        setup(58, "059.png", true);
        setup(59, "060.png", true);

        setup(60, "061.png", true);
        setup(61, "062.png", true);
        setup(62, "063.png", true);
        setup(63, "064.png", true);

        setup(64, "065.png", true);
        setup(65, "066.png", true);
        setup(66, "067.png", true);
        setup(67, "068.png", true);

        setup(68, "069.png", true);
        setup(69, "070.png", true);
        setup(70, "071.png", true);
        setup(71, "072.png", true);

        setup(72, "073.png", true);
        setup(73, "074.png", true);
    }
    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public  void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
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

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX,screenY,null);
            }

            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
