import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import javax.swing.*;
import java.lang.*;
public class Reservations
{
	private String rName;
	private int rNum;
	private String rType;
	private String checkInDate;
	private int noOfNights;
	private String noOfRooms;
	private String roomChoice;
	private String occupancy;
	private double breakfastCost = 10.00;
	private double cost = 0;
	private double deposit;
	
	double d;
	double realAmount;
	double advancedPurchase;
	int month, day, year;
	String breakfastIncluded;
	ArrayList<Integer> days;
	
	private static ArrayList<String> hotelReservations = new ArrayList<String>();
	
	/**no args constructor*/
	Reservations() throws IOException
	{
		readFiles();
	}
	/**reads .csv file and adds it to array list*/
	public void readFiles() throws IOException 
	{
		Scanner in = null;
		File files = new File("l4Hotels.csv");
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
	/**takes in reservation info*/
	public void Customer(ArrayList<String> hotelReservations) throws IOException
	{
		String wantsBreakfast;
		Scanner userInput = new Scanner(System.in);
		rNum = (int)(Math.random()*999999+1);//makes random reservation number
		System.out.println("Your reservation number is " + rNum);
		System.out.println("Please enter full name: ");
		rName = userInput.nextLine();
		System.out.println("Please enter what type of hotel you want (1, 2, or 3): \n1.3-star\n2.4-star\n3.5-star");
		String hotelChoice;
		int hotelType = Integer.parseInt(userInput.nextLine());
		if(hotelType == 1)
			hotelChoice = "3-star";
		else if(hotelType == 2)
			hotelChoice = "4-star";
		else
			hotelChoice = "5-star";
		System.out.println("Please enter what kind of room you want(Please enter name as you see it): ");
		for(int i= 0; i < hotelReservations.size(); i++)
		{
			if(hotelReservations.get(i).equalsIgnoreCase(hotelChoice))//checks to find i value where hotelChoice is located
			{
				System.out.println(hotelReservations.get(i+1));//gets i value + 1 to findroom name 
			}
		}
		Scanner in = new Scanner(System.in);
		roomChoice = in.nextLine();
		/*int intnoOfRooms;
		for(int i = 0; i < hotelReservations.size(); i++)
		{
			if(hotelReservations.get(i).equalsIgnoreCase(roomChoice))//finds i value of the room 
			{
				noOfRooms = (hotelReservations.get(i+1));
				intnoOfRooms = Integer.parseInt(noOfRooms);
				intnoOfRooms = intnoOfRooms - 1;
				hotelReservations.set(i,String.valueOf(intnoOfRooms));
			}
		}
		updateReservations(hotelReservations);*/
		incrementRoomCount();
		String maxAdults = "";
		String maxChildren = "";
		for(int i = 0; i < hotelReservations.size(); i++)
		{
			if(hotelReservations.get(i).equalsIgnoreCase(roomChoice))//finds i value of the room 
			{
				String maxPeople[] = hotelReservations.get(i+3).split("\\+");//gets i value + 3 to find max adults and children, splits at a backslash
				maxAdults = maxPeople[0];
				maxChildren = maxPeople[1];
			}
		}
		System.out.println("The maximum number of adults you can have is: " + maxAdults);
		System.out.println("The maximum number of children you can have is: " + maxChildren + "\nPlease enter the amount of adults and children, seperated by a comma(eg 1,0): ");
		String people[] = in.nextLine().split(","); // splits people when there is a comma
		occupancy = people[0] + " " + people[1];
		//int noOfPeople = Integer.valueOf(occupancy);
		Dates checkDate = new Dates();
		
		System.out.println("How many nights would you like to stay for?");
		noOfNights = in.nextInt();
		
		boolean check = checkDate.validateDate(checkInDate, noOfNights, cost);//validates dates
		ArrayList<Integer> daysStayed = new ArrayList<>();
		Calendar stayDates;
		stayDates = Calendar.getInstance();
		stayDates.set(Calendar.YEAR, year);
		stayDates.set(Calendar.MONTH,month - 1);
		stayDates.set(Calendar.DAY_OF_MONTH, day);
		int dayOfWeek = stayDates.get(Calendar.DAY_OF_WEEK);
		daysStayed.add(dayOfWeek);
		for(int i= 1; i < noOfNights; i++)//checks days the customer is staying
		{
			stayDates.add(Calendar.DATE, i);
			dayOfWeek = stayDates.get(Calendar.DAY_OF_WEEK);
			daysStayed.add(dayOfWeek);
			stayDates.add(Calendar.DATE, -i);
		}
		days = daysStayed; //checkDate.getDaysStayed();
		Cost dayCosts = new Cost();
		cost = dayCosts.hotelRates(days, hotelReservations, roomChoice);//finds total cost of stay
		
		checkInDate = checkDate.getDate();
		System.out.println("Would you like breakfast? (Y or N): \nY(es)\nN(o)");
		wantsBreakfast = userInput.nextLine();
		
		if(wantsBreakfast.equalsIgnoreCase("y")||wantsBreakfast.equalsIgnoreCase("yes"))
		{
			cost = cost + (breakfastCost);//adds breakfast to cost if wanted
			breakfastIncluded = "Breakfast";
		}
		else
			breakfastIncluded = "No breakfast";
		System.out.println("Price to pay: " + cost);
		deposit = (cost*.5);//deposit is 50% of total cost
		System.out.println("Please pay a deposit of " + deposit);
		writeToFile();//writes reservation info to new file
	}
	
	/** writes into reservation file*/
	public void writeToFile()
	{
		BufferedWriter addToFile = null;
		try
		{
			addToFile = new BufferedWriter(new FileWriter("Reservations.csv", true));
			addToFile.write(rNum + "," + rName + "," + checkInDate + "," + noOfNights
			+ "," + roomChoice + ","+ occupancy + "," + breakfastIncluded + "," + cost);
			addToFile.newLine();
			addToFile.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void incrementRoomCount() throws IOException
	{
		int intnoOfRooms;
		for(int i = 0; i < hotelReservations.size(); i++)
		{
			if(hotelReservations.get(i).equalsIgnoreCase(roomChoice))//finds i value of the room 
			{
				noOfRooms = (hotelReservations.get(i+1));
				intnoOfRooms = Integer.parseInt(noOfRooms);
				if(intnoOfRooms > 0)
				{
					intnoOfRooms = intnoOfRooms - 1;
					hotelReservations.set(i+1,String.valueOf(intnoOfRooms));
				}
			}
		}
		updateReservations(hotelReservations);
	}
	
	public void updateReservations(ArrayList<String> reservationsFile) throws IOException
	{
		Scanner file;
		String filename = "l4Hotels.csv";
		file = new Scanner(filename);
		File name = new File(filename);
		PrintWriter print = new PrintWriter(filename);
		int counter =0;
		for(int i =1; i < reservationsFile.size(); i++)
		{
			counter++;
			print.print(reservationsFile.get(i)+",");
			if(counter == 12)
			{
				print.println();
				counter =0;
			}
		}
		print.close();
	}
}