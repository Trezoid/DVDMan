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
			System.out.println("Pick a number to start:\n1) View all titles.\n2) Manage collection\n3) Manage groups.\n4) Pick a random movie\n5) Admin Tools \n0) Quit.");
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
					collectionOptions(c);
				break;
				case 3:
					groupOptions(c);
				break;
				case 4:
					randomOptions(c);
				break;
				case 5:
					adminOptions(c);
				break;
				case 0:
				System.out.println("Goodbye");
				break;
			}
			}
			catch(Exception e){}
		}
	}

	public static void adminOptions(collection c)
	{
		int choice = -1;
		while(choice != 0)
		{
			System.out.println("Pick a number to start:\n1) Force collection sort.\n2) Force update all movies\n3) Update a specific movie\n0) Back.");
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
					sortMovies(c);
				break;
				case 2:
					updateAllMovies(c);
				break;
				case 3:
					updateMovie(c);
				break;
				
			}
			}
			catch(Exception e){}
		}
	}

	public static void randomOptions(collection c)
	{
		int choice = -1;
		while(choice < 0)
		{
			System.out.println("Pick a number to choose where to choose random movie from:\n1) Full collection\n2) search\n3) groups.\n0) cancel\n");
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
					randomMovie(c);
				break;
				case 2:
					randomFromSearch(c);
				break;
				case 3:
					randomFromGroup();
				break;

			}
			}
			catch(Exception e){}
		}
	}


	public static void groupOptions(collection c)
	{
		int choice = 0;
		while(choice != 0)
		{
			System.out.println("Pick a number to manage groups:\n1) Add group.\n2) Add a movie to a group\n3) view a group\n0) back");
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
					addGroup();
				break;
				case 2:
					movieToGroup();
				break;
				case 3:
					viewGroup();
				break;
			}
			}
			catch(Exception e){}
		}
	}
	


	public static void collectionOptions(collection c)
	{
		int choice = -1;
		while(choice != 0)
		{
			System.out.println("Pick a number to manage collection:\n1) add a movie.\n2) search collection\n3) View movie details.\n0) Quit.");
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
					addMovie(c);
				break;
				case 2:
					search(c);
				break;
				case 3:
					viewDetails(c);
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
	public static void updateAllMovies(collection c)
	{
		ArrayList<DVD> disks = c.getMovies();
		for(DVD d : disks)
		{
			c.updateMovie(d.getTitle());
		}
	}
	public static void sortMovies(collection c)
	{
		c.sortCollection();
	}

	public static void addGroup()
	{
		System.out.println("Please enter a name for this group");
		Scanner k = new Scanner(System.in);
		String title = k.nextLine().toLowerCase();
		group g = new group();
		g.addGroup(title);
	}
	public static void movieToGroup()
	{
		group g = new group();
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter the movie title you want to add");
		String mov = k.nextLine().toLowerCase();
		System.out.println("Please enter a group to add this movie too. You currently have the following groups: ");
		ArrayList<String> groups = g.getGroups();

		for(String gr : groups)
		{
			System.out.println(gr);
		}
		System.out.println("\n");
		String group = k.nextLine().toLowerCase();
		g.addToGroup(mov, group);
	}
					
	
	public static void viewGroup()
	{
		group g = new group();
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a group to add this movie too. You currently have the following groups: ");
		ArrayList<String> groups = g.getGroups();

		for(String gr : groups)
		{
			System.out.println(gr);
		}
		System.out.println("\n");
		String group = k.nextLine().toLowerCase();

		ArrayList<DVD> dvdGroup = g.searchGroup(group);
		
		System.out.println("The group "+group+" contains the following movies: ");
		for(DVD d : dvdGroup)
		{
			System.out.println(d.getTitle());
		}
		System.out.println();
	}

	public static void randomFromGroup()
	{	
		group g = new group();
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter a group name: ");
		String query = k.nextLine().toLowerCase();
		random(g.searchGroup(query));
	}
	
}

		

