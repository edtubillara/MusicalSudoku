package sudoku.main.views;

import sudoku.main.MainActivity;
import sudoku.main.R;
import sudoku.main.listeners.ClearAllButtonListener;
import sudoku.main.listeners.PlayStopButtonListener;
import sudoku.main.listeners.SolveButtonListener;
import sudoku.main.listeners.SudokuCellListener;
import sudoku.solver.sudokusolver.SudokuModel;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * SudokuView Class
 * 
 * @author etubil2
 * 
 *         This is the view object that is shown on the android screen.
 * 
 *         it should look like:
 * 
 *         Sudoku Grid 9x9 Solve Button Clear All Button Play/Stop Button
 * 
 */
@SuppressLint("ViewConstructor")
public class SudokuView extends LinearLayout {

	private NumPadDialogue numPad; // a dialogue pop-up
	private SudokuCellListener cellListen;
	private OptionDialogue optionDialogue;
	private PlayStopButtonListener playListener;
	private SudokuModel model;
	public SudokuCellView[] cellCollection;
	private int _cellIndex;
	private int colorResource;
	public final Button solveButton;
	public final Button playStopButton;
	public final Button clearButton;
	public final Button optionButton;
	private MainActivity main;

	public SudokuView(MainActivity context) {
		super(context);
		this.main = context;
		solveButton = new Button(context);
		playStopButton = new Button(context);
		clearButton = new Button(context);
		optionButton = new Button(context);
		initialize(context);
		this.setBackgroundColor(Color.BLACK);
	}

