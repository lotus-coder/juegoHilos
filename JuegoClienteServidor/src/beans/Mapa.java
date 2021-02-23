package beans;

import java.io.Serializable;
import java.util.Scanner;

public class Mapa implements Serializable{
	private final String PRIMERA_FILA ="__________________________________________________";
	private final String ULTIMA_FILA  ="͞͞ ͞ ͞ ͞   ͞  ͞  ͞  ͞  ͞  ͞   ͞  ͞   ͞   ͞   ͞  ͞  ͞  ͞  ͞  ͞   ͞  ͞  ͞ ͞   ͞ ͞ ͞ ͞ ͞   ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞  ͞   ͞  ͞   ͞ ͞  ͞ ͞ ͞ ͞ ͞ ͞";
	private final String FILA ="_͞_͞͞͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞͞͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_͞_";
	private final String UNIDAD="▓   ▓";
	private final String UNIDAD_DESCUBIERTA="▓ D ▓";
	private final String UNIDAD_CASA="▓ ⌂ ▓";
	private final String UNIDAD_JUGADOR="▓ ⛷ ▓";
	private final String UNIDAD_ALHAJA="▓ X ▓";
	private String  estado;
	private String [][]tableroGrafico;
	private int [][]tableroPosicion;
	private int []posicionBase,posicionJugador,posicionAlhaja;
	private Personaje jugador;
	private boolean tieneAlhaja;
	private int descubiertas,totalMovimientos;
	
	public Mapa() {
		tableroGrafico = new String [10][10];
		tableroPosicion= new int [10][10];
		posicionAlhaja = new int [2];
		posicionBase = new int [2];
		posicionJugador = new int [2];
		jugador = new Personaje();
		totalMovimientos=0;
		descubiertas=1;
		estado = "";
		generaTablero();
		tieneAlhaja = false;
	}
	
    private String generarLocalizacion(){
        String localizacion="";
        int localizacionAleatoriaAux= dameRandom(1, 6);
        switch(localizacionAleatoriaAux){
            case 1:localizacion= "Pradera";
            break;
            case 2:localizacion= "Aldea";
            break;
            case 3:localizacion= "Bosque";
            break;
            case 4:localizacion= "Pantano";
            break;
            case 5:localizacion= "Cementerio";
            break;
            case 6:localizacion= "Desierto";
            break;
        }
        return localizacion;
    }
    
    
    public String getEstado() {
		return estado;
	}

	public void mModificarjugador(String localizacion){
        switch(localizacion){
            case "Pradera":
            	jugador.setVida(jugador.getVida()+1);
                jugador.setCordura(jugador.getCordura()+1);
                break;           
                           
            case "Aldea":jugador.setCordura(jugador.getCordura()+2);
            	break;           
            case "Bosque":jugador.setVida(jugador.getVida()+2);
            	break;           
            case "Pantano":
	            jugador.setVida(jugador.getVida()-1);               
	            jugador.setCordura(jugador.getCordura()-1);
            	break;
            case "Cementerio":
            	jugador.setCordura(jugador.getCordura()-2);
            break;           
            case "Desierto":
            	jugador.setVida(jugador.getVida()-2);
            break;           
        }
    }
    
	private void generaTablero() {
		for (int i = 0; i < tableroGrafico.length; i++) {
			for (int j = 0; j < tableroGrafico[i].length; j++) {
				tableroGrafico[i][j] = UNIDAD;
			}
		}
		posicionAlhaja[0] = dameRandom(0,9);
		posicionAlhaja[1] = dameRandom(0,9);
		posicionBase[0] = dameRandom(0,9);
		posicionBase[1] = dameRandom(0,9);
		posicionJugador[0] = posicionBase[0];
		posicionJugador[1] = posicionBase[1];
		jugador.setCorX(posicionJugador[1]);
		jugador.setCorY(posicionJugador[0]);
		tableroGrafico[posicionBase[0]][posicionBase[1]] = UNIDAD_JUGADOR;
	}
	
	public void mostrarTablero() {
		System.out.println(PRIMERA_FILA);
		for (int i = 0; i < tableroGrafico.length-1; i++) {
			for (int j = 0; j < tableroGrafico[i].length; j++) {
				System.out.print(tableroGrafico[i][j]);
			}
			System.out.println("\n"+FILA);
		}
		for (int i = 0; i < tableroGrafico[9].length; i++) {
			System.out.print(tableroGrafico[9][i]);	
		}
		System.out.println("\n"+ULTIMA_FILA);
		System.out.println("vida: "+jugador.getVida()+" cordura: "+jugador.getCordura());
	}

   private int dameRandom(int min,int max){
        int numero = (int)(Math.random()*(max-min+1)+min);
        return numero;
    }
	   
	public synchronized void jugada(String direccion) {
		String d =direccion.toUpperCase();
		totalMovimientos++;
		if(tableroGrafico[jugador.getCorY()][jugador.getCorX()].equals(UNIDAD)) descubiertas++;
		tableroGrafico[jugador.getCorY()][jugador.getCorX()] = UNIDAD_DESCUBIERTA;
		if(d.equals("E")) {
			jugador.setCorX(jugador.getCorX()-1);
		}else if (d.equals("O")) {
			jugador.setCorX(jugador.getCorX()+1);
		}else if (d.equals("N")) {
			jugador.setCorY(jugador.getCorY()-1);
		}else if (d.equals("S")) {
			jugador.setCorY(jugador.getCorY()+1);
		}
		mModificarjugador(generarLocalizacion());
		actualzarTablero();
		mostrarTablero();
		if(posicionAlhaja[1]==jugador.getCorX()&&posicionAlhaja[0]==jugador.getCorY()) {
			System.out.println("Ya tienes la halaja, vuelve a la base!");	
			tieneAlhaja = true;
		}if(jugador.getCordura()<=0||jugador.getVida()<=0) {
			System.out.println("Has perdido");
			estado = "F";
		}
	}
	   
	private void actualzarTablero() {
		tableroGrafico[posicionBase[0]][posicionBase[1]] = UNIDAD_CASA;
		tableroGrafico[posicionAlhaja[0]][posicionAlhaja[1]] = UNIDAD_ALHAJA;
		tableroGrafico[jugador.getCorY()][jugador.getCorX()] = UNIDAD_JUGADOR;
	}
	
	public static void main(String[] args) {
		Mapa m = new Mapa();
		m.mostrarTablero();
		while (true) {
			Scanner teclado = new Scanner(System.in); 
	        System.out.println("dime un nombre");
	        String nombre = teclado.nextLine();
			m.jugada(nombre);
		}
	}
	
}
