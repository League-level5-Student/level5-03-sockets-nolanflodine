package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ChatAppServer extends JFrame {
	JButton button = new JButton("Send");
	JPanel panel = new JPanel();
	public JLabel label = new JLabel("Messages:");
	 ArrayList<String> messages = new ArrayList<String>();
	public  JTextField field = new JTextField("");
	Server server;
	Client client;
	String empty = "Messages: ";
	public static void main(String[] args) {
		new ChatAppServer();
	}

	public ChatAppServer() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e) -> {
				System.out.println("clciked");
				Server.sendMessage();
				messages.add(field.getText());
				String t = "";
				field.setText(t);			
			for (int i = 0; i < messages.size(); i++) {
				empty+="Out: " + messages.get(i) + " ";
			}
			label.setText(empty);
			empty = "Messages: ";
			
			});
			panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			add(panel);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.add(label);
			panel.add(field);
			panel.add(button);
			setVisible(true);
			setSize(800, 600);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port, label);
			System.out.println("test");
			button.addActionListener((e) -> {
				messages.add(field.getText());
				Server.sendMessage();
				String t = "";
				field.setText(t);
			for (int i = 0; i < messages.size(); i++) {
				empty+=messages.get(i) + " ";
			}
			label.setText(empty);
			empty = "Messages: ";
			});
			panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			add(panel);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.add(label);
			panel.add(field);
			panel.add(button);
			setVisible(true);
			setSize(800, 600);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
