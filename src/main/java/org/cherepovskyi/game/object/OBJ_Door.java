package org.cherepovskyi.game.object;

import org.cherepovskyi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}
