import java.util.*;
public class DVD{
	private String title;
	private String date;
	private String genre;
	private String overview;
	private String id;
	public DVD(String movie)
	{
		String[] Col = movie.split("#");
		this.id = Col[0];
		this.title = Col[1];
		this.date = Col[2];
		this.genre = Col[3];
		this.overview = Col[4];
	}

	public String id()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getDate()
	{
		return date;
	}

	public String getGenre()
	{
		return genre;
	}

	public String getOverview()
	{
		return overview;
	}
	
	public String toString()
	{
		String out = title + ", " + genre + "release: " + date +".\n\t"+overview;
		return out;
	}

	public String toPrintString()
	{
		String out =id +"#"+ title + "#" + date + "#" + genre + "#" + overview + "\n";
		return out;
	}
}
class CustomComparator implements Comparator<DVD> {
	@Override
	public int compare(DVD o1, DVD o2) 
	{
		String title1 = o1.getTitle().toLowerCase().replaceAll("the ", "");
		String title2 = o2.getTitle().toLowerCase().replaceAll("the ", "");
		return title1.compareTo(title2);
	}
}
		 
