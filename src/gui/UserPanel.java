package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import gameStuff.BattleField;
import gameStuff.Launcher;

public class UserPanel extends JPanel {

	private JTextField angleBox = new JTextField(5);
	private JComboBox launcherSelect = new JComboBox();
	
	public UserPanel() {
		setLayout(new GridLayout(0,1));
		angleBox.setFont(new Font("SansSerif", Font.BOLD, 12));
		setBorder(new TitledBorder (new EtchedBorder(), "Angle"));
		add(angleBox);
		
		JButton launchButton = new JButton("Launch");
		//launchButton.addActionListener(new LaunchListener());
		add(launchButton);
		
		launcherSelect = new JComboBox();
		for (Launcher l : BattleField.getInstance().getLaunchers()) {
			launcherSelect.addItem(l.toString());
		}
		add(launcherSelect);
	}
	
	private class LaunchListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (launcherSelect.getSelectedItem() == "Ballista") {
				BattleField.getInstance().setLauncher(0);
			}
			if (launcherSelect.getSelectedItem() == "Catapult") {
				BattleField.getInstance().setLauncher(1);
			}
			if (launcherSelect.getSelectedItem() == "Trebuchet") {
				BattleField.getInstance().setLauncher(2);
			}
			BattleField.getInstance().launch(Integer.parseInt(angleBox.getText()));
		}
		
	}
}
