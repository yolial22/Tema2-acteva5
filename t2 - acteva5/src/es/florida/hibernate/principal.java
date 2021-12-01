package es.florida.hibernate;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.Serializable;

public class principal 
{
	// Este metode ens permitira fer un ArrayList de tots el llibres que tenim en la llista i guardarlos.
	public static ArrayList<llibre> Llibres = new ArrayList<llibre>();
		
	// Este metode ens permitira verificar lo que anem a escriure nosaltres per consola.
	static Scanner teclat = new Scanner(System.in);
	
	// Este metode ens permitira recorrer la llista de llibres i afegirlos al ArrayList a traves del metode recuperarTots,
	// i si el id que nosotres preguntem per pantalla es el mateix que esta en este metode, ens traura el id i el titol,
	// de eixe llibre.
	public static int recuperarLlibre(int identificaor, SessionFactory sessionFactory) 
	{				
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<llibre> Llibrests = recuperarTots(sessionFactory);
					
		for(llibre lli : Llibrests) 
		{
			if(lli.getIdentificaor() == identificaor) 
			{
				System.out.println("Identificaor del llibre: " + lli.getIdentificaor());
				System.out.println("Titol del llibre: " + lli.getTitol());
				System.out.println("Autor del llibre: " + lli.getAutor());
				System.out.println("Any de naixement del autor: " + lli.getAnynaixement());
				System.out.println("Any de publicacio del llibre: " + lli.getAnypublicacio());			
				System.out.println("Editorial del llibre: " + lli.getEditorial());
				System.out.println("Nombre de pagines del llibre: " + lli.getNumpagines());
			}
		}
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
		
		return identificaor;
	}
	
	// Este metode ens permitira mostrar tota la informacio dels llibres que hi ha en la llista del ArrayList.
	public static void mostrarLlibre(llibre llibre, SessionFactory sessionFactory)
	{
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("Identificaor del llibre: " + llibre.getIdentificaor());
			
		System.out.println("Titol del llibre: " + llibre.getTitol());
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
	}
	
	// Este metode ens permitira recorrer tota la lista de llibres del ArrayList i afegirlos a la llista.
	public static List<llibre> recuperarTots(SessionFactory sessionFactory)
	{
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<llibre> llistallibres = new ArrayList();
		
		llistallibres = session.createQuery("FROM llibre").list();
		
		session.getTransaction().commit();
		session.close();
		
		return llistallibres;
	}
	
	// Este metode ens permitira crear un nou llibre que se afegira al ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre nou que se ha afexit.
	public static int crearLlibre(llibre llibre, SessionFactory sessionFactory) 
	{
		List<llibre> Llibrests = recuperarTots(sessionFactory);
				
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Serializable id = session.save(llibre);
		
		Llibrests.add(llibre);
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
		
		return llibre.getIdentificaor();
	}
	
	// Este metode ens permitira a traves de un id que nosaltres preguntarem, actualizar un llibre que estaba en el ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre actualizat de la llista.
	public static void actualitzarLlibre(int identificaor, SessionFactory sessionFactory) 
	{					
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		llibre llibre = (llibre) session.load(llibre.class, identificaor);
		
		System.out.print("Cambia el titol: ");
		llibre.setTitol(teclat.nextLine());
		
		System.out.print("Cambia el autor: ");
		llibre.setAutor(teclat.nextLine());
				
		System.out.print("Cambia el any de naixement: ");
		llibre.setAnynaixement(teclat.nextLine());
		
		System.out.print("Cambia el any de publicacio: ");
		llibre.setAnypublicacio(teclat.nextLine());
				
		System.out.print("Cambia la editorial: ");
		llibre.setEditorial(teclat.nextLine());
				
		System.out.print("Cambia el nombre de pagines: ");
		llibre.setNumpagines(Integer.parseInt(teclat.nextLine()));
		
		session.update(llibre);
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
	}
	
	// Este metode ens permitira a traves de un id que nosaltres preguntarem, borrar un llibre que estaba en el ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre borrar de la llista.
	public static void borrarLlibre(int identificaor, SessionFactory sessionFactory) 
	{		
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<llibre> Llibrests = recuperarTots(sessionFactory);
		
		llibre llibre2 = new llibre();
		llibre2.setIdentificaor(identificaor);
		session.delete(llibre2);
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		// Carrega la configuracio i crea un session factory.
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(llibre.class);
		ServiceRegistry registry = new
		StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
				
		// Obri una nova sessió de la session factory.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
				
		System.out.println("Menu de la biblioteca.");
		System.out.println("");
		System.out.println("1. Mostrar tots els titols de la biblioteca.");
		System.out.println("2. Mostrar informacion detallada d'un llibre a partir del seu identificaor.");
		System.out.println("3. Afegir un nou llibre a la biblioteca.");
		System.out.println("4. Actualitzar un llibre a partir del seu identificaor.");
		System.out.println("5. Borrar un llibre a partir del seu identificaor.");
		System.out.println("6. Tancar la biblioteca.");	
		System.out.println("");
				
		System.out.print("Introduix un numero: ");
		
		int numero;
		
		numero = Integer.parseInt(teclat.nextLine());
				
		int identificaor;
		String titol;
		String autor;
		String anynaixement;
		String anypublicacio;
		String editorial;
		int numpagines;
				
		switch(numero) 
		{
			case 1:
				
			List<llibre> Llibrests = recuperarTots(sessionFactory);
						
			for(llibre lli : Llibrests) 
			{
				mostrarLlibre(lli, sessionFactory);
			}
			
			break;
			
			case 2:
			System.out.print("Introduix un identificaor: ");
			
			int id = Integer.parseInt(teclat.nextLine());
			
			recuperarLlibre(id,sessionFactory);
			
			break;
			
			case 3:			
			System.out.print("Introduix un titol: ");
			titol = teclat.nextLine();
						
			System.out.print("Introduix un autor: ");
			autor = teclat.nextLine();
						
			System.out.print("Introduix un any de naixement: ");
			anynaixement = teclat.nextLine();
						
			System.out.print("Introduix un any de publicacio: ");
			anypublicacio = teclat.nextLine();
						
			System.out.print("Introduix una editorial: ");
			editorial = teclat.nextLine();
						
			System.out.print("Introduix un nombre de pagines: ");
			numpagines = Integer.parseInt(teclat.nextLine());
						
			llibre llibrenou = new llibre(titol,autor,anynaixement,anypublicacio,editorial,numpagines);
						
			crearLlibre(llibrenou,sessionFactory);
			
			break;
			
			case 4:
			System.out.print("Introduix un identificaor: ");
			
			int ide = Integer.parseInt(teclat.nextLine());
			
			actualitzarLlibre(ide,sessionFactory);
			
			break;
			
			case 5:
			System.out.print("Introduix un identificaor: ");
			
			int iden = Integer.parseInt(teclat.nextLine());
			
			borrarLlibre(iden,sessionFactory);
			
			break;
			
			case 6:
				
			System.out.print("Biblioteca tanca.");
			
			break;
			
			default:
				
			System.out.print("El nombre introduit no es correcte.");
			
			break;
		}
		
		// Commit de la transacció i tanca de sessió.
		session.getTransaction().commit();
		session.close();
	}
}