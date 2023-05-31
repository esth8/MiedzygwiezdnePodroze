package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Simulation {

	public static void main(String[] args) {		
		// liczba planet, asteroid, statków obcych, statków podróżników i czarnych dziur
		int pl, ast, al, tr, bh;
		
		pl=5;
		bh=5;
		ast=5;
		tr=5;
		al=5;
		
		// utworzenie planszy
		int spaceSize=10;
		Space space = new Space();
		Square[][] map = space.createSpace(spaceSize);
		
		// utworzenie list obiektow kazdej klasy i umieszczenie ich na planszy
		Planet planet = new Planet();
		List<Planet> planetList = planet.createPlanetList(pl);
		planet.planetsInSpace(spaceSize, pl, planetList, map);
		
		BlackHole blackHole = new BlackHole();
		List<BlackHole> blackHoleList = blackHole.createBHList(bh);
		blackHole.holesInSpace(spaceSize, bh, blackHoleList, map);
		
		Asteroid asteroid = new Asteroid();
		List<Asteroid> asteroidList = asteroid.createAsteroidList(ast);
		asteroid.asteroidsInSpace(spaceSize, ast, asteroidList, map);
		
		TravelersSpaceship traveler = new TravelersSpaceship();
		List<TravelersSpaceship> travelerList = traveler.createTravelersList(tr);
		traveler.travelersInSpace(spaceSize, tr, travelerList, map);
		
		AliensSpaceship alien = new AliensSpaceship();
		List<AliensSpaceship> alienList = alien.createAliensList(al);
		alien.aliensInSpace(spaceSize, al, alienList, map);
		
		space.printSpace(map, spaceSize);
		
		runSimulation(map, planetList, blackHoleList, asteroidList, travelerList, alienList, 1, spaceSize);
		
		space.printSpace(map, spaceSize);
	}
	
	public static void runSimulation(Square[][] map, List<Planet> planetList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, int iter, int spaceSize) {
		int iteration=0;
		int shipsInSpace=travelerList.size()+alienList.size();
		
		while (iteration<iter && shipsInSpace>0) {
			for (int i=0; i<travelerList.size(); i++) {
				travelerList.get(i).move(travelerList.get(i), i, map, spaceSize);
				System.out.println("traveler " + i);
			}
			for (int i=0; i<alienList.size(); i++) {
				alienList.get(i).move(alienList.get(i), i, map, spaceSize);
				System.out.println("alien " + i);
			}
			
			iteration++;
		}
	}
}
