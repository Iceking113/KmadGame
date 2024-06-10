package org.cherepovskyi.game.object;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {
    public OBJ_Door(GamePanel gp, int a){
        super(gp);
        name = "door";
        if(a==0){
            down1 = setup("door.png");
        }else if(a==1) {
            down1 = setup("door2.png");
        }else if(a==2) {
            down1 = setup("door2_left.png");
        }else if(a==3) {
            down1 = setup("door2_right.png");
        }else if(a==4) {
            down1 = setup("door2orenge.png");
        }
        colision = true;
    }
}
