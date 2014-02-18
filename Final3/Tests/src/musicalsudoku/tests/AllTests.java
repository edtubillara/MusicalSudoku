package musicalsudoku.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SudokuSolverTests.class, TestECColumnPrioQue.class,
		TestExactCover.class, TestFileParse.class, TestReducableSolve.class })
public class AllTests {

}
