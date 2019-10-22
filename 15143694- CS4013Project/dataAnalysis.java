import java.io.*;
import java.util.*;
public class dataAnalysis
{
	private static ArrayList<String> hotelAnalysis = new ArrayList<String>();
	/**sends method to read files*/
	dataAnalysis() throws IOException
	{
		readFiles();
	}
	/**only uses information once the user checks out*/
	public void readFiles() throws IOException
	{
		Scanner in = null;
		File files = new File("CheckOutInfo.csv");
		in = new Scanner(files);
		while(in.hasNextLine())
		{
			String[] result = in.nextLine().split(",");
			for(String results:result)
			{
				hotelAnalysis.add(results);
			}
		}
		Customer(hotelAnalysis);
	}
	/**Supervisor can choose a hotel and room type to check the occupancy and rates*/
	public void Customer(ArrayList<String> hotelAnalysis) throws IOException
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Please choose a hotel to collect data from (1, 2, or 3): \n1.3-star\n2.4-star\n3.5-star");
		String certainHotel;
		int hotelType = Integer.parseInt(in.nextLine());
		if(hotelType == 1)
			certainHotel = "3-star";
		else if(hotelType == 2)
			certainHotel = "4-star";
		else
			certainHotel = "5-star";
		String roomChoice;
		System.out.println("Please enter what kind of room you want(Please enter name as you see it): ");
		for(int i= 0; i < hotelAnalysis.size(); i++)
		{
			if(hotelAnalysis.get(i).equalsIgnoreCase(certainHotel))
			{
				System.out.println(hotelAnalysis.get(i+1));
			}
		}
		roomChoice = in.nextLine();
	}
}