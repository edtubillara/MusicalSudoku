package sudoku.solver.exactcover;

import java.util.Comparator;

/**
 * CompareECNodes -compares the column nodes and their length
 * @author etubil2
 *
 */
public class CompareECNodes implements Comparator<ECNode>{
    @Override
    public int compare(ECNode x, ECNode y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        if (x.getLength() > y.getLength())
        {
            return 1;
        }
        if (x.getLength() < y.getLength())
        {
            return -1;
        }
        return 0;
    }
}
