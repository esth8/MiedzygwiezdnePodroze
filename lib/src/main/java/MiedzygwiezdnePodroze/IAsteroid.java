package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public interface IAsteroid {
	void move(Asteroid asteroid, Square[][] map);
	void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map);
	void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map);
	void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map);
	void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map);
}
