package sudoku.musicgenerator;

import java.io.InputStream;
import java.util.Stack;

import sudoku.main.MainActivity;
import sudoku.main.R;
import sudoku.solver.sudokusolver.SudokuModel;

/**
 * Class SudokuMusicBoxPresenter
 * 
 * @author etubil2
 * 
 *         This class connects the MusicBox/View/Model It is called when the
 *         user requests something to be played
 * 
 */
public class SudokuMusicLogic extends Thread {
	private ChordProgression chords;
	private SudokuMusicSolver solver;
	private int currentChordNum;
	
	private final long WAIT_TIME = 400; //how much time to wait between time slices
	private final int MAX_NOTES = 4;//max notes of a chord
	private final int MELODY_NUMBER = 4;//which voice has the melody
	
	private SudokuMusicBox mBox;

	/**
	 * Constructor
	 * 
	 * @param SudokuModel
	 *            model
	 * @param SudokuMusicBox
	 *            mBox
	 * @param android
	 *            Activity main
	 */
	public SudokuMusicLogic(SudokuModel model,
			MainActivity main, int selectProgression) {
		int songResource;
		if(selectProgression == 0){
			songResource = R.raw.chordprog1;
		} else if(selectProgression == 1){
			songResource = R.raw.chordprog2;
		} else{
			songResource = R.raw.chordprog3;
		}
		this.mBox = main.getMusicBox();
		InputStream in = main.getResources().openRawResource(songResource);
		chords = ParseProgression.ParseChordProgressionFile(in);
		solver = new SudokuMusicSolver(model);
	}

	/**
	 * play a chord and solve one step of the puzzle
	 * and then play a melody note based on number inserted
	 */
	public Stack<int[]> playAndSolve() {

		currentChordNum %= chords.getDurationLength();

		Stack<int[]> result = solveAndPlayMelody();

		currentChordNum++;

		return result;
	}

	/**
	 * Solve a step and play the melody based
	 * on the number inserted
	 */
	private Stack<int[]> solveAndPlayMelody() {
		MelodyMap m = chords.getMelodyMap(currentChordNum);
		
		Stack<int[]> result= solver.solveAStep(m);
		if (result == null ||result.peek()==null)
			return null;
		
		int[] insertedResult = result.peek();
		
		MusicNote note = m.getNote(insertedResult[2]);
		playChord();
		mBox.playNote(note, MELODY_NUMBER);

		waitAWhile();
		return result;
	}

	/**
	 * play a chord
	 */
	private void playChord() {
		Chord currChord = chords.getChord(currentChordNum);
		if (currChord != null) {
			for (int j = 0; j < MAX_NOTES ; j++) {
				MusicNote note = currChord.getNote(j);
				if (note != null) {
					mBox.playNote(note, j);
				}
			}
		}
	}

	/**
	 * Waits until next note
	 */
	private void waitAWhile() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// do nothing
			e.printStackTrace();
		}
	}

}
