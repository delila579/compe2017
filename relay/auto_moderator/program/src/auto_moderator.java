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
        String line = "";
        String splitLineBy = ",";
        String splitMessageBy = " ";

        String messageId = "";
        String message = "";

        try {
            bufferedReader = new BufferedReader(new FileReader(CSVpath));
            line = bufferedReader.readLine();

            while(line != null){

                String[] lineSPlit = line.split(splitLineBy);
                messageId = lineSPlit[0];
                message = lineSPlit[1];

                String[] words = message.split(splitMessageBy);


                line = bufferedReader.readLine();
            }

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

    public MessageClassification evaluateMessage(String[] words){

    }

    //write in standard output
    public void showResult(String messageId, MessageClassification classification){
        switch (classification){
            case MessageClassification.POSITIVE:
                System.out.println("This message is positive.");
                break;

        }
    }
}
