import java.awt.*;
import java.beans.PropertyChangeSupport;

public class GridBoard extends PropertyChangeSupport {

    private static final GridBoard instance = new GridBoard();

    private GridCell[][] grid;
    private int size;

    private GridBoard() {
        super(new Object());
        setSize(20); // default
    }

    public static GridBoard getInstance() {
        return instance;
    }

    public void setSize(int size) {
        this.size = size;
        grid = new GridCell[size][size];

        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                grid[y][x] = GridCell.EMPTY;

        firePropertyChange("gridReset", null, null);
    }

    public GridCell get(int x, int y) {
        return grid[y][x];
    }

    public void set(int x, int y, GridCell value) {
        GridCell prev = grid[y][x];
        grid[y][x] = value;
        firePropertyChange("cell", prev, new Point(x, y));
    }

    public int getSize() { return size; }
}
