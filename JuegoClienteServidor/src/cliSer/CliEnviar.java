package cliSer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import beans.Mapa;

public class CliEnviar extends Thread{

	
	private Mapa mapa;
	private Socket sock;
	public CliEnviar(Socket s,Mapa m) {
		this.sock = s;
		this.mapa = m;
	}
	
	@Override
	public void run() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
				oos.writeObject(mapa);
			} catch (IOException e ) {
				e.printStackTrace();
			}
	}
	
	
	
	
	
}
