package gameStuff;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Quiz extends JDialog{
	private Question q;
	private JPanel options, question, enterSelection;
	
	public Quiz(Question q){
		this.q = q;
		this.question = createQuestion();
		this.options = createOptions();
		this.enterSelection = createEnterButton();
		s();
		setModal(true);
		setLocationRelativeTo(null);
		
	}

	private void s() {
		// TODO Auto-generated method stub
		
	}

	private JPanel createQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel createEnterButton() {
		
		return null;
	}

	private JPanel createOptions() {
		JPanel optionPanel = new JPanel();
		optionPanel.setBorder(new TitledBorder(new EtchedBorder(), q.getQuestion()));
		Random rand = new Random();
		ArrayList<String> ans = new ArrayList<>();
		
		for(String s : q.getWrongResponses()) ans.add(s);
		ans.add(rand.nextInt(2), q.getAnswer());
		
		for(String s : ans){
			
			JRadioButton tmp = new JRadioButton(s);
			optionPanel.add(tmp);
		}
		
		return optionPanel;
	}
}
