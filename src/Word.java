public class Word {
    private String word;
    private String state;
    public Word(String word){
        this.word = word;
        Crossword.indexAdd();
    }

    public String toString(){
        return word;
    }

    public String getWord(){
        return word;
    }

    public int length() {
        return word.length();
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
