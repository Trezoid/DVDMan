import java.util.ArrayList;
public class tmdb{
	final private String key = "80e41064b55f3048ad2722864b322764";
	final private String base = "http://api.themoviedb.org/2.1/";
	final private String lang = "en/xml";

	public tmdb()
	{
	}

	public ArrayList<DVD> search(String movTitle)
	{
		ArrayList<DVD> results = new ArrayList();
		String cleanTitle = movTitle.replaceAll(" ", "+");
		String movURL = base + "Movie.search/"+lang+"/"+key +"/"+cleanTitle;

		urlConn c = new urlConn(movURL);
		String content = c.getContent();
		
		int numResults = Integer.parseInt(content.split("totalResults>")[1].split("</opensearch:")[0]);
		String[] movies = content.split("<movie>");

		for(int i = 1; i < movies.length; i++)
		{
			String id = movies[i].split("<id>")[1].split("</id>")[0];
			
			try{
			DVD disk = getInfo(id);
			results.add(disk);
			}
			catch(Exception e){System.out.println("An unknown error occured");}
		}

		return results;

	}

	public DVD getInfo(String mID)
	{
		DVD res;
		String movURL = base+"Movie.getInfo/"+lang+"/"+key+"/"+mID;
		urlConn c = new urlConn(movURL);
		String details = c.getContent().replaceAll("&#8217;", "'").replaceAll("#", "");
		String title = details.split("<name>")[1].split("</name>")[0];
		String date = details.split("<released>")[1].split("</released>")[0];
		String genres = details.split("<categories>")[1].split("</categories>")[0];
		String[] genre = genres.split("name=\"");

		String finalGenre = "";

		for(int x = 1; x < genre.length; x++)
		{
			genre[x] = genre[x].split("\"")[0];
			finalGenre += genre[x] + ", ";
		}

		if(finalGenre.length() < 1)
		{
			finalGenre = "No genre found";
		}

		String overview = details.split("<overview>")[1].split("</overview>")[0];
		
		if(overview.length() < 1)
		{
			overview = "no overview found.";
		}
		res = new DVD(mID+"#"+title+"#"+date+"#"+finalGenre+"#"+overview);
		return res;
	}
}
