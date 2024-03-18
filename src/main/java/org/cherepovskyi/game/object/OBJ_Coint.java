package org.cherepovskyi.game.object;

import org.cherepovskyi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coint extends SuperObject{
    GamePanel gp;
    public OBJ_Coint(GamePanel gp){
        this.gp = gp;
        name = "coint";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("coint.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}
