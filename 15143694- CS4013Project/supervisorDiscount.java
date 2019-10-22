import java.util.*;
import java.io.*;
public class supervisorDiscount
{
	private static ArrayList<String> hotelCheckIns = new ArrayList<String>();
	
	/** sends method to read files*/
	supervisorDiscount() throws IOException
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
		discount();
	}
	/** apply a discount to customer*/
	public double discount()
	{
		Scanner in = new Scanner(System.in);
		String rNo;
		double discount = 0;
		System.out.println("Enter reservation number to apply discount: ");
		rNo = in.nextLine();
		boolean found = false;
		for(int a = 0; a < hotelCheckIns.size();a++) //checks if inputted reservation number exists in file
		{
			if(hotelCheckIns.get(a).equalsIgnoreCase(rNo))
			{
				found = true;
			}
		}
		if(found == false)
			System.out.println("Invalid number");
		else
		{
			System.out.println("How much would you like to discount this customer?(%): ");
			discount = in.nextDouble();
			discount = (discount/100);//changes from percent to double
			Cost isDiscountValid = new Cost();
			boolean check = isDiscountValid.validDiscount(discount);
		}
		System.out.println(discount);
		return discount;
	}
}