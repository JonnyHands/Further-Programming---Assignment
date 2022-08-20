package pkg198397.ms3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonny
 */
public class MineTile {

    private boolean mined;
    private int minedNeighboursCount;
    private boolean revealed;
    private boolean flagged;
    

    public String toString() {
        if (isRevealed()) {
            return String.valueOf(minedNeighboursCount);
        }
        if (isMarked()) {
            return "?";
        }
        return ".";
    }

    public String revealAll() {
        if (this.isMined()) {
            return "*";
        } else {
            return String.valueOf(this.minedNeighboursCount);
        }
    }

    public void toggleFlagged() {
        //checks mine is revealed if so prints error
        if (this.isRevealed()) {
            System.out.println("Cannot flag an already revealed tile");
            return;
        } else {
            flagged = !flagged;
        }
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getMinedNeighboursCount() {
        return minedNeighboursCount;
    }

    public void incMinedNeighbours() {
        minedNeighboursCount++;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public boolean isMarked() {
        return flagged;
    }

}
