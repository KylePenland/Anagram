package anagram;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.Nemesis.anagram.R;

import filework.SimpleFileDialog;


public class MainActivity extends Activity {

    ArrayList<String> wordList = new ArrayList<String>();
    ArrayList<String> anagramList = new ArrayList<String>();
    String wordCheck = "";
    File fileChosen;
    Button importIt, exportIt, runIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        importIt = (Button) findViewById(R.id.importFile);
        exportIt = (Button) findViewById(R.id.exportFile);
        runIt = (Button) findViewById(R.id.runIt);

        importIt.setOnClickListener(new View.OnClickListener()
        {
            String m_chosen;
            @Override
            public void onClick(View v) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                //Create FileOpenDialog and register a callback
                /////////////////////////////////////////////////////////////////////////////////////////////////
                SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(MainActivity.this, "FileOpen",
                        new SimpleFileDialog.SimpleFileDialogListener()
                        {
                            @Override
                            public void onChosenDir(String chosenDir)
                            {
                                // The code in this function will be executed when the dialog OK button is pushed
                                m_chosen = chosenDir;
                                fileChosen = new File(m_chosen).getAbsoluteFile();
                                Toast.makeText(MainActivity.this, "Chosen FileOpenDialog File: " +
                                        m_chosen, Toast.LENGTH_LONG).show();
                                new ImportTextFile(fileChosen).execute();
                            }
                        });

                //You can change the default filename using the public variable "Default_File_Name"
                FileOpenDialog.Default_File_Name = "";
                FileOpenDialog.chooseFile_or_Dir();

                /////////////////////////////////////////////////////////////////////////////////////////////////
                //fileChosen = new File(m_chosen).getAbsoluteFile();
            }
        });


    }

    public void runFile(View v){
        anagramChecker();
    }
    public void exportFile(View v){
        new ExportTextFile().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void anagramChecker(){
        for (int x =0; x < wordList.size();x++){
            //checks to see if there is an anagram
           wordCheck = findAnagram(wordList.get(x));
            //if blank then goes to next word
            if(wordCheck.equalsIgnoreCase("")){

            }else{
                boolean inList = false;
                //if yes then checks the word to see if already in anagram list
                if(anagramList.size()>0){
                inList = checkAnagramList(wordList.get(x), wordCheck);
                    //if inList gets set to true breaks out of here
                    if(inList == true){
                        break;
                    }else{
                        //if not values yet then just adds them to anagram list
                        anagramList.add(wordList.get(x));
                        anagramList.add(wordCheck);
                    }

                }else{
                //if no values yet then just adds them to anagram list
                anagramList.add(wordList.get(x));
                anagramList.add(wordCheck);
                }
            }
        }
    }
    public boolean checkAnagramList(String word1, String word2){
        //sets to true if words are already in the list;
    	boolean temp = true;
        for (int y = 0; y < anagramList.size();y++){
            if(word1.equalsIgnoreCase(anagramList.get(y))){
               //if word1 is in list then true
                temp = true;
            }else {
                //else false
                temp = false;
            }
            if(word2.equalsIgnoreCase(anagramList.get(y))){
                //if word2 is in list then true
                temp = true;
            }else{
                //else false
                temp = false;
            }
        }
        //return value of temp
        return temp;
    }
    
    public String findAnagram(String word){
		String secondWord = null;
		for(int i = 0;i<wordList.size();i++){
			//sets the word to check against
			secondWord = wordList.get(i);
			//these two if statements make sure their is not a random null appended to either word
			if(word.contains("")){
				int foundNull =word.indexOf(null);
				word = word.substring(foundNull);
			}if(secondWord.contains("")){
				int foundNull =secondWord.indexOf(null);
				secondWord = secondWord.substring(foundNull);
			}
			//checks to make sure it is not the same word
			if(secondWord.equalsIgnoreCase(word)){
				
			}else{
				//then it checks to make sure that the words are roughly the same size
				if(secondWord.length()>word.length()||word.length()<secondWord.length()){
					
				}else{
				//if they are then it gets to checking
				int[] wordOneLetters = new int[26];
				int[] wordTwoLetters = new int[26];
				
				wordOneLetters = createAlphabetArray(word);
				wordTwoLetters = createAlphabetArray(secondWord);
				
				}
				
				
			}
		}
    	return secondWord;
    }
    
    public int[] createAlphabetArray(String word){
    	int[] alphabet = new int[26];
    	for(int j = 0; j<word.length();j++){
    		if(word.charAt(j) == 'a'||word.charAt(j) == 'A'){
    			alphabet[0] += 1;
    		}
    		if(word.charAt(j) == 'b'||word.charAt(j) == 'B'){
    			alphabet[1] += 1;
    		}
    		if(word.charAt(j) == 'c'||word.charAt(j) == 'C'){
    			alphabet[2] += 1;
    		}
    		if(word.charAt(j) == 'd'||word.charAt(j) == 'D'){
    			alphabet[3] += 1;
    		}
    		if(word.charAt(j) == 'e'||word.charAt(j) == 'E'){
    			alphabet[4] += 1;
    		}
    		if(word.charAt(j) == 'f'||word.charAt(j) == 'F'){
    			alphabet[5] += 1;
    		}
    		if(word.charAt(j) == 'g'||word.charAt(j) == 'G'){
    			alphabet[6] += 1;
    		}
    		if(word.charAt(j) == 'h'||word.charAt(j) == 'H'){
    			alphabet[7] += 1;
    		}
    		if(word.charAt(j) == 'i'||word.charAt(j) == 'I'){
    			alphabet[8] += 1;
    		}
    		if(word.charAt(j) == 'j'||word.charAt(j) == 'J'){
    			alphabet[9] += 1;
    		}
    		if(word.charAt(j) == 'k'||word.charAt(j) == 'K'){
    			alphabet[10] += 1;
    		}
    		if(word.charAt(j) == 'l'||word.charAt(j) == 'L'){
    			alphabet[11] += 1;
    		}
    		if(word.charAt(j) == 'm'||word.charAt(j) == 'M'){
    			alphabet[12] += 1;
    		}
    		if(word.charAt(j) == 'n'||word.charAt(j) == 'N'){
    			alphabet[13] += 1;
    		}
    		if(word.charAt(j) == 'o'||word.charAt(j) == 'O'){
    			alphabet[14] += 1;
    		}
    		if(word.charAt(j) == 'p'||word.charAt(j) == 'P'){
    			alphabet[15] += 1;
    		}
    		if(word.charAt(j) == 'q'||word.charAt(j) == 'Q'){
    			alphabet[16] += 1;
    		}
    		if(word.charAt(j) == 'r'||word.charAt(j) == 'R'){
    			alphabet[17] += 1;
    		}
    		if(word.charAt(j) == 's'||word.charAt(j) == 'S'){
    			alphabet[18] += 1;
    		}
    		if(word.charAt(j) == 't'||word.charAt(j) == 'T'){
    			alphabet[19] += 1;
    		}
    		if(word.charAt(j) == 'u'||word.charAt(j) == 'U'){
    			alphabet[20] += 1;
    		}
    		if(word.charAt(j) == 'v'||word.charAt(j) == 'V'){
    			alphabet[21] += 1;
    		}
    		if(word.charAt(j) == 'w'||word.charAt(j) == 'W'){
    			alphabet[22] += 1;
    		}
    		if(word.charAt(j) == 'x'||word.charAt(j) == 'X'){
    			alphabet[23] += 1;
    		}
    		if(word.charAt(j) == 'y'||word.charAt(j) == 'Y'){
    			alphabet[24] += 1;
    		}
    		if(word.charAt(j) == 'z'||word.charAt(j) == 'Z'){
    			alphabet[25] += 1;
    		}
    	}
    	return alphabet;
    }
    
  /*  public String findAnagram(String word){
        //Stores the anagram of the first word
        String secondAnagram = "";
        //starts at top of list
        for(int i =0; i<wordList.size();i++){
            //checks to see if it is the same word
            if(word.equalsIgnoreCase(wordList.get(i))){

            }else{
                //breaks the word into a char array
                char[] firstWord = word.toCharArray();
                //breaks the second word into char array
                char[] secondWord = wordList.get(i).toCharArray();
                //counter for first word
                int j = 0;
                //makes sure the loops end
                boolean matchOut = true;
                //makes sure the two words are the same length
                if(firstWord.length == secondWord.length){
                    //if they are then check each character against each other
                    while(j < firstWord.length){
                        //boolean to break out if true
                        boolean match = true;
                        //puts first Character into a string
                        String fChar = Character.toString(firstWord[j]);
                        //checks first letter against each letter in second word
                        for(int k = 0;k<secondWord.length;k++){
                            //puts second character into a string
                            String sChar = Character.toString(secondWord[k]);
                            //if first letter matches a letter in the second word
                            if(fChar.equalsIgnoreCase(sChar)){
                                //breaks out of loop
                                match = true;
                                break;
                            }else{
                                //if no match then match stays false
                                match = false;
                            }
                        }
                        //breaks out of loop
                        if(match == false){
                            matchOut = false;
                            break;
                        }
                        //increment first word counter
                        j++;
                    }
                    //if still no match breaks out of loop
                    if(matchOut == false){
                        break;
                    }
                    //sets second anagram to word
                    secondAnagram = wordList.get(i);
                }else{

                }
            }
        }
        //returns word
        return secondAnagram;
    }
     
     */
    public class ImportTextFile extends AsyncTask<Void,Void,Void>{
        String chosenFile;
        public ImportTextFile(File chosen){
            chosenFile = chosen.getAbsolutePath();
        }

        public int numberLines(){
         int numberOfLines = 0;
            try {
                FileReader file = new FileReader(chosenFile);
                BufferedReader bif = new BufferedReader(file);
                String aLine;
                while((aLine = bif.readLine()) != null){
                    numberOfLines++;
                }

            }catch(IOException f){
                System.out.println(f);
            }
         return numberOfLines;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            int counter = numberLines();
            String[] aryLines = new String[counter];
            try{
                FileReader fr = new FileReader(chosenFile);
                BufferedReader textReader = new BufferedReader(fr);
                for(int i = 0; i < counter; i++){
                    aryLines[i] = textReader.readLine();
                }
                textReader.close();
            }catch(IOException e){
                System.out.println(e);
            }

            for (int g=0;g < aryLines.length;g++){

                String[] temp = aryLines[g].split(",|\\.");
                for (int h = 0; h < temp.length;h++){

                    temp[h] = temp[h].trim();
                    wordList.add(temp[h]);
                }
            }
            return null;
        }
    }
    public class ExportTextFile extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids){
            File anagramLoc = Environment.getExternalStorageDirectory();
            File anagram = new File(anagramLoc, "anagram.txt");
            try{
                FileWriter write = new FileWriter(anagram);
                PrintWriter printer = new PrintWriter(write);

                for(int q = 0; q< anagramList.size();q++){
                    printer.printf("%s"+"%n", anagramList.get(q)+" , "+anagramList.get(q++));
                }

                printer.close();
            }
            catch (IOException z){

            }
            return null;
        }

        protected void onPostExecute(Void v){
            Toast.makeText(MainActivity.this, "anagram.txt created", Toast.LENGTH_LONG).show();
        }
    }
}
