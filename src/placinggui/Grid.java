/**
 *
 * @author Zach
 */

public class Grid
{
/**#########################################################################
# Data
##########################################################################*/
    private int grid[];
    public  static final int EMPTY = 0;
    public  static final int OCCUPIED = 1;
    public  static final int MISS = 2;
    public  static final int HIT = 3;
    
/**#########################################################################
# Constructors
##########################################################################*/
    public Grid() {
	grid = new int[100];
        for(int i = 0; i<100; i++)
            grid[i] = EMPTY;
    }
   
/**#########################################################################
# Methods
##########################################################################*/
    
    public boolean add(int[] b) {
	//Checks if the building is completely on the grid
        
        for(int i=1; i<b.length; i++)
            if (b[i] >= (b[0]/10)*10+10)
                return false;
	
        for(int i=0; i<b.length; i++)
            if(grid[b[i]]==OCCUPIED)
                return false;
        
        for(int i=0; i< b.length; i++)
            grid[b[i]] = OCCUPIED;
        return true;
    }
    
    public void remove(int[] b) {
        for(int i=0; i<b.length; i++)
            grid[b[i]] = EMPTY;
    }

    public int[] getOccupied()  {
        int numOccupied=0;
	for(int i=0; i<grid.length; i++)
            if(grid[i] == OCCUPIED)
                numOccupied++;
        
        int occ[] = new int[numOccupied];
        int index = 0;
        
        for(int i=0; i<grid.length; i++)
            if(grid[i] == OCCUPIED) {
                occ[index]=i;
                index++;
            }
        return occ;
    }
    
    public int guess(int i) {
        if(grid[i]==MISS || grid[i]==HIT)
            return 0;
        if(grid[i]==OCCUPIED) {
            grid[i] = HIT;
            return HIT;
        }
        grid[i]=MISS;
        return MISS;    
    }

    public int[] getGrid() {
        return grid;
    }

    public boolean isGameOver() {
        int count =0;
        for(int i = 0; i < grid.length; i++) {
            if (grid[i] == HIT)
                count++;
        }
        if (count >= 17)
            return true;
        return false;
    }
    
    public void putLocations(int[] locations) {
	grid = locations;
    }
}
