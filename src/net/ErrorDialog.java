package net;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

class ErrorDialog 
{
	public static void main(String[] args) throws IOException {
		BufferedReader cons = new BufferedReader(new
		InputStreamReader(System.in));
		boolean quit = false;
		while (!quit) {
		System.out.print("Client> "); 
		String input = cons.readLine(); 
		String[] tokens = input.trim().split("\\s+");
		
		if(tokens != null)
		{
			if(tokens.length == 2 && tokens[0].equals("help") && tokens[1].equals("send"))
			{
				System.out.println("Syntax : <send> <message>\n" + "send text message to the " + "currently connected echo server");
			}
			
			if(tokens.length == 3 && tokens[0].equals("connect") && tokens[1].equals("131.159.52.1") && tokens[2].equals("50000"))
			{
				
				
				Socket client = new Socket("131.159.52.1", 50000);

				///PrintWriter writer = new PrintWriter(client.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

////BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

				System.out.println(reader.readLine()); // read welcome message

			}
			
			if(tokens.length == 2 && tokens[0].equals("send") )
			{
				
				System.out.println("test:"+tokens[1]);
				Socket client = new Socket("131.159.52.1", 50000);

				PrintWriter writer = new PrintWriter(client.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

				////System.out.println(reader.readLine()); // read welcome message
				
				while (true) {
					////System.out.print("Enter message to echo or Exit to end : ");
					String message = tokens[1];
					
		
						////System.out.print("Enter message to echo or Exit to end : ");
						//message = stdin.readLine();
						
						if (message == null || message.equalsIgnoreCase("exit"))
							break;
						
						writer.println(message); 
						
						System.out.println(reader.readLine());
						writer.flush();
						//client.close();
					}
					
					
				

			}
			
			
		}
		}
		}
		} 
	
	
	
