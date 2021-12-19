package edu.waketech.sallyspeak;

import java.util.Arrays;
import java.util.Scanner;

public class SallySpeakSolution {	
	private static final
		String[] TERMINALS = {"earperk" //reaction
                             ,"tailwag" //after reaction
                             ,"bark" //expression
                             ,"whimper"
                             ,"growl" };

	private Scanner tokenizer;
	
	private char flag;
	private char previousFlag = 'x';

	private String token;

	private int count = 0;
	private boolean legal = true;
	


	public SallySpeakSolution(String inputToken) {
		tokenizer = new Scanner(inputToken)
				.useDelimiter(" ");
	}
	
	private void nextToken() {
		if(tokenizer.hasNext()) {
			this.count++;
			
			if(!isFirstRun()) setPreviousFlag(token.charAt(0));
			
			token = tokenizer.next();
			
		}else {
			if(isFirstRun())
				legal = false;
			
			if(flag == 'w' || flag == 'g' || flag == 'b')
				legal = false;
			
			if(isFirstRun() && flag == 'e')
				legal = false;
			
			token = "bite";
		}
	}

	public boolean isLegal() {
		nextToken();
		
		if(token.equals("bite")) 
			return legal;

		if(!legal)
			return false;
		
		if(Arrays.stream(TERMINALS).anyMatch(t -> t.equals(token))) {
			setFlag(token.charAt(0));
			switch(flag) {
				case('e'):
				case('t'):
					reaction();
					break;
					
				case('b'):
					if(isFirstRun()) legal = false;
					expression();
					break;
					
				case('w'):
				case('g'):
					whimpergrowl();
					break;
			}
		}else {
			legal = false;
			isLegal();
		}
		return legal;
	}
	
	private void reaction() {
		if(!legal) return;	
		
		switch(flag) {
		
			case ('e'):		
				if(previousFlag == 'b') 
					legal = false;
				else isLegal();
				break;
				
			case ('t'):	
				if(previousFlag != 't'
				&& previousFlag != 'e')
					legal = false;
				else isLegal();
				break;
		}

		if(!legal) return;
	}
	
	private void expression() {
		if(!legal) return;
		
		switch(flag) {
			case ('b'):
				if(previousFlag == 'b')
					legal = false;
				else isLegal();
				break;
		}	
		
		if(!legal) return;
	}
	
	private void whimpergrowl() {
		if(!legal) return;
		
		switch(flag) {
			case ('w'):
			case ('g'):	
				
				if(previousFlag != 'b' 
				&& previousFlag != 'w' 
				&& previousFlag != 'g')
					legal = false;
				else isLegal();
				break;
		}	
		if(!legal) return;
	}

	private void setFlag(char c) {
		this.flag = c;
	}
	
	public void setPreviousFlag(char pc) {
		this.previousFlag = pc;
	}
	
	public boolean isFirstRun() {
		return this.count <= 1;
	}

}
