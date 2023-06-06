package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public interface IAsteroid {
	public String randomDirection ();
	void move(Asteroid asteroid, int i, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, Square[][] map, int spaceSize);
	void interaction(Asteroid asteroid, Square square, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, Square[][] map);
	void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map);
	void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map);
	void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map);
	void collisionAsteroid(Asteroid asteroid1, Asteroid asteroid2, Square[][] map);
	void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map);
}
