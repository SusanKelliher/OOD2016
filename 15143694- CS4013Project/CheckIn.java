import java.util.*;
import java.io.*;
public class CheckIn
{
	private static ArrayList<String> hotelCheckIns = new ArrayList<String>();
	private String resNo;
	private int errorCheck1;
	
	/** no arg constructor*/
	CheckIn() throws IOException
	{
		readFiles();
	}
	/**read reservation information and store in array list*/
	public void readFiles() throws IOException
	{
		Scanner in = null;
		File files = new File("Reservations.csv");
		in = new Scanner(files);
		while(in.hasNextLine())
		{
			String[] result = in.nextLine().split(",");
			for(String results:result)
			{
				hotelCheckIns.add(results);
			}
		}
		Customer(hotelCheckIns);
	}
	/** does input exist method*/
	public void Customer(ArrayList<String> hotelCheckIns) throws IOException
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter reservation number of customer: ");
		resNo = in.nextLine();
		boolean found = false;
		for(int a = 0; a < hotelCheckIns.size();a++) //checks if inputted reservation number exists in file
		{
			if(hotelCheckIns.get(a).equalsIgnoreCase(resNo))
			{
				found = true;
				addToHotelStays();
			}
		}
		if(found == false)
			System.out.println("Invalid number");
	}
	/**writes check in to file*/
	public void addToHotelStays()
	{
		BufferedWriter addToFile = null;
		try
		{
			addToFile = new BufferedWriter(new FileWriter("CheckInInfo.csv", true));
			for(int j = 0; j < 8;j++)
			{
				for(int i =0; i < hotelCheckIns.size();i++)
				{
					if(hotelCheckIns.get(i).equalsIgnoreCase(String.valueOf(resNo)))
					{
						addToFile.write(hotelCheckIns.get(i + j) +",");
					}
				}
			}
			addToFile.newLine();
			addToFile.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Customer has been checked in");
	}
}