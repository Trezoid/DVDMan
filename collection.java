import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

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
			return null;
		}

		return disks;
	}
	public int addMovie(String title)
	{
		errors er = new errors();
		ArrayList<DVD> currentMovies = this.searchTitle(title);
		if(currentMovies.size() > 0)
		{
			int cont = er.foundOthers(currentMovies);
			if(cont == 0)
			{
				return 0;
			}
		}	
		
		tmdb t = new tmdb();
		ArrayList<DVD> movies = t.search(title);
		DVD finalMov;
		if(movies.size() > 1)
		{
			int choice = er.addError(movies);
			finalMov = movies.get(choice - 1);
		}
		else
		{
			finalMov = movies.get(0);
		}
			
		try{
			File mov = new File(collectionFile);
			FileWriter FW = new FileWriter(mov, true);
			FW.write(finalMov.toPrintString() + "\n");
			FW.flush();
			FW.close();
		}
		catch(Exception e)
		{
			return 0;
		}
		sortCollection();

		return 1;
	}



	public int updateMovie(String title)
	{
		ArrayList<DVD> allDisks = this.getMovies();
		DVD updater = this.searchTitle(title).get(0);
		int updatePost = -1;

		for(int i = 0; i < allDisks.size(); i++)
		{
			if(allDisks.get(i).getTitle().toLowerCase().indexOf(title.toLowerCase()) > -1)
			{
				updatePost = i;
				i = allDisks.size();
			}
		}
		tmdb t = new tmdb();
		String id = this.searchTitle(title).get(0).id();
		DVD result = t.getInfo(id);
		
		allDisks.set(updatePost, result);

		String fileDump = "";

		for(DVD d : allDisks)
		{
			fileDump += d.toPrintString() + "\n";
		}

		try{
			File mov = new File(collectionFile);
			FileWriter FW = new FileWriter(mov, false);
			FW.write(fileDump);
			FW.flush();
			FW.close();
		}
		catch(Exception e)
		{
			return 0;
		}
		return 1;
	}
	
	public int sortCollection()
	{
		ArrayList<DVD> c = getMovies();
		Collections.sort(c, new CustomComparator());
		String fileDump = "";

		for(DVD d : c)
		{
			fileDump += d.toPrintString() + "\n";
		}

		try{
			File mov = new File(collectionFile);
			FileWriter FW = new FileWriter(mov, false);
			FW.write(fileDump);
			FW.flush();
			FW.close();
		}
		catch(Exception e)
		{
			return 0;
		}
		return 1;
	}
			
	private ArrayList<DVD> innerSearch(String query)
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
		
		query = query.replaceAll("s", "");

		for(DVD mov : disks)
		{
			if(mov.toPrintString().toLowerCase().indexOf(query.toLowerCase()) > -1 && !res.contains(mov))
			{
				res.add(mov);
			}
		}


		return res;
	}

	public ArrayList<DVD> search(String query)
	{
		query = query.toLowerCase().replaceAll("and", "");
		ArrayList<DVD> results = new ArrayList();
		String[] queries = query.split(" ");
		for(int i = 0; i < queries.length; i++)
		{
				ArrayList<DVD> searchRes = this.innerSearch(queries[i]);

				if(i == 0)
				{
					results.addAll(searchRes);
				}
				else{
					ArrayList<DVD> temp = new ArrayList();
					for(int y = 0; y < searchRes.size(); y++)
					{
						for(int x = 0; x < results.size(); x++)
						{
							if(results.get(x).getTitle().indexOf(searchRes.get(y).getTitle()) > -1)
							{
								temp.add(results.get(x));
							}

						}
					}
					results.clear();
					results.addAll(temp);

				}
					
		}
		return results;
	}

	public ArrayList<DVD> searchTitle(String query)
	{
		ArrayList<DVD> res = new ArrayList();
		ArrayList<DVD> disks = getMovies();
		
		for(DVD mov : disks)
		{
			if(mov.getTitle().toLowerCase().indexOf(query.toLowerCase()) > -1)
			{
				res.add(mov);
			}
		}

		return res;
	}
	

}
