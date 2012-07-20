import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 @author Christopher J. Wald
 @date April 3, 2012
*/

public class Prototype extends javax.swing.JFrame implements ActionListener
{
    private int[] grid_info = new int[100];
    private final int[] ship = {62, 63, 64, 65, 66};
    private int orig_ship_hit_spot = -1;
    private boolean guessing_ship = false;
    private final int EMPTY = 0;
    private final int MISS  = 1;
    private final int HIT   = 2;
    private int last_guess  = 0;
    private int llast_guess = 0;
    private int num_guesses = 0;
    private boolean have_direction = false;
    private Timer timer;

    /** Creates new form Prototype */
    public Prototype()
    {
	for (int i = 0; i < grid_info.length; i ++){
	    grid_info[i] = EMPTY;
	}
	timer = new Timer(250, this);
	timer.start();
        initComponents();
    }

    public void actionPerformed(ActionEvent evt){
	AI_Guess();
    }

    private void AI_Guess(){
	if (num_guesses == 100){
	    timer.stop();
	    return;
	}

	if (ship_sunk(ship)) {
	    guessing_ship = false;
	    have_direction = false;
	    timer.stop();
	    return;
	}

	if (!guessing_ship) {
	    int grid_spot = (int) (Math.random() * 100);
	    while (grid_info[grid_spot] != EMPTY) {
		grid_spot = (int) (Math.random() * 100);
	    }
	    
	    if (hit_ship(ship, grid_spot))
		update_grid_info(grid_spot, HIT);
	    else
		update_grid_info(grid_spot, MISS);

	    return;
	} else {
	    if (grid_info[last_guess] <= MISS && grid_info[llast_guess] <= MISS) {
		int next_spot = orig_ship_hit_spot;
		if (next_spot - 10 >= 0 && grid_info[next_spot - 10] == EMPTY) {
		    next_spot -= 10;
		}
		else if (next_spot + 1 < 100 && grid_info[next_spot + 1] == EMPTY) {
		    next_spot ++;
		}
		else if (next_spot + 10 < 100 && grid_info[next_spot + 10] == EMPTY) {
		    next_spot += 10;
		}
		else {
		    next_spot --;
		}
		
		if (hit_ship(ship, next_spot))
		    update_grid_info(next_spot, HIT);
		else
		    update_grid_info(next_spot, MISS);
		return;
	    }
	    if (grid_info[last_guess] <= MISS && grid_info[llast_guess] == HIT) {
		if (have_direction) {
		    int next_spot = orig_ship_hit_spot;
		    next_spot += -(last_guess - llast_guess);
		    if (next_spot < 0 || next_spot >= 100) {
			do {
			    next_spot += -(last_guess - llast_guess);
			} while(grid_info[next_spot] != EMPTY);
		    }
		    if (hit_ship(ship, next_spot))
			update_grid_info(next_spot, HIT);
		    else
			update_grid_info(next_spot, MISS);
		    return;
		} else {
		    int next_spot = orig_ship_hit_spot;
		    if (next_spot - 10 >= 0 && grid_info[next_spot - 10] == EMPTY) {
			next_spot -= 10;
		    }
		    else if (next_spot + 1 < 100 && grid_info[next_spot + 1] == EMPTY) {
			next_spot ++;
		    }
		    else if (next_spot + 10 < 100 && grid_info[next_spot + 10] == EMPTY) {
			next_spot += 10;
		    }
		    else {
			next_spot --;
		    }
		    
		    if (hit_ship(ship, next_spot))
			update_grid_info(next_spot, HIT);
		    else
			update_grid_info(next_spot, MISS);
		    return;
		}
	    }
	    if (grid_info[last_guess] == HIT && grid_info[llast_guess] == HIT) {
		int next_spot = last_guess;
		next_spot += (last_guess - llast_guess);
		if (next_spot < 0 || next_spot >= 100) {
		    do {
			next_spot += (last_guess - llast_guess);
		    } while(grid_info[next_spot] != EMPTY);
		}
		if (hit_ship(ship, next_spot))
		    update_grid_info(next_spot, HIT);
		else
		    update_grid_info(next_spot, MISS);
		return;
	    }
	    if (grid_info[last_guess] == HIT && grid_info[llast_guess] <= MISS) {
		int next_spot = orig_ship_hit_spot;
		if (next_spot - 10 >= 0 && grid_info[next_spot - 10] == EMPTY) {
		    next_spot -= 10;
		}
		else if (next_spot + 1 < 100 && grid_info[next_spot + 1] == EMPTY) {
		    next_spot ++;
		}
		else if (next_spot + 10 < 100 && grid_info[next_spot + 10] == EMPTY) {
		    next_spot += 10;
		}
		else {
		    next_spot --;
		}
		
		if (hit_ship(ship, next_spot))
		    update_grid_info(next_spot, HIT);
		else
		    update_grid_info(next_spot, MISS);
		return;
	    }
	}
    }

    private boolean ship_sunk(int[] ship) {
	for (int i = 0; i < ship.length; i ++) {
	    if (grid_info[ship[i]] < HIT)
		return false;
	}
	return true;
    }

