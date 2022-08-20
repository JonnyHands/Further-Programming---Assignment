
import java.util.Observable;

/**
 *
 * @author candidate number 198397
 */
public class MineTile extends Observable {

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

    /**
     *
     * @return
     */
    public String revealAll() {
        if (this.isMined()) {
            return "*";
        } else {
            return String.valueOf(this.minedNeighboursCount);
        }
    }

    /**
     *
     */
    public void toggleFlagged() {
        //checks mine is revealed if so prints error
        if (this.isRevealed()) {
            System.out.println("Cannot flag an already revealed tile");
            return;
        } else {
            flagged = !flagged;
        }
    }

    /**
     *
     * @return
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     *
     * @param revealed
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
        setChanged();
        notifyObservers(getMinedNeighboursCount());
    }

    /**
     *
     * @return
     */
    public int getMinedNeighboursCount() {
        return minedNeighboursCount;
    }

    /**
     *
     */
    public void incMinedNeighbours() {
        minedNeighboursCount++;
    }

    /**
     *
     * @return
     */
    public boolean isMined() {
        return mined;
    }

    /**
     *
     * @param mined
     */
    public void setMined(boolean mined) {
        this.mined = mined;
    }

    /**
     *
     * @return
     */
    public boolean isMarked() {
        return flagged;
    }

}
