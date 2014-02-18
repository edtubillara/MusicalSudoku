package sudoku.main.views;

import sudoku.main.R;
import sudoku.main.listeners.OptionDialogListener;
import sudoku.main.listeners.PlayStopButtonListener;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

public class OptionDialogue extends Dialog{

	OptionDialogListener done;
	public OptionDialogue(Context context,SudokuView view, PlayStopButtonListener lis) {
		super(context);
		this.setTitle("Customize");
		this.setContentView(R.layout.optiondialog);
		setUpListener(view,lis);
		
	}

	private void setUpListener(SudokuView view, PlayStopButtonListener lis) {
		Button doneButton = (Button) this.findViewById(R.id.donewithoption);
		done = new OptionDialogListener(this,view,lis);
		doneButton.setOnClickListener(done);
	}

}
