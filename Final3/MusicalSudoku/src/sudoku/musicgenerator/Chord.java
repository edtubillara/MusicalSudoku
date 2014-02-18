package sudoku.musicgenerator;


/**
 * Class that holds a group of notes
 * @author etubil2
 *
 */
public class Chord {

MusicNote[] notes;

	/**
	 * Constructor
	 * @param size
	 */
	public Chord(int size){
		notes = new MusicNote[size];
	}
	
	public void setNote(int instrumentNumber,MusicNote note){
		notes[instrumentNumber]=note;
	}
	
	public MusicNote getNote(int instrumentNumber){
		return notes[instrumentNumber];
	}
	

}
