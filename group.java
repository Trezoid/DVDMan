import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class group{
	private String groupFile = "groups.txt";

	public group()
	{
	}

	public ArrayList<String> getGroups()
	{
		ArrayList<String> groups = new ArrayList();
		try{
			File mc = new File(groupFile);
			FileReader fr = new FileReader(mc);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				if(line.length() > 1)
					{
						groups.add(line);
					}
			}
		}

		catch(Exception e)
		{
			return null;
		}

		return groups;
	}
	public int addGroup(String title)
	{
		try{
			File mov = new File(groupFile);
			FileWriter FW = new FileWriter(mov, true);
			FW.write(title + "\n");
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
	
	public int sortCollection()
	{
		ArrayList<String> c = getGroups();
		Collections.sort(c);
		String fileDump = "";

		for(String d : c)
		{
			fileDump += d + "\n";
		}

		try{
			File mov = new File(groupFile);
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

	public ArrayList<DVD> searchGroup(String query)
	{
		collection c = new collection("collection.txt");
		ArrayList<DVD> disks = c.getMovies();
		ArrayList<DVD> group = new ArrayList();

		for(DVD d : disks)
		{
			if(d.getGroups().indexOf(query) > -1)
			{
				group.add(d);
			}
		}
		return group;
	}
	
	public int addToGroup(String title, String Group)
	{
		collection c = new collection("collection.txt");
		ArrayList<DVD> allDisks = c.getMovies();
		DVD updater = c.searchTitle(title).get(0);
		int updatePost = -1;

		for(int i = 0; i < allDisks.size(); i++)
		{
			if(allDisks.get(i).getTitle().toLowerCase().indexOf(title.toLowerCase()) > -1)
			{
				updatePost = i;
				i = allDisks.size();
			}
		}

		DVD result = allDisks.get(updatePost);
		result.writeGroup(Group);
		allDisks.set(updatePost, result);

		String fileDump = "";

		for(DVD d : allDisks)
		{
			fileDump += d.toPrintString() + "\n";
		}

		try{
			File mov = new File("collection.txt");
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

}
