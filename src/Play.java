import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Play {
	
	boolean testing = true;
	boolean testingWon = false;

	File file = new File("English Word List.txt");
	int[] freqList = new int[26];
	int[] freqList2 = new int[26];
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	Word word;
	ArrayList<Integer> guessedLetters = new ArrayList();
	ArrayList<Integer> maxes = new ArrayList();
	
	public Play() throws FileNotFoundException {

		if(testing == false) {
			getNewWord();
			generateFreqList();
			for(int i = 0; i < freqList.length; i++) {
				freqList2[i] = freqList[i];
			}
			guess();
		}
		else {
			test();
		}
		
	}
	
	public void getNewWord() {
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("How long is your word?");
		int length = input.nextInt();
		*/
		word = new Word("crush");
		System.out.println();
	}
	//sculpture
	public void guess() throws FileNotFoundException {
		Scanner response = new Scanner(System.in);
		String yn = "";
		int temp = 0;
		ArrayList<Integer> indexes = new ArrayList();
		char guessChar = ' ';
		int wrong = 0;
		boolean won = false;
		while(wrong < 7) {
			
			generateFreqList();
			/*
			for(int i = 0; i < freqList.length; i++) {
				System.out.print(alphabet.charAt(i) + ":" + freqList2[i] + ", ");
			}
			
			System.out.println();
			*/
			guessChar = getBestLetter();
			if(testing == false) {
				System.out.println("Is there an " + guessChar + " in your word?");
			}
			if(word.contains(guessChar)) {
				word.addRight(guessChar, word.getIndexes(guessChar));
				
			}
			else {
				word.addWrong(guessChar);
				wrong++;
			}
			if(testing == false) {
				System.out.println("Wrong: " + wrong);
			}
			
			if(testing == false) {
				for(int j = 0; j < word.getRight().length; j++) {
					System.out.print(word.getCharAt(word.getRight(), j));
				}
			}
			
			yn = "";
			
			if(word.guessedAll() == true) {
				won = true;
				break;
			}
			if(testing == false) {
				System.out.println();
				System.out.println("==========================================");
				System.out.println();
			}
		}
		if(testing == false) {
			if(won == true) {
				System.out.println();
				System.out.print("Looks like I won! Your word was ");
				for(int j = 0; j < word.getRight().length; j++) {
					System.out.print(word.getCharAt(word.getRight(), j));
				}
				System.out.print(".");
			}
			else {
				System.out.println("Looks like lost. I'll do better next time!");
			}
		}
		else {
			if(won == true) {
				testingWon = true;
			}
			else {
				testingWon = false;
			}
		}
		
	}
	
	public void generateFreqList() throws FileNotFoundException {
		int wordCounter = 0;
		
		for(int j = 0; j < freqList.length; j++) {
			freqList[j] = 0;
		}
		
		Scanner readFile = new Scanner(file);
		boolean hasWrongChars = false;
		boolean hasRightChars = true;
		boolean isSymbol = false;
		int index = 0;
		
		while(readFile.hasNext()) {
			
			String tempWord = readFile.next().toLowerCase();
			hasWrongChars = false;
			hasRightChars = true;
			
			
			
			if(tempWord.length() == word.getLength()) {
				
				for(int i = 0; i < tempWord.length(); i++) {
					for(char c : word.getWrong()) {
						if(tempWord.charAt(i) == c) {
							hasWrongChars = true;
						}
					}
				}
				
				for(int i = 0; i < word.getRight().length; i++) {
					
					if(word.getCharAt(word.getRight(), i) != '_') {
						if(word.getCharAt(word.getRight(), i) != tempWord.charAt(i)) {
							hasRightChars = false;
						}
					}
				}
				
				if(hasWrongChars == false && hasRightChars == true) {
					wordCounter++;
					index = 0;
					isSymbol = false;
					for(int i = 0; i < tempWord.length(); i++) {
						
						while(tempWord.charAt(i) != alphabet.charAt(index)) {
							index++;
							if(index == 26) {
								isSymbol = true;
								break;
							}
						}
						if(isSymbol == false) {
							freqList[index] += 1;
						}
						index = 0;
					}
				}
				
			}	
			
		}
		/*
		for(int i = 0; i < freqList.length; i++) {
			System.out.print(Character.toString(alphabet.charAt(i)) + ": " + freqList[i] + ", " );
		}
		System.out.println();
		*/
		//System.out.println(wordCounter);
		if(wordCounter == 0) {
			for(int i = 0; i < freqList.length; i++) {
				freqList[i] = freqList2[i];
			}
		}
		
	}
	
	public char getBestLetter() {
		
		boolean foundChar = false;
		int max = -1;
		boolean countThisMax = true;
		int index = 0;
		char retChar = ' ';
			for(int i = 0; i < freqList.length; i++) {
				if(freqList[i] > max) {
					for(int j : guessedLetters) {
						if(j == i) {
							countThisMax = false;
						}
					}
					if(countThisMax == true) {
						max = freqList[i];
						index = i;
					}
				}
				countThisMax = true;
			}
			//System.out.println("Adding " + index + " to maxes list");
			
			maxes.add(index);
			
			retChar = alphabet.charAt(index);
			
		guessedLetters.add(index);
		maxes.clear();
		return retChar;
		
	}
	
	public void test() throws FileNotFoundException {
		testing = true;
		boolean newLength = true;
		int length = 1;
		ArrayList<TestWord> lengths = new ArrayList();
		Scanner readFile = new Scanner(file);
		String wordString = "";
		testingWon = false;
		int status = 0;
		while(readFile.hasNext()) {
			
			wordString = readFile.next();
			word = new Word("wordString");
			length = word.getLength();
			
			guess();
			
			for(int i = 0; i < lengths.size(); i++) {
				if(lengths.get(i).getLength() == length) {
					newLength = false;
				}
			}
			
			if(newLength == true) {
				lengths.add(new TestWord(length));
			}
			
			for(int i = 0; i < lengths.size(); i++) {
				if(lengths.get(i).getLength() == length) {
					if(testingWon == true) {
						lengths.get(i).addWin();
					}
					else {
						lengths.get(i).addLoss();
					}
				}
			}
			
			newLength = false;
			testingWon = false;
			status++;
			System.out.println(wordString);
			if(status % 500 == 0) {
				System.out.println(status);
			}
			
			
		}
		TestWord temp = new TestWord(0);
		int min = 10000;
		int index = 0;
		for(int i = 0; i < lengths.size(); i++) {
			for(int j = i; j < lengths.size(); j++) {
				if(lengths.get(i).getLength() > min) {
					min = lengths.get(i).getLength();
					index = i;
				}
			}
			temp = lengths.get(index);
			lengths.remove(index);
			lengths.add(i, temp);
		}
		
		for(TestWord tw : lengths) {
			System.out.println(tw);
		}
	}
}
