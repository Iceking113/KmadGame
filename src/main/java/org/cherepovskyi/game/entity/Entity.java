package org.cherepovskyi.game.entity;

import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
	public Rectangle solidActionArea = new Rectangle(-64, -64, 128+64, 128+64);
	public int solidArreaDefaultX, solidArreaDefaultY;
	public int solidActionArreaDefaultX = -64, solidActionArreaDefaultY = -64;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	//public int actionWaitCounter = 0;
	public String[] dialogue = new String[20];
	public int dialogueIndex = 0;
	public BufferedImage image, image_2, image_3;
	public String name;
	public boolean colision = false;

	//cherecter status
	public int maxLife;
	public int life;


	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {}
	public void speak() {
		if (dialogue[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogue[dialogueIndex];
		dialogueIndex++;

		switch(gp.player.direction){
			case "up": direction = "down"; break;
			case "down": direction = "up"; break;
			case "left": direction = "right"; break;
			case "right": direction = "left"; break;
		}
	}
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);

		//attempt to make entity wait after turning
		/*if(actionWaitCounter > 120 && actionWaitCounter < 180) {
			if(actionWaitCounter == 180){
				actionWaitCounter = 0;
			}
			//WAIT
		}else {
			// IF COLLISION IS FALSE, ENTITY CAN MOVE
			if(!collisionOn){
				switch (direction){
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}

			// SPRITE IMAGE CHANGING AT MUVEMENT
			spriteCounter++;
			if(spriteCounter>10){
				if(spriteNum == 1){
					spriteNum = 2;
				}else {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}*/

		// IF COLLISION IS FALSE, ENTITY CAN MOVE
		if(!collisionOn){
			switch (direction){
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
			}
		}

		// SPRITE IMAGE CHANGING AT MUVEMENT
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
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

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
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	public BufferedImage setup(String imagePath) {
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
}
