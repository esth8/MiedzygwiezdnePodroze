package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class BlackHole {
	private int x;
	private int y;
	private int destroyedTravShips;
	private int destroyedAlienShips;
	private int destroyedAstrd;
	
	public BlackHole (){
		destroyedTravShips=0;
		destroyedAlienShips=0;
		destroyedAstrd=0;
	}
	
	public List<BlackHole> createBHList (int n){ 	// n = liczba czarnych dziur
		List<BlackHole> blackHoleList = new ArrayList<BlackHole>();
		for (int i=0; i<n; i++) {
			blackHoleList.add(new BlackHole());
		}
		return blackHoleList;
	}
	
	public void holesInSpace (int n, int bh, List<BlackHole> blackHoleList, Square[][] map) { 	// n = rozmiar mapy, bh = liczba czarnych dziur
		for (int i=0; i<bh; i++) {
			// losowanie współrzędnych dla planety dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			blackHoleList.get(i).x=ox;
			blackHoleList.get(i).y=oy;
			map[ox][oy].type='H';
			map[ox][oy].index=i;
		}
	}
}

