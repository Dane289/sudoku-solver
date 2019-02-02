package ro.danielst.verifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.danielst.Puzzles;

public class PossibleEntriesTest {
    private boolean[] possibleEntries = {true,true,true,true,true,true,true,true,true};

    @Before
    public void setup(){
        possibleEntries = new boolean[]{true, true, true, true, true, true, true, true, true};
    }
    @Test
    public void testPossibleEntries1(){
        Solver.findPossibleEntriesOnRowsAndColumns(new Puzzles().PUZZLE_FULL, 1, 1, possibleEntries);
        for(int i = 0; i<possibleEntries.length; i++) {
            Assert.assertFalse(possibleEntries[i]);
        }
    }

    @Test
    public void testPossibleEntries2(){
        Solver.findPossibleEntriesOnRowsAndColumns(new Puzzles().PUZZLE_1, 7, 7, possibleEntries);

        Assert.assertTrue(possibleEntries[2]);
        for(int i = 0; i<possibleEntries.length; i++) {
            if(i != 2)
                Assert.assertFalse(possibleEntries[i]);
        }
    }
    @Test
    public void testPossibleEntries3(){
        Solver.findPossibleEntriesOnRowsAndColumns(new Puzzles().PUZZLE_2, 8, 7, possibleEntries);

        Assert.assertTrue(possibleEntries[1]);
        Assert.assertTrue(possibleEntries[4]);
    }

    @Test
    public void testPossibleEntries4(){
        Solver.findPossibleEntriesOnCell(new Puzzles().PUZZLE_3, 7, 7, possibleEntries);

        Assert.assertTrue(possibleEntries[2]);
    }
}
