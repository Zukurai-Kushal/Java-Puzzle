import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;
/**
 * This PuzzlePiece class contains information of the cropped image and methods to interact with those variables  
 * @author Kushal Thapa UID:3035477082
 *
 */
public class PuzzlePiece{
	private boolean lock = false;
	private int originalPos[] = new int[2];
	private int currentPos[] = new int[2];
	private BufferedImage pic;
	
	/**
	 * Initiates the PuzzlePiece class, sets the cropped image with the inputed BufferedImage and sets the original position and current position as the inputed int[] position 
	 * @param pic the picture that is to be set as the cropped image
	 * @param i the position of the image relative to the panel
	 */
	
	public PuzzlePiece(BufferedImage pic, int i[]){
		this.pic = pic;
		originalPos = i.clone();
		currentPos = i.clone();
	}
	
	/**
	 * Returns the cropped image of the PuzzlePiece
	 * @return the cropped image of the PuzzlePiece
	 */
	public Image getPic() {
		return pic;
	}
	
	/**
	 * Sets the current position value to the inputed x,y coordinates 
	 * @param x the x coordinate of the current position
	 * @param y the y coordinate of the current position
	 */
	public void setCurrentPos(int x,int y) {
		currentPos[0] = x;
		currentPos[1] = y;
	}
	
	/**
	 * Returns the current position of the PuzzlePiece
	 * @return the current position of the PuzzlePiece
	 */
	public int[] getCurrentPos() {
		return currentPos;
	}
	
	/**
	 * Returns true if the cropped image is in the original position and false if not
	 * @return true if the cropped image is in the original position and false if not
	 */
	public boolean getLock() {
		return lock;
	}
	
	/**
	 * Checks if the cropped image is in the original position and sets the lock status as true if it is so  
	 */
	public void updateLock() {
		if (originalPos[0] == currentPos[0] && originalPos[1] == currentPos[1]) {
			lock = true;
		}
	}
	


}
