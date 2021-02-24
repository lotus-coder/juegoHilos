package cliSer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import beans.Mapa;

public class ClienteJuego{

	private Socket sock;
	private Integer numero;
	private InetAddress dir;
	
	
	public ClienteJuego(String ip, int port) throws IOException {
		dir = InetAddress.getByName(ip);
		sock= new Socket(ip,port);
	}

	
	private void envia() {
		
//		ByteArrayInputStream bs = new ByteArrayInputStream();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			Mapa m = (Mapa) ois.readObject();
			CliRecivir cr = new CliRecivir(sock,m);
			cr.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ClienteJuego c;
		try {
			c = new ClienteJuego("127.0.0.1", 6000);
			c.envia();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
