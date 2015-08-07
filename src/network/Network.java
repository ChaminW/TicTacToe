package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Network {

    public int createFile(String otherPC) throws IOException {
        //return my symbol X or O as 1 and 0. return 2 if not connected...
        
        String mypath = "C:/Users/Public/tictactoe";
        File myfile = new File(mypath);

        //myfile.createNewFile();

        if (mypath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "File can not create");
        }

        String hispath = "//" + otherPC + "/Users/Public/tictactoe";
        File hisfile = new File(hispath);

        FileWriter myfileWriter = new FileWriter(myfile.getAbsoluteFile());
        BufferedWriter mybufferedWriter = new BufferedWriter(myfileWriter);

        if (hisfile.exists()) {
            mybufferedWriter.write("222222222X");
            mybufferedWriter.close();
            return 1;
        } else {

            mybufferedWriter.write("222222222O");
            mybufferedWriter.close();
            return 0;
        }

    }

public void write(int[] buttonData) throws IOException {
        String path = "C:/Users/Public/tictactoe";
        File file = new File(path);
        // if file doesnt exists, then create it
        if (file.exists()) {

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = "";

            for (int df = 0; df < buttonData.length; df++) {
                data += buttonData[df];
            }

            bufferedWriter.write(data);
            // Always close files.
            bufferedWriter.close();
        } else {
            JOptionPane.showMessageDialog(null, "Something is wrong. Initialize the game again");
        }
    }

    public int[] read(String OtherPC)  {
        try {
            //System.out.println(OtherPC);
            String path = "//" + OtherPC + "/Users/Public/tictactoe";
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
                String buttonDataString = bufferedReader.readLine();

                int[] buttonData = new int[buttonDataString.length()];
                
                for (int gh = 0; gh < buttonDataString.length(); gh++) {
                    buttonData[gh] = Integer.parseInt(buttonDataString.substring(gh, gh + 1));
                }

                bufferedReader.close();
                return buttonData;
            
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Other player is not connected");
            return null;
        }
    }

    public void deleteFile() {
        String path = "C:/Users/Public/tictactoe";
        File file = new File(path);
        file.delete();
    }

}
