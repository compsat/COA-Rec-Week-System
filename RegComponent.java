import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;

public class RegComponent extends JComponent{
    HashMap<String,String[]> oldMembers;
    SchoolSorter sorter;
    Image img;

    public RegComponent() throws IOException{
        setUpOldMemberDictionary();
        img = ImageIO.read(new File("background.png"));
    }

    private void setUpOldMemberDictionary() {
        oldMembers = new HashMap<String, String[]>();
        BufferedReader br = null;

    		try {
    			String currentLine;
    			br = new BufferedReader(new FileReader("oldmembers.csv"));

    			while ((currentLine = br.readLine()) != null) {
    				String[] rawData = currentLine.split(",");
                    String[] data = Arrays.copyOfRange(rawData, 1, rawData.length);
                    oldMembers.put(rawData[0], data);
    			}

    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if (br != null)br.close();
    			} catch (IOException ex) {
    				ex.printStackTrace();
    			}
    		}
        System.out.println ("Successfully imported old members!");
    }

    public static boolean isNumeric(String str)
    {
      return str.matches("^[0-9]+$");  //match a number with optional '-' and decimal.
    }

    public void paintComponent(Graphics g){
      g.drawImage(img, 0, 0, null);
    }
}
