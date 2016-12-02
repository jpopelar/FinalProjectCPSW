package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import gameStuff.*;


public class GameWindow extends JFrame{
	BattleField field;
	private JMenuItem exitOpt;
	private JMenuBar menuBar;
	private JMenu menu;
	public static int SCALE_FACTOR = 10;
	
	
	public GameWindow() {
		super();
		setTitle("Angle Launch Game");
		setSize(800, 850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, this.getHeight(), this.getWidth(), this.getHeight());
		
		field = BattleField.getInstance();
		// hard code the file names and load up the battleField
		field.setLaunchersFile("launcherConfig.txt");
		field.setQuestionFile("questionListTest.txt");
		field.setFieldFile("battleField.txt");
		
		try {
			field.initialize();
		} catch (BadConfigException e) {
			System.out.println("Error loading config files, please check before continuing.");
		}
		
		
		
		//initialize and display the menu
		this.menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		this.menu = new JMenu("File");
		
		this.exitOpt = createFileExitItem();
		exitOpt.addActionListener(new exitListener());
		menu.add(exitOpt);
		menuBar.add(menu);
		
		add(field, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
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
	
	public static void main (String[] args) {
		GameWindow gui = new GameWindow();
		gui.field.launch(90);
		gui.field.launch(10);
		
		gui.field.incrementLevel();
		gui.field.launch(45);
		gui.field.setLauncher(0);
		gui.field.launch(80);
		gui.field.setLauncher(2);
		gui.field.launch(30);
	}
	
}