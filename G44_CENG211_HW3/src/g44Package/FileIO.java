package g44Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class FileIO {
	private static int dayConstantForMovie = 0;
	private static int dayConstantForGame = 0;
	
	/*
	 * CT = content type you want to read
	 */
	public static Stack<IContent> readContentsArrivedDayByDay(String CT, String filePath) {
		Stack<IContent> dailyArrivedContents = new Stack<>();
		boolean isReadingSuccessfull = readContentLine(CT,filePath,dailyArrivedContents);
		if(isReadingSuccessfull == false) {	return null;}
		return dailyArrivedContents;
	}

	/*
	 * Reads contentLine, creates objects from those lines and adds to given Stack
	 * returns true  for success 
	 * 		   false for failure
	 */
	private static boolean readContentLine(String CT, String filePath,Stack<IContent> dailyArrivedContents) {
		int dayConstant = increaseDayConstant(CT);
		if(dayConstant == -1) {	return false;}
		try {
			Scanner sc = new Scanner(new File(filePath));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] splittedLine = line.split(",");
				int arrivalDay = Integer.parseInt(splittedLine[0]);
				int index = Integer.parseInt(splittedLine[1]);
				if (arrivalDay == dayConstant) {
					if (isThisContentTypeHaveYear(index) > 0 && CT.toLowerCase().equals("movie")) {
						String name = splittedLine[2];
						int	year = Integer.parseInt(splittedLine[3]);
						int duration = Integer.parseInt(splittedLine[4]);
						double averageRating =  Double.parseDouble(splittedLine[5]);
						dailyArrivedContents.add(new Content(arrivalDay, index, name, year, duration, averageRating));
					} else if (isThisContentTypeHaveYear(index) < 0 && CT.toLowerCase().equals("game")) {
						String name = splittedLine[2];
						int	year = -1;
						int duration = Integer.parseInt(splittedLine[3]);
						double averageRating =  Double.parseDouble(splittedLine[4]);
						dailyArrivedContents.add(new Content(arrivalDay, index, name, year, duration, averageRating));	
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File couldn't found");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Couldn't parsed elements of splittedLine");
			System.exit(0);
		} catch (NullPointerException e ) {
			System.out.println("Couldn't parsed elements of splittedLine to double");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Fatal error");
			System.exit(0);
		}
		return true;
	}
		
	/* checks wheter CT is equal to "movie" or "game". CT is case-insensitive. as result :
	 * 		if CT is valid -- > increases proper day constant
	 * 		else           -- > return -1
	 */
	private static int increaseDayConstant(String CT) {
		String lowerCT = CT.toLowerCase();
		if (lowerCT.equals("movie")){
			dayConstantForMovie++;
			return dayConstantForMovie;
		} else if (lowerCT.equals("game")) {
			dayConstantForGame++;
			return dayConstantForGame;
		}
		return -1;
	}

	/* checks whether this  content type have a year or not.
	 * returns >0 if CT is "movie" 
	 * 		   <0 if CT is "game"
	 * 		   =0 if CT is not valid 
	 */
	private static int isThisContentTypeHaveYear(int index) {
		if (index == 0){
			return 1;
		} else if (index == 1 || index == 2 || index == 3) {
			return -1;
		} else {
			return 0;
		}
	}

	/*
	 * Reset data of this class to be reused
	 */
	public static void resetData() {
		dayConstantForMovie = 0;
		dayConstantForGame = 0;
	}
}	

	