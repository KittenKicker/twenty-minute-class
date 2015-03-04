import java.io.*;
import java.util.*;

public class DatabasesAss3 
{
	//insert into the file
	public static void saveDatabase(HashMap<String, int[]> database)
	{
		File fout = new File("database.txt");
		FileOutputStream fos;
		try
		{fout.createNewFile();}
		catch(IOException e)
		{
			System.out.println("Couldn't make file");
		}
		try
		{
			fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			Iterator it = database.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, int[]> pair = (Map.Entry)it.next();
				try
				{
					bw.write(pair.getKey() + " a1: " + pair.getValue()[0] + " a2: " + pair.getValue()[1] + " a3: " + pair.getValue()[2] + " a4: " + pair.getValue()[3]);
					bw.newLine();
				}
				catch(IOException e)
				{
					System.out.println("COuldn't write");
				}
			}
			try
			{
				bw.close();
			}
			catch(IOException e)
			{
				System.out.println("Couldn't Close");
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("couldn't open file");
		}
	}
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Input Command or q to quit: ");
		String command = in.nextLine();
		String input;
		boolean iFlag = false;
		boolean rFlag = false;
		boolean dFlag = false;
		boolean uFlag = false;
		HashMap<String, int[]> database = new HashMap<String,int[]>();
		
		File file = new File("database.txt");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			try
			{
				while((line = br.readLine()) != null)
				{
					if(!line.isEmpty())
					{
						String[] splitLine = line.split("\\s+");
						int[] aValues = new int[4];
						aValues[0] = Integer.parseInt(splitLine[2]);
						aValues[1] = Integer.parseInt(splitLine[4]);
						aValues[2] = Integer.parseInt(splitLine[6]);
						aValues[3] = Integer.parseInt(splitLine[8]);
						
						database.put(splitLine[0], aValues);
					}
				}
				br.close();
			}
			catch(IOException e)
			{
				System.out.println("Couldn't read from file");
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Couldn't open file");
		}
		
		while(!command.toLowerCase().equals("q"))
		{
			iFlag = false;
			rFlag = false;
			dFlag = false;
			uFlag = false;
			if(command.toLowerCase().equals("i"))
			{
				iFlag = true;
				System.out.println("\nInsert record information: ");
			}
			else if(command.toLowerCase().equals("r"))
			{
				rFlag = true;
				System.out.println("\nInsert 18 character string to find record for: ");
			}
			else if(command.toLowerCase().equals("d"))
			{
				dFlag = true;
				System.out.println("\nInsert 18 character string to delete record for: ");
			}
			else
			{
				uFlag = true;
				System.out.println("\nInsert 18 character string, a[1-4], and new number to update: ");
			}
			
			input = in.nextLine();
			
			if(iFlag)
			{
				String[] splitInput = new String[5];
				splitInput = input.split("\\s+");
				if(database.containsKey(splitInput[0]))
				{
					System.out.println("Key already exists. Try again");
				}
				else
				{
					int[] aValues = new int[4];
					aValues[0] = Integer.parseInt(splitInput[1]);
					aValues[1] = Integer.parseInt(splitInput[2]);
					aValues[2] = Integer.parseInt(splitInput[3]);
					aValues[3] = Integer.parseInt(splitInput[4]);
					database.put(splitInput[0], aValues);
					System.out.println("Record Added");
				}
			}
			else if(rFlag)
			{
				if(database.containsKey(input))
				{
					int[] aValues = new int[4];
					aValues = database.get(input);
					System.out.println("Name: " + input + " a1: " + aValues[0] + " a2: " + aValues[1] + " a3: " + aValues[2] + " a4: " + aValues[3]);		
				}
				else
				{
					System.out.println("Key does not exist. Try again");
				}
			}
			else if(dFlag)
			{
				if(database.containsKey(input))
				{
					database.remove(input);
					System.out.println("Record deleted");
				}
				else
				{
					System.out.println("Record does not exist. Try again");
				}
			}
			else
			{
				String[] splitInput = new String[3];
				splitInput = input.split("\\s+");
				if(database.containsKey(splitInput[0]))
				{
					database.get(splitInput[0])[Integer.parseInt(splitInput[1].substring(1, 2))-1] = Integer.parseInt(splitInput[2]);
					System.out.println("Value updated");
				}
				else
				{
					System.out.println("Record does not exist. Try again");
				}
				
			}
			
			System.out.println("Input Command or q to quit: ");
			command = in.nextLine();
		}
		saveDatabase(database);
	}
}
