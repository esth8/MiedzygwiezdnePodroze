package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public interface ISpaceship {
	void move(Spaceship spaceship, int i, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<Asteroid> asteroidList, List<BlackHole> blackHoleList, Square[][] map, int spaceSize);
	void interaction(Spaceship spaceship, int i, Square square, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<Asteroid> asteroidList, List<BlackHole> blackHoleList, Square[][] map);
	void land(Spaceship spaceship, Planet planet, Square[][] map);
	void fight(TravelersSpaceship traveler, AliensSpaceship alien, char atk1st, Square[][] map);
	void travelerWon(TravelersSpaceship traveler, AliensSpaceship alien, Square[][] map);
	void alienWon(TravelersSpaceship traveler, AliensSpaceship alien, Square[][] map);
	void beDestroyed(Spaceship spaceship, BlackHole blackHole, Square[][] map);
}