package cliSer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import beans.Mapa;

public class ClienteJuego extends Thread {

	private Socket sock;
	private Integer numero;
	private InetAddress dir;
	
	
	public ClienteJuego(String ip, int port) throws IOException {
		dir = InetAddress.getByName(ip);
		sock= new Socket(ip,port);
	}
	@Override
	public void run() {
		try {
			sleep(1000);
			envia();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	private void envia() throws IOException, ClassNotFoundException {
		
//		ByteArrayInputStream bs = new ByteArrayInputStream();
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		Mapa m = (Mapa) ois.readObject();
		while(m.getEstado().equals("")) {
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		ClienteJuego c = new ClienteJuego("127.0.0.1", 6000);
	}
	
}
