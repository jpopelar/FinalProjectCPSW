package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import gameStuff.*;


public class GameWindow extends JFrame{
	private BattleField field;
	private JMenuItem exitOpt;
	private JMenuBar menuBar;
	private JMenu menu;
	private UserPanel control;
	public static int SCALE_FACTOR = 10;
	
	
	public GameWindow() {
		super();
		field = BattleField.getInstance();
		setTitle("Angle Launch Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// hard code the file names and load up the battleField
		field.setLaunchersFile("/data/launcherConfig.txt");
		field.setQuestionFile("/data/questionListTest.txt");
		field.setFieldFile("/data/battleField.txt");
		
		field.initialize();
		
		setSize(field.getXDim() * SCALE_FACTOR + 120, field.getYDim() * SCALE_FACTOR + 80);
		
		//initialize and display the menu
		this.menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		this.menu = new JMenu("Game Options");
		this.exitOpt = createFileExitItem();
		exitOpt.addActionListener(new exitListener());
		menu.add(exitOpt);
		menuBar.add(menu);
		
		add(field, BorderLayout.CENTER);
		//addMouseListener(new locListener());
		control = new UserPanel();
		add(control, BorderLayout.EAST);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	private class practiceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}			
	}
	private JMenuItem createFileExitItem() {
		JMenuItem exitopt = new JMenuItem("Exit");
		return exitopt;
	}
	
	private class locListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			double x = e.getPoint().getX() / SCALE_FACTOR;
			double y = (field.getYDim() * SCALE_FACTOR - e.getPoint().getY()) / SCALE_FACTOR;
			y = y + 5.0;
			System.out.println("X: " + x + ", Y: " + y);
			return;
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	public static void main (String[] args) {
		GameWindow gui = new GameWindow();
	}
	
}
