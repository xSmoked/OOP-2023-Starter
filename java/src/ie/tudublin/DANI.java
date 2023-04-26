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

    public String[] writeSonnet()
    {
        return null;
    }

	public void setup() {
		colorMode(HSB);

		loadedWords = loadFile();
		
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
        
	}

	public String[] loadFile(){
		String[] lines = loadStrings("small.txt");
		int wordCount = 0;
		for (String line : lines) {
			String[] splitLine = split(line, " ");
			for (String word : splitLine) {
				wordCount++;
			}
		}
		String[] splitListArray = new String[wordCount];
		int i = 0;
		for (String line : lines) {
			String[] splitLine = split(line, " ");
			for (String word : splitLine) {
				word = word.toLowerCase();
				word = word.replaceAll("[^\\w\\s]", "");
				splitListArray[i] = word;
				i++;
			}
		}
		return splitListArray;
	}

	public boolean findWord(String str){
		str = str.toLowerCase();
		str = str.replaceAll("[^\\w\\s]", "");

		for (String word : loadedWords) {
			if (word.equals(str)) {
				return true;
			}
		}
		return false;
	}
}
