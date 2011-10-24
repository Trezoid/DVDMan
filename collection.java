import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class collection{
	private String collectionFile = "Collection.txt";
	
	public collection(String collectionFile)
	{
		this.collectionFile = collectionFile;
	}

	public ArrayList<String> getAllTitles()
	{
		ArrayList<DVD> dvds = getMovies();
		ArrayList<String> DVDTitles = new ArrayList();

		for(DVD disk : dvds)
		{
			DVDTitles.add(disk.getTitle());
		}

		return DVDTitles;
	}

	public ArrayList<DVD> getMovies()
	{
		ArrayList<DVD> disks = new ArrayList();
		try{
			File mc = new File(collectionFile);
			FileReader fr = new FileReader(mc);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				if(line.length() > 1)
					{
						DVD newDisk = new DVD(line);
						disks.add(newDisk);
					}
			}
		}

		catch(Exception e)
		{
			System.out.println("An error occured: " + e.getCause() + ", " + e.getMessage());
			e.printStackTrace();
		}

		return disks;
	}
	public int addMovie(String title)
	{
		ArrayList<DVD> currentMovies = this.search(title);
		if(currentMovies.size() > 0)
		{
			System.out.println("Some movies with a similar name already exist in your collection. They are:");
			for(DVD curr : currentMovies)
			{
				System.out.println(curr.getTitle());
			}
			System.out.println("\nWould you like to continue adding this movie anyway? (y/n)");
			Scanner s = new Scanner(System.in);
			String yN = s.nextLine();
			if(yN.toLowerCase().indexOf("n") > -1)
			{
				return 0;
			}
		}

		tmdb t = new tmdb();
		ArrayList<DVD> movies = t.search(title);
		DVD finalMov;
		if(movies.size() > 1)
		{
			System.out.println(movies.size() + " movies for found with that title. Which one would you like to add?\n========================");
			int i = 1;
			for(DVD disk : movies)
			{
				System.out.println(i + ": " + disk.toString());
				i++;
			}
			System.out.println("Enter a number to add that movie to your collection: ");
			Scanner s = new Scanner(System.in);
			int choice = -1;
			while (choice < 0)
			{
				try{
					choice = Integer.parseInt(s.nextLine());
				}
				catch(Exception e)
				{
					System.out.println("That wasn't valid input");
				}
			}
			finalMov = movies.get(choice - 1);
		}
		else
		{
			finalMov = movies.get(0);
		}
			
		try{
			File mov = new File(collectionFile);
			FileWriter FW = new FileWriter(mov, true);
			FW.write(finalMov.toPrintString());
			FW.flush();
			FW.close();
		}
		catch(Exception e)
		{
			System.out.println("An error occured: " + e.getCause());
			return 0;
		}
		return 1;
	}
	
	public ArrayList<DVD> search(String query)
	{
		ArrayList<DVD> res = new ArrayList();
		ArrayList<DVD> disks = getMovies();
		
		for(DVD mov : disks)
		{
			if(mov.toPrintString().toLowerCase().indexOf(query.toLowerCase()) > -1)
			{
				res.add(mov);
			}
		}

		return res;
	}

	public ArrayList<DVD> searchTitle(String query)
	{
		ArrayList<DVD> res = new ArrayList();
		ArrayList<DVD> disks = getMovies();
		
		for(DVD mov : disks)
		{
			if(mov.getTitle().toLowerCase().indexOf(query) > -1)
			{
				res.add(mov);
			}
		}

		return res;
	}
	

}
