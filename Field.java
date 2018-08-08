import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.awt.GraphicsEnvironment;

@SuppressWarnings("serial")
public class Field extends JTextField {

    private String placeholder;
    int limit;

    public Field (String placeholder, int limit) {
        this.placeholder = placeholder;
        this.limit = limit;
        /*
        try {
             GraphicsEnvironment ge =
                 GraphicsEnvironment.getLocalGraphicsEnvironment();
                 ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")));
        } catch (Exception e) {
             //Handle exception
        }
        setFont(new Font("Futura Book", Font.PLAIN, 18));*/
        setFont(new Font("Arial", Font.PLAIN, 18));
        setForeground(Color.decode("#333333"));
        setDocument(new JTextFieldLimit(limit));
    }

    public Field (int limit) {
        this.placeholder = "";
        this.limit = limit;
        /*
        try {
             GraphicsEnvironment ge =
                 GraphicsEnvironment.getLocalGraphicsEnvironment();
                 Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf"));
                 font = font.deriveFont(Font.BOLD,18);
                 ge.registerFont(font);
                 setFont(font);
        } catch (Exception e) {
             //Handle exception
        }*/
        setFont(new Font("Arial", Font.PLAIN, 18));
        setForeground(Color.decode("#333333"));
        setDocument(new JTextFieldLimit(limit));
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (super.getText().length() > 0 || placeholder == null) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(super.getDisabledTextColor());
		g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
	}
}
