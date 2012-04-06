package Estado;

import java.util.ArrayList;

public class Estado 
{
	private String nome;
    private boolean inicial;
    private boolean estFinal;
    
    //posicao
    private float x; 
    private float y;
    
    private int xCentral;
    private int yCentral;
    
    public Estado( String nome, boolean inicial, boolean estFinal, float x, float y ) {
        this.nome = nome;
        this.inicial = inicial;
        this.estFinal = estFinal;
        this.x = x;
        this.y = y;
    }

    public boolean isFinal() {
        return estFinal;
    }

    public boolean isInicial() {
        return inicial;
    }

    public String getNome() {
        return nome;
    }

    public int getXCentral() {
        return xCentral;
    }

    public int getYCentral() {
        return yCentral;
    }
    
    public void setFinal( boolean estFinal ) {
        this.estFinal = estFinal;
    }
    
    public void setXCentral( int xCentral ) {
        this.xCentral = xCentral;
    }

    public void setYCentral( int yCentral ) {
        this.yCentral = yCentral;
    }

    public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Estado other = ( Estado ) obj;
        if ( this.nome != other.nome && ( this.nome == null || !this.nome.equals( other.nome ) ) ) {
            return false;
        }
        return true;
    }

    
}
