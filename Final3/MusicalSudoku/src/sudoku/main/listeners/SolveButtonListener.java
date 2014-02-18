package sudoku.main.listeners;

import sudoku.main.views.SudokuView;
import sudoku.solver.sudokusolver.SudokuModel;
import android.os.AsyncTask;
import android.view.View;
/**
 * The listener for the solve button
 * @author etubil2
 *
 */
public class SolveButtonListener implements android.view.View.OnClickListener {

	private SudokuView view;
	private SudokuModel model;

	public SolveButtonListener(SudokuView view, SudokuModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void onClick(View v) {
		view.enableAllButtons(false);
		//start solving
		new Solve().execute();
	}

	/**
	 * @author etubil2
	 * this runs in the background
	 */
	class Solve extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
		
			model.solve();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			view.reflectModel();
			view.enableAllButtons(true);
		}
		
	}

}
