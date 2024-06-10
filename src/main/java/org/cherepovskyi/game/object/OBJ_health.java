package org.cherepovskyi.game.object;

import org.cherepovskyi.game.entity.Entity;
import org.cherepovskyi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_health extends Entity {
    public OBJ_health(GamePanel gp){
        super(gp);
        name = "health";
        image = setup("key.png");
        image_2 = setup("key.png");
        image_3 = setup("key.png");
    }
}
