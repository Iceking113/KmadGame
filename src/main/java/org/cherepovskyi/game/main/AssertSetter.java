package org.cherepovskyi.game.main;

import org.cherepovskyi.game.entity.NPC_1;
import org.cherepovskyi.game.object.*;

public class AssertSetter {
    GamePanel gp;

    public AssertSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setOjgect(){
        int mapNum = 0;
        int i = 0;
        gp.obj[1][i] = new OBJ_Door(gp, 0);
        gp.obj[1][i].worldX = gp.tileSize*16;
        gp.obj[1][i].worldY = gp.tileSize*42;
        i++;

        /*gp.obj[1][i] = new OBJ_Door(gp, 1);
        gp.obj[1][i].worldX = gp.tileSize*16;
        gp.obj[1][i].worldY = gp.tileSize*42;
        i++;*/

        gp.obj[1][i] = new OBJ_Door(gp, 4);
        gp.obj[1][i].worldX = gp.tileSize*30;
        gp.obj[1][i].worldY = gp.tileSize*30;
        i++;

        gp.obj[0][i] = new OBJ_Key(gp);
        gp.obj[0][i].worldX = gp.tileSize*25;
        gp.obj[0][i].worldY = gp.tileSize*30;
        i++;

        gp.obj[1][i] = new OBJ_Bench(gp, 0);
        gp.obj[1][i].worldX = gp.tileSize*13;
        gp.obj[1][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[1][i] = new OBJ_Bench(gp, 1);
        gp.obj[1][i].worldX = gp.tileSize*14;
        gp.obj[1][i].worldY = gp.tileSize*47;
        i++;


        gp.obj[2][i] = new OBJ_Lamp(gp);
        gp.obj[2][i].worldX = gp.tileSize*20;
        gp.obj[2][i].worldY = gp.tileSize*38;
        i++;
        gp.obj[2][i] = new OBJ_Lamp(gp);
        gp.obj[2][i].worldX = gp.tileSize*23;
        gp.obj[2][i].worldY = gp.tileSize*38;
        i++;
        gp.obj[2][i] = new OBJ_Lamp(gp);
        gp.obj[2][i].worldX = gp.tileSize*12;
        gp.obj[2][i].worldY = gp.tileSize*38;
        i++;


        gp.obj[1][i] = new OBJ_Flower(gp, 0);
        gp.obj[1][i].worldX = gp.tileSize*12;
        gp.obj[1][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[1][i] = new OBJ_Flower(gp, 0);
        gp.obj[1][i].worldX = gp.tileSize*12;
        gp.obj[1][i].worldY = gp.tileSize*47;
        i++;

        //
        gp.obj[2][i] = new OBJ_Bench(gp, 0);
        gp.obj[2][i].worldX = gp.tileSize*10;
        gp.obj[2][i].worldY = gp.tileSize*43;
        i++;

        gp.obj[2][i] = new OBJ_Bench(gp, 1);
        gp.obj[2][i].worldX = gp.tileSize*11;
        gp.obj[2][i].worldY = gp.tileSize*43;
        i++;


        //
        gp.obj[2][i] = new OBJ_Bench(gp, 0);
        gp.obj[2][i].worldX = gp.tileSize*20;
        gp.obj[2][i].worldY = gp.tileSize*43;
        i++;

        gp.obj[2][i] = new OBJ_Bench(gp, 1);
        gp.obj[2][i].worldX = gp.tileSize*21;
        gp.obj[2][i].worldY = gp.tileSize*43;
        i++;

        gp.obj[2][i] = new OBJ_Door(gp, 0);
        gp.obj[2][i].worldX = gp.tileSize*15;
        gp.obj[2][i].worldY = gp.tileSize*37;
        i++;
        gp.obj[2][i] = new OBJ_Door(gp, 0);
        gp.obj[2][i].worldX = gp.tileSize*17;
        gp.obj[2][i].worldY = gp.tileSize*37;
        i++;

        //


    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;
        gp.npc[2][i] = new NPC_1(gp);
        gp.npc[2][i].worldX = gp.tileSize*20;
        gp.npc[2][i].worldY = gp.tileSize*39;
        i++;
    }
}
