import java.util.*;
public class Cost
{
	double amount;
	/**gets cost of rooms per day stayed*/
	public double hotelRates(ArrayList<Integer> days, ArrayList<String> hotelReservations, String roomChoice)
	{
		String totalCost="";
		for(int j =0; j < days.size(); j++)
		{
			for(int i= 0; i < hotelReservations.size(); i++)
			{
				if(hotelReservations.get(i).equalsIgnoreCase(roomChoice))
				{
					totalCost = hotelReservations.get(i + 3 + days.get(j));
					amount += Double.parseDouble(totalCost);
				}
			}
		}
		return amount;
	}
	
	public boolean validDiscount(double discount)
	{
		System.out.println(amount);
		boolean valid = false;
		if(discount > 100)
		{
			valid = false;
			System.out.println("please try again");
		}
		else
			valid = true;
		return valid;
	}
}