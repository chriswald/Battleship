
/**
 *
 * @author Zach
 */
public class Building 
{
    private int[] location;
    private String name;

    public Building(int[] s, String n) 
    {
        location = s;
        name = n;
    }

    public int[] getLoc() 
    {
        return location;
    }
    public int getX()
    {
        return location[0];
    }
    public int getY()
    {
        return location[1];
    }
    public String getName() 
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        String t = "Name:" + name + ", " + "Location:";
        for(int i=0; i<location.length-1; i++)
            t+=location[i]+"-";
        t+=location[location.length-1];
        return t;
    }
    public void print()
    {
        System.out.println(this.toString());
    }
}
