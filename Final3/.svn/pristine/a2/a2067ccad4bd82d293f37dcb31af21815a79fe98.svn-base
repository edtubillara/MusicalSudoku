package sudoku.musicgenerator;

/**
 * Class that holds chords and probable melodies
 * @author etubil2
 *
 */
public class ChordProgression {
	
	private Chord[] progression;
	private MelodyMap[] melodyMaps; 
	private int numChords;
	private int maxNotes;
	
	/**
	 * Constructor
	 * @param numChords
	 * @param maxNotes
	 */
	public ChordProgression(int numChords, int maxNotes) {
		this.numChords=numChords;
		progression = new Chord[numChords];
		melodyMaps = new MelodyMap[numChords];
		this.maxNotes = maxNotes;
	}

	public Chord getChord(int loc){
		return progression[loc];
	}
	
	public void setNote(int progressionLoc, int instrumentNumber, MusicNote note){
		if(progression[progressionLoc]==null){
			Chord chord = new Chord(maxNotes);
			progression[progressionLoc]=chord;
		}
		
		progression[progressionLoc].setNote(instrumentNumber, note);
	}
	
	/**
	 * Gets the length of the progression
	 * @return
	 */
	public int getDurationLength(){
		return numChords;
	}
	
	public void setMelodyMap(int loc, MelodyMap mMap){
		melodyMaps[loc]=mMap;
	}
	public MelodyMap getMelodyMap(int loc){
		return melodyMaps[loc];
	}
}
