package sudoku.musicgenerator;

/**
 * Class MelodyMap
 * @author etubil2
 *
 * A melody map is an object that 
 * takes in an integer and returns a note
 *
 */
public class MelodyMap {
	private int[] midiNoteArray = new int[9];
	private final float HARM_ENV = (float)0.1;
	private final int VELOCITY = 100;
	public MelodyMap(){
		midiNoteArray = new int[9];
	}
	
	public MelodyMap(int[] midiNoteArray){
		for(int i=0; i < midiNoteArray.length;i++){
			this.midiNoteArray[i]=midiNoteArray[i];
		}	
	}
	public int getMidi(int i){
		if(i<1||i>9)
			return 0;
			
		return midiNoteArray[i-1];
	}
	public MusicNote getNote(int i){
		int midiVal = getMidi(i);
		MusicNote note = new MusicNote(true,midiVal,VELOCITY, HARM_ENV);
		return note;
	}
	
}
