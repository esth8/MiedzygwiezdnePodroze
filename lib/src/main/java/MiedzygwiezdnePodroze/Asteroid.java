package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Asteroid {
	private int x;
	private int y;
	private String direction;
	
	public Asteroid (){
		direction = "\0";
	}
	
	public List<Asteroid> createAsteroidList (int n){ 	// n = liczba asteroid
		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
		for (int i=0; i<n; i++) {
			asteroidList.add(new Asteroid());
		}
		return asteroidList;
	}
	
	public void asteroidsInSpace (int n, int ast, List<Asteroid> asteroidList, Square[][] map) { 	// n = rozmiar mapy, ast = liczba asteroid
		for (int i=0; i<ast; i++) {
			// losowanie współrzędnych dla asteroidy dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			asteroidList.get(i).x=ox;
			asteroidList.get(i).y=oy;
			map[ox][oy].type='A';
			map[ox][oy].index=i;
		}
	}
}
