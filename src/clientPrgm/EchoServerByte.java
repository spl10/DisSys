package clientPrgm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerByte {
	public char one;
	public char[] mess = new char[128 * 8];
	public byte[] msg = new byte[128 * 8];
	public int i = 0;

	public EchoServerByte(int portnum) {
		try {
			server = new ServerSocket(portnum);
		} catch (Exception err) {
			System.out.println(err);
		}
	}

	public void serve() {
		try {
			while (true) {
				Socket client = server.accept();
				DataInputStream in = new DataInputStream(
						client.getInputStream());
				DataOutputStream out = new DataOutputStream(
						client.getOutputStream());
				System.out
						.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
				out.writeBytes("Welcome to the Java EchoServer.  Type 'bye' to close. \n");

				String line;
				byte[] msg = new byte[128 * 8];
				do {
					while (((one = in.readChar()) != '\n') && i < (128 * 8)) {
						mess[i] = one;
					}
					msg = mess.toString().getBytes();
					line = new String(msg);
					if (line != null)
						out.writeBytes("Got: " + line);
				} while (!line.trim().equals("bye"));
				client.close();
			}
		} catch (Exception err) {
			System.err.println(err);
		}
	}

	public static void main(String[] args) {
		EchoServerByte s = new EchoServerByte(9999);
		s.serve();
	}

	private ServerSocket server;
}
