package musicalsudoku.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import sudoku.musicgenerator.Chord;
import sudoku.musicgenerator.ChordProgression;
import sudoku.musicgenerator.MusicNote;
import sudoku.musicgenerator.ParseProgression;


public class TestFileParse {
	String DIR = "/home/masterjildo/workspace/ParseChordProgression/src/";
	
	/**
	 * Test that grabbing the notes is correct for one chord
	 */
	@Test
	public void test1() {
		
		try {
			FileInputStream in = new FileInputStream(DIR + "test1.txt");
			ChordProgression progression = ParseProgression.ParseChordProgressionFile(in);
			
			assertTrue(progression.getDurationLength()==1);
			Chord chord = progression.getChord(0);
			for(int i =0; i < 4; i++){
			MusicNote note1 = chord.getNote(i);
				assertTrue(note1.getMidiNumber()==60);
				assertTrue(note1.getVeloctiy()==100);
				assertTrue(note1.getHarmEnv()==(float)0.4);
			}			
			
			
		} catch (FileNotFoundException e) {
			fail("could not open file");
		}
	}
	/**
	 * Test that grabbing the chords is correct for 4 time slots
	 */
	@Test
	public void test2() {
		
		try {
			FileInputStream in = new FileInputStream(DIR + "test2.txt");
			ChordProgression progression = ParseProgression.ParseChordProgressionFile(in);
			
			assertTrue(progression.getDurationLength()==4);
			
			for(int i =0; i < 4; i++){
				Chord chord = progression.getChord(i);
				MusicNote note1 = chord.getNote(i);
				assertTrue(note1.getMidiNumber()==60);
				assertTrue(note1.getVeloctiy()==100);
				assertTrue(note1.getHarmEnv()==(float)0.4);
			}			
			
			
		} catch (FileNotFoundException e) {
			fail("could not open file");
		}
	}

}
