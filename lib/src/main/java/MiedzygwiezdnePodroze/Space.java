package MiedzygwiezdnePodroze;

public class Space {
	// utworzenie dwuwymiarowej tablicy obiektow klasy Square
	public Square[][] createSpace (int n) {
		Square[][] map = new Square[n][n];
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++) {
				map[i][j] = new Square('\0',-1);
			}
		return map;
	}
	
	// wypisanie mapy w kolorach
	public void printSpace (Square[][] map, int n) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				switch(map[j][i].type) {
				case '\0': {
					System.out.print("_");
					break;
				}
				case 'T': {
					System.out.print("\033[0;46m"+"T"+"\033[0m");
					break;
				}
				case 'L': {
					System.out.print("\033[0;42m"+"L"+"\033[0m");
					break;
				}
				case 'P': {
					System.out.print("\033[0;45m"+"P"+"\033[0m");
					break;
				}
				case 'H': {
					System.out.print("\033[0;41m"+"H"+"\033[0m");
					break;
				}
				case 'A': {
					System.out.print("\033[0;43m"+"A"+"\033[0m");
					break;
				}
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	// pojedyncza komorka mapy
	public class Square {
		public char type; 	// oznaczenie klasy obiektu znajdujacego sie w danej komorce
		public int index;	// indeks danego obiektu na liscie obiektow jego klasy
		
		public Square(char type, int index){
			this.type=type;
			this.index=index;
		}
	}
}
