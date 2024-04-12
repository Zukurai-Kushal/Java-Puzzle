import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This ImageHandler class contains variables and methods to manipulate an 10*10 puzzle panel of an image 
 * @author Kushal Thapa UID:3035477082
 *
 */

public class ImageHandler {
	/**
	 * The status of the completion of the game
	 */
	public boolean solved = false;
	private FileChooser fc = new FileChooser();
	private String path = fc.getPath();
	private BufferedImage original;
	private BufferedImage resized;
	private BufferedImage crop;
	/**
	 * The panel containing the current puzzle image
	 */
	public CurrentPanel currP = new CurrentPanel();
	/**
	 * The panel containing the original puzzle image
	 */
	public OriginalPanel oriP = new OriginalPanel();
	/**
	 * The 2D array of the current puzzle pieces 
	 */
	public PuzzlePiece[][] pieces = new PuzzlePiece[10][10];
	private PuzzlePiece[][] originals = new PuzzlePiece[10][10];
	
	/**
	 * Retrieves an image from the path, resizes the image and then crops the resized image to initialize the 10*10 puzzle  
	 */
	public ImageHandler() {
		try {
            original = ImageIO.read(new File(path));;
            resized = resizeImage(original,800,800,BufferedImage.TYPE_INT_ARGB);
            
            for(int i=0; i<10; i++) {
            	for(int j=0; j<10; j++) {
            		crop = resized.getSubimage(i*80, j*80, 80, 80);
            		int pos[] = {i,j};
            		pieces[i][j] = new PuzzlePiece(crop,pos);
            		originals[i][j] = new PuzzlePiece(crop,pos);
            	}
            }
            
            
        } catch (IOException ex) {
            System.out.println("Cannot Resize Image!");
        }
	}
	
	private BufferedImage resizeImage(BufferedImage originalImage, int width,
            int height, int type) throws IOException {

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
	/**
	 * Swaps two puzzle pieces from the x,y position to the x2,y2 position, then updates their current position and lock status
	 * @param x the x position of the first puzzle piece
	 * @param y the y position of the first puzzle piece
	 * @param x2 the x position of the second puzzle piece
	 * @param y2 the y position of the second puzzle piece
	 */
	public void swap(int x, int y,int x2,int y2) {
		PuzzlePiece temp = pieces[x][y];
		pieces[x][y] = pieces[x2][y2];
		pieces[x][y].setCurrentPos(x,y);
		pieces[x2][y2] = temp;
		pieces[x2][y2].setCurrentPos(x2,y2);
		pieces[x][y].updateLock();
		pieces[x2][y2].updateLock();	
		currP.repaint();
		updateSolved();
	}
	
	/**
	 * Checks if the puzzle is solved, sets the solved status as solved if puzzle is solved
	 */
	public void updateSolved() {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(pieces[i][j].getLock() == false) {
					return;
				}
			}
		}
		solved = true;
	}
	/**
	 * Returns the solved status of the puzzle
	 * @return the solved status of the puzzle
	 */
			
	public boolean getSolved() {
		return solved;
	}
	
	/**
	 * This CurrentPanel class extends the JPanel class and draws the current puzzle image onto the panel 
	 * @author Kushal Thapa UID:3035477082
	 *
	 */
	public class CurrentPanel extends JPanel{
		/**
		 * Draws the current puzzle image onto the panel
		 */
		public void paintComponent(Graphics g) {
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					g.drawImage(pieces[i][j].getPic(),i*80,j*80,this);
				}
			}
		}
	}
	/**
	 * This CurrentPanel class extends the JPanel class and draws the current puzzle image onto the panel
	 * @author Kushal Thapa UID:3035477082
	 *
	 */
	public class OriginalPanel extends JPanel{
		/**
		 * Draws the current puzzle image onto the panel
		 */
		public void paintComponent(Graphics g) {
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					g.drawImage(originals[i][j].getPic(),i*80,j*80,this);
				}
			}
		}
	}
	/**
	 * Shuffles the puzzle pieces then checks and updates the puzzle pieces lock status
	 */
	public void shuffle() {
		 for(int i = 0; i<10; i++) {
			 for(int j=0; j<10; j++) {
				 int x = (int)(Math.random()*(10));
				 int y = (int)(Math.random()*(10));
				 int x2 = (int)(Math.random()*(10));
				 int y2 = (int)(Math.random()*(10));
				 
				 PuzzlePiece temp = pieces[x][y];
				 pieces[x][y] = pieces[x2][y2];
				 pieces[x][y].setCurrentPos(x,y);
				 pieces[x2][y2] = temp;
				 pieces[x2][y2].setCurrentPos(x2,y2);
			 }
		 }
		 for(int i = 0; i<10; i++) {
			 for(int j=0; j<10; j++) {
				 pieces[i][j].updateLock();
			 }
		 }
	}
	/**
	 * Returns a panel with the original puzzle image
	 * @return a panel with the original puzzle image
	 */
	public OriginalPanel getOriginal(){
		return oriP;
	}

	/**
	 * Returns a panel with the current puzzle image
	 * @return a panel with the current puzzle image
	 */
	public CurrentPanel getPanel() {
		return currP;
	}

}
