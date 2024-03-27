package org.cherepovskyi.game.entity;


import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    public KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidArreaDefaultX = solidArea.x;
        solidArreaDefaultY = solidArea.y;
        solidArea.width = 16;
        solidArea.height = 16;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gp.tileSize*25 - gp.tileSize/2;
        worldY = gp.tileSize*25 - gp.tileSize/2;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("Player/up.png");
        up2 = setup("Player/up2.png");

        left1 = setup("Player/left.png");
        left2 = setup("Player/left2.png");

        right1 = setup("Player/right.png");
        right2 = setup("Player/right2.png");

        down1 = setup("Player/down.png");
        down2 = setup("Player/down2.png");
    }
    public void update() {
        if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed){
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        int npcDialogueIndex = gp.cChecker.checkActionArria(this, gp.npc);
        interactNPC(npcDialogueIndex);
    }

    public void pickUpObject(int index){
        if(index != 999){

        }
    }
    public void interactNPC(int index) {
        if(index != 999){
            if(keyH.actionPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
            gp.ui.showMessage("Prasse E");
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }else {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }else {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }else {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }else {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null);
    }
}
