

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.abs;

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

            //condition 1
        if (Arrays.stream(words).anyMatch("Ideji"::equals)
                && Arrays.stream(types).anyMatch("b"::equals)){
            return -1;
            //condition 2
        } else if (Arrays.stream(words).anyMatch("Ideji"::equals)
                && Arrays.stream(types).anyMatch("g"::equals)){
            return 1;
            //condition 3
        } else if ( Arrays.stream(words).anyMatch("Your"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("b"::equals)
                && (Arrays.asList(words).indexOf("Your") == Arrays.asList(types).indexOf("s")-1) ){
            return - 1;
        } else if ( Arrays.stream(words).anyMatch("your"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("b"::equals)
                && (Arrays.asList(words).indexOf("your") == Arrays.asList(types).indexOf("s")-1) ){
            return - 1;
            //condition 4  // I think there is a typo in the README : they put "s" instead of "g"
        } else if ( Arrays.stream(words).anyMatch("Your"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("g"::equals)
                && (Arrays.asList(words).indexOf("Your") == Arrays.asList(types).indexOf("s")-1) ){
            return - 1;
        } else if ( Arrays.stream(words).anyMatch("your"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("g"::equals)
                && (Arrays.asList(words).indexOf("your") == Arrays.asList(types).indexOf("s")-1) ){
            return - 1;
            //condition 5
        } else if ( !Arrays.stream(words).anyMatch("your"::equals) ){
            return 0;
            //condition 6
        } else if ( Arrays.stream(types).anyMatch("c"::equals)
                && Arrays.stream(types).anyMatch("b"::equals) ){
            return 1;
            //condition 8 
        } else if ( Arrays.stream(types).anyMatch("c"::equals)
                && Arrays.stream(types).anyMatch("g"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("b"::equals)
                && ( (abs(Arrays.asList(types).indexOf("s") - Arrays.asList(types).indexOf("g")))
                    < (abs(Arrays.asList(types).indexOf("s") - Arrays.asList(types).indexOf("b")))) ){
            return 0;
            //condition 9
        } else if ( Arrays.stream(types).anyMatch("c"::equals)
                && Arrays.stream(types).anyMatch("g"::equals)
                && Arrays.stream(types).anyMatch("s"::equals)
                && Arrays.stream(types).anyMatch("b"::equals)
                && ( (abs(Arrays.asList(types).indexOf("s") - Arrays.asList(types).indexOf("g")))
                > (abs(Arrays.asList(types).indexOf("s") - Arrays.asList(types).indexOf("b")))) ){
            return 0;
            //condition 7
        } else if ( Arrays.stream(types).anyMatch("c"::equals)
                && Arrays.stream(types).anyMatch("g"::equals) ){
            return -1;
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
