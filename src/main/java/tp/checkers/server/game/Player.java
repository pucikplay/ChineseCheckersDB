package tp.checkers.server.game;

import tp.checkers.message.MessageClickedField;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

public class Player {
    private ThreadPlayer thread;
    private Color color;
    private Color enemyColor;
    private boolean active;
    private int leftToWin;

    public void setThread(ThreadPlayer thread) {
        this.thread = thread;
    }
    public ThreadPlayer getThread() {
        return thread;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }
    public Color getEnemyColor() {
        return enemyColor;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean state){
        this.active = state;
    }
    public void setLeftToWin(int number) {
        this.leftToWin = number;
    }
    public MessageClickedField pieceSelect() {
        return thread.pieceSelect();
    }

    public void sendPossibilities(Coordinates[] possibilities) {
        thread.sendPossibilities(possibilities);
    }

    public MessageMove pieceMove() {
        return thread.pieceMove();
    }

    public void updateBoard(MessageMove messageMove, boolean b) {
        thread.updateBoard(messageMove, b);
    }

    public boolean checkIfWon() {
        leftToWin--;
        if(leftToWin == 0) {
            return true;
        }
        else return false;
    }
}
