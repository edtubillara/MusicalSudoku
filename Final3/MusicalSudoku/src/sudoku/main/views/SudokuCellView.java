package sudoku.main.views;

import sudoku.main.R;
import sudoku.main.listeners.SudokuCellListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
/**
 * SudokuCellView Class
 * @author etubil2
 *
 * This is the cell that the user taps to 
 * view/input a number.
 */ 
@SuppressLint("ViewConstructor")
public class SudokuCellView extends TextView {
	private int y;
	private int x;
	private String spacing;
	/**
	 * Constructor
	 * @param Context context 
	 * @param SudokuCellListener listener
	 * @param int y -Y Coordinate
	 * @param int x -X Coordinate 
	 */
	public SudokuCellView(Context context, SudokuCellListener listener, int y, int x) {
		super(context);
		this.setOnClickListener(listener);

		this.setBackgroundResource(R.layout.bluetheme);
		setTextSize(context);

		this.setPadding(15,5, 15, 5);
		this.setTextColor(Color.WHITE);
		this.y=y;
		this.x=x;
		this.setNumber(0);
		
	}

	public void setTextSize(Context context) {
		DisplayMetrics dm = getResources().getDisplayMetrics();

	    double x = Math.pow(dm.widthPixels/dm.xdpi,2);
	    double y = Math.pow(dm.heightPixels/dm.ydpi,2);
	    double screenInches = Math.sqrt(x+y);

		int textSize;
				
		if(screenInches >6){
			textSize = 30;
			spacing = "   ";
		}
		else{ 
			textSize = 18;
			spacing = "  ";
		}
	
		this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	}

	public int getXCoord(){
		return this.x;
	}
	public int getYCoord(){
		return this.y;
	}
	public void setNumber(int value){
		int color = getColorFromNumber(value);
		if(value==0)
			this.setText(spacing);
		else this.setText(Integer.toString(value));
		this.setTextColor(color);
	}
	/**
	 * Give a number and get a color
	 */
	private int getColorFromNumber(final int val){
		
		if(val == 1){
			return Color.BLUE;
		} else if(val == 2){
			return Color.MAGENTA;
		} else if(val == 3){
			return Color.GREEN;
		} else if(val == 4){
			return Color.YELLOW;
		} else if(val == 5){
			return Color.RED;
		} else if(val == 6){
			return Color.CYAN;
		} else if(val == 7){
			return Color.WHITE;
		} else if(val == 8){
			return Color.rgb(0x93, 0x70, 0xdb);
		} else {
			return Color.rgb(0xff, 0xa5, 0x00);
		} 
	}

}
