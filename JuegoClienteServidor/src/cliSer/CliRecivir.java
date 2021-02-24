package cliSer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;


import beans.Mapa;

public class CliRecivir extends Thread{

	private Mapa m;
	private Socket sock;
	
	public CliRecivir(Socket sock,Mapa m) {
		this.m = m;
		this.sock = sock;
	}
	
	@Override
	public void run() {
		String s = "";Scanner teclado ;int cont =0;
		while(s.equals("")) {
			try {
				if(cont>0) {
				ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
				m = (Mapa) ois.readObject();}
				teclado = new Scanner(System.in);
				m.mostrarTablero();
		        System.out.println("dime una direccion");
		        String nombre = teclado.nextLine();
				m.jugada(nombre);
				s = m.getEstado();
				cont++;
				CliEnviar c =new CliEnviar(sock, m);
				c.start();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
