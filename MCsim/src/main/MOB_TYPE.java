package main;

import java.util.Random;

public enum MOB_TYPE {

	PLAYER(new String[]{
			//PHRASES
			"Wheres your coursework?",
			"Jamie that breaks the chairs, yeah?",
			"Ben, I don't want to hear any more from you",
			"Need it by the end of the week, monday at the latest",
			"We don't need a running commentary",
			"Lets not waste any more time, yeah?",
			"Ahad, wheres your extended essay?",
			"Correct format is 120% of the marks",
			"Hindolo, this isn't a maths lesson",
			"Just do it",
			"Google it, yeah?"
	},
			new String[]{
					//NAMES
					"Mr Collins"		
	}),


	ENEMY(new String[]{
			//PHRASES
			"I just need to print it off",
			"Its on my computer at home",
			"My printer is broken",
			"The program is done, I just need to do the documentation",
			"I am doing it right now",
			"I have it on my memory stick",
			"I forgot to send it",
			"I'll email it tonight",
			"I thought you said monday",
			"Did you not get my email?",
			"I can't get eclipse to work",
			null,null,null,null,null,null,null,null,null,null //Bias towards Collins
	},
			new String[]{
					//NAMES
					"Ben",
					"Ahad",
					"Hindolo",
					"Jamie",
					"Peter",
					"Will",
					"Max",
					"Tony"					
	});

	private String phrases[];
	private String names[];

	private MOB_TYPE(String givenPhrases[], String givenNames[]) {
		phrases = givenPhrases;
		names = givenNames;
	}
	
	public String getPhrase() {
		return phrases[new Random().nextInt(phrases.length)];
	}
	
	public String getName() {
		return names[new Random().nextInt(names.length)];
	}

}
