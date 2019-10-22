import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import javax.swing.*;
import java.lang.*;
public class Cancellations extends HotelBooking
{
	private int rNum;
	private static ArrayList<String> hotelReservations = new ArrayList<String>();
	
	/** no args constructor*/
	Cancellations() throws IOException
	{
		readFiles();
	}
	/**reads files containing reservation information and store in new array list*/
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
				hotelReservations.add(results);
			}
		}
		Customer(hotelReservations);
	}
	
	/** take in reservation number and checks if it exist*/
	public void Customer(ArrayList<String> hotelReservations)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your reservation number: ");
		rNum = in.nextInt();
		for(int i =0; i < hotelReservations.size();i++)
		{
			if(hotelReservations.get(i).equalsIgnoreCase(String.valueOf(rNum)))//if the reservation number inputted exists
			{
				addToCancellations();
			}
		}
	}
	/**write cancellation to new file*/
	public void addToCancellations()
	{
		BufferedWriter addToFile = null;
		try
		{
			addToFile = new BufferedWriter(new FileWriter("Cancellations.csv", true));
			for(int j = 0; j < 8;j++)
			{
				for(int i =0; i < hotelReservations.size();i++)
				{
					if(hotelReservations.get(i).equalsIgnoreCase(String.valueOf(rNum)))
					{
						addToFile.write(hotelReservations.get(i + j) +",");
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
	}	
}