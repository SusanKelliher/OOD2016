import java.util.Scanner;
import java.io.IOException;
import java.util.*;
import java.io.File;
import javax.swing.*;
public class HotelBooking
{
	public static void main(String[]args) throws IOException
	{
		Menu();
	}
	/**menu for user to chose customer, hotel personnel or supervisor*/
	public static void Menu() throws IOException
	{
		Scanner in = new Scanner(System.in);
		System.out.print("1.Customer\n2.Hotel Personnel\n3.Supervisor\n");
		String hotelUser = in.nextLine();
		if(Integer.valueOf(hotelUser) == 1)//if user is a customer
		{
			Scanner userInput = new Scanner(System.in);
			System.out.print("Do you want to make a reservation or a cancellation? (1 or 2): \n1.Reservation\n2.Cancellation\n");
			int rORc = Integer.parseInt(userInput.nextLine());
			if(rORc == 1)
			{
				Reservations Customer = new Reservations();//makes new reservation
			}
			else if(rORc == 2)
			{
				Cancellations Customer = new Cancellations();//makes new cancellation
			}
			else
			{
				System.out.println("Invalid input, please try again");
			}
		}
		else if(Integer.valueOf(hotelUser) == 2)//if user is hotel personnel
		{
			Scanner userInput = new Scanner(System.in);
			System.out.println("Do you want to make a reservation, cancellationor check someone in or out?(1, 2, 3 or 4):\n1.Reservation\n2.Cancellation\n3.Check-in\n4.Check-out");
			int hdp = Integer.parseInt(userInput.nextLine());
			if(hdp == 1)
			{
				Reservations Customer = new Reservations();
			}
			else if(hdp == 2)
			{
				Cancellations Customer = new Cancellations();
			}
			else if(hdp == 3)
			{
				CheckIn Customer = new CheckIn();//checks a customer in 
			}
			else if(hdp == 4)
			{
				CheckOut Customer = new CheckOut();//checks a customer out
			}
			
		}
		else if(Integer.valueOf(hotelUser)== 3)//if user is a supervisor
		{
			Scanner userInput = new Scanner(System.in);
			System.out.println("Do you want to make a reservation, cancellation, check someone in or out, apply discount or request data?(1, 2, 3, 4, 5 or 6):\n1.Reservation\n2.Cancellation\n3.Check-in\n4.Check-out\n5.Apply discounts\n6.Request data");
			int supervisor = Integer.parseInt(userInput.nextLine());
			if(supervisor == 1)
			{
				Reservations Customer = new Reservations();
			}
			else if(supervisor == 2)
			{
				Cancellations Customer = new Cancellations();
			}
			else if(supervisor == 3)
			{
				CheckIn Customer = new CheckIn();
			}
			else if(supervisor == 4)
			{
				CheckOut Customer = new CheckOut();
			}
			else if(supervisor == 5)
			{
				supervisorDiscount discount = new supervisorDiscount();//apply discounts for customer
			}
			else if(supervisor == 6)
			{
				dataAnalysis Customer = new dataAnalysis();//analyse data
			}
		}
	}
}