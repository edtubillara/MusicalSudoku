package sudoku.musicgenerator;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ParseProgression
 * @author etubil2
 *
 * This class builds a ChordProgression object from a
 * file. See the example in the res/raw/ to see format of 
 * such a file.
 */
public class ParseProgression {

	/**
	 * Returns a ChordProgression object based on the file from 
	 * the file's Inputstream
	 */
	public static ChordProgression ParseChordProgressionFile(InputStream in){
		return startParsingFile(in);
	}
	
	
	private static ChordProgression startParsingFile(InputStream in){
		try{
			List<String> lines = getLines(in);
			
			ChordProgression progression = parseHeader(lines);
			
			parseBody(progression,lines);
			
			
			return progression;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
			
	}

	/**
	 * Get all the lines in the file first
	 */
	private static List<String> getLines(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		List<String> lines = new ArrayList<String>();
		
		String line;
		
		while ((line = br.readLine()) != null) {
		    lines.add(line);
		}
		return lines;
	}
	
	/**
	 * The first line is a header line. It contains the length
	 * of the chord progression and number of total notes that
	 * can be played at the same time
	 */
	private static ChordProgression parseHeader(List<String> lines ){
		String[] chordLine = lines.get(0).split("\\s+");
		int numTotalChords = Integer.parseInt(chordLine[0]);
		int numMaxNotesPlayed = Integer.parseInt(chordLine[1]);
		ChordProgression progression = new ChordProgression(numTotalChords,numMaxNotesPlayed);
		return progression;
	}

	/**
	 * Goes through the lines and gets the notes and melody mappings
	 */
	private static void parseBody(ChordProgression progression, List<String> lines ){

		for(int i =0; i < lines.size(); i++){
			String[] noteLine = lines.get(i).split("\\s+");
			String noteType = noteLine[0];
			
			if(noteType.equals("c"))
				insertNote(progression, noteLine);
			else if(noteType.equals("m"))
				insertMelody(progression, noteLine);
		}
	}

	/**
	 * Inserts a note into the chord progression
	 */
	private static void insertNote(ChordProgression progression, String[] noteLine){
		
		int instrumentNumber = Integer.parseInt(noteLine[1]);
		int midiValue = Integer.parseInt(noteLine[2]);
		int velocity = Integer.parseInt(noteLine[3]);
		float harm = Float.parseFloat(noteLine[4]);
		int time = Integer.parseInt(noteLine[5]);
		
		MusicNote note = new MusicNote(true, midiValue, velocity, harm);
		progression.setNote(time, instrumentNumber, note);
	}
	
	/**
	 * Inserts a melody mapping into the chord progression
	 */
	private static void insertMelody(ChordProgression progression, String[] noteLine){
		int progressionLoc = Integer.parseInt(noteLine[1]);
		int[] possibleNotes = new int[9];
		for(int i =2; i < 11;i++){
			possibleNotes[i-2]=Integer.parseInt(noteLine[i]);
		}
		
		MelodyMap mMap = new MelodyMap(possibleNotes);
		progression.setMelodyMap(progressionLoc, mMap);
	}
}