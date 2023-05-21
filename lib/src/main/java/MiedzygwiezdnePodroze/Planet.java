package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

import java.util.ArrayList;

public class Planet {
	private int x;
	private int y;
	private int landedTravShips;
	private int landedAlienShips;
	private int collidedAstrd;
	
	public Planet (){
		landedTravShips=0;
		landedAlienShips=0;
		collidedAstrd=0;
	}
	
	public List<Planet> createPlanetList (int n){ 	// n = liczba planet
		List<Planet> planetList = new ArrayList<Planet>();
		for (int i=0; i<n; i++) {
			planetList.add(new Planet());
		}
		return planetList;
	}
	
	public void planetsInSpace (int n, int pl, List<Planet> planetList, Square[][] map) { 	// n = rozmiar mapy, pl - liczba planet
		for (int i=0; i<pl; i++) {
			// losowanie współrzędnych dla planety dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			planetList.get(i).x=ox;
			planetList.get(i).y=oy;
			map[ox][oy].type='P';
			map[ox][oy].index=i;
		}
	}
}
