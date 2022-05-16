package g44Package;

public interface ICritic {
	
	/*
	 * Getter method of criticOpinion, returns criticOpinion
	 */
	public double getCriticOpinion();

	/*
	 * Getter method of criticName, returns criticName
	 */
	public String getCriticName();
	
	/*
	 * Getter method of content, returns a deep copy of content
	 */
	public IContent getContent();
	
	/*
	 * Setter method of content, it don't creates deepcopy of given parameter 
	 */
	public void setContent(IContent other);
	
	/*
	 * Getter method of isWorking, it returns isWorking
	 */
	public boolean getIsWorking();
	
	/*
	 * Setter method of isWorking
	 */
	public void setIsWorking(boolean isWorking);
	
	/*
	 * Calculates rating for given content;
	 * returns:
	 * 		rating, 	for minRating <= rating <= maxRating
	 * 		minRating,  for rating < minRating
	 * 		maxRating,  for rating > maxRating
	 */
	public double calculateRating(IContent content);
	
	/*
	 * Critics work in their way
	 */
	public void work();
	
	
	/*
	 * Returns meaningful representation of content, with respect to it's type
	 */
	public String toString();
	
}	
