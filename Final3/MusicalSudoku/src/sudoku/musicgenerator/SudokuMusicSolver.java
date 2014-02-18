package sudoku.musicgenerator;


import java.util.Stack;

import sudoku.solver.exactcover.ECMatrix;
import sudoku.solver.exactcover.ECReducable;
import sudoku.solver.sudokusolver.SudokuModel;
import sudoku.solver.sudokusolver.SudokuSolver;

/**
 * A functor that wraps the logic that
 * solves the sudoku puzzle step by step.
 * @author etubil2
 *
 */
public class SudokuMusicSolver {

	private SudokuModel model;
	private ECMatrix matrix;
	private ECReducable reduceable;
	
	public SudokuMusicSolver(SudokuModel model){

		this.model = model;
		this.matrix = SudokuSolver._translateToECMatrix(model.getMatrix());
		reduceable = new ECReducable(matrix);
	}
	
	/**
	 * returns the melody
	 * @return
	 */
	public Stack<int[]> solveAStep(MelodyMap melodyMap){
		
		int[] result = reduceable.reduce();
		//update view and model
		Stack<int[]> changed = updateModel(result);

		return changed;
		
	}

	private Stack<int[]> updateModel(int[] result) {
		model.setAt(result[0], result[1], (short)(result[2]+1));
		Stack<int[]> changed = reduceable.getBackTrackedNumbers();
		
		for(int[] i : changed){
			model.setAt(i[0], i[1], (short) 0);
		}
		
		changed.add(result);
		return changed;
	}
	
	
}
