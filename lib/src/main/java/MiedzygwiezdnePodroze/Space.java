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
	
	// wypisanie mapy
	public void printSpace (Square[][] map, int n) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (map[i][j].type=='\0') {
					System.out.print("_");
				}
				else { 
					System.out.print(map[i][j].type);
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
