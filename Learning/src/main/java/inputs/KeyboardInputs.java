package inputs;

import com.example.learning.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        gamePanel.updatename(e.getKeyChar());
        if(gamePanel.updatename(e.getKeyChar())==0) {
            switch (e.getKeyChar()) {
                case 'w':
                    gamePanel.Jump();
                    break;
                case 'r':
                    gamePanel.Restart();
                    break;
                case 'W':
                    gamePanel.Jump();
                    break;
                case 'R':
                    gamePanel.Restart();
                    break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.setKeyProcessed(false);
    }

}
