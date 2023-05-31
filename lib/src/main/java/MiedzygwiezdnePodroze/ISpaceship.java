package MiedzygwiezdnePodroze;

import MiedzygwiezdnePodroze.Space.Square;

public interface ISpaceship {
	void move(Spaceship spaceship, int i, Square[][] map, int spaceSize);
	void land(Spaceship spaceship, Planet planet, Square[][] map);
	void fight(TravelersSpaceship travelers, AliensSpaceship alien, Square[][] map);
	void beDestroyed(Spaceship spaceship, BlackHole blackHole, Square[][] map);
}