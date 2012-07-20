
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
x */
/**
 *
 * @author Zach
 */
public class MainGame extends javax.swing.JFrame
{
    private Grid playerGrid;
    private compai cp;
    private boolean gameover = false;
    private int count = 0;
    /**
     * Creates new form MainGame
     */
    public MainGame(Grid inPlayer)
    {
	cp = new compai();
	playerGrid = inPlayer;
	initComponents();
	this.setLocation();
	textArea.setText("Welcome to BattleShips" +
			 "\nBegin by guessing a spot on your opponents grid");

    }
    
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
	
      topGrid = new javax.swing.JPanel();
      bottomGrid = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      textArea = new javax.swing.JTextArea();
      key = new javax.swing.JPanel();
	
      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      topGrid.setBackground(new java.awt.Color(255, 255, 255));
      topGrid.setPreferredSize(new java.awt.Dimension(200, 200));
      topGrid.addMouseListener(new java.awt.event.MouseAdapter() {
	      public void mouseClicked(java.awt.event.MouseEvent evt) {
		  topGridMouseClicked(evt);
	      }
	  });
	
        javax.swing.GroupLayout topGridLayout = new javax.swing.GroupLayout(topGrid);
        topGrid.setLayout(topGridLayout);
        topGridLayout.setHorizontalGroup(
					 topGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					 .addGap(0, 200, Short.MAX_VALUE)
					 );
        topGridLayout.setVerticalGroup(
				       topGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				       .addGap(0, 200, Short.MAX_VALUE)
				       );
	
