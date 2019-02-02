package ro.danielst;

import ro.danielst.print.SudokuPrinter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(SudokuPrinter.prettyPrint(new Puzzles().PUZZLE_FULL));
    }
}
