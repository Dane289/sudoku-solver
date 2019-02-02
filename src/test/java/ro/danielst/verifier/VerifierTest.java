package ro.danielst.verifier;

import org.junit.Assert;
import org.junit.Test;
import ro.danielst.Puzzles;

public class VerifierTest {
    @Test
    public void testFullBoardIsFull() {
        boolean full = Verifier.isFull(new Puzzles().PUZZLE_FULL);
        Assert.assertTrue(full);
    }

    @Test
    public void testFullBoardIsNotFull() {
        boolean full = Verifier.isFull(new Puzzles().PUZZLE_1);
        Assert.assertFalse(full);
    }
}
