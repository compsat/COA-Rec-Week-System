import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.logging.*;

public class ThankYouScreen extends JFrame {

    public ThankYouScreen() {
        setWindowSize();
        setContentPane(new JLabel(new ImageIcon("thankyou.png")));

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(RegSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
          Logger.getLogger(RegSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
          Logger.getLogger(RegSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
          Logger.getLogger(RegSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        JButton return_button = new JButton("Go Back");
        return_button.setFont(new Font("Futura", Font.PLAIN, 20));
        return_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add (return_button);
        return_button.setBackground(Color.decode("#EFEFEF"));
        return_button.setForeground(Color.decode("#333333"));
        return_button.setBorderPainted(false);
        return_button.setFocusPainted(false);
        return_button.setBounds (703, 602, 120, 56);
    }


    private void setWindowSize () {
        int width = 1000;
        int height = 750;

        // Full Screen Settings
        /*
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        */
        //setSize (new Dimension (1000, 750));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        setVisible(true);
        setLayout (null);
    }
}
