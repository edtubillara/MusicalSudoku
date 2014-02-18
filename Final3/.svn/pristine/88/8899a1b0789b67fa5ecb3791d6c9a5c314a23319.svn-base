package sudoku.main.listeners;

import sudoku.main.views.SudokuView;
import sudoku.solver.sudokusolver.SudokuModel;
import android.view.View;

/**
 * Listener for the clear all button
 * @author etubil2
 *
 */
public class ClearAllButtonListener implements android.view.View.OnClickListener {
	SudokuView view;
	SudokuModel model;
	
	/**
	 * Constructor
	 * @param view
	 * @param model
	 */
	public ClearAllButtonListener(SudokuView view,SudokuModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void onClick(View v) {
		
		//set all elements in model to 0 and update view
		for(int y = 0; y<81; y++){
			for(int x =0; x < 81; x++){
				model.setAt(y, x, (short)0);
			}
		}
		view.reflectModel();

	}

}
