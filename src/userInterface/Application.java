package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Application extends KeyAdapter {
	private JTextArea area = new JTextArea();
	private JFrame frame = new JFrame("Echo Client");
	private Font font = new Font("Verdana", Font.BOLD, 12);

	// private JLabel label = new JLabel("EchoClient>");

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Application test = new Application();
				test.go();
			}
		});
	}

	private void go() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 300);
		// Add the ubiquitous "Hello World" label.
		// frame.getContentPane().add(label);

		String lab = "EchoClient>";
		if (lab.equals("EchoClient>")) {
			area.setFont(font);
			area.setForeground(Color.BLUE);
		} else {
			area.setFont(font);
			area.setForeground(Color.BLACK);
		}
		area.insert(lab, area.getCaretPosition());
		area.setCaretPosition(area.getDocument().getLength());
		area.addKeyListener(this);
		frame.add(new JScrollPane(area), BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			e.consume();
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			int pos = area.getCaretPosition();
			String lab = "Hello";
			if (lab.equals("EchoClient>")) {
				area.setFont(font);
				area.setForeground(Color.BLUE);
			} else {
				area.setFont(font);
				area.setForeground(Color.BLACK);
			}
			area.insert(lab, pos);
		}
	}

}
