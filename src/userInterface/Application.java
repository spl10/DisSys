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
	/**
	 * 
	 */
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
		// tp.setParagraphAttributes(aset, true);
		// tp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			e.consume();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			appendToPane(tPane, "EchoClient>", Color.RED);
			appendToPane(tPane, "", Color.BLACK);
		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			e.consume();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			e.consume();
		}
	}

}
