package ro.danielst.verifier;

import org.junit.Assert;
import org.junit.Test;
import ro.danielst.Puzzles;

public class SolverTest {
    @Test
    public void test_oneEntryMissing(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_1);
        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }

    @Test
    public void test_more1Missing(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_2);
        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }

    @Test
    public void test_more2Missing(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_3);
        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }

    @Test
    public void test_more3Missing(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_4);
        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }

    @Test
    public void test_more4Missing(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_5);
        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }

    @Test
    public void test_humanFail(){
        int[][] solve = Solver.solve(new Puzzles().PUZZLE_6);
//        Assert.assertArrayEquals(new Puzzles().PUZZLE_FULL, solve);
    }
}
