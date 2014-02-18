package sudoku.main.listeners;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import sudoku.main.MainActivity;
import sudoku.main.views.SudokuView;
import sudoku.musicgenerator.SudokuMusicLogic;
import sudoku.solver.sudokusolver.SudokuModel;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

/**
 * The Listener for the Play/Stop button
 * @author etubil2
 *
 */
public class PlayStopButtonListener implements android.view.View.OnClickListener{

	SudokuView view;
	SudokuModel model;
	MainActivity main;
	AtomicBoolean isPlaying;
	int selectResource;
	public PlayStopButtonListener(SudokuView view, SudokuModel model, MainActivity main){
		this.view = view;
		this.model = model;
		this.main = main;
		this.isPlaying = new AtomicBoolean(false);
		this.selectResource = 0;
	}
	
	
	@Override
	public void onClick(View v) {
		Button b = (Button)v;
		
		if(!this.isPlaying.get())
			startMusic(b);
		else
			stopMusic(b);
		
	}
	
	/**
	 * Sets which file to play
	 */
	public void setSelectProgression(int resource){
		this.selectResource = resource;
	}
	/**
	 * Start the playing and solving
	 */
	private void startMusic(Button b){
		this.isPlaying.set(true);
		view.enableAllButtons(false);
		
		b.setEnabled(true);
		b.setText("Stop");
		//start playing
		new Play().execute();
	}
	/**
	 * Stop it and reset.
	 * @param b
	 */
	private void stopMusic(Button b){
		b.setText("Play");
		
		this.isPlaying.set(false);
	}
	
	/**
	 * This is run in the background to play the music,
	 * can be stopped by pressing the Stop button
	 * @author etubil2
	 */
	class Play extends AsyncTask<Void,Stack<int[]>,Void>{
		
		
		@Override
		protected Void doInBackground(Void... params) {
			
			SudokuMusicLogic presenter = new SudokuMusicLogic(model,main,selectResource);
			Stack<int[]> result; 
			do{
				result = presenter.playAndSolve();
				if(result==null)return null;
				this.publishProgress(result);
			}while(result!=null&&isPlaying.get());

			return null;
		}


		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		
			view.reflectModel();
			view.enableAllButtons(true);

		}


		@Override
		protected void onProgressUpdate(Stack<int[]>... values) {
			super.onProgressUpdate(values);
			Stack<int[]> result = values[0];
			int[] first = result.pop();
			view.updateSudokuCell(first[0], first[1], first[2]+1);
			for(int[] i : values[0])
			view.updateSudokuCell(i[0], i[1], 0);
		}
		
	}
	
}
