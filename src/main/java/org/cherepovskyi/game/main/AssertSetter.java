package org.cherepovskyi.game.main;

import org.cherepovskyi.game.entity.NPC_1;
import org.cherepovskyi.game.object.OBJ_Coint;
import org.cherepovskyi.game.object.OBJ_Door;
import org.cherepovskyi.game.object.OBJ_Key;

public class AssertSetter {
    GamePanel gp;

    public AssertSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setOjgect(){

    }
    public void setNPC(){
        gp.npc[0] = new NPC_1(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}
