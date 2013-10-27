package userInterface;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Application extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JPanel topPanel;
	private JTextPane tPane;

	public Application() throws AWTException {
		topPanel = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);

		EmptyBorder eb = new EmptyBorder(new Insets(0, 0, 0, 0));
		tPane = new JTextPane();
		topPanel.setBorder(eb);
		tPane.setBorder(eb);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		tPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		// tPane.setMargin(new Insets(5, 5, 5, 5));
		setBackground(Color.WHITE);
		topPanel.add(tPane, BorderLayout.WEST);
		appendToPane(tPane, "EchoClient>", Color.RED);
		appendToPane(tPane, "", Color.BLACK);
		tPane.addKeyListener(this);
	}

	private void appendToPane(JTextPane tp, String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, c);
		aset = sc.addAttribute(aset, StyleConstants.FontFamily,
				"Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment,
				StyleConstants.ALIGN_LEFT);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
		getContentPane().add(topPanel, BorderLayout.WEST);
		// pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Application();
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String text = "";
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			text = tPane.getText().substring(
					tPane.getText().lastIndexOf(">") + 1,
					tPane.getText().length());
			System.out.print("tPane : " + tPane.getDocument().getLength()
					+ "  text: " + text);
			if (text.contains("connect") || text.contains("send")
					|| text.contains("help") || text.equals("")) {

			} else {
				appendToPane(tPane, "Please enter a valid command. \n",
						Color.RED);
				e.setKeyCode(KeyEvent.VK_ENTER);
			}
			tPane.setEditable(true);
			appendToPane(tPane, "EchoClient>", Color.RED);
			appendToPane(tPane, "", Color.BLACK);
		}
		AttributeSet attributeSet = tPane.getInputAttributes();
		Color c = (Color) (attributeSet == null ? null : attributeSet
				.getAttribute(StyleConstants.Foreground));
		System.out.println("Color : " + c.getRed());
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && c.getRed() == 255) {
			appendToPane(tPane, "", Color.BLACK);
			e.consume();
		}
		if (c.getRed() == 255) {
			tPane.setEditable(false);
			appendToPane(tPane, "", Color.BLACK);
			tPane.setEditable(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
