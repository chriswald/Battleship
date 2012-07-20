/**
 *
 * @author Zach
 */

public class Grid
{
/**#########################################################################
# Data
##########################################################################*/
    private Building[] buildings;
    private int buildingsCount;
    private int[] occupied;
    private int occupiedCount;
    private final int NUM_BUILDINGS = 5;
    private final int TOTAL_BUILDING_TILES = 17;
    
/**#########################################################################
# Constructors
##########################################################################*/
    public Grid()
    {
        buildings = new Building[NUM_BUILDINGS];
        buildingsCount = 0;
        occupiedCount = 0;
        occupied = new int[TOTAL_BUILDING_TILES];
    }
   
/**#########################################################################
# Methods
##########################################################################*/
    public Building[] getBuildings()
    {
        Building[] tmp = new Building[buildingsCount-1];
        System.arraycopy(buildings, 0, tmp, 0, buildingsCount);
        return tmp;
    }
    
    public void printBuildings()
    {
        System.out.println("----------------------------------");
        for(int i=0; i<buildingsCount; i++)
        {
            System.out.println(buildings[i]);
        }
        System.out.println("----------------------------------");
    }
    
    public boolean add(Building b)
    {
        //Checks for duplicate buildings
        for(int i=0; i<buildingsCount; i++)
            if(buildings[i].getName().equals(b.getName()))
            {
                System.out.println("Cannot add duplicate building to the grid");
                return false;
            }
        
        //Checks if the grid is already full
        if(buildingsCount == NUM_BUILDINGS)
        {
            System.out.println("Cannot add Building: Grid is already full");
            return false;
        }
        
        //Checks if the building overlaps with another building
        for(int i=0; i<b.getLoc().length; i++)
            for(int c=0; c<occupiedCount; c++)
                if(b.getLoc()[i] == occupied[c])
                {
                    System.out.println("Cannot add building: Overlapping Location");
                    return false;
                }
        
        //Updates the occupied array with the buildings coordinates
        for(int i=0; i<b.getLoc().length; i++)
        {
            int t = b.getLoc()[i];
            occupied[occupiedCount] = t;
            occupiedCount++;
        }
        
        //Adds the building
        buildings[buildingsCount] = b;
        buildingsCount++;
        return true;
    }
    
    public boolean remove(String n)
    {
        //Selection sort to find the index of the building
        int index = -1;
        for(int i=0; i<buildingsCount; i++)
            if(buildings[i].getName().equals(n))
                index = i;
        
        //Removes building if the building is found
        if(index != -1)
        {
            //Removes the buildings coordinated from the occupied array
            for(int i=0; i<buildings[index].getLoc().length; i++)
                for(int c=0; c<occupiedCount; c++)
                    if(occupied[c] == buildings[index].getLoc()[i])
                    {
                        for(int v=c; v<occupiedCount - 1; v++)
                            occupied[i] = occupied[i+1];
                        occupiedCount--;
                    }
            //Removes the building itself
            for(int i=index; i<buildingsCount-1; i++)
                buildings[i] = buildings[i+1];
            buildingsCount--;
            return true;
        }
        
        //Case if the building isnt found
        System.out.println("Building not found in Grid");
        return false;
    }
}