    private boolean hit_ship(int[] ship, int spot) {
	for (int i = 0; i<ship.length; i ++) {
	    if (ship[i] == spot) {
		System.out.println("HITHITHIT");
		return true;
	    }
	}

	return false;
    }

    private void update_grid_info(int spot, int info) {
	grid_info[spot] = info;
	llast_guess = last_guess;
	last_guess = spot;
	if (info == HIT && grid_info[llast_guess] <= MISS) orig_ship_hit_spot = spot;
	if (info == HIT) guessing_ship = true;
	num_guesses ++;
	get_num_hits();
	System.out.println(num_guesses + ": At location " + spot + " was a " + ((info == MISS) ? "MISS" : "HIT"));
    }

    private void get_num_hits() {
	int hits = 0;
	for (int i : ship) {
	    if (grid_info[i] == HIT)
		hits ++;
	}

	if (hits > 1)
	    have_direction = true;
    }
    
    /** This method is called from within the constructor to
	initialize the form.
	WARNING: Do NOT modify this code. The content of this method is
        always regenerated by the Form Editor.
    */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
	
	jPanel1 = new javax.swing.JPanel();
	jPanel2 = new javax.swing.JPanel();
	jPanel3 = new javax.swing.JPanel();
	
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setTitle("Battleship Prototype");
	setResizable(false);
	
	jPanel1.setBackground(new java.awt.Color(255, 255, 255));
	jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    jPanel1MouseClicked(evt);
		}
	    });
	
	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(
					 jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					 .addGap(0, 306, Short.MAX_VALUE)
					 );
	jPanel1Layout.setVerticalGroup(
				       jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				       .addGap(0, 300, Short.MAX_VALUE)
				       );
	
	jPanel2.setBackground(new java.awt.Color(255, 255, 255));
	jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    jPanel2MouseClicked(evt);
		}
	    });
	
	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	jPanel2.setLayout(jPanel2Layout);
	jPanel2Layout.setHorizontalGroup(
					 jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					 .addGap(0, 306, Short.MAX_VALUE)
					 );
	jPanel2Layout.setVerticalGroup(
				       jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				       .addGap(0, 300, Short.MAX_VALUE)
				       );
	
	javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	jPanel3.setLayout(jPanel3Layout);
	jPanel3Layout.setHorizontalGroup(
					 jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					 .addGap(0, 310, Short.MAX_VALUE)
					 );
	jPanel3Layout.setVerticalGroup(
				       jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				       .addGap(0, 50, Short.MAX_VALUE)
				       );
	
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(
				  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				  .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				  .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				  );
	layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					  .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
	
	pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel1MouseClicked
    {//GEN-HEADEREND:event_jPanel1MouseClicked
	Graphics g = jPanel1.getGraphics();
	g.setColor(Color.red);
	g.drawString("Clicked", evt.getX(), evt.getY());
    }//GEN-LAST:event_jPanel1MouseClicked
    
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel2MouseClicked
    {//GEN-HEADEREND:event_jPanel2MouseClicked
	Graphics g = jPanel2.getGraphics();
	g.setColor(Color.blue);
	g.drawString("Clicked", evt.getX(), evt.getY());
    }//GEN-LAST:event_jPanel2MouseClicked
    /**
       @param args the command line arguments
    */
    public static void main(String args[])
    {
	java.awt.EventQueue.invokeLater(new Runnable() 
	    {
		public void run() 
		{
		    new Prototype().setVisible(true);
		}
	    });
    }
    
    @Override
	public void paint(Graphics g)
    {
	super.paint(g);
	drawOPGrid();
	drawUserGrid();
	drawLegend();
    }
    
    private void drawOPGrid()
    {
	Graphics g = jPanel1.getGraphics();
	int panel_width = jPanel1.getWidth();
	int panel_height = jPanel1.getHeight();
	int step_x = panel_width / 10;
	int step_y = panel_height / 10;
	
	for (int i = 1; i < 10; i ++)
	    {
		g.drawLine(i * step_x, 0, i * step_x, panel_height);
		g.drawLine(0, i * step_y, panel_width, i * step_y);
	    }
    }
    
    private void drawUserGrid()
    {
	Graphics g = jPanel2.getGraphics();
	int panel_width = jPanel2.getWidth();
	int panel_height = jPanel2.getHeight();
	int step_x = panel_width / 10;
	int step_y = panel_height / 10;
	
	for (int i = 1; i < 10; i ++)
	    {
		g.drawLine(i * step_x, 0, i * step_x, panel_height);
		g.drawLine(0, i * step_y, panel_width, i * step_y);
	    }
    }
    
    private void drawLegend()
    {
	Graphics g = jPanel3.getGraphics();
	g.setColor(Color.green);
	g.fillRect(10, 10, 20, 20);
	g.setColor(Color.red);
	g.fillRect(200, 10, 20, 20);
	g.setColor(Color.black);
	g.drawString("MISS", 35, 30);
	g.drawString("HIT", 225, 30);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
    
}
