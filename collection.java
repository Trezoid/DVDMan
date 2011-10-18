import java.net.*;
import java.io.*;
import java.util.ArrayList;


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
				DVD newDisk = new DVD(line);
				disks.add(newDisk);
			}
		}

		catch(Exception e)
		{
			System.out.println("An error occured: " + e.getCause() + ", " + e.getMessage());
			e.printStackTrace();
		}

		return disks;
	}
	public int addMovie(String title, String date, String genre)
	{
		String movie = title +"#"+date+"#"+genre+"\n";
		try{
			File mov = new File(collectionFile);
			FileWriter FW = new FileWriter(mov, true);
			FW.write(movie);
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
			if(mov.toString().toLowerCase().indexOf(query) > -1)
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
