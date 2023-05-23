package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class TravelersSpaceship extends Spaceship{
	public TravelersSpaceship (){
		inSpace = true;
		durability=RandomNumber.generateRandom(10)+1;
		enduredBattles=0;
	}
	
	public List<TravelersSpaceship> createTravelersList (int n){ 	// n = liczba statków podróżników
		List<TravelersSpaceship> travelerList = new ArrayList<TravelersSpaceship>();
		for (int i=0; i<n; i++) {
			travelerList.add(new TravelersSpaceship());
		}
		return travelerList;
	}
	
	public void travelersInSpace (int n, int tr, List<TravelersSpaceship> travelerList, Square[][] map) { 	// n = rozmiar mapy, tr = liczba statków podróżników
		for (int i=0; i<tr; i++) {
			// losowanie współrzędnych dla statku podróżników dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			travelerList.get(i).x=ox;
			travelerList.get(i).y=oy;
			map[ox][oy].type='T';
			map[ox][oy].index=i;
		}
	}
}