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
		
		int size=10;
		Space space = new Space();
		Square[][] map = space.createSpace(size);
		
		Planet planet = new Planet();
		List<Planet> planetList = planet.createPlanetList(pl);
		planet.planetsInSpace(size, pl, planetList, map);
		
		BlackHole blackHole = new BlackHole();
		List<BlackHole> blackHoleList = blackHole.createBHList(bh);
		blackHole.holesInSpace(size, bh, blackHoleList, map);
		
		Asteroid asteroid = new Asteroid();
		List<Asteroid> asteroidList = asteroid.createAsteroidList(ast);
		asteroid.asteroidsInSpace(size, ast, asteroidList, map);
		
		TravelersSpaceship traveler = new TravelersSpaceship();
		List<TravelersSpaceship> travelerList = traveler.createTravelersList(tr);
		traveler.travelersInSpace(size, tr, travelerList, map);
		
		AliensSpaceship alien = new AliensSpaceship();
		List<AliensSpaceship> alienList = alien.createAliensList(al);
		alien.aliensInSpace(size, al, alienList, map);
		
		space.printSpace(map, size);
	}
}
