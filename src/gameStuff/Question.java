package gameStuff;

import java.util.ArrayList;

public class Question {

	private String[] wrongResponses;
	private String answer;
	private String theQuestion;
	
	public Question(String q, String a, String[] wrongA){
		this.theQuestion = q;
		this.answer = a;
		this.wrongResponses = wrongA;
		
	}
	
	public String getAnswer() {
		return answer.toString();
	}
	
	@Override
	public String toString() {
		return theQuestion;
	}

	public String[] getWrongResponses() {
		return wrongResponses;
	}

	public String getQuestion() {
		return theQuestion;
	}

	
	
	
}
