

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import project.view.panel.DisplayPanel;

/**
 * The Main frame the user sees. It houses the home screen and its buttons.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Apr 26, 2018
 */
public class MainFrame extends JFrame {

	/**
	 * Serial code of the class.
	 */
	private static final long serialVersionUID = -7259295803716311757L;
	
	private static final double REDUCTION = 0.75;
	
	private static final double SIDE = 0.10;
	
	/**
	 * The panel that is held to the frame.
	 */
	private JPanel displayPanel;
	
	/**
	 * The current panel that's displayed to the user.
	 */
	private JPanel dynamicPanel;
	
	/**
	 * The Side Panel that houses the buttons.
	 */
	private JPanel sidePanel;
	
	/**
	 * The constructor. Initialize the values of the frame and sets up the panels.
	 * 
	 * @param width Width of the frame.
	 * @param height Height of the frame.
	 */
	public MainFrame(int width, int height) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//setSize(new Dimension(width, height));
		setSize((int)(size.width * REDUCTION), (int)(size.height * REDUCTION));
		dynamicPanel = new JPanel();
		//dynamicPanel.setSize(new Dimension(width, height));
		displayPanel = new JPanel(); //Replace with Caleb's code.
		//displayPanel.setSize(new Dimension(width, height));
		sidePanel = createSidePanel((int)(size.width * SIDE), (int)(size.height * REDUCTION));
		add(sidePanel, BorderLayout.WEST);
		add(displayPanel, BorderLayout.EAST);
		displayPanel.add(dynamicPanel);
	}
	
	private JPanel createSidePanel(int width, int height) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(new GridLayout());
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(width, height));
		panel.add(createButton(new DisplayPanel(Color.BLACK), "Home"));
		panel.add(createButton(new DisplayPanel(Color.WHITE), "Graph"));
		panel.add(createButton(new DisplayPanel(Color.BLUE), "Shop"));
		panel.add(createButton(new DisplayPanel(Color.RED), "Other"));
		return panel;
	}
	
	private JButton createButton(JPanel panel, String icon) {
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(panel != null) {
					switchPanel(panel);
				}
			}
		});
		return button;
	}
	
	private void switchPanel(JPanel panel) {
		displayPanel.remove(dynamicPanel);
		dynamicPanel = panel;
		displayPanel.add(dynamicPanel, BorderLayout.CENTER);
		validate();
	}
}
