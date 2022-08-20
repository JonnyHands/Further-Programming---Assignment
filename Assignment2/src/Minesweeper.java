
/**
 *
 * @author candidate number 198397
 */
import java.util.Random;

/**
 *
 * @author Jonny
 */
public class Minesweeper {

    private final int maxMines;
    private MineTile[][] minefield;
    private final int rows;
    private final int columns;
    private int mineCount;

    Minesweeper(int rows, int columns, int maxMines) {
        this.rows = rows;
        this.columns = columns;
        minefield = new MineTile[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                minefield[i][j] = new MineTile();
            }
        }
        this.maxMines = maxMines;

    }

    /**
     *
     * @return
     */
    public int getMineCount(){
        return mineCount;
    }
    
    /**
     * checks correct number of mines is used randomly generates mines across
     * minefield
     *
     * @return boolean
     */
    boolean populate() {
        if ((rows * columns) - 1 < maxMines) {
            throw new IllegalArgumentException("Too many mines");
        }

        if (maxMines < 1) {
            throw new IllegalArgumentException("Not enough mines");
        }

        // for each mine keeps generating random numbers until it can place one
        for (int i = 0; i < maxMines; i++) {
            while (true) {
                Random r = new Random();
                int randX = r.nextInt(rows);
                int randY = r.nextInt(columns);
                if (randX == 0 && randY == 0) {
                    continue;
                }
                if (mineTile(randX, randY)) {
                    break;
                }
            }
        }
        return true;
    }

    /**
     * places a mine in tile and increments it's neighbours
     *
     * @param row
     * @param col
     * @return boolean
     */
    boolean mineTile(int row, int col) {
        if (minefield[row][col].isMined() || mineCount >= maxMines) {
            return false;
        }
        minefield[row][col].setMined(true);
        mineCount++;
        //either side of our row
        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > rows - 1) {
                continue;
            }
            //either side of column
            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > columns - 1) {
                    continue;
                }
                minefield[i][j].incMinedNeighbours();
            }
        }
        return true;
    }

    /**
     *
     * @return 
     * @returns the minefield with tiles hidden
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(minefield[i][j].toString());
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     *
     * @return 
     * @returns the minefield with no hidden tiles
     */
    public String toStringReveal() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(minefield[i][j].revealAll());
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @param row
     * @param col
     * @return given mineTile
     */
    public MineTile getMineTile(int row, int col) {
        return minefield[row][col];
    }

    /**
     * steps on a tile and checks if its neighbours should be revealed
     *
     * @param row
     * @param col
     * @return 
     */
    public boolean step(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            //checks for mine
            if (minefield[row][col].isMined()) {
                //game over
                return false;
            }
            //else it reveals tile
            minefield[row][col].setRevealed(true);
            //show any others if need be
            if (minefield[row][col].getMinedNeighboursCount() == 0) {
                checkNeighbours(row, col);
            }

        }
        return true;
    }

    /**
     * mark a tile
     *
     * @param row
     * @param column
     */
    public void mark(int row, int column) {
        this.minefield[row][column].toggleFlagged();
    }

    /**
     * if given tile can be revealed, reveals it then recursively checks
     * neighbours to see if they should be revealed
     *
     * @param row
     * @param col
     */
    private void checkNeighbours(int row, int col) {
        //either side of our row
        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > rows - 1) {
                continue;
            }

            // either side of our column
            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > columns - 1) {
                    continue;
                }
                if (i == row && j == col) {
                    continue;
                }
                // checks tile doesnt contain a mine and hasnt been revealed
                if (!minefield[i][j].isRevealed() && !minefield[i][j].isMined() && minefield[i][j].getMinedNeighboursCount() == 0) {
                    minefield[i][j].setRevealed(true);
                    //if we have no neighbours recursively check mine
                    if (minefield[row][col].getMinedNeighboursCount() == 0) {
                        checkNeighbours(i, j);
                    }
                }
            }
        }

    }

    /**
     * @return have all mines been correctly flagged
     */
    public boolean areAllMinesRevealed() {
        //iterates through every row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //checks if mined and not flagged or if not mined and flagged
                if ((minefield[i][j].isMined()
                        && !minefield[i][j].isMarked())
                        || (!minefield[i][j].isMined()
                        && minefield[i][j].isMarked())) {
                    return false;
                }
            }
        }
        return true;
    }

}
