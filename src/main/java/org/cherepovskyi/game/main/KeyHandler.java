package org.cherepovskyi.game.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, actionPressed, enterPressed;

	public KeyHandler(GamePanel gp){
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		//PLAY STATE
		if(gp.gameState == gp.playState || gp.gameState == gp.fightState) {
			playState(code);
		}
		//OPTIONS STATE
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// TITLE STATE
		else if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			if (gp.player.speed == 5) {
				gp.player.speed = 3;
			}
		}
		if(code == KeyEvent.VK_E) {
			actionPressed = false;
		}
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
		if(code == KeyEvent.VK_SHIFT) {
			if(gp.player.speed == 3) {
				gp.player.speed = 5;

			}
		}
		if(code == KeyEvent.VK_E) {
			actionPressed = true;
		}
		if(code == KeyEvent.VK_M) {

		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}
	public void optionsState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.subState == 0){
				gp.ui.commandNumb--;
				gp.playSE(12);
				if(gp.ui.commandNumb < 0) gp.ui.commandNumb = 4;
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.subState == 0){
				gp.ui.commandNumb++;
				gp.playSE(12);
				if(gp.ui.commandNumb > 4) gp.ui.commandNumb = 0;
			}
		}
		// VOLUME
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.subState == 0){
				if(gp.ui.commandNumb == 1 && gp.music.volumeScale > 0){
					gp.music.volumeScale--;
					gp.music.checkVolume();
					//gp.playSE();
				}
				if(gp.ui.commandNumb == 2 && gp.se.volumeScale > 0){
					gp.se.volumeScale--;
					//gp.playSE();
				}
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.subState == 0){
				if(gp.ui.commandNumb == 1 && gp.music.volumeScale < 5){
					gp.music.volumeScale++;
					gp.music.checkVolume();
					//gp.playSE();
				}
				if(gp.ui.commandNumb == 2 && gp.se.volumeScale < 5){
					gp.se.volumeScale++;
					//gp.playSE();
				}
			}
		}
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			if(gp.ui.commandNumb == 0) {
				if(gp.ui.subState == 0){
					gp.gameState = gp.playState;
				}
				if(gp.ui.subState == 1){
					gp.ui.subState = 0;
				}
			} else if(gp.ui.commandNumb == 1) {
				//
			} else if(gp.ui.commandNumb == 2) {
				//
			} else if(gp.ui.commandNumb == 3) {
				gp.ui.subState = 1;
				gp.ui.commandNumb = 0;
			} else if(gp.ui.commandNumb == 4) {
				System.exit(0);
			}

		}
	}
	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0){
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNumb--;
				gp.playSE(12);
				if(gp.ui.commandNumb < 0) gp.ui.commandNumb = 2;
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNumb++;
				gp.playSE(12);
				if(gp.ui.commandNumb > 2) gp.ui.commandNumb = 0;
			}
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNumb == 0) {
					gp.stopMusic();
					gp.playMusic(1);
					gp.ui.titleScreenState = 1;
				} else if(gp.ui.commandNumb == 1) {
					//
				} else if(gp.ui.commandNumb == 2) {
					System.exit(0);
				}
			}
		}else if(gp.ui.titleScreenState == 1){
				/*if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
					gp.ui.commandNumb--;
					if(gp.ui.commandNumb < 0) gp.ui.commandNumb = 2;
				}
				if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					gp.ui.commandNumb++;
					if(gp.ui.commandNumb > 2) gp.ui.commandNumb = 0;
				}*/
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
				gp.stopMusic();
				gp.playMusic(1);
				gp.ui.commandNumb = 0;
				gp.ui.titleScreenState = 0;
				gp.gameState = gp.playState;
			}
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
	}
}
