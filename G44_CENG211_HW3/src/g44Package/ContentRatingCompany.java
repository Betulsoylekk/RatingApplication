package g44Package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ContentRatingCompany implements IContentRatingCompany {
	
	private String filePath;
	
	private Stack<IContent> arrivedMovies = new Stack<>();
	private Stack<IContent> arrivedGames  = new Stack<>();
	
	private Queue<ICritic> availableMovieCritics = new LinkedList<>();
	private Queue<ICritic> availableGameCritics = new LinkedList<>();
	
	private ArrayList<IContent> ratedMovies = new ArrayList<>();
	private ArrayList<IContent> ratedGames = new ArrayList<>();
	
	private ArrayList<ICritic> criticsWithWorksToDo = new ArrayList<>();
	
	/*
	 * only constructor for company class
	 */
	public ContentRatingCompany(String filePath) {
		loadCritics();
		this.filePath = filePath;
	}
	
	/*
	 * Simulates rating process for given days and reset all the data to be able to simulate multiple times
	 */
	public void simulate(int D) {	
		if (D <=0) {
			System.out.println("Invalid day.");
			System.out.println("Day must be greater than 0.");
			
		} else {
			for (int day = 1; day <= D; day++) {
				System.out.println("Day :" + day);
				processADayForMovies();
				processADayForGames();
				System.out.println("");
			}
			System.out.println("Ratings: \n");
			
			sortArray(ratedMovies);
			sortArray(ratedGames);
			
			System.out.println("Ratings of Movies: \n");
			for (IContent i : ratedMovies) {
				System.out.println(i.toString());
			}
			System.out.println("");
			
			System.out.println("Ratings of Games: \n");
			for (IContent i : ratedGames) {
				System.out.println(i.toString());
			}
			resetData();
		}
	}
	
	/*
	 * Process 1 day for movies
	 */
	private void processADayForMovies() {
		arrivedMovies.addAll(FileIO.readContentsArrivedDayByDay("movie", filePath));
		Queue<ICritic> tempAvailableMovieCritics = new LinkedList<ICritic>();
		
		while (arrivedMovies.size() != 0 && availableMovieCritics.size() != 0) {
			matchMovieAndCritic(tempAvailableMovieCritics);
		}
		
		availableMovieCritics.addAll(tempAvailableMovieCritics);
	}
	
	/*
	 * Takes 1 movie and 1 movieCritic and assign this movie to movieCritic's contents
	 * PRECONDITION : arrivedMovies.size() != 0 && availableMovieCritics.size() != 0
	 */
	private void matchMovieAndCritic(Queue<ICritic> tempAvailableMovieCritics) {
		IContent movie = arrivedMovies.pop();
		ICritic critic = availableMovieCritics.remove();
		critic.setContent(movie);
		double rating = critic.calculateRating(movie);
		movie.setCompanyRating(rating);
		System.out.println(critic.getCriticName() + " evaluated " + movie.getName());
		ratedMovies.add(movie);
		critic.setContent(null);
		tempAvailableMovieCritics.add(critic);
	}
	
	/*
	 * Process 1 day for games
	 */
	private void processADayForGames() {
		arrivedGames.addAll(FileIO.readContentsArrivedDayByDay("game", filePath));
		while (arrivedGames.size() != 0 && availableGameCritics.size() != 0) {
			matchGameAndCritic();
		}
		for (int i = 0; i < 8;i++) {
			process1HourForGameCritics();
		}
	}
	
	/*
	 * Takes 1 game and 1 gameCritic and assign this game to gameCritic's contents
	 * PRECONDITION: arrivedMovies.size() != 0 && availableMovieCritics.size() != 0
	 */
	private void matchGameAndCritic() {
		if (!(arrivedGames.size() == 0 || availableGameCritics.size() == 0)) {
			IContent game = arrivedGames.pop();
			ICritic critic = availableGameCritics.remove();
			critic.setContent(game);
			System.out.println(critic.getCriticName() + " works on " + game.getName());
			criticsWithWorksToDo.add(critic);
		}	
	}
	
	/*
	 * As known: gameCritics works for seperate times and as soon as their works done they go back to queue
	 * This method process 1 Hour for game Critics that are in the criticsWithWorksToDo list
	 */
	private void process1HourForGameCritics() {
		for(int i = 0; i < criticsWithWorksToDo.size(); i++) {
			if (criticsWithWorksToDo.get(i) != null) {
				ICritic tempCritic = criticsWithWorksToDo.get(i);
				IContent tempContent = tempCritic.getContent();
				tempCritic.work();
				if (!tempCritic.getIsWorking()) {
					double rating = tempCritic.calculateRating(tempContent);
					tempContent.setCompanyRating(rating);
					ratedGames.add(tempContent);
					System.out.println(tempCritic.getCriticName() + " evaluated " + tempContent.getName());
					tempCritic.setContent(null);
					availableGameCritics.add(tempCritic);
					criticsWithWorksToDo.remove(i);
					i--;
					matchGameAndCritic();
				}
			}
		}
	}

	/*
	 * It sorts contents by name
	 */
	private void sortArray(ArrayList<IContent> arr) {
		int n = arr.size();
		IContent temp;
		for(int i = 0; i< n ; i++) {
			for (int j = i+1; j < n;j++) {
				if(arr.get(i).getName().compareTo(arr.get(j).getName()) > 0) {
					temp = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			}
		}	
	}
	
	/**
	 *	Fills Critics queues for the first and only time, 
	 *								same as given order in the PDF
	 */
	private void loadCritics() {
		availableMovieCritics.add(new MovieCritic("1. Movie Critic", +0.1));
		availableMovieCritics.add(new MovieCritic("2. Movie Critic", -0.2));
		availableMovieCritics.add(new MovieCritic("3. Movie Critic", +0.3));
		
		availableGameCritics.add(new GameCritic("1. Game Critic", +5));
		availableGameCritics.add(new GameCritic("2. Game Critic", +9));
		availableGameCritics.add(new GameCritic("3. Game Critic", -3));
		availableGameCritics.add(new GameCritic("4. Game Critic", +2));
		availableGameCritics.add(new GameCritic("5. Game Critic", -7));
	}
	
	
	private void resetData() {
		arrivedMovies = new Stack<>();
		arrivedGames  = new Stack<>();
		
		availableMovieCritics = new LinkedList<>();
		availableGameCritics = new LinkedList<>();
		loadCritics();
		
		ratedMovies = new ArrayList<>();
		ratedGames = new ArrayList<>();
		
		criticsWithWorksToDo = new ArrayList<>();
		FileIO.resetData();
	}
}
