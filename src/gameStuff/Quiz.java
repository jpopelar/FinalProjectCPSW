package gameStuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Quiz extends JDialog{
	private Question q;
	private JPanel options, enterSelection;
	private ArrayList<JRadioButton> choices;
	private String userSelection;
	private BattleField field = BattleField.getInstance();
	
	public Quiz(Question q){
		this.q = q;
		this.options = createOptions();
		this.enterSelection = createEnterButton();	
		setLayout(new GridLayout(0,1));
		add(options);
		add(enterSelection);
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("Quiz Time!");
		setSize(400,200);
		//setVisible(true);
	}

	private JPanel createEnterButton() {
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("OK");
		button.addActionListener(new ButtonListener());
		buttonPanel.add(button);
		return buttonPanel;
	}

	private JPanel createOptions() {
		choices = new ArrayList<JRadioButton>();
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(0,1));
		optionPanel.setBorder(new TitledBorder(new EtchedBorder(), q.getQuestion()));
		
		//Create button group
		ButtonGroup group = new ButtonGroup();
		
		//Place all 4 potential answers in an array list
		Random rand = new Random();
		ArrayList<String> ans = new ArrayList<String>();	
		for(String s : q.getWrongResponses()) ans.add(s);
		ans.add(rand.nextInt(2), q.getAnswer());
		
		RadioListener listener = new RadioListener();
		
		//create each radio button, add it to group/panel, and add a listener
		for(String s : ans){
			JRadioButton tmp = new JRadioButton(s);
			optionPanel.add(tmp);
			tmp.addActionListener(listener);
			group.add(tmp);			
			choices.add(tmp);
		}
		return optionPanel;
	}
	
	private class RadioListener implements ActionListener {
		  public void actionPerformed(ActionEvent e){
		   for(JRadioButton b : choices){
			   if(b.isSelected()) 
				   userSelection = b.getText();
		   }
		  }
	}
	
	private class ButtonListener implements ActionListener {
		  public void actionPerformed(ActionEvent e){
		   if(userSelection.equals(q.getAnswer())){
			   JOptionPane.showMessageDialog(null, "That answer is correct! Press OK to advance to the next level.");
			   dispose();
			   field.incrementLevel();
		   }
		   else {
			   //splash wrong!
			   JOptionPane.showMessageDialog(null, "That's not the right answer. Try again!");
			   //loop back to quiz
		   }
			   
		  }
	}
}
