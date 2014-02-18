package sudoku.main.listeners;
import sudoku.main.R;
import sudoku.main.views.NumPadDialogue;
import sudoku.main.views.SudokuCellView;
import sudoku.solver.sudokusolver.SudokuModel;
import android.view.View;

/**
 * NumPadDialogueListener Class
 * @author etubil2
 *
 * When a number on the NumPad object is pressed
 * this is the class that listens and 
 * changes state/returns the value that was pressed
 */
public class NumPadDialogueListener implements android.view.View.OnClickListener {

	NumPadDialogue numPad;
	SudokuModel model;
	
	public NumPadDialogueListener(NumPadDialogue numPad,SudokuModel model){
		this.numPad = numPad;
		this.model = model;
	}
	
	@Override
	public void onClick(View button) {
		
		short value = getButtonValue(button);
		SudokuCellView orig = numPad.getCalledCell();
		
		int y = orig.getYCoord();
		int x = orig.getXCoord();
	
		//check to see if model accepts value
		boolean isSet = model.setAt(y, x, value);
		if(isSet){
			orig.setNumber(value);
		}
		numPad.dismiss();
	}

	/**
	 * Gets the button's value 0,1,2..9
	 * @param View button
	 * @return short 
	 */
	private short getButtonValue(View button) {
		int buttonID = button.getId();
		if(buttonID == R.id.buttonclear)
			return 0;
		if(buttonID ==  R.id.button1)
			return 1;
		if(buttonID ==  R.id.button2)
			return 2;
		if(buttonID ==  R.id.button3)
			return 3;
		if(buttonID ==  R.id.button4)
			return 4;
		if(buttonID ==  R.id.button5)
			return 5;
		if(buttonID ==  R.id.button6)
			return 6;
		if( buttonID ==  R.id.button7)
			return 7;
		if(buttonID ==  R.id.button8)
			return 8;
		if(buttonID ==  R.id.button9)
			return 9;
		
		return -1;
	}

}
