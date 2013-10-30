package clientPrgm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CilentClass {
	public String outcome() {

		// ///int port = 50000;
		String message = null, outputmsg = null;

		try {
			// ///String host = InetAddress.getLocalHost().getHostName();
			Socket client = new Socket("131.159.52.1", 50000);

			PrintWriter writer = new PrintWriter(client.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println(reader.readLine()); // read welcome message

			while (true) {
				System.out.print("1");
				message = stdin.readLine();
				System.out.print("2");
				if (message == null || message.equalsIgnoreCase("exit")) {
					System.out.print("3");
					break;
				}
				System.out.print("4");
				// writer.println(message);
				// writer.flush();
				System.out.println(reader.readLine());
				outputmsg = reader.readLine();

			}
			// client.close();

		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
		System.out.print("5");
		return outputmsg;

	}
}
