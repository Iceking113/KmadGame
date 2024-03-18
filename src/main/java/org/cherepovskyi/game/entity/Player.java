package org.cherepovskyi.game.entity;


import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.KeyHandler;
import org.cherepovskyi.game.main.UtilityTool;
import org.cherepovskyi.game.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
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
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("up.png");
        up2 = setup("up2.png");

        left1 = setup("left.png");
        left2 = setup("left2.png");

        right1 = setup("right.png");
        right2 = setup("right2.png");

        down1 = setup("down.png");
        down2 = setup("down2.png");
    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
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
    }

    public void pickUpObject(int index){
        if(index != 999){
            String objectName = gp.obj[index].name;
            switch (objectName){
                case "key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "door":
                    if(hasKey > 0){
                        gp.playSE(2);
                        hasKey--;
                        gp.obj[index] = null;
                        gp.ui.showMessage("You opend the door!");
                    }else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "coint":
                    gp.stopMusic();
                    gp.playSE(0);
                    gp.ui.gameFinished = true;
            }
        }
    }

    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, qp.tileSize, qp.tileSize);

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
