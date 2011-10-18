public class DVD{
	private String title;
	private String date;
	private String genre;
	public DVD(String movie)
	{
		String[] Col = movie.split("#");
		this.title = Col[0];
		this.date = Col[1];
		this.genre = Col[2];
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
	public String toString()
	{
		String out = title + ", " + date + ", " + genre;
		return out;
	}
}
		 
