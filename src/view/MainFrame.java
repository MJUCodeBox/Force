package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	MainCanvas mainCanvas;
	
	public MainFrame() {
		// private attributes
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setBounds(300, 300, 400, 400);//for Test
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		
		// create components
		this.mainCanvas = new MainCanvas();
		this.add(mainCanvas);
	}
	
	public void initialize() {
		this.setVisible(true);
		mainCanvas.initialize();
	}
	
}
