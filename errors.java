import java.util.ArrayList;
import java.util.Scanner;
public class errors{
	public errors()
	{
	}

	public int addError(ArrayList<DVD> movies)
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
					return 0;
				}

			}

			return choice;
	}
	
	public int foundOthers(ArrayList<DVD> collection)
	{
		System.out.println("Some movies with a similar name already exist in your collection. They are:");
		for(DVD curr : collection)
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
		return 1;
	}
			
}
