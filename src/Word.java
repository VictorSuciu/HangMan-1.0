import java.util.ArrayList;
import java.util.Scanner;

public class Word {
	int wordLength;
	String wordString;
	private char[] guessedRight;
	private ArrayList <Character> guessedWrong;
	
	public Word(String word) {
		//Scanner getLength = new Scanner(System.in);
		//wordLength = getLength.nextInt(); 
		wordString = word;
		wordLength = word.length();
		guessedRight = new char[wordLength];
		for(int i = 0; i < wordLength; i++) {
			guessedRight[i] = '_';
		}
		
		guessedWrong = new ArrayList();
		
		
	}
	
	public boolean guessedAll() {
		for(char c : guessedRight) {
			if(c == '_') {
				return false;
			}
		}
		return true;
	}
	
	public void addWrong (char c) {
		
		guessedWrong.add(c);
		
	}
	
	public void addRight(char c, ArrayList<Integer> indexes) {
		
		for(int i = 0; i < indexes.size(); i++) {
			guessedRight[indexes.get(i)] = c;
		}
	}
	
	public char[] getRight() {
		return guessedRight;
	}
	
	public ArrayList<Character> getWrong() {
		return guessedWrong;
	}
	
	public int getLength() {
		return wordLength;
	}
	
	public char getCharAt(char[] array, int index) {
		
		return array[index];
	}
	public String getWord() {
		return wordString;
	}
	
	public boolean contains(char c) {
		for(int i = 0; i < wordString.length(); i++) {
			if(wordString.charAt(i) == c) {
				return true;
			}
		}
		return false;
		
	}
	
	public ArrayList<Integer> getIndexes(char c) {
		ArrayList<Integer> ret = new ArrayList();
		
		for(int i = 0; i < wordString.length(); i++) {
			if(wordString.charAt(i) == c) {
				ret.add(i);
			}
		}
		return ret;
	}
}
