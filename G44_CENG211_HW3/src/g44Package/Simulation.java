/*
 * This homework has done by group 44;
 * Betul Soylek
 * Hakan Coban
 * Cihat Gelir
 */

/*
 * Class design for this homework:
 * 		Content class implements IContent;
 * 		There aren't classes like Movie,IndefiniteGame,StoryGame... because there was not necessary
 * 		It knows content type by checking content's index attribute
 * 
 * 		Critic class implements ICritic
 * 		MovieCritic and GameCritic derived from Critic class
 * 				
 * 		ContentRatingCompany implements IContentRatingCompany
 * 		
 * 		In this homework we used program to interface principle.
 * 			Instance variable declarations and parameters declarations are interfaces.
 */

package g44Package;

public class Simulation {
	public static void main(String[] args) {
		IContentRatingCompany crc = new ContentRatingCompany("contents.csv");
		crc.simulate(5); // You can invoke simulate for different timeline, feel free to try :)
	}
}