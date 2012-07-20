import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class SplashScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private String image_filename = "ss.jpg";
	
	public SplashScreen() {
		super();
		this.setSize(375, 250);
		this.setUndecorated(true);
		this.setLocation();
		
		try {
			img = ImageIO.read(new File(this.image_filename));
		} catch (Exception e) {
			System.err.println("Could not load splash screen image from " + image_filename);
			img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			img_err();
		}
	}
	
	private void setLocation() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int locx = (dim.width/2) - (this.getWidth()/2);
		int locy = (dim.height/2) - (this.getHeight()/2);
		
		super.setLocation(locx, locy);
	}
	
	private void img_err() {
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, this.getWidth(), this.getHeight(), Color.blue, false));
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.black);
		g.drawString("BATTLESHIP", 150, 120);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
	}
	
	public static void main(String[] args) {
		SplashScreen ss = new SplashScreen();
		ss.setVisible(true);
		try {
			Thread.sleep(1000 * 10);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		ss.setVisible(false);
		ss.dispose();
	}
}