	/**
	 * Initializes the view/model/listeners
	 * 
	 * @param context
	 */
	private void initialize(Context context) {
		model = new SudokuModel();
		numPad = new NumPadDialogue(context, model, this);
		cellListen = new SudokuCellListener(numPad);
		cellCollection = new SudokuCellView[81];


		this.setOrientation(1);
		this.setGravity(Gravity.CENTER);
		this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout
				.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));

		createSudokuGrid(context);
	
		addButtons(context);
		

	}

	public void addButtons(Context context) {
		addSolveButton();
		addClearButton();
		addPlayStopButton();
		optionDialogue = new OptionDialogue(context,this,playListener);
		addOptionButton();
		
		TableLayout buttonTable = new TableLayout(context);
		TableRow buttonRow1 = new TableRow(context);
		TableRow buttonRow2 = new TableRow(context);
		
		buttonTable.setStretchAllColumns(true);
		
		buttonRow1.addView(this.solveButton);
		buttonRow1.addView(this.clearButton);
		buttonRow2.addView(this.playStopButton);
		buttonRow2.addView(this.optionButton);
		
		buttonTable.addView(buttonRow1);
		buttonTable.addView(buttonRow2);
		
		this.addView(buttonTable);

		buttonRow1.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1f));
	

		buttonRow2.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1f));
	
	}

	/**
	 * Adds the "Play" button
	 */
	private void addPlayStopButton() {
		playStopButton.setText("Play");
		playListener = new PlayStopButtonListener(this,model, main);
		playStopButton.setOnClickListener(playListener);
		playStopButton.setTextColor(Color.GREEN);
	}

	/**
	 * Adds the "Solve" button
	 */
	private void addSolveButton() {
		solveButton.setText("Solve");
		solveButton.setOnClickListener(new SolveButtonListener(this, model));
		solveButton.setTextColor(Color.YELLOW);
	}

	/**
	 * Adds the "Clear All" button
	 */
	private void addClearButton() {
		clearButton.setText("Clear All");
		clearButton.setOnClickListener(new ClearAllButtonListener(this, model));
		clearButton.setTextColor(Color.WHITE);
	}

	/**
	 * Adds the "Options" button
	 */
	private void addOptionButton() {
		optionButton.setText("Options");
		optionButton.setTextColor(Color.MAGENTA);
		optionButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				optionDialogue.show();
			}
		});
	}

	/**
	 * Creates the main table layout of the sudoku grid
	 */
	private void createSudokuGrid(Context context) {
		TableLayout sudokuBG = new TableLayout(context);
	
		sudokuBG.setGravity(Gravity.CENTER_HORIZONTAL);
		sudokuBG.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT));


		// add the rows
		TableRow[] boxRows = new TableRow[3];
		sudokuBG.setPadding(10, 10, 10, 10);

		for (int i = 0; i < 3; i++) {
			createSudokuGridRow(context, sudokuBG, boxRows, i);

		}
	
		this.addView(sudokuBG);
		
	}

	public void createSudokuGridRow(Context context, TableLayout sudokuBG,
			TableRow[] boxRows, int i) {
		boxRows[i] = new TableRow(context);
		boxRows[i].setGravity(Gravity.CENTER);
		boxRows[i].addView(createNumberGrid(context, i * 3, 0));
		boxRows[i].addView(createNumberGrid(context, i * 3, 3));
		boxRows[i].addView(createNumberGrid(context, i * 3, 6));
		sudokuBG.addView(boxRows[i]); 
	}

	/**
	 * Creates a number grid, the box sections
	 */
	private TableLayout createNumberGrid(Context context, int y, int x) {
		TableLayout numberGrid = new TableLayout(context);
		numberGrid.setGravity(Gravity.CENTER);

		numberGrid.setPadding(10, 10, 10, 10);
		numberGrid.setBackgroundColor(Color.BLACK);

		TableRow[] numberRows = new TableRow[3];

		for (int i = 0; i < 3; i++) {
			numberRows[i] = new TableRow(context);
			numberRows[i].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
			numberRows[i].addView(createTextField(context, y + i, x));
			numberRows[i].addView(createTextField(context, y + i, x + 1));
			numberRows[i].addView(createTextField(context, y + i, x + 2));
			numberGrid.addView(numberRows[i]);
		
		}
		return numberGrid;
	}

	/**
	 * Creates a Sudoku cell. 81 are assumed to be made
	 */
	@SuppressLint("NewApi")
	private TextView createTextField(Context context, int y, int x) {
		SudokuCellView cell = new SudokuCellView(context, this.cellListen, y, x);
		cellCollection[_cellIndex++] = cell;
		return cell;
	}

	/**
	 * updates a sudoku cell view
	 */
	public void updateSudokuCell(final int y, final int x, final int value) {

		main.runOnUiThread(new Runnable() {
			public void run() {
				SudokuCellView view = findCell(y, x);
				view.setNumber(value);
			}
		});

	}

	/**
	 * Disables/Enables all Buttons
	 */
	public void enableAllButtons(final boolean enable) {
		main.runOnUiThread(new Runnable() {
			public void run() {
				solveButton.setEnabled(enable);
				playStopButton.setEnabled(enable);
				clearButton.setEnabled(enable);
				optionButton.setEnabled(enable);
				for (int j = 0; j < 81; j++) {
					SudokuCellView currCell = cellCollection[j];
					currCell.setEnabled(enable);
				}
			}
		});

	}

	/**
	 * The view updates itself to reflect the current sudoku model
	 */
	public void reflectModel() {
		main.runOnUiThread(new Runnable() {
			public void run() {
				for (int j = 0; j < 81; j++) {

					SudokuCellView currCell = cellCollection[j];

					int y = currCell.getYCoord();
					int x = currCell.getXCoord();

					short value = model.getAt(y, x);

					currCell.setNumber(value);

				}
			}
		});

	}

	/**
	 * Finds and returns the cell with the coordinates.
	 */
	private SudokuCellView findCell(int y, int x) {
		for (int i = 0; i < 81; i++) {
			SudokuCellView currCell = this.cellCollection[i];
			boolean validX = currCell.getXCoord() == x;
			boolean validY = currCell.getYCoord() == y;
			if (validX && validY)
				return currCell;

		}
		return null;
	}

	/**
	 * Returns the main activity
	 */
	public void showErrorMessage(String message) {
		this.main.getMusicBox().toast(message);
	}
	
	/**
	 * Sets the color theme
	 */
	public void setColorTheme(int colorSelected){
		
	
		if(colorSelected == 0)
			colorResource = R.layout.bluetheme;
		else if(colorSelected == 1)
			colorResource = R.layout.redtheme;
		else
			colorResource = R.layout.greentheme;
		
		main.runOnUiThread(new Runnable() {
			
			public void run() {
				for(int i =0; i < cellCollection.length; i++){
					cellCollection[i].setBackgroundResource(colorResource);
				}
			}
		});
		
	}
}