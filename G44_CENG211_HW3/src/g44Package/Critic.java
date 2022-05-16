package g44Package;

public abstract class Critic implements ICritic {
	private double criticOpinion;
	private String criticName;
	private IContent content;
	
	private boolean isWorking;
	
	/*
	 * Only constructor of this class, 
	 * criticName and criticOpinion must be given as parameter to creat an object from this class
	 */
	public Critic(String criticName, double criticOpinion) {
		this.criticName = criticName;
		this.criticOpinion = criticOpinion;
		isWorking = false;
	}
	
	public double getCriticOpinion() {	return criticOpinion;}

	public String getCriticName() {	return criticName;}
	
	public IContent getContent() {	return content.copy();}
	
	public void setContent(IContent other) {	content = other;}
	
	public boolean getIsWorking() {	return isWorking;}
	
	public void setIsWorking(boolean isWorking) {	this.isWorking = isWorking;}
	
	/*
	 * It checks rating and returns suitable rating
	 * returns:
	 * 		rating, 	for minRating <= rating <= maxRating
	 * 		minRating,  for rating < minRating
	 * 		maxRating,  for rating > maxRating 
	 */
	public double validateRating(double rating, double maxRating, double minRating) {
		if (rating > maxRating) {
			return maxRating;
		} else if (rating < minRating){
			return minRating;
		} else {
			return rating;
		}
	}
	
	@Override
	public abstract double calculateRating(IContent content);
	@Override
	public void work() {
		isWorking = true;
	}
	

}
