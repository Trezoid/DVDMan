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
			System.out.println("Pick a number to start:\n1) View all titles\n2) add a movie\n3) pick a random movie.\n 0) Quit.");
			Scanner k = new Scanner(System.in);
			String temp = k.next();
			try{
				choice = Integer.parseInt(temp);
			}
			catch(Exception e){}
			
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
				case 0:
				System.out.println("Goodbye");
				break;
			}
		}
	}
	
	public static void printMovies(collection c)
	{
		System.out.println("Printing all the movies in your collections\n=========================");
		ArrayList<String> titles = c.getAllTitles();
		for(String title : titles)
		{
			System.out.println(title);
		}				
		System.out.println("============================\n");
	}

	public static void randomMovie(collection c)
	{
		ArrayList<String> titles = c.getAllTitles();
		int len = titles.size();
		Random r = new Random();
		int random = r.nextInt(len);
		System.out.println("\nYour next movie is " + titles.get(random) + "\n");
	}

	public static void addMovie(collection c)
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a title");
		String title = k.nextLine();
		System.out.println("Please enter when it was released");
		String date = k.nextLine();
		System.out.println("Please enter a genre");
		String genre = k.nextLine();
		c.addMovie(title, date, genre);
	}


}

		
