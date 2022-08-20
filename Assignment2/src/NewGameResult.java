/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonny
 */
public class NewGameResult {

    private final int x_size;
    private final int y_size;
    private final int noMines;

    /**
     *
     * @param x_size
     * @param y_size
     * @param noMines
     */
    public NewGameResult(int x_size, int y_size,int noMines) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.noMines = noMines;
        
    }

    /**
     *
     * @return
     */
    public int getX_size() {
        return x_size;
    }

    /**
     *
     * @return
     */
    public int getY_size() {
        return y_size;
    }

    /**
     *
     * @return
     */
    public int getNoMines() {
        return noMines;
    }
    
    
    
}
