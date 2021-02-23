package cliSer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import beans.Mapa;

public class CliRecivir extends Thread{

	private Mapa m;
	private Socket sock;
	
	public CliRecivir() {
		
	}
	
	@Override
	public void run() {
		String s = "";Scanner teclado ;
		while(s.equals("")) {
			try {
				ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
				m = (Mapa) ois.readObject();
				teclado = new Scanner(System.in); 
		        System.out.println("dime una direccion");
		        String nombre = teclado.nextLine();
				m.jugada(nombre);
				s = m.getEstado();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
