package g44Package;

public class GameCritic extends Critic{

	public static final double maxRating = 100;
	public static final double minRating = 0;
	
	private int workToDo;
	
	public GameCritic(String criticName, double criticOpinion) {
		super(criticName, criticOpinion);
	}

	@Override
	public double calculateRating(IContent content) {
		double rating;
		if (content.getIndex() == 1) {
			rating = content.getAverageRating() + (10-content.getDuration())*0.25 + super.getCriticOpinion();
		} else if (content.getIndex() == 2) {
			rating = content.getAverageRating() + content.getDuration()*0.25 + super.getCriticOpinion();
		} else {
			rating = content.getAverageRating() + (content.getDuration() - 3)*3 + super.getCriticOpinion();
		} 
		return super.validateRating(rating, maxRating, minRating);
	}
	
	@Override
	public void setContent(IContent other) {
		super.setContent(other);
		if (other != null) {	workToDo = calculateWorkToDo(super.getContent());}
	}
	
	
	@Override
	public void work() {
		workToDo--;
		if (workToDo == 0) {
			super.setIsWorking(false);
		} else {
			super.setIsWorking(true);
		}
	}
	
	private int calculateWorkToDo(IContent content) {
		int workToDo = 0;
		if (content.getIndex() == 1 ) {	workToDo = 4;}
		else if (content.getIndex() == 2) {	workToDo = content.getDuration();}
		else if (content.getIndex() == 3) {workToDo = content.getDuration()*3;}
		return workToDo;
	}
		
}
