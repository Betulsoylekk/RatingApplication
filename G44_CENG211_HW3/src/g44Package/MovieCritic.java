package g44Package;

public class MovieCritic extends Critic{

	public static final double maxRating = 10.0;
	public static final double minRating = 0.0;
	
	public MovieCritic(String criticName, double criticOpinion) {	super(criticName, criticOpinion);}
	
	@Override
	public double calculateRating(IContent content) {
		double rating  = content.getAverageRating() + 
				 		 (content.getDuration()-150)*0.01 - 
				 		 (2021-content.getYear())*0.01 + super.getCriticOpinion();
		return super.validateRating(rating, maxRating, minRating);			 		 
	}
}
