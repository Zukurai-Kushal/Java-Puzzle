import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * FileChooser class enables the user to choose a file from the device and returns the path to the file
 * @author Kushal Thapa UID:3035477082
 *
 */
public class FileChooser{
	private String path;
	/**
	 * Prompts the user to select any image file 
	 */
	public FileChooser(){
		JFileChooser file = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		file.addChoosableFileFilter(filter);
		int result = file.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = file.getSelectedFile();
			
			try {
				path = selectedFile.getAbsolutePath();
				ImageIO.read(selectedFile).getHeight();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Wrong File Selected");
				System.exit(1);
			}
			}  
		else if(result == JFileChooser.CANCEL_OPTION){
			System.out.println("No File Select");
		}
	}
	/**
	 * Returns the path to the file
	 * @return the path to the file
	 */
	public String getPath() {
			 return path;
	}
	 

  }