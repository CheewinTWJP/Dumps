import java.io.*;
import java.util.*;

public class CheewinT_Project1 {
	public static String data;
	public static int total;
	public static int count;

	public static void main(String[] args) {
		try {
			Scanner inFile1 = new Scanner(new FileReader(args[0]));
			
			BufferedWriter outFile1 = new BufferedWriter(new FileWriter(args[1]));
			
			processInt(inFile1, outFile1);
			
			inFile1.close();
			outFile1.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void processInt(Scanner inFile1, BufferedWriter outFile1)
	{
		try {
			outFile1.write("in processInt method");
			total = 0;
			count = 0;
			
			outFile1.write("\n");
			
			while(inFile1.hasNext())
			{
				data = inFile1.next();
				outFile1.write(data + " ");
				total++;
				count++;
				if(count >= 5)
				{
					outFile1.write("\n");
					count = 0;
				}
			}
			
			outFile1.write("\n"+ "The total string count is " + total);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
