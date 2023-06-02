package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public interface IAsteroid {
	public String randomDirection ();
	void move(Asteroid asteroid, int i, Square[][] map, int spaceSize);
	void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map);
	void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map);
	void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map);
	void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map);
}
