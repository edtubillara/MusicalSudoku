package sudoku.main.listeners;

import sudoku.main.R;
import sudoku.main.views.OptionDialogue;
import sudoku.main.views.SudokuView;
import android.view.View;
import android.widget.RadioGroup;

public class OptionDialogListener implements android.view.View.OnClickListener {

	OptionDialogue optionDialog;
	SudokuView sudokuView;
	PlayStopButtonListener listen;
	
	int currentColor;
	int currentProgression;
	
	
	public OptionDialogListener(OptionDialogue origin,	SudokuView sudokuView,
	PlayStopButtonListener listen){
		this.optionDialog=origin;
		this.sudokuView = sudokuView;
		this.listen = listen;
	}
	
	
	@Override
	public void onClick(View arg0) {
		optionDialog.dismiss();
		
		//update the colors and set of chord progressions
		setSelectedColor();
		setSelectedProgression();
			
	}


	private void setSelectedProgression() {
		RadioGroup progressionGroup = (RadioGroup) optionDialog.findViewById(R.id.radioselectchordprogression);
		int progressionID = progressionGroup.getCheckedRadioButtonId();
		
		if(progressionID == R.id.radioButtonProg1){
			currentProgression = 0;
		} else if (progressionID == R.id.radioButtonProg2){
			currentProgression = 1;
		} else {
			currentProgression = 2;
		}
		
		listen.setSelectProgression(currentProgression);
	}


	private void setSelectedColor() {
		RadioGroup progressionGroup = (RadioGroup) optionDialog.findViewById(R.id.radioselectcolor);
		int progressionID = progressionGroup.getCheckedRadioButtonId();
		
		if(progressionID == R.id.radioButtonBlue){
			currentColor = 0;
		} else if (progressionID == R.id.radioButtonRed){
			currentColor = 1;
		} else {
			currentColor = 2;
		}
		
		sudokuView.setColorTheme(currentColor);
		
	}


}
