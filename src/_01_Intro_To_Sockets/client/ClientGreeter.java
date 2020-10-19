package _01_Intro_To_Sockets.client;

import java.net.*;

import javax.swing.JOptionPane;

import _01_Intro_To_Sockets.server.ServerGreeter;

import java.io.*;

public class ClientGreeter extends ServerGreeter {

   public ClientGreeter() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

public static void main(String [] args) {
	  //1. Create a String for the ip address of the server. 
	  // If you don't know how to find a computer's ip address, ask about ifconfig on linux/mac and ipconfig on windows.
      String ip = "192.168.1.97";
      //2. Create an integer for the server's port number
      int servPort = 54200;
      //3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
    try {
   	 //4. Create an object of the Socket class. When initializing the object, pass in the ip address and the port number
    	 Socket sock = new Socket(ip, servPort);
        //5. Create a DataOutputStream object. When initializing it, use the Socket object you created in step 4 to call the getOutputStream() method.
        DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
        //6. Use the DataOutputStream object to send a message to the server using the writeUTF(String message) method
        dos.writeUTF("Ello Mate");
        //7. Create a DataInputStream object. When initializing it, use the Server object you created in step 4 to call the getInputStream() method.
        try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("reading");
        DataInputStream dis = new DataInputStream(sock.getInputStream());
        //8. Use the DataInputStream object to print a message from the server using the readUTF() method.
        System.out.println(dis.readUTF());
        //9. Close the client's server object
        sock.close();	
	} catch (IOException e) {
		// TODO: handle exception
	}
   }
}
