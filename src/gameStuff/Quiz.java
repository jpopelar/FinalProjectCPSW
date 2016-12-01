package gameStuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Quiz extends JDialog{
	private Question q;
	private JPanel options, question, enterSelection;
	private ArrayList<JRadioButton> choices;
	private String userSelection;
	
	public Quiz(Question q){
		this.q = q;
		this.options = createOptions();
		this.enterSelection = createEnterButton();	
		setModal(true);
		setLocationRelativeTo(null);
		
	}

	private void s() {
		// TODO Auto-generated method stub
		
	}

	private JPanel createEnterButton() {
		
		return null;
	}

	private JPanel createOptions() {
		// Initialize panel
		JPanel optionPanel = new JPanel();
		setLayout(new GridLayout(0,1));
		optionPanel.setBorder(new TitledBorder(new EtchedBorder(), q.getQuestion()));
		
		//Create button group
		ButtonGroup group = new ButtonGroup();
		
		//Get random, and place all 4 potential answers in an array list
		Random rand = new Random();
		ArrayList<String> ans = new ArrayList<>();	
		for(String s : q.getWrongResponses()) ans.add(s);
		ans.add(rand.nextInt(2), q.getAnswer());
		
		RadioListener listener = new RadioListener();
		
		//create each radio button, add it to group/panel, and add a listener
		for(String s : ans){
			JRadioButton tmp = new JRadioButton(s);
			optionPanel.add(tmp);
			group.add(tmp);
			tmp.addActionListener(listener);
			choices.add(tmp);
		}
		
		return optionPanel;
	}
	
	private class RadioListener implements ActionListener {
		  public void actionPerformed(ActionEvent e){
		   for(JRadioButton b : choices){
			   if(b.isSelected()) 
				   userSelection = b.toString();
		   }
		  }
	}
}
