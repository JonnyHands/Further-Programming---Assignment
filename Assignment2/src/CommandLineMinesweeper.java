/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonny
 */
public class CommandLineMinesweeper {

    private Parser parser = new Parser();

    /**
     *
     */
    public CommandLineMinesweeper() {

    }

    private void parseInput() {
        Minesweeper minefield = null;
        while (true) {
            Command cmd = parser.getCommand();
            switch (cmd.getCommand()) {
                case QUIT:
                    System.out.println(cmd.getMsg());
                    return;
                case UNKNOWN:
                    System.out.println(cmd.getMsg());
                    break;
                case NEW:
                    minefield = new Minesweeper(cmd.getRow(), cmd.getColumn(), 5);
                    minefield.populate();
                    break;
                case STEP:
                    if (!minefield.step(cmd.getRow(), cmd.getColumn())) {
                        System.out.println("GAME OVER");
                        return;
                    }
                    break;
                case MARK:
                    minefield.mark(cmd.getRow(), cmd.getColumn());                    
                    if (minefield.areAllMinesRevealed()) {
                        System.out.println("YOU WIN");
                    }
                    break;
            }
            if (minefield != null) {
                System.out.print(minefield.toString());
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        CommandLineMinesweeper clm = new CommandLineMinesweeper();
        clm.parseInput();
    }

}
