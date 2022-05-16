package g44Package;

/*
 * year attribute for games should be -1
 */
public class Content implements IContent {

	private int arrivalDay;
	private int index;
	private String name;
	private int duration;
	private double averageRating; 
	private int year;
	private double companyRating; // by default it will be -10.0 -> check constructor
	
	/*
	 * Only constructor for this class
	 * arrivalDay, index,name,year,duration,averageRating must be given as parameter
	 */
	public Content(int arrivalDay, int index, String name, int year, int duration, double averageRating) 
	{
	this.arrivalDay = arrivalDay;
	this.index = index;
	this.name = name;
	this.year = year;
	this.duration = duration;
	this.averageRating = averageRating;
	companyRating = -10.0;
	}
	
	public int getArrivalDay() {	return arrivalDay;}
	
	public int getIndex() {	return index;}
	
	public String getName() {	return name;}
	
	public int getYear() {	return year;}
	
	public int getDuration() {	return duration;}
	
	public double getAverageRating() {	return averageRating;}
	
	public double getCompanyRating() {	return companyRating;}
	public void setCompanyRating(double companyRating) {	this.companyRating = companyRating;}
	
	public IContent copy() {
		IContent temp = new Content(arrivalDay,index,name,year,duration,averageRating);
		temp.setCompanyRating(companyRating);
		return temp;
	}	
	
	public String toString() {
		if (index == 0) {
			return name + " rating : " + String.format("%.2f", companyRating);
		} else {
			Double d = companyRating;
			return name + " rating : " + d.intValue();
		}
	}
}
