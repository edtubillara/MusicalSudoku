package sudoku.main.listeners;
import sudoku.main.views.NumPadDialogue;
import sudoku.main.views.SudokuCellView;
import android.view.View;

/**
 * CellListener
 * @author etubil2
 *
 *When a Sudoku cell is tapped this listener's
 *onClick() method is called. 
 *
 */
public class SudokuCellListener implements android.view.View.OnClickListener {
	NumPadDialogue numberDialog;
	
	/**
	 * Constructor
	 * @param enterNumberDialog- The dialog to show
	 */
	public SudokuCellListener (NumPadDialogue enterNumberDialog){
		this.numberDialog = enterNumberDialog;
	}
	
	@Override
	public void onClick(View v) {
		SudokuCellView cell = (SudokuCellView) v;
		numberDialog.show(cell);
	}
}

