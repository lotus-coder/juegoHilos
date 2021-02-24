package cliSer;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import beans.Mapa;

public class ServidorJuego {
	
	private Mapa mapa;

	private ServerSocket sockServ;
	private InetAddress laIp;
	
	

	public ServidorJuego(String ip,int port) throws IOException{
		this.mapa = new Mapa();
		laIp = InetAddress.getByName(ip);
		sockServ = new  ServerSocket(port,3, laIp);
	}

	private void escuchar(){
	try {
		Socket sockCli = sockServ.accept();
		while(true) {
			ObjectOutputStream zos = new ObjectOutputStream(sockCli.getOutputStream());
			zos.writeObject(mapa);
			System.out.println("envia");
			ObjectInputStream os = new ObjectInputStream(sockCli.getInputStream());
			mapa = (Mapa) os.readObject();
			System.out.println("recive");

		}

	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
	
public static void main(String[] args) throws IOException {
	ServidorJuego s = new ServidorJuego("127.0.0.1", 6000);
	s.escuchar();
}

}

