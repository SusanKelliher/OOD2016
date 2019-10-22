import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.*;
import java.io.*;
public class Dates
{
	int month, day, year;
	boolean validDate;
	private Calendar c;
	private Calendar stayDates;
	private Calendar stayDates1;
	private Date apCutoff;
	private double ap;
	private String dateToReturn;
	private double cost1;
	private Date todayDate;
	ArrayList<Integer> daysStayed;
	private ArrayList<Integer> setDaysArray = new ArrayList<>();
	
	/** no arg constructor*/
	Dates()
	{
	}
	/**checks validity of date*/
	public boolean validateDate(String checkInDate, int noOfNights, double cost)
	{
		validDate = true;
		System.out.println("Enter the date you want to check in(in the format(dd/mm/yyyy): ");
		Scanner dateInput = new Scanner(System.in);
		checkInDate = dateInput.nextLine();
		if(checkInDate == null)
			validDate = false;
		String date [] = checkInDate.split("/");//splits check in date when there is a forward slash
		day = Integer.parseInt(date[0]); 
		month = Integer.parseInt(date[1]); 
		year = Integer.parseInt(date[2]);
		if(checkInDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))//validates dates here
		{
				if((month >= 1 && month <= 12) && (day >= 1 && day <= 31))
				{
					if((month == 4||month == 6||month == 9||month == 11) && (day <= 30))
						validDate = true;
					else if((month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month ==12) && day <= 31)
						validDate = true;
					else if((month ==2)&&(day <30))
					{
						boolean validLeapYear = false;
						if((year%400==0)||((year%4==0)&&(year%100!=0)))
							validLeapYear = true;
						if(validLeapYear == true && day <=29)
							validDate = true;
						else if(validLeapYear == false && day <=28)
							validDate = true;
					}
					else
						validDate = false;
				}
			else
				validDate = false;
		}
		else
			validDate = false;
		c = Calendar.getInstance();
		stayDates = Calendar.getInstance();
		stayDates1 = Calendar.getInstance();
		todayDate = new Date();//sets todays date
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		Date bookingDate = c.getTime();//day customer wants to book in
		c.add(Calendar.DATE, -14);
		apCutoff = c.getTime();//booking date - 14
		if(apCutoff.after(todayDate)) //if advanced purchase is available
		{
			ap = (cost*.05);
			cost = cost - ap;
		}
		stayDates.set(Calendar.YEAR, year);
		stayDates.set(Calendar.MONTH,month - 1);
		stayDates.set(Calendar.DAY_OF_MONTH, day);
		
		/*ArrayList<Integer> daysStayed = new ArrayList<>();
		int dayOfWeek = stayDates.get(Calendar.DAY_OF_WEEK);
		daysStayed.add(dayOfWeek);
		for(int i= 1; i < noOfNights; i++)//checks days the customer is staying
		{
			stayDates.add(Calendar.DATE, i);
			dayOfWeek = stayDates.get(Calendar.DAY_OF_WEEK);
			daysStayed.add(dayOfWeek);
			stayDates.add(Calendar.DATE, -i);
		}
		setArray(daysStayed);*/
		
		if(validDate == true)
			checkInDate = Integer.toString(day) + "\\" + Integer.toString(month) + "\\" + Integer.toString(year);
		else if(validDate == false)
		{
			System.out.println("Invalid input please try again");
			validateDate(checkInDate, noOfNights, cost);
		}
		dateToReturn = checkInDate;
		setCost(cost);
		return validDate;
	}
	
	/** return date*/
	public String getDate()
	{
		return dateToReturn;
	}
	/***/
	public ArrayList<Integer> getDaysStayed()
	{	
		setArray(daysStayed);
		return daysStayed;
	}
	/***/
	public void setArray(ArrayList<Integer> daysStayed)
	{
		setDaysArray = daysStayed;
	}
	
	public void setCost(double cost)
	{
		cost1 = cost;
	}
	/**returns cost*/
	public double getCost(double cost)
	{
		return cost1;
	}
	
}