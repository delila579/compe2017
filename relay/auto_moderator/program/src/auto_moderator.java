import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class auto_moderator {

    final int POSITIVE = 1;
    final int NEGATIVE = -1;
    final int NEUTRAL = 0;

    public static void main(String args[]) {

        String CSVpath = args[0];

        BufferedReader bufferedReader = null;
        String line;
        String splitLineBy = ",";
        String splitMessageBy = " ";

        String messageId = "";
        String message;

        HashMap<String, String> dictionary = new HashMap<>();
        createDictionary(dictionary);

        int somme= 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(CSVpath));
            line = bufferedReader.readLine();

            while(line != null){

                String[] lineSPlit = line.split(splitLineBy);
                messageId = lineSPlit[0];
                message = lineSPlit[1];

                String[] words = message.split(splitMessageBy);

                somme += evaluateLineOfMessage(dictionary, words);

                line = bufferedReader.readLine();
            }

            showResult(messageId, somme);

        } catch (FileNotFoundException e) {
            System.out.println("CSV file does not exist.");

        } catch (IOException e) {
            System.out.println("An error occured.");

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void createDictionary(HashMap<String, String> dictionary){

        BufferedReader bufferedReader = null;
        String line;
        String splitLineBy = "/";

        try {
            bufferedReader = new BufferedReader(new FileReader("dictionary.txt"));
            line = bufferedReader.readLine();

            while(line != null) {

                String[] lineSPlit = line.split(splitLineBy);
                dictionary.put(lineSPlit[0], lineSPlit[1]);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Dictionary does not exist.");

        } catch (IOException e) {
            System.out.println("An error occured.");

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //words[] contains the words in a line
    public static int evaluateLineOfMessage(HashMap<String, String> dictionary, String[] words){

        String[] types = new String[words.length];

        for(int i = 0; i < words.length; i++ ) {
            types[i] = dictionary.get(words[i]);
        }



        return 0;
    }

    //write in standard output
    public static void showResult(String messageId, int somme){
        switch (somme){
            case 1:
                System.out.println(messageId + ",positive");
                break;
            case -1:
                System.out.println(messageId + ",negative");
                break;
            case 0:
                System.out.println(messageId + ",neutral");
                break;

        }
     }
}
