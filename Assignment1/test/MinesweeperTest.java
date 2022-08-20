
/**
 *
 * @author candidate number 198397
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperTest {

    public MinesweeperTest() {
    }

    @Test
    public void testMinefield() {
        Minesweeper obj = new Minesweeper(10, 10, 10);
        assertNotNull(obj);
    }

    @Test
    public void testPopulate() {
        Minesweeper obj = new Minesweeper(10, 10, 3);
        obj.populate();
        assertEquals(3, obj.getMineCount());
    }

    @Test(expected = Exception.class)
    public void testPopulateNoMinesShouldThrowException() {
        Minesweeper obj = new Minesweeper(10, 10, 0);
        obj.populate();
    }

    @Test(expected = Exception.class)
    public void testPopulateTooManyMinesShouldThrowException() {
        Minesweeper obj = new Minesweeper(10, 10, 101);
        obj.populate();
    }

    @Test
    public void testAddingMineToATile() {
        Minesweeper obj = new Minesweeper(10, 10, 10);
        assertTrue(obj.mineTile(1, 1));
        assertTrue(obj.getMineTile(1, 1).isMined());
    }

    @Test
    public void testAddingTwoMinesToOneTile() {
        Minesweeper obj = new Minesweeper(10, 10, 10);
        assertTrue(obj.mineTile(1, 1));
        assertTrue(obj.getMineTile(1, 1).isMined());
        assertFalse(obj.mineTile(1, 1));
    }

    @Test
    public void testAddingTooManyMines() {
        Minesweeper obj = new Minesweeper(10, 10, 1);
        assertTrue(obj.mineTile(1, 1));
        assertTrue(obj.getMineTile(1, 1).isMined());
        assertFalse(obj.mineTile(2, 1));
    }

    @Test
    public void testMinedNeighboursIncrement() {
        Minesweeper obj = new Minesweeper(2, 2, 1);
        assertTrue(obj.mineTile(1, 1));
        assertEquals(1, obj.getMineTile(0, 0).getMinedNeighboursCount());
        assertEquals(1, obj.getMineTile(0, 1).getMinedNeighboursCount());
        assertEquals(1, obj.getMineTile(1, 0).getMinedNeighboursCount());
    }

    @Test
    public void testMinedNeighboursWithMultipleMines() {
        Minesweeper obj = new Minesweeper(2, 2, 2);
        obj.mineTile(0, 1);
        obj.mineTile(1, 1);
        assertEquals(2, obj.getMineTile(0, 0).getMinedNeighboursCount());
        assertEquals(2, obj.getMineTile(1, 0).getMinedNeighboursCount());
    }

    @Test
    public void testToStringWithOneMine() {
        Minesweeper obj = new Minesweeper(2, 2, 1);
        obj.mineTile(0, 1);
        assertEquals("1*\n11\n", obj.toStringReveal());
    }

    @Test
    public void testToStringOutputToConsole() {
        Minesweeper obj = new Minesweeper(10, 20, 30);
        obj.populate();
        String minefield = obj.toStringReveal();
        assertNotEquals("", minefield);
        System.out.print(minefield);
    }

    @Test
    public void testToStringWithTwoMines() {
        Minesweeper obj = new Minesweeper(2, 2, 2);
        obj.mineTile(0, 1);
        obj.mineTile(1, 1);
        assertEquals("2*\n2*\n", obj.toStringReveal());

    }

    @Test
    public void testToStringWithBigField() {
        Minesweeper obj = new Minesweeper(5, 5, 2);
        obj.mineTile(0, 1);
        obj.mineTile(1, 1);
        assertEquals("2*200\n2*200\n11100\n00000\n00000\n", obj.toStringReveal());

    }

    @Test
    public void testMineTileToStringOnMinedTile() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mineTile(2, 2);
        assertEquals(".", obj.getMineTile(2, 2).toString());
    }

    @Test
    public void testMineTileToStringFlagged() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mark(2, 2);
        assertEquals("?", obj.getMineTile(2, 2).toString());
    }

    @Test
    public void testMineTileToStringRevealed() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mineTile(2, 1);
        obj.step(2, 2);
        assertEquals("1", obj.getMineTile(2, 2).toString());
    }

    @Test
    public void testFirstTileEmpty() {
        for (int i = 0; i < 10; i++) {
            Minesweeper obj = new Minesweeper(2, 2, 3);
            obj.populate();
            assertFalse(obj.getMineTile(0, 0).isMined());
        }
    }

    @Test
    public void testCurrentMines() {
        Minesweeper obj = new Minesweeper(2, 2, 3);
        obj.populate();
        assertEquals(3, obj.getMineCount());
    }

    @Test
    public void testMarked() {
        Minesweeper obj = new Minesweeper(3, 3, 3); //
        obj.mark(1, 1);
        assertEquals(true, obj.getMineTile(1, 1).isMarked());
    }

    @Test
    public void testMarkToggle() {
        Minesweeper obj = new Minesweeper(3, 3, 3); //
        obj.mark(1, 1);
        assertEquals(true, obj.getMineTile(1, 1).isMarked());
        obj.mark(1, 1);
        assertEquals(false, obj.getMineTile(1, 1).isMarked());
    }

    @Test
    public void testStepMinedReturnsFalse() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mineTile(1, 1);
        assertEquals(false, obj.step(1, 1));
    }

    @Test
    public void testStepSetsRevealedToTrue() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.step(1, 1);
        assertEquals(true, obj.getMineTile(1, 1).isRevealed());
    }

    @Test
    public void testStepRevealsNeighbours() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        //put mine in bottom right corner
        obj.mineTile(2, 2);
        obj.step(0, 0);
        assertEquals(true, obj.getMineTile(0, 0).isRevealed());
        assertEquals(true, obj.getMineTile(0, 1).isRevealed());
        assertEquals(true, obj.getMineTile(0, 2).isRevealed());
        assertEquals(true, obj.getMineTile(1, 0).isRevealed());
        assertEquals(true, obj.getMineTile(2, 0).isRevealed());

    }

    @Test
    public void testAreAllMinesRevealedWhenFalse() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mineTile(1, 1);
        obj.mineTile(2, 2);
        obj.mark(2, 2);
        assertEquals(false, obj.areAllMinesRevealed());
    }

    @Test
    public void testMinedNeighboursCountOfNeighbouringMine() {
        Minesweeper obj = new Minesweeper(3, 3, 3);
        obj.mineTile(0, 1);
        obj.mineTile(0, 2);
        assertEquals(1, obj.getMineTile(0, 0).getMinedNeighboursCount());
    }
}
