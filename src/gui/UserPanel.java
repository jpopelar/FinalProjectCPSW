package gui;

import java.awt.BorderLayout;
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
	JButton launchButton;
	JButton nextLevelButton;
	private int selectedAngle;
	
	public UserPanel() {
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
		for (Launcher l : BattleField.getInstance().getLaunchers()) {
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
			if (launcherSelect.getSelectedItem().equals("Ballista")) {
				BattleField.getInstance().setLauncher(0);
			}
			if (launcherSelect.getSelectedItem() == "Catapult") {
				BattleField.getInstance().setLauncher(1);
			}
			if (launcherSelect.getSelectedItem() == "Trebuchet") {
				BattleField.getInstance().setLauncher(2);
			}
			selectedAngle = (int) angleSelect.getSelectedItem();
			//System.out.println(launcherSelect.getSelectedItem());
			BattleField.getInstance().launch(selectedAngle);
		}	
	}
	
	private class NextListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (Target t: BattleField.getInstance().getCurrentLevel().getTargetList()) {
				if (!t.wasHit()) {
					// display promt
					JOptionPane err = new JOptionPane();
					err.showMessageDialog(null, "Not all targets have been hit! \nLevel incomplete.");
					return;
				}
			}
			BattleField.getInstance().incrementLevel();
		}	
	}
}
