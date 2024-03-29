package org.cherepovskyi.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, actionPressed;

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
		if(gp.gameState == gp.playState) {
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
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_SHIFT) {
				if(gp.player.speed == 3) {
					gp.player.speed = 5;
				}
			}
			if(code == KeyEvent.VK_E) {
				actionPressed = true;
			}
		}
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}

		}
		//
		else if (gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNumb--;
				if(gp.ui.commandNumb < 0) gp.ui.commandNumb = 2;
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNumb++;
				if(gp.ui.commandNumb > 2) gp.ui.commandNumb = 0;
			}
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNumb == 0) {
					gp.stopMusic();
					gp.playMusic(1);
					gp.gameState = gp.playState;
				} else if(gp.ui.commandNumb == 1) {
					//
				} else if(gp.ui.commandNumb == 2) {
					System.exit(0);
				}
			}
		}
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
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
}
