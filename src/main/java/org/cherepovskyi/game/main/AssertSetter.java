package org.cherepovskyi.game.main;

import org.cherepovskyi.game.object.OBJ_Coint;
import org.cherepovskyi.game.object.OBJ_Door;
import org.cherepovskyi.game.object.OBJ_Key;

public class AssertSetter {
    GamePanel gp;

    public AssertSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setOjgect(){
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23*gp.tileSize;
        gp.obj[0].worldY = 25*gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 20*gp.tileSize;
        gp.obj[1].worldY = 25*gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 25*gp.tileSize;
        gp.obj[2].worldY = 20*gp.tileSize;

        gp.obj[3] = new OBJ_Coint(gp);
        gp.obj[3].worldX = 35*gp.tileSize;
        gp.obj[3].worldY = 25*gp.tileSize;
    }
}
