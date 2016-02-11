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
