/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zach
 */
public class Test 
{
    public static void main(String args[])
    {
        Grid g = new Grid();
        g.printBuildings();
        
        System.out.println(">> End Test 1 <<");
        
        int[] t = {12,13,14};
        g.add(new Building(t,"3A"));
        g.printBuildings();
        
        System.out.println(">> End Test 2 <<");
        
        int[] t1 = {14,15,15};
        g.add(new Building(t1,"3B"));
        g.printBuildings();
        
        System.out.println(">> End Test 3 <<");
        
        System.out.println(">> End Test 4 <<");
        
        System.out.println(">> End Test 5 <<");
        
        System.out.println(">> End Test 6 <<");
    }
    
}
