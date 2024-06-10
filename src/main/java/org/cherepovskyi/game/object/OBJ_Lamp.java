package org.cherepovskyi.game.object;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.main.GamePanel;

public class OBJ_Lamp extends Entity {
    public OBJ_Lamp(GamePanel gp){
        super(gp);
        name = "lamp";
        down1 = setup("lamp.png");

    }
}
