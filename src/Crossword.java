import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Crossword{
    private int i = 0;
    private final int fivetoeight = randomnum(5, 8);
    private static int index = 0;
    private int gaprow = 0;
    private int[][] indexholder = new int[fivetoeight][2];
    private String[] holidays = {"NewYears","IndependenceDay","Thanksgiving",
            "Christmas","MemorialDay","LaborDay",
            "ColumbusDay","VeteransDay","GroundhogDay",
            "Ramadan","MothersDay","FathersDay",
            "Halloween","MartinLutherKingsDay","ValentinesDay",
            "Easter","AprilFools","Hanukkah","LunarNewYear","MoonFestival"};

    private int randomnum(int min, int max){
        return (int)(Math.random()*(max - min + 1) + min);
    }

    private int randomnum(int max){
        return (int)(Math.random()*max + 1) - 1;
    }

    private void holidayScramble(){
        int temp = randomnum(20);
        String[] holder = new String[20];
        ArrayList<Integer> taken = new ArrayList<Integer>();
        for(int i = 0; i < 20; i++){
            while(taken.contains(temp)) temp = randomnum(20);
            holder[i] = holidays[temp];
            taken.add(temp);
        }
        holidays = holder;
    }

    private boolean save = true;
    // unreadable method (hi ms qiu)
    private void findIndex(int index1, int index2, Word word1, Word word2, int index) { //finds indexes of the same character

        int[] returned = new int[2];
        if(index2 == word2.getWord().length()){ //if index2 = second word lenght
            if(save) {
                indexholder[index][0] = -1;
                indexholder[index][1] = -1;
            }
            save = false;
            return;
        }

        if (!(word1.getWord().toLowerCase().charAt(index1) == word2.getWord().toLowerCase().charAt(index2))) {
            //System.out.println(word1.getWord().toLowerCase().charAt(index1) + " " + word2.getWord().toLowerCase().charAt(index2));
            if (index1 == word1.getWord().length() - 1) {
                findIndex(index1 - word1.getWord().length() + 1, index2 + 1, word1, word2, index);
            }
            else{
                findIndex(index1 + 1, index2, word1, word2, index);
            }
        }
        if(save) {
            indexholder[index][0] = index1;
            indexholder[index][1] = index2; //whyt not save? why not save? why not save? why not save? why? why? why>? whyt?
        }
        save = false;
    }

    public int indexOfLongestWord(Word[] words){
        int index = 0;
        int length = 0;
        for(int i = 0; i < fivetoeight; i++){
            if(words[i].length() > length){
                length = words[i].length();
                index = i;
            }
        }
        return index;
    }

    public char[][] createBoard(Word[] words, ConcurrentHashMap<int[], int[]> indexMap, ArrayList<Integer> taken){
        int rows = 0;
        int columns = 0;
        int longest = indexOfLongestWord(words);
        columns = words[longest].length();
        int up = 0;
        int down = 0;
        for (ConcurrentHashMap.Entry<int[],int[]> mapElement : indexMap.entrySet()) {
            int[] key = mapElement.getKey();
            int[] value = (mapElement.getValue());
            if(words[key[1]].length() - (value[1] + 1) > down){
                down = words[key[1]].length() - value[1];
            }
            if(value[1] > up){
                up = value[1];
            }
        }
        rows = up + down + 1;

        gaprow = up;

        //return finished initialized board;
        char[][] board = new char[rows][columns];
        return board;
    }

    public void displayBoard(Word[] words, ConcurrentHashMap<int[], int[]> map, ArrayList<Integer> taken){
        char[][] board = createBoard(words, map, taken);
        //initializes board with bullets
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = '•';
            }
        }

        //display longest word in grid
        for(int i = 0; i < words[indexOfLongestWord(words)].length(); i++){ // first horizontal
            board[gaprow][i] = words[indexOfLongestWord(words)].toString().charAt(i);
        }

        //display the rest of words

        for (ConcurrentHashMap.Entry<int[],int[]> mapElement : map.entrySet()) { //insert char into each point
            int[] key = mapElement.getKey();
            int[] value = (mapElement.getValue());
            for(int i = gaprow -)
        }

        //actually displays baord
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j]);;
            }
            System.out.println();
        }
    }


    public Crossword(){
        //declarations
        ArrayList<String> holidayList = new ArrayList<String>(); // list of 5-8 holidays
        Word[] words = new Word[fivetoeight]; // array of word objects
        //declarations end

        //main
        holidayScramble(); //scramble holidays in array

        for(int i = 0; i < fivetoeight; i++) holidayList.add(holidays[i]); // add 5-8 holidays to arraylist
        System.out.println(holidayList); //remove when project is done
        for(int i = 0; i < fivetoeight; i++){
            words[index] = new Word(holidayList.get(index));
        }
        int debugindex = 0;
        /*
        for(int i = 0; i < fivetoeight - 1; i++){
            findIndex(0, 0, words[i], words[i+1]);
            System.out.println(words[i].toString() + " " + words[i+1] + ": " + indexholder[i][0] + " " + indexholder[i][1]);
        }
        BAD BAD BAD BAD BAD BAD BAD
         */
        ArrayList<Integer> unusedWords = new ArrayList<>() {
            {
                for(int i = 0; i < fivetoeight; i++){
                    add(i);
                }
            }
        };
        System.out.println(unusedWords);
        //i really dont know how to do this wihtout a hashmap forgive me
        ConcurrentHashMap<int[]/*index of overlapping words in array words[]*/, int[]/*index of overlap respectively*/> map = new ConcurrentHashMap<>();
        ArrayList<Integer> taken = new ArrayList<>();
        int temp1 = 0;
        int temp2 = 0;
        int index = 0;
        int minusthis = 0;
        int longest = indexOfLongestWord(words);
        unusedWords.remove(unusedWords.indexOf(longest));
        if(longest == 0) index++;
        findIndex(temp1, temp2, words[longest], words[index], index);
        taken.add(indexholder[index][0]);
        unusedWords.remove(unusedWords.indexOf(index));
        save = true;
        index++;
        minusthis++;
        if(index == longest){
            index++;
            minusthis++;
        }
        while(index < words.length){
            temp1 = 0;
            if(index >= words.length || temp1 >= words[longest].length()){
                break;
            }
            map.put(new int[]{longest, index - minusthis}, new int[]{indexholder[index - minusthis][0], indexholder[index - minusthis][1]});
            minusthis = 1;
            findIndex(temp1, temp2, words[longest], words[index], index);
            while(taken.contains((Integer)indexholder[index][0]) || taken.contains((Integer)(indexholder[index][0] - 1)) || taken.contains((Integer)(indexholder[index][0] +  1))){
                if(!(temp1 >= words[longest].length() - 1)){
                    findIndex(temp1++, temp2, words[longest], words[index], index);
                    save = true;
                } else if(!(temp2 >= words[index].length() - 1)){
                    findIndex(temp1, temp2++, words[longest], words[index], index);
                    save = true;
                } else{
                    indexholder[index][0] = -1;
                    indexholder[index][1] = -1;
                    break;
                }
            }
            if(indexholder[index][0] != -1) {
                taken.add(indexholder[index][0]);
                unusedWords.remove(unusedWords.indexOf(index));
            }
            if(index == words.length - 1){
                map.put(new int[]{longest, index}, new int[]{indexholder[index][0], indexholder[index][1]});
            }
            index++;
            if(index == longest){
                index++;
                minusthis++;
            }
        }
        //debug moment
        int sigh = fivetoeight + 1;
        for (ConcurrentHashMap.Entry<int[],int[]> mapElement : map.entrySet()) { //fix results
            int[] key = mapElement.getKey();
            int[] value = (mapElement.getValue());
            if(key[0] == -1 || value[0] == -1){
                map.remove(key);
            } else {
                if (words[key[0]].getWord().toLowerCase().charAt(value[0]) != words[key[1]].getWord().toLowerCase().charAt(value[1])) {
                    map.remove(key);
                    unusedWords.add(key[1]);
                } else {
                    System.out.println(Arrays.toString(key) + " : " + Arrays.toString(value));
                }
            }

        }
        System.out.println(unusedWords);
        System.out.println(map);
        displayBoard(words, map, taken);
        //debug moment end

        //displayBoard(words);



        //add hashmap that stores key index in word[index], and stores object arraylist with taken indexes for the word.
    }

    public static void indexAdd(){
        index++;
    }

}
