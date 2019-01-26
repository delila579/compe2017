import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class auto_moderator {

    public enum MessageClassification {
        POSITIVE, NEGATIVE, NEUTRAL, NOT_RELATED
    }

    public static void main(String args[]) {

        String CSVpath = args[0];

        BufferedReader bufferedReader = null;
        String line;
        String splitLineBy = ",";
        String splitMessageBy = " ";

        String messageId = "";
        String message;

        int somme= 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(CSVpath));
            line = bufferedReader.readLine();

            while(line != null){

                String[] lineSPlit = line.split(splitLineBy);
                messageId = lineSPlit[0];
                message = lineSPlit[1];

                String[] words = message.split(splitMessageBy);

                somme += evaluateMessage(words);

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

    public static int evaluateMessage(String[] words){

        for(int i = 0; i < words.length; i++ ) {
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
