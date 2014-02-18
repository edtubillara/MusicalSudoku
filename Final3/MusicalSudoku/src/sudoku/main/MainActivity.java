package sudoku.main;

import sudoku.main.views.SudokuView;
import sudoku.musicgenerator.SudokuMusicBox;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
 

/** 
 * Main Activity
 * @author etubil2 
 *
 */ 
public class MainActivity extends Activity {

	private SudokuMusicBox  smBox; //Used to talk to pure data service
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		startGUI();
		
		startMusicBox();
		
	}

	private void startGUI() {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		SudokuView sudokuView = new SudokuView(this);
		
		setContentView(sudokuView);
	}
	
	private void startMusicBox(){
			smBox = new SudokuMusicBox(this);
			smBox.startPD();
	}
	
	public SudokuMusicBox getMusicBox(){
		return smBox;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		smBox.onStart();
	}
	
	@Override
	protected void onStop() {
		smBox.onStop();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		smBox.onDestroy();
		super.onDestroy();
	}	
	
	
	
	
	
}
