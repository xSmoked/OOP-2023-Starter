package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;

public class DANI extends PApplet {

	

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;
	String[] loadedWords;
	ArrayList<Word> model = new ArrayList<Word>();
	public void printSonnet(){
		for(String line : sonnet){
			System.out.println(line);
		}
	}
    public String[] writeSonnet()
    {
		
		sonnet = new String[14];
		String line = "";
		Word currentWord;
		Follow currentFollow;

		for(int i = 0; i < 14; i++){
			currentWord = model.get((int)random(0, model.size()-1));
			line = "";
			for(int j = 0; j < 8; j++){
				if(currentWord.getFollows().isEmpty()){
					break;
				}

				line += currentWord.getWord() + " ";

				currentFollow = currentWord.getFollows().get((int)random(0, currentWord.getFollows().size()-1));
				for(Word word : model){
					if(word.getWord().equals(currentFollow.getWord())){
						currentWord = word;
						break;
					}
				}
			}
			sonnet[i] = line;
		}
		
        return null;
    }

	public void setup() {
		colorMode(HSB);

		loadFile();
		printModel();
		
		writeSonnet();
		printSonnet();
	}

	public void keyPressed() {
		if (key == ' ') {
			writeSonnet();
		}
	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
		for(int i = 0; i < 14; i++){
			text(sonnet[i], width/2, 200 + (i * 20));
		}
        
	}

	public void loadFile(){
		String[] lines = loadStrings("shakespere.txt");

		for(String line : lines){
			line = line.toLowerCase();
			line = line.replaceAll("[^\\w\\s]", "");
			loadedWords = line.split(" ");
	
			for(int i = 0; i < loadedWords.length; i++){
				if(!findWord(loadedWords[i])){
					model.add(new Word(loadedWords[i]));
				}
				for (Word word : model) {
					if (word.getWord().equals(loadedWords[i]) && i < (loadedWords.length - 1)) {
						word.findFollow(loadedWords[i + 1]);
					}
				}
			}
		}
	}

	public boolean findWord(String str){
		for (Word word : model) {
			if (word.getWord().equals(str)) {
				return true;
			}
		}
		return false;
	}

	public void printModel(){
		System.out.println("Model: ");
		for (Word word : model) {
			print(word.getWord() + ":");

			for (Follow follow : word.getFollows()) {
				System.out.print(" " + follow.getWord() + "(" + follow.getCount() + ")");
			}
			System.out.println();
		}
	}
}
