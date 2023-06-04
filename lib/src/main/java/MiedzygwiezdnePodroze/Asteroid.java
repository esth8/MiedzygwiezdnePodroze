package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Asteroid implements IAsteroid {
	private int x;
	private int y;
	private String direction;
	private boolean inSpace;
	
	public Asteroid (){
		direction = "\0";
		inSpace = true;
	}
	
	public List<Asteroid> createAsteroidList (int n){ 	// n = liczba asteroid
		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
		for (int i=0; i<n; i++) {
			asteroidList.add(new Asteroid());
		}
		return asteroidList;
	}
	
	public void asteroidsInSpace (int n, int ast, List<Asteroid> asteroidList, Square[][] map) { 	// n = rozmiar mapy, ast = liczba asteroid
		for (int i=0; i<ast; i++) {
			// losowanie współrzędnych dla asteroidy dopóki nie zostanie wylosowane niezajęte pole
			int ox, oy;
			do {
				ox = RandomNumber.generateRandom(n);
				oy = RandomNumber.generateRandom(n);
			} while (map[ox][oy].type!='\0');
			
			asteroidList.get(i).x=ox;
			asteroidList.get(i).y=oy;
			asteroidList.get(i).direction=randomDirection();
			map[ox][oy].type='A';
			map[ox][oy].index=i;
		}
	}
	
	public String randomDirection () {
		int dir=RandomNumber.generateRandom(8);
		switch (dir) {
		case 0:
			return "NW";
		case 1: 
			return "N";
		case 2:
			return "NE";
		case 3:
			return "W";
		case 4:
			return "E";
		case 5:
			return "SW";
		case 6:
			return "S";
		case 7:
			return "SE";
		}
		return "\0";
	}
	
	@Override
	public void move(Asteroid asteroid, int i, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, Square[][] map, int spaceSize) {
		switch (asteroid.direction) {
		case "NW": {
			if (asteroid.x!=0 && asteroid.y!=0) {
				if (map[asteroid.x-1][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x-1][asteroid.y-1].type='A';
					map[asteroid.x-1][asteroid.y-1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "N": {
			if (asteroid.y!=0) {
				if (map[asteroid.x][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x][asteroid.y-1].type='A';
					map[asteroid.x][asteroid.y-1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "NE": {
			if (asteroid.x<spaceSize-1 && asteroid.y!=0) {
				if (map[asteroid.x+1][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x+1][asteroid.y-1].type='A';
					map[asteroid.x+1][asteroid.y-1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "W": {
			if (asteroid.x!=0) {
				if (map[asteroid.x-1][asteroid.y].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x-1][asteroid.y].type='A';
					map[asteroid.x-1][asteroid.y].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "E": {
			if (asteroid.x<spaceSize-1) {
				if (map[asteroid.x+1][asteroid.y].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x+1][asteroid.y].type='A';
					map[asteroid.x+1][asteroid.y].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "SW": {
			if (asteroid.x!=0 && asteroid.y<spaceSize-1) {
				if (map[asteroid.x-1][asteroid.y+1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x-1][asteroid.y+1].type='A';
					map[asteroid.x-1][asteroid.y+1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "S": {
			if (asteroid.y<spaceSize-1) {
				if (map[asteroid.x][asteroid.y+1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x][asteroid.y+1].type='A';
					map[asteroid.x][asteroid.y+1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		case "SE": {
			if (asteroid.x<spaceSize-1 && asteroid.y<spaceSize-1) {
				if (map[asteroid.x+1][asteroid.y+1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x+1][asteroid.y+1].type='A';
					map[asteroid.x+1][asteroid.y+1].index=i;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, map);
				}
			} else {
				map[asteroid.x][asteroid.y].type='\0';
				map[asteroid.x][asteroid.y].index=-1;
				asteroid.inSpace=false;
			}
			break;
		}
		}
	}
	
	public void interaction(Asteroid asteroid, Square square, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, Square[][] map) {
		switch (square.type) {
		case ('P'):
			asteroid.collisionPlanet(asteroid, planetList.get(square.index), map);
			break;
		case ('T'):
			asteroid.collisionTraveler(asteroid, travelerList.get(square.index), map);
			break;
		case ('L'):
			asteroid.collisionAlien(asteroid, alienList.get(square.index), map);
			break;
		case ('H'):
			asteroid.beDestroyed(asteroid, blackHoleList.get(square.index), map);
			break;
		//case ('A'):
		}
	}

	@Override
	public void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map) {
		planet.collidedAstrd++;
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
	}

	@Override
	public void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map) {
		traveler.inSpace=false;
		traveler.durability=0;
		asteroid.inSpace=false;
		map[traveler.x][traveler.y].type='\0';
		map[traveler.x][traveler.y].index=-1;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
	}

	@Override
	public void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map) {
		alien.durability-=2;
		if (alien.durability<=0) {
			alien.inSpace=false;
			map[alien.x][alien.y].type='\0';
			map[alien.x][alien.y].index=-1;
		} else {
			alien.collidedAstrd++;
		}
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
	}

	@Override
	public void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map) {
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
		blackHole.destroyedAstrd++;
	}
}
