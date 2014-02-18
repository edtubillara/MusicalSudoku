package sudoku.main.views;

import sudoku.main.R;
import sudoku.main.listeners.NumPadDialogueListener;
import sudoku.solver.sudokusolver.SudokuModel;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

/**
 * NumPad Class
 * @author etubil2
 *
 * This is the dialog box that pops up
 * when the user clicks a cell to select
 * a number to insert.
 *
 */
public class NumPadDialogue extends Dialog {
	
	Button[] numbers;
	NumPadDialogueListener listener;
	SudokuCellView currentCellView;
	SudokuModel model;
	/**
	 * Constructor
	 * @param context
	 * @param model
	 * @param view
	 */ 
	public NumPadDialogue(Context context,SudokuModel model, SudokuView view) {
		super(context);
		this.setTitle("Enter Number");
		this.setContentView(R.layout.numpad);
		listener = new NumPadDialogueListener(this,model);
		setUpButtons();
		this.model = model;
	}

	/**
	 * Shows the dialogue
	 * @param SudokuCellView cellView -original cell that was activated
	 */
	public void show(SudokuCellView cellView){
		this.currentCellView = cellView;
		showOnlyPossibleNumbers();
		this.show();
		
	}
	/**
	 * Show's only the possible numbers
	 */
	private void showOnlyPossibleNumbers() {
		int y = currentCellView.getYCoord();
		int x = currentCellView.getXCoord();
		
		numbers[1].setEnabled(!model.restrictionIsSatisfied(y, x, 1));
		numbers[2].setEnabled(!model.restrictionIsSatisfied(y, x, 2));
		numbers[3].setEnabled(!model.restrictionIsSatisfied(y, x, 3));
		numbers[4].setEnabled(!model.restrictionIsSatisfied(y, x, 4));
		numbers[5].setEnabled(!model.restrictionIsSatisfied(y, x, 5));
		numbers[6].setEnabled(!model.restrictionIsSatisfied(y, x, 6));
		numbers[7].setEnabled(!model.restrictionIsSatisfied(y, x, 7));
		numbers[8].setEnabled(!model.restrictionIsSatisfied(y, x, 8));
		numbers[9] .setEnabled(!model.restrictionIsSatisfied(y, x, 9));
		
		
	}

	/**
	 * Gets the Sudoku Cell that was activated
	 */
	public SudokuCellView getCalledCell(){
		return this.currentCellView;
	}
	
	
	/**
	 * Adds the OnclickListener/NumPadListener to the buttons
	 */
	private void setUpButtons() {
		numbers =  new Button[10];
		numbers[0] = (Button) this.findViewById(R.id.buttonclear);
		numbers[1] = (Button) this.findViewById(R.id.button1);
		numbers[2] = (Button) this.findViewById(R.id.button2);
		numbers[3] = (Button) this.findViewById(R.id.button3);
		numbers[4] = (Button) this.findViewById(R.id.button4);
		numbers[5] = (Button) this.findViewById(R.id.button5);
		numbers[6] = (Button) this.findViewById(R.id.button6);
		numbers[7] = (Button) this.findViewById(R.id.button7);
		numbers[8] = (Button) this.findViewById(R.id.button8);
		numbers[9] = (Button) this.findViewById(R.id.button9);
		
		for(int i =0; i < 10; i++){
			numbers[i].setOnClickListener(listener);
		}
 
		  
	}
	
}
