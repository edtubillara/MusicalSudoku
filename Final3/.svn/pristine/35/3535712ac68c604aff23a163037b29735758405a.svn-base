package sudoku.musicgenerator;
import java.io.File;
import java.io.IOException;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.core.PdBase;
import org.puredata.core.utils.IoUtils;

import sudoku.main.R;
import android.app.Activity;
import android.widget.Toast;
/**
 * Class SudokuMusicBox 
 * @author etubil2
 *
 * This class is a wrapper for 
 * the Pure Data for Android Library.
 * 
 */
public class SudokuMusicBox {

	Activity main;
	private static final int MIN_SAMPLE_RATE = 44100;
	private static final String TAG = "Musical Sudoku";

	public SudokuMusicBox(Activity context) {
		this.main = context;
	}
	
	public void startPD() {
		try {
			initPd();
		} catch (IOException e) {
			toast(e.toString());
			main.finish();
		}
	}

	private void initPd() throws IOException {
		AudioParameters.init(main);

		int srate = Math.max(MIN_SAMPLE_RATE,
				AudioParameters.suggestSampleRate());
		PdAudio.initAudio(srate, 0, 2, 1, true);

		File dir = main.getFilesDir();
		File patchFile = new File(dir, "synthRecieve.pd");
		IoUtils.extractZipResource(
				main.getResources().openRawResource(R.raw.patch), dir, true);
		PdBase.openPatch(patchFile.getAbsolutePath());
	}

	public void cleanup() {
		// make sure to release all resources
		PdAudio.release();
		PdBase.release();
	}

	public void onStart() {

		PdAudio.startAudio(main);
	}

	public void onStop() {
		PdAudio.stopAudio();

	}

	public void onDestroy() {
		cleanup();
	}

	public void playNote(int instrumentNumber, int midiValue, int velocity,
			float harmEnv) {
		PdBase.sendList("param", instrumentNumber, midiValue, velocity, harmEnv);
	}

	/**
	 * Sends a note to the Pure Data Module object to be played
	 * @param note
	 * @param instrumentNumber
	 */
	public void playNote(MusicNote note, int instrumentNumber) {
		int midiValue = note.getMidiNumber();
		int velocity = note.getVeloctiy();
		float harmEnv = note.getHarmEnv();
		playNote(instrumentNumber + 1, midiValue, velocity, harmEnv);

	}

	
	/**
	 * The toast is to send messages to the user incase something went wrong
	 */
	
	private Toast toast = null;
	
	public void toast(final String msg) {
		main.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (toast == null) {
					toast = Toast.makeText(main.getApplicationContext(), "",
							Toast.LENGTH_SHORT);
				}
				toast.setText(TAG + ": " + msg);
				toast.show();
			}
		});
	}
	
}
