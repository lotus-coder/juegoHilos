package beans;

public class Personaje {
   private int corX,corY,vida,cordura;
   
   private String localizacion;
   
   public Personaje() {
	   this.localizacion= "Aldea";
       this.vida = 5;
       this.cordura=5;
   }
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getCorX() {
		return corX;
	}

	public void setCorX(int corX) {
		this.corX = corX;
	}

	public int getCorY() {
		return corY;
	}

	public void setCorY(int corY) {
		this.corY = corY;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public int getCordura() {
        return cordura;
    }

    public void setCordura(int cordura) {
        this.cordura = cordura;
    }
}

