package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class AliensSpaceship extends Spaceship{
	private int collidedAstrd;
	
	public AliensSpaceship (){
		inSpace = true;
		durability=RandomNumber.generateRandom(10)+1;
		enduredBattles=0;
		collidedAstrd=0;
		type='L';
	}
	
	public List<AliensSpaceship> createAliensList (int n){ 	// n = liczba statków obcych
		List<AliensSpaceship> alienList = new ArrayList<AliensSpaceship>();
		for (int i=0; i<n; i++) {
			alienList.add(new AliensSpaceship());
		}
		return alienList;
	}
	
	public void aliensInSpace (int n, int al, List<AliensSpaceship> alienList, Square[][] map) { 	// n = rozmiar mapy, al = liczba statków obcych
		for (int i=0; i<al; i++) {
			// losowanie współrzędnych dla statku obcych dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			alienList.get(i).x=ox;
			alienList.get(i).y=oy;
			map[ox][oy].type='L';
			map[ox][oy].index=i;
		}
	}
}