import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class main{
	public static void main(String[] args)
	{
		collection c = new collection("collection.txt");
		
		int choice = -1;
		while(choice != 0)
		{
			System.out.println("Pick a number to start:\n1) View all titles.\n2) add a movie.\n3) pick a random movie.\n4) Random movie from search\n5) Search for movie.\n6) view details of a movie \n7) Update a movie \n0) Quit.");
			Scanner k = new Scanner(System.in);
			String temp = k.next();
			try{
				choice = Integer.parseInt(temp);
			}
			catch(Exception e){}
			try{	
			switch(choice)
			{
				case 1:
					printMovies(c);
				break;

				case 2:
					addMovie(c);
				break;
				case 3:
					randomMovie(c);
				break;
				case 4:
					randomFromSearch(c);
				break;
				case 5:
					search(c);
				break;
				case 6:
					viewDetails(c);
				break;	
				case 7:
					updateMovie(c);
				break;

				case 9999:
					sortMovies(c);
				break;
				case 0:
				System.out.println("Goodbye");
				break;
			}
			}
			catch(Exception e){}
		}
	}
	
	public static void printMovies(collection c)
	{
		ArrayList<String> titles = c.getAllTitles();
		System.out.println("Printing all the movies ("+titles.size()+") in your collection\n=========================");
		
		for(String title : titles)
		{
			System.out.println(title);
		}				
		System.out.println("============================\n");
	}

	public static void randomMovie(collection c)
	{
		random(c.getMovies());
	}

	public static void randomFromSearch(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a search query: ");
		String query = k.nextLine().toLowerCase();
		random(c.search(query));
	}

	public static void random(ArrayList<DVD> col)
	{
		int len = col.size();
		Random r = new Random();
		int random = r.nextInt(len);
		System.out.println("\nYour next movie is " + col.get(random).getTitle() + "\n");
	}

	public static void addMovie(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a title");
		String title = k.nextLine();
		c.addMovie(title);
	}


	public static void search(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a search query: ");
		String query = k.nextLine().toLowerCase();
		ArrayList<DVD> res = c.search(query);
		if(res.size() != 1)
		{
			System.out.println("\nYour search returned the following "+res.size()+" results: \n==============================");
			for(DVD d : res)
			{
				System.out.println(d.getTitle());
			}
		}
		else{
			System.out.println("\nYour search found the following movie:\n ");
			System.out.println(res.get(0).toString());
		}
		System.out.println("\n");
	}

	public static void viewDetails(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a movie title: ");
		String query = k.nextLine().toLowerCase();
		ArrayList<DVD> res = c.searchTitle(query);
		try{
			DVD mov = res.get(0);
			System.out.println(mov.toString());	
		}
		catch(Exception e)
		{
			System.out.println("No movie was found");
		}
		System.out.println("\n");
	}

	public static void updateMovie(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a movie title: ");
		String query = k.nextLine().toLowerCase();
		c.updateMovie(query);
		System.out.println(query +" Was succesfully updated.");
	}

	public static void sortMovies(collection c)
	{
		c.sortCollection();
	}
}

		

