public class HorizontalWord extends Word {
    public HorizontalWord(String word) {
        super(word);
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < getWord().length(); i++) {
            temp += getWord().charAt(i);
            if (i < getWord().length() - 1) temp += " ";
        }
        return temp;
    }
}
