
public class TestWord {

	int length;
	int wins = 0;
	int total = 0;
	public TestWord(int length) {
		this.length = length;
	}
	
	public void addWin() {
		wins++;
		total++;
	}
	public void addLoss() {
		total++;
	}
	public String toString() {
		return "Length " + length + ": " + wins + "/" + total;
	}
	public int getLength() {
		return length;
	}
}
