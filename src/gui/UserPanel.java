package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import gameStuff.BattleField;
import gameStuff.Launcher;
import gameStuff.Target;

public class UserPanel extends JPanel {
	private JComboBox<String> launcherSelect;
	private JComboBox<Integer> angleSelect;
	private JButton launchButton;
	private JButton nextLevelButton;
	private BattleField field;
	
	public UserPanel() {
		field = BattleField.getInstance();
		setLayout(new GridLayout(0,1));
		setBorder(new TitledBorder (new EtchedBorder(), "Angle"));
		// Angle Selection
		angleSelect = new JComboBox<Integer>();
		for (int i = 5; i <= 90; i+=5) {
			angleSelect.addItem(i);
		}
		add(angleSelect);
		// Launch Button
		launchButton = new JButton("Launch");
		launchButton.addActionListener(new LaunchListener());
		add(launchButton);
		// Launcher Selection
		launcherSelect = new JComboBox<String>();
		for (Launcher l : field.getLaunchers()) {
			launcherSelect.addItem(l.toString());
		}
		add(launcherSelect);
		// Next Level Button
		nextLevelButton = new JButton("Next Level");
		nextLevelButton.addActionListener(new NextListener());
		add(nextLevelButton);
	}
	
	private class LaunchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (field.isLaunching()) {
				JOptionPane.showMessageDialog(null, "Wait until current launch is finished");
				return;
			}
			
			if (launcherSelect.getSelectedItem().equals("Ballista")) {
				field.setLauncher(0);
				field.repaint();
			}
			if (launcherSelect.getSelectedItem().equals("Catapult")) {
				field.setLauncher(1);
				field.repaint();
			}
			if (launcherSelect.getSelectedItem().equals("Trebuchet")) {
				field.setLauncher(2);
				field.repaint();
			}
			int theAngle = (Integer) angleSelect.getSelectedItem();
			//System.out.println(theAngle);
			field.launch(theAngle);
		}	
	}
	
	private class NextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (Target t: field.getCurrentLevel().getTargetList()) {
				if (!t.wasHit()) {
					// display prompt
					JOptionPane err = new JOptionPane();
					err.showMessageDialog(null, "Not all targets have been hit! \nLevel incomplete.");
					return;
				}
			}
			// call doQuiz - make sure dialog locks
			field.doQuiz();
			// Increment level should only be called if quiz is complete
		}	
	}
}
