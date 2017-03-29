
/**
 * @author Rodolfo Ferreira n 41654
 * @author Joao Elvas n 41934
 */
public class BigOldDesert {
	
	
	
	public static final char EMPTY_PLAIN  = '_';
	public static final char PLAIN_CANOE ='c' ;
	public static final char PLAIN_PLANK = 'p' ;
	public static final char PLAIN_BALLOON = 'b';
	public static final char LAKE = 'L';
	public static final char PIT = 'P';
	public static final char MOUNTAIN = 'M'; 
	
	public static final int LEAVE_N = 0; // sair com nada
	public static final int LEAVE_P = 1; // sair com prancha
	public static final int LEAVE_B = 2; // sair com balao
	public static final int LEAVE_C = 3; // sair com canoa
	
	
	private char[] terrain;
	
	int[][] matrix ; 
	int nSpaces;
	
	public BigOldDesert (char[] terrain){
		this.terrain = terrain ;
		this.nSpaces = terrain.length;
		matrix = new int [4][nSpaces];
	}
	
	/**
	 * Retorna o tempo em unidades de tempo que demora a percurer o caminho mais curto
	 * @return - unidades de tempo do caminho mais curto 
	 */
	public int fasterTrack(){
		for(int x = 0  ; x < terrain.length  ; x++){
			for (int y = 0 ; y < 3 ; y++ ){
				if(x == 0){
					firstStep(terrain[x]);
				} else {
					switch(terrain[x]){
					case EMPTY_PLAIN:
						matrix[LEAVE_N][x] = min(matrix[LEAVE_N][x-1] + 1, matrix[LEAVE_P][x-1] + 2, matrix[LEAVE_B][x-1] + 2, matrix[LEAVE_C][x-1] + 2);
						matrix[LEAVE_P][x] = matrix[LEAVE_P][x-1] + 3;
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 3;
						matrix[LEAVE_C][x] = matrix[LEAVE_C][x-1] + 3;
						break;
					case PLAIN_CANOE:
						matrix[LEAVE_N][x] = min(matrix[LEAVE_N][x-1] + 1, matrix[LEAVE_P][x-1] + 2, matrix[LEAVE_B][x-1] + 2, matrix[LEAVE_C][x-1] + 2);
						matrix[LEAVE_P][x] = matrix[LEAVE_P][x-1] + 3;
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 3;
						matrix[LEAVE_C][x] = min(matrix[LEAVE_N][x-1] + 2, matrix[LEAVE_P][x-1] + 3, matrix[LEAVE_B][x-1] + 3, matrix[LEAVE_C][x-1] + 3);
						break;
					case PLAIN_PLANK:
						matrix[LEAVE_N][x] = min(matrix[LEAVE_N][x-1] + 1, matrix[LEAVE_P][x-1] + 2, matrix[LEAVE_B][x-1] + 2, matrix[LEAVE_C][x-1] + 2);
						matrix[LEAVE_P][x] = min(matrix[LEAVE_N][x-1] + 2, matrix[LEAVE_P][x-1] + 3, matrix[LEAVE_B][x-1] + 3, matrix[LEAVE_C][x-1] + 3);
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 3;
						matrix[LEAVE_C][x] = matrix[LEAVE_C][x-1] + 3;
						break;
					case PLAIN_BALLOON:
						matrix[LEAVE_N][x] = min(matrix[LEAVE_N][x-1] + 1, matrix[LEAVE_P][x-1] + 2, matrix[LEAVE_B][x-1] + 2, matrix[LEAVE_C][x-1] + 2);
						matrix[LEAVE_P][x] = matrix[LEAVE_P][x-1] + 3;
						matrix[LEAVE_B][x] = min(matrix[LEAVE_N][x-1] + 2, matrix[LEAVE_P][x-1] + 3, matrix[LEAVE_B][x-1] + 3, matrix[LEAVE_C][x-1] + 3);
						matrix[LEAVE_C][x] = matrix[LEAVE_C][x-1] + 3;
						break;
					case LAKE:
						matrix[LEAVE_N][x] = Integer.MAX_VALUE;
						matrix[LEAVE_P][x] = matrix[LEAVE_P][x-1] + 5;
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 6;
						matrix[LEAVE_C][x] = matrix[LEAVE_C][x-1] + 4;
						break;
					case PIT:
						matrix[LEAVE_N][x] = Integer.MAX_VALUE;
						matrix[LEAVE_P][x] = matrix[LEAVE_P][x-1] + 5;
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 6;
						matrix[LEAVE_C][x] = Integer.MAX_VALUE;
						break;
					case MOUNTAIN:
						matrix[LEAVE_N][x] = Integer.MAX_VALUE;
						matrix[LEAVE_P][x] = Integer.MAX_VALUE;
						matrix[LEAVE_B][x] = matrix[LEAVE_B][x-1] + 6;
						matrix[LEAVE_C][x] = Integer.MAX_VALUE;
						break;
					}
				}
				
			}
		}
		
		return min(matrix[LEAVE_N][nSpaces - 1],matrix[LEAVE_P][nSpaces - 1],matrix[LEAVE_B][nSpaces - 1],matrix[LEAVE_C][nSpaces - 1]);
	}
	
	
	/**
	 * retorna o numero mais pequeno do conjunto dos 4 numeros
	 * @param a numero a comparar
	 * @param b numero a comparar
	 * @param c numero a comparar
	 * @param d numero a comparar
	 * @return o numero mais pequeno
	 */
	private int min(int a, int b, int c, int d) {
		if(a < 0) {
			a = Integer.MAX_VALUE;
		}
		if(b < 0) {
			b = Integer.MAX_VALUE;
		}
		if(c < 0) {
			c = Integer.MAX_VALUE;
		}
		if(d < 0) {
			d = Integer.MAX_VALUE;
		}
		
		return Math.min(Math.min(a, b), Math.min(c, d));
	}
		
	/**
	 * preenche a matrix com os custos de dar o primeiro passo 
	 * @param terrainX o terreno onde se vai dar o primeiro passo
	 */
	private void firstStep(char terrainX){
		switch(terrainX){
		case EMPTY_PLAIN:
			matrix[LEAVE_N][0] = 1;
			matrix[LEAVE_P][0] = Integer.MAX_VALUE;
			matrix[LEAVE_B][0] = Integer.MAX_VALUE;
			matrix[LEAVE_C][0] = Integer.MAX_VALUE;
			break;
		case PLAIN_CANOE:
			matrix[LEAVE_N][0] = 1;
			matrix[LEAVE_P][0] = Integer.MAX_VALUE;
			matrix[LEAVE_B][0] = Integer.MAX_VALUE;
			matrix[LEAVE_C][0] = 2;
			break;
		case PLAIN_PLANK:
			matrix[LEAVE_N][0] = 1;
			matrix[LEAVE_P][0] = 2;
			matrix[LEAVE_B][0] = Integer.MAX_VALUE;
			matrix[LEAVE_C][0] = Integer.MAX_VALUE;
			break;
		case PLAIN_BALLOON:
			matrix[LEAVE_N][0] = 1;
			matrix[LEAVE_P][0] = Integer.MAX_VALUE;
			matrix[LEAVE_B][0] = 2;
			matrix[LEAVE_C][0] = Integer.MAX_VALUE;
			break;
		
		}
	
	}
}