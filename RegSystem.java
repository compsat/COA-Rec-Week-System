//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Arrays;
import java.awt.Font;
import java.io.File;
import java.awt.GraphicsEnvironment;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RegSystem extends JFrame {
    Field idNumber;
    Field nickname;
    Field year;
    Field lastName;
    Field course;
    Field email;
    Field firstName;
    Field middleInitial;
    Field mobileNumber;
    Field birthMonth;
    Field birthDay;
    Field birthYear;

    ButtonGroup membershipStatus;
    JRadioButton oldMember;
    JRadioButton newMember;
    ButtonGroup scholarshipStatus;
    JRadioButton isScholar;
    JRadioButton notScholar;
    ArrayList<Field> fields;

    JLabel studentDetails;

    HashMap<String, String[]> oldMembers;
    SchoolSorter sorter;

    Timestamp timestamp;
    SimpleDateFormat sdf;
    String imageFilename;
    String csvFilename;
    int width;
    int height;
    String text;

    public RegSystem() {
        csvFilename = JOptionPane.showInputDialog("CSV Filename:", "database.csv");
        setUpOldMemberDictionary(csvFilename);
        imageFilename = JOptionPane.showInputDialog("Image filename", "REGSCREEN.png,1600,850");
        setWindowSize(imageFilename);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        fields = new ArrayList<Field>();

        studentDetails = new JLabel();
        studentDetails.setFont(new Font("Arial", Font.PLAIN, 60));
        studentDetails.setBounds((width/2)-500, (5*height/9)-32, 1000, 64);

        addFields();
        addSubmitButton();
        addCSVandBGChange();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(studentDetails);

        validate();
        repaint();

        EventQueue.invokeLater(new Runnable() {

           @Override
             public void run() {
                 idNumber.grabFocus();
                 idNumber.requestFocus();//or inWindow
             }
        });

        sorter = new SchoolSorter();

        // Disable Maximizing
        setResizable (false);
        setMaximizedBounds(new Rectangle(0, 0));
            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(final WindowEvent e) {
                    if (e.getNewState() == MAXIMIZED_BOTH) {
                        setExtendedState(NORMAL);
                    }
                }
            });

        System.out.println ("Set-up completed!");
    }

    private void setUpOldMemberDictionary(String s) {
        oldMembers = new HashMap<String, String[]>();
        BufferedReader br = null;
        if(!s.equals("")) {
            try {
                String currentLine;
                br = new BufferedReader(new FileReader(s));

                while ((currentLine = br.readLine()) != null) {
                    String[] rawData = currentLine.split(",");
                    String[] data = Arrays.copyOfRange(rawData, 1, rawData.length);
                    oldMembers.put(rawData[0], data);
                }
                System.out.println ("Successfully imported old members!");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Cannot locate file", "File Not Found Error", JOptionPane.ERROR_MESSAGE);
                csvFilename = JOptionPane.showInputDialog("CSV Filename:", "database.csv");
                setUpOldMemberDictionary(csvFilename);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void addFields () {
        // Text Fields
        // Construct Components
        idNumber = new Field ("ID Number", 6, true);
        year = new Field ("Yr", 1, false);
        lastName = new Field ("Last Name", 20, false);
        course = new Field ("Course", 10, false);
        firstName = new Field ("First Name", 20, false);
        middleInitial = new Field ("MI", 1, false);

        // Add Text Fields to ArrayList
        fields.add (idNumber);

        for (Field text_field : fields) {
            add (text_field);
        }

        idNumber.setBounds((width/2)-50, (4*height/9)-17, 100, 35);

        // Mouse pointer to ID Number
        idNumber.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (idNumber.getText().length() == 6 && oldMembers.containsKey(idNumber.getText())){
                    String[] data = oldMembers.get(idNumber.getText());
                    lastName.setText(data[0]);
                    firstName.setText(data[1]);
                    middleInitial.setText(data[2].substring(0,1));
                    year.setText(String.valueOf(Integer.parseInt(data[3])));
                    course.setText(data[4]);
                }
                else{
                    lastName.setText("");
                    firstName.setText("");
                    middleInitial.setText("");
                    year.setText("");
                    course.setText("");
                    studentDetails.setText("");
                }
            }
        });
    }

    private void addSubmitButton () {
        // Submit Button
        try {
             GraphicsEnvironment ge =
                 GraphicsEnvironment.getLocalGraphicsEnvironment();
                 ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")));
        } catch (Exception e) {
             //Handle exception
        }
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = submitButtonClicked();
                System.out.println(result);
                switch (result) {
                    case "Success":
                        String idNum = idNumber.getText();
                        if(oldMembers.containsKey(idNum)) {
                            String[] data = oldMembers.get(idNum);
                            text = "Welcome to RecWeek! ID #: " + data[0]+", "+data[1]+" "+data[2].substring(0,1);
                        }
                        else 
                            text = "Welcome to RecWeek! ID #: " + idNumber.getText();
                        exportData();
                        studentDetails.setText(text);
                        idNumber.requestFocus();
                        break;
                    case "Incomplete":
                        JOptionPane.showMessageDialog(new JFrame(), "Please fill up all fields.", "Submission Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(new JFrame(), "Please re-enter the following information:\n" + result, "Submission Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
        add (submitButton);
        submitButton.setBackground(Color.decode("#EFEFEF"));
        submitButton.setForeground(Color.decode("#333333"));
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.setBounds ((width/2)-60, (2*height/3)-28, 120, 56);

        getRootPane().setDefaultButton(submitButton);
    }

    private void setWindowSize(String s) {
        String[] filenameSplit = s.split(",");
        width = Integer.parseInt(filenameSplit[1]);
        height = Integer.parseInt(filenameSplit[2]);

        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));    
        setContentPane(new JLabel(new ImageIcon(filenameSplit[0])));
        setLayout(null);
    }

    // 0: Success
    // 1: Incomplete Fields
    // 2: Failed Validation
    private String submitButtonClicked () {
        // Check for Empty Fields
        for (Field text_field : fields) {
            if (text_field.getText().length() == 0)
                return "Incomplete";
        }

        // Validate Data
        String incorrect_fields = "";
        // idNumber
        if (!isNumeric(idNumber.getText()) || idNumber.getText().replaceAll("\\s+","").length() != 6)
            incorrect_fields += "ID Number (eg.131356)\n";

        if (!incorrect_fields.isEmpty())
            return incorrect_fields;

        return "Success";
    }

    private void addCSVandBGChange()
    {
        addMouseListener(new MouseListener()
        {
            public void mousePressed(MouseEvent me)
            {
                int meX = me.getX();
                int meY = me.getY();

                if( meY>=height-25 && meY<=height && meX <= 25 && meX>=0 )
                {
                    dispose();
                    RegSystem a = new RegSystem();
                }
            }
            public void mouseReleased(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
        });
    }

    public static boolean isNumeric(String str)
    {
      return str.matches("^[0-9]+$");  //match a number with optional '-' and decimal.
    }

    public void exportData () {
        // Export data
        try {
            String fileDate = "RecWeek-Attendance_"+ returnDateToday() + ".csv";
            sdf = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
            timestamp = new Timestamp(System.currentTimeMillis());


            String data =
                idNumber.getText() + "," +
                lastName.getText() + "," +
                firstName.getText() + "," +
                middleInitial.getText().toUpperCase() + "," +
                year.getText() + "," +
                course.getText().toUpperCase() + "," +
                sorter.getSchool(course.getText()) + "," +
                sdf.format(timestamp);

                File file = new File(fileDate);
                boolean fileExistedBefore = true;
          			// if file doesnt exists, then create it
          			if (!file.exists()) {
          				    file.createNewFile();
                      fileExistedBefore = false;
          			}

          			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // boolean: append mode //getAbsoluteFile()
          			BufferedWriter bw = new BufferedWriter(fw);
                if (!fileExistedBefore) {
                    bw.append("ID Number,Last Name,First Name,MI,Year,Course,School,Timestamp");
                }
                bw.newLine();
          			bw.append(data);
          			bw.close();

            System.out.println ("Registered user!");
            for (Field field : fields) {
                field.setText("");
            }

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public String returnDateToday()
    {
      Calendar cal = Calendar.getInstance();
  		int day = cal.get(Calendar.DAY_OF_MONTH);
  		int month = cal.get(Calendar.MONTH)+1;
  		int year = cal.get(Calendar.YEAR);
  		String dateToday = Integer.toString(month)+"_"+Integer.toString(day)+"_"+Integer.toString(year);
  		return dateToday;
    }

    public static void main (String[] args) {
        RegSystem r = new RegSystem();
    }
}
