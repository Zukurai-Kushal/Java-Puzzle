import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
/**
 * This JavaPuzzle class contains variables and methods that creates and manipulates a puzzle game with users choice of image
 * @author Kushal Thapa UID:3035477082
 *
 */
public class JavaPuzzle {
	
	private ImageHandler ih = new ImageHandler();
	private JFrame jf = new JFrame("Puzzle Image");
	private JPanel jpi;
	private JPanel jp = new JPanel();
	private JPanel jpb = new JPanel();
	private JTextArea jt = new JTextArea(5,10);
	private JButton jbLoad = new JButton("Load Another Image");
	private JButton jbShow = new JButton("Show Original Image");
	private JButton jbExit = new JButton("Exit");
	private int[] mousePress = new int[2];
	private int[] mouseRelease = new int[2];
	private boolean inGame = true;

	private void Load() {
		try{
			ImageHandler newih = new ImageHandler();
			ih = newih;
			ih.shuffle();
			jpi = ih.getPanel();
			jf.getContentPane().add(BorderLayout.CENTER,jpi);
			jf.setVisible(true);
			inGame = true;
			jbShow.setLabel("Show Original Image");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(jf, "Error, Cannot Resize Image!");
		}
	}
	
	private void Show() {
		if(inGame) {	
			ih.oriP.repaint();
			jf.getContentPane().add(BorderLayout.CENTER,ih.getOriginal());
			jf.setVisible(true);
			jbShow.setLabel("Go Back to Puzzle");
			inGame = false;
		}else {
			ih.currP.repaint();
			jf.getContentPane().add(BorderLayout.CENTER,jpi);
			jf.setVisible(true);
			jbShow.setLabel("Show Original Image");
			inGame = true;
		}
	}
	
	private void go() {
		
		ih.shuffle();
		jpi = ih.getPanel();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(818,980);
		
		
		JScrollPane scroller = new JScrollPane(jt);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		BoxLayout boxlayout = new BoxLayout(jp,BoxLayout.Y_AXIS);
		jp.setLayout(boxlayout);
		jbLoad.addActionListener(new LoadListener());
		jbShow.addActionListener(new ShowListener());
		jbExit.addActionListener(new ExitListener());
		jpb.add(jbLoad);
		jpb.add(jbShow);
		jpb.add(jbExit);
		
		jt.setEditable(false);
		jp.add(scroller);
		jp.setSize(800, 200);
		jp.add(jpb);
		jpi.addMouseListener(new DraggerListener());
		jf.getContentPane().add(BorderLayout.CENTER,jpi);
		jf.getContentPane().add(BorderLayout.SOUTH,jp);
		jf.setVisible(true);
		jt.append("Game started!\n");
	}
	
	private void updateSwap() {
		if(ih.pieces[mousePress[0]][mousePress[1]].getLock() || ih.pieces[mouseRelease[0]][mouseRelease[1]].getLock()) {
			return;
		}
		else if(mousePress[0] == mouseRelease[0] && mousePress[1] == mouseRelease[1]) {
			return;
		}else {
			ih.swap(mousePress[0], mousePress[1], mouseRelease[0], mouseRelease[1]);
			if(ih.pieces[mousePress[0]][mousePress[1]].getLock() || ih.pieces[mouseRelease[0]][mouseRelease[1]].getLock()) {
				jt.append("Image block in correct position!\n");
			}
			if(ih.solved) {
				JOptionPane.showMessageDialog(jf, "You win!!!.");
			}
			
		}
	}
	
	
	private class DraggerListener implements MouseListener, MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
			int i = e.getX();
			i = i/80;
			int j = e.getY();
			j = j/80;
			
			if(i<10 && j<10) {
				mousePress[0] = i;
				mousePress[1] = j;
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			int i = e.getX();
			i = i/80;
			int j = e.getY();
			j = j/80;
			if(i<10 && j<10 && inGame) {
				mouseRelease[0] = i;
				mouseRelease[1] = j;
				updateSwap();
			}
			
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Load();
		}
	}
	private class ShowListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Show();
		}
	}
	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			jf.dispose();
		}
	}

	
	public static void main(String[] args) {
		JavaPuzzle tp = new JavaPuzzle();
		tp.go();
	
	}
	
}