package es.florida.hibernate;

public class llibre 
{
	int identificaor;
	String titol;
	String autor;
	String anynaixement;
	String anypublicacio;
	String editorial;
	int numpagines;
		
	public llibre(String titol, String autor, String anynaixement, String anypublicacio, String editorial, int numpagines) 
	{
		this.titol = titol;
		this.autor = autor;
		this.anynaixement = anynaixement;
		this.anypublicacio = anypublicacio;
		this.editorial = editorial;
		this.numpagines = numpagines;
	}
	
	public llibre() 
	{
		
	}
		
	public int getIdentificaor() 
	{
		return identificaor;
	}
		
	public void setIdentificaor(int identificaor) 
	{
		this.identificaor = identificaor;
	}

	public String getTitol() 
	{
		return titol;
	}
		
	public void setTitol(String titol) 
	{
		this.titol = titol;
	}
		
	public String getAutor() 
	{
		return autor;
	}
		
	public void setAutor(String autor)
	{
		this.autor = autor;
	}
		
	public String getAnynaixement() 
	{
		return anynaixement;
	}
		
	public void setAnynaixement(String anynaixement) 
	{
		this.anynaixement = anynaixement;
	}
		
	public String getAnypublicacio() 
	{
		return anypublicacio;
	}
		
	public void setAnypublicacio(String anypublicacio) 
	{
		this.anypublicacio = anypublicacio;
	}
		
	public String getEditorial() 
	{
		return editorial;
	}
		
	public void setEditorial(String editorial) 
	{
		this.editorial = editorial;
	}
		
	public int getNumpagines() 
	{
		return numpagines;
	}
		
	public void setNumpagines(int numpagines) 
	{
		this.numpagines = numpagines;
	}
}