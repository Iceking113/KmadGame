package org.cherepovskyi.game.object;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "key";
        down1 = setup("key.png");

    }
}
