package org.cherepovskyi.game.entity;

import org.cherepovskyi.game.main.GamePanel;

import java.util.Random;

public class NPC_1 extends Entity {
    GamePanel gp;
    public NPC_1(GamePanel gp){
        super(gp);
        this.gp = gp;
        direction = "down";
        speed = 1;
        getImage();
    }
    public void getImage(){
        up1 = setup("NPC/up.png");
        up2 = setup("NPC/up2.png");

        left1 = setup("NPC/left.png");
        left2 = setup("NPC/left2.png");

        right1 = setup("NPC/right.png");
        right2 = setup("NPC/right2.png");

        down1 = setup("NPC/down.png");
        down2 = setup("NPC/down2.png");

        setDialogue();
    }

    public void setDialogue() {
        dialogue[0] = "Constructs a new String by decoding the\nspecified array of bytes using the platform's\ndefault charset.";
        dialogue[1] = "How are you?";
        dialogue[2] = "Do you know abuot water?";
        dialogue[3] = "HA";
        dialogue[4] = "HAAA";
        dialogue[5] = "HAAAAAA";

    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter > 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            }  else if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak() {
        super.speak();
    }
}
