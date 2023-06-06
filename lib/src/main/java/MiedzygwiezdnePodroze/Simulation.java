package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Simulation {

	public static void main(String[] args) {		
		// liczba planet, asteroid, statków obcych, statków podróżników i czarnych dziur
		int pl, ast, al, tr, bh;
		
		pl=3;
		bh=3;
		ast=3;
		tr=3;
		al=3;
		
		// utworzenie planszy
		int spaceSize=5;
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
	}
	
	public static void runSimulation(Square[][] map, List<Planet> planetList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, int iter, int spaceSize) {
		int iteration=0;
		int shipsInSpace=travelerList.size()+alienList.size();
		
		while (iteration<iter && shipsInSpace>0) {
			shipsInSpace=0;
			for (int i=0; i<travelerList.size(); i++) {
				if (travelerList.get(i).inSpace==true) {
					System.out.println("traveler " + i + " dur: " + travelerList.get(i).durability + " x: " + travelerList.get(i).x + " y: " + travelerList.get(i).y);
					travelerList.get(i).move(travelerList.get(i), i, planetList, travelerList, alienList, asteroidList, blackHoleList, map, spaceSize);
					System.out.println("traveler " + i + " dur: " + travelerList.get(i).durability);
				}
			}
			for (int i=0; i<alienList.size(); i++) {
				if (alienList.get(i).inSpace==true) {
					System.out.println("alien " + i + " dur: " + alienList.get(i).durability + " x: " + alienList.get(i).x + " y: " + alienList.get(i).y);
					alienList.get(i).move(alienList.get(i), i, planetList, travelerList, alienList, asteroidList, blackHoleList, map, spaceSize);
					System.out.println("alien " + i + " dur: " + alienList.get(i).durability);
				}
			}
			for (int i=0; i<asteroidList.size(); i++) {
				if (asteroidList.get(i).inSpace==true) {
					System.out.println("asteroid " + i + " x: " + asteroidList.get(i).x + " y: " + asteroidList.get(i).y);
					asteroidList.get(i).move(asteroidList.get(i), i, planetList, travelerList, alienList, blackHoleList, asteroidList, map, spaceSize);
					System.out.println("asteroid " + i + " dir: " + asteroidList.get(i).direction);
				}
			}
			for (int i=0; i<travelerList.size(); i++) {
				if (travelerList.get(i).inSpace==true) {
					shipsInSpace++;
				}
			}
			for (int i=0; i<alienList.size(); i++) {
				if (alienList.get(i).inSpace==true) {
					shipsInSpace++;
				}
			}
			
			iteration++;
			
			Space space = new Space();
			space.printSpace(map, spaceSize);
			System.out.println("ships in space: " + shipsInSpace + "\n");
		}
	}
}