      bottomGrid.setBackground(new java.awt.Color(255, 255, 255));
      bottomGrid.setPreferredSize(new java.awt.Dimension(200, 200));
	bottomGrid.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    bottomGridMouseClicked(evt);
		}
	    });
        javax.swing.GroupLayout bottomGridLayout = new javax.swing.GroupLayout(bottomGrid);
        bottomGrid.setLayout(bottomGridLayout);
        bottomGridLayout.setHorizontalGroup(
					    bottomGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					    .addGap(0, 200, Short.MAX_VALUE)
					    );
        bottomGridLayout.setVerticalGroup(
					  bottomGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGap(0, 200, Short.MAX_VALUE)
					  );
	
      textArea.setColumns(20);
      textArea.setEditable(false);
      textArea.setRows(5);
      jScrollPane1.setViewportView(textArea);
	
        javax.swing.GroupLayout keyLayout = new javax.swing.GroupLayout(key);
        key.setLayout(keyLayout);
        keyLayout.setHorizontalGroup(
				     keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				     .addGap(0, 324, Short.MAX_VALUE)
				     );
        keyLayout.setVerticalGroup(
				   keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				   .addGap(0, 70, Short.MAX_VALUE)
				   );
	
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
				  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				  .addGroup(layout.createSequentialGroup()
					    .addContainerGap()
					    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						      .addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									  .addComponent(topGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
									  .addComponent(bottomGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(jScrollPane1)
								.addContainerGap())
						      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(0, 90, Short.MAX_VALUE)
								.addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(96, 96, 96))))
				  );
        layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						    .addGroup(layout.createSequentialGroup()
							      .addComponent(topGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							      .addComponent(bottomGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						    .addComponent(jScrollPane1))
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(key, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					  .addContainerGap())
				);
	
      pack();
   }// </editor-fold>//GEN-END:initComponents
    
    private void topGridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGridMouseClicked
	//Process the player's guess
        if (!gameover) {
	    int x = evt.getX() / (topGrid.getWidth()/10);
	    int y = evt.getY() / (topGrid.getHeight()/10);
	    
	    int ret = cp.mygrid.guess(y*10+x);
	    if (ret == 0)
		return;
	    repaint();
	    
	    if(cp.mygrid.isGameOver())
		gameOver("Player Won");
	}

	//Process the computer's guess
	if (!gameover) {
	    cp.AI_Guess(playerGrid);
	    repaint();
	    
	    if(playerGrid.isGameOver())
		gameOver("Computer Won");
	}
    }//GEN-LAST:event_topGridMouseClicked

    private void bottomGridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bottomGridMouseClicked
	if (!gameover)
	    textArea.append("Click the other grid!\n");
    }//GEN_LAST:event_bottomGridMouseClicked
    
    private void gameOver(String s)
    {
        //msg box xxxx won!
        JOptionPane.showMessageDialog(this,s, "Game Over",
              JOptionPane.INFORMATION_MESSAGE);
        textArea.append("\nGAMEOVER: " + s);
        System.exit(0);
	textArea.append("GAMEOVER: " + s + "\n");
	gameover = true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
	/*
	 * Set the Nimbus look and feel
	 */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	}
	catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>
	
    }

    
    private void setLocation()
    {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int locx = (dim.width / 2) - (this.getWidth() / 2);
	int locy = (dim.height / 2) - (this.getHeight() / 2);
	
	super.setLocation(locx, locy);
    }
    
    private void taunt() throws IOException
    {
	BufferedReader stdin;
	stdin = new BufferedReader(new FileReader("taunts.in"));
	String taunt = null;
	Random rand = new Random();
	int n = 0;
	for (Scanner sc = new Scanner(stdin); sc.hasNext();) {
	    ++n;
	    String line = sc.nextLine();
	    if (rand.nextInt(n) == 0) {
		taunt = line;
	    }
	}
	textArea.setLineWrap(true);
	textArea.setWrapStyleWord(true);
	textArea.setForeground(Color.blue);
	textArea.append("Computer Player: " + taunt + "\n");
    }
    
    private void drawGrid()
    {
	Graphics g = topGrid.getGraphics();
	int panel_width = topGrid.getWidth();
	int panel_height = topGrid.getHeight();
	int step_x = panel_width / 10;
	int step_y = panel_height / 10;
	for (int i = 1; i < 10; i++) {
	    g.drawLine(i * step_x, 0, i * step_x, panel_height);
	    g.drawLine(0, i * step_y, panel_width, i * step_y);
	}
	
	g = bottomGrid.getGraphics();
	panel_width = bottomGrid.getWidth();
	panel_height = bottomGrid.getHeight();
	step_x = panel_width / 10;
	step_y = panel_height / 10;
	
	for (int i = 1; i < 10; i++) {
	    g.drawLine(i * step_x, 0, i * step_x, panel_height);
	    g.drawLine(0, i * step_y, panel_width, i * step_y);
	}
    }
    
    private void drawLegend()
    {
	Graphics g = key.getGraphics();
	g.setColor(Color.green);
	g.fillRect(10, 10, 20, 20);
	g.setColor(Color.red);
	g.fillRect(200, 10, 20, 20);
	g.setColor(Color.black);
	g.drawString("MISS", 35, 30);
	g.drawString("HIT", 225, 30);
    }
    
    private void drawBottomGrid()
    {
	Graphics g = bottomGrid.getGraphics();
	for(int i=0; i<playerGrid.getGrid().length; i++) {
	    if(playerGrid.getGrid()[i] == Grid.OCCUPIED) {
		int x = (i%10)*(bottomGrid.getWidth()/10);
		int y = (i/10)*(bottomGrid.getHeight()/10);
		
		g.setColor(Color.gray);
		g.fillRect(x, y, (bottomGrid.getWidth()/10), (bottomGrid.getHeight()/10));
	    } else if(playerGrid.getGrid()[i] == Grid.HIT) {
		int x = (i%10)*(bottomGrid.getWidth()/10);
		int y = (i/10)*(bottomGrid.getHeight()/10);
		
		g.setColor(Color.red);
		g.fillRect(x, y, (bottomGrid.getWidth()/10), (bottomGrid.getHeight()/10));
	    } else if(playerGrid.getGrid()[i] == Grid.MISS) {
		int x = (i%10)*(bottomGrid.getWidth()/10);
		int y = (i/10)*(bottomGrid.getHeight()/10);
		
		g.setColor(Color.green);
		g.fillRect(x, y, (bottomGrid.getWidth()/10), (bottomGrid.getHeight()/10));
	    }
	}
    }
    
    private void drawTopGrid()
    {
	Graphics g = topGrid.getGraphics();
	for(int i=0; i<cp.mygrid.getGrid().length; i++) {
	    if(cp.mygrid.getGrid()[i] == Grid.HIT) {
		int x = (i%10)*(topGrid.getWidth()/10);
		int y = (i/10)*(topGrid.getHeight()/10);
		
		g.setColor(Color.red);
		g.fillRect(x, y, (topGrid.getWidth()/10), (topGrid.getHeight()/10));
	    } else if(cp.mygrid.getGrid()[i] == Grid.MISS) {
		int x = (i%10)*(topGrid.getWidth()/10);
		int y = (i/10)*(topGrid.getHeight()/10);
		
		g.setColor(Color.green);
		g.fillRect(x, y, (topGrid.getWidth()/10), (topGrid.getHeight()/10));
	    }
	}
    }
    
    @Override
    public void paint(Graphics g)
    {
	super.paint(g);
	drawBottomGrid();
	drawTopGrid();
	drawGrid();
	drawLegend();
    }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel bottomGrid;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JPanel key;
   private javax.swing.JTextArea textArea;
   private javax.swing.JPanel topGrid;
   // End of variables declaration//GEN-END:variables
}
