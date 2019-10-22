import java.util.*;
import java.io.*;
public class CheckOut
{
	private static ArrayList<String> hotelCheckOuts = new ArrayList<String>();
	private String resNo;
	private int errorCheck1;
	
	/**no arg constructor*/
	CheckOut() throws IOException
	{
		readFiles();
	}
	/**reads in reservation file*/
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
				hotelCheckOuts.add(results);
			}
		}
		Customer(hotelCheckOuts);
	}
	/** does input exist*/
	public void Customer(ArrayList<String> hotelCheckOuts) throws IOException
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter reservation number of customer: ");
		resNo = in.nextLine(); 
		boolean found = false;
		for(int a = 0; a < hotelCheckOuts.size();a++)//check if inputted reservation number exists in file
		{
			if(hotelCheckOuts.get(a).equalsIgnoreCase(resNo))
			{
				found = true;
				addToHotelLeaves();
			}
		}
		if(found == false)
			System.out.println("Invalid number");
	}
	
	/**writes check in to file*/
	public void addToHotelLeaves()
	{
		BufferedWriter addToFile = null;
		try
		{
			addToFile = new BufferedWriter(new FileWriter("CheckOutInfo.csv", true));
			for(int j = 0; j < 8;j++)
			{
				for(int i =0; i < hotelCheckOuts.size();i++)
				{
					if(hotelCheckOuts.get(i).equalsIgnoreCase(String.valueOf(resNo)))
					{
						addToFile.write(hotelCheckOuts.get(i + j) +",");
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
		System.out.println("Customer has been checked out");
	}
}