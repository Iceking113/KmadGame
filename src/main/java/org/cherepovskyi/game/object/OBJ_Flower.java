package org.cherepovskyi.game.object;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.main.GamePanel;

public class OBJ_Flower extends Entity {
    public OBJ_Flower(GamePanel gp, int a){
        super(gp);
        name = "bench";
        if(a==0){
            down1 = setup("flower.png");
        }else if(a==1) {
            //down1 = setup("bench_right.png");
        }
        colision = true;
    }
}
