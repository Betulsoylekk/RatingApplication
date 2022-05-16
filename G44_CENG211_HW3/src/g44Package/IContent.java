package g44Package;

public interface IContent {
	/*
	 * Getter Method of arrivalDay
	 */
	public int getArrivalDay();
	/*
	 * Getter Method of index
	 */
	public int getIndex();
	/*
	 * Getter Method of name
	 */
	public String getName();
	/*
	 * Getter Method of duration
	 */
	public int getDuration();
	/*
	 * Getter Method of year
	 */
	public int getYear(); // returns -1 if content doesn't have a year 
	/*
	 * Getter Method of averageRating
	 */
	public double getAverageRating();
	/*
	 * Getter Method of companyRating
	 */
	public double getCompanyRating();
	/*
	 * Setter Method of companyRating
	 */
	public void setCompanyRating(double companyRating);
	
	/*
	 * creates independent copy of itself
	 */
	public IContent copy();
}
