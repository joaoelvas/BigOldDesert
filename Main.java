import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Rodolfo Ferreira n 41654
 * @author Joao Elvas n 41934
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BigOldDesert desert;
		BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
		int numProblems = Integer.parseInt(in.readLine());
		
		for (int x = 0 ; x < numProblems; x++){
			char[] terrain = in.readLine().toCharArray();
			desert =  new BigOldDesert(terrain);
			
			System.out.println(desert.fasterTrack());	
		}
		in.close();
	}
}
