package sudoku.musicgenerator;

/**
 * Class MusicNote
 * @author etubil2
 *
 * A MusicNote object contains information
 * to send to the pure data object to play as a
 * note
 */
public class MusicNote {

	private boolean playNote;
	private int midiNumber;
	private int velocity;
	private float harmEnv;

	/**
	 * Constructor
	 * @param playNote
	 * @param midiNumber
	 * @param velocity
	 * @param harmEnv
	 */
	public MusicNote(boolean playNote, int midiNumber, int velocity, float harmEnv){
		this.playNote=playNote;
		this.midiNumber=midiNumber;
		this.velocity=velocity;
		this.harmEnv = harmEnv;
	}
	
	public void setPlayNote(boolean playNote){
		this.playNote=playNote;
	}
	
	public int getMidiNumber(){
		return this.midiNumber;
	}
	public int getVeloctiy(){
		return this.velocity;
	}
	public float getHarmEnv(){
		return this.harmEnv;
	}
	public boolean isplayable(){
		return playNote;
	}
	
}
