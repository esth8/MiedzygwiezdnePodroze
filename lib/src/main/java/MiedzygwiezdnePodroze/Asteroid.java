package MiedzygwiezdnePodroze;

import java.util.ArrayList;
import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Asteroid implements IAsteroid {
	public int x;
	public int y;
	public String direction;
	public boolean inSpace;
	public int destroyedTravShips;
	public int destroyedAlienShips;
	public int destroyedAstrd;
	
	public Asteroid (){
		direction = "\0";
		inSpace = true;
		destroyedTravShips=0;
		destroyedAlienShips=0;
		destroyedAstrd=0;
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
	public void move(Asteroid asteroid, int i, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, Square[][] map, int spaceSize) {
		switch (asteroid.direction) {
		case "NW": {
			if (asteroid.x!=0 && asteroid.y!=0) {
				if (map[asteroid.x-1][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x-1][asteroid.y-1].type='A';
					map[asteroid.x-1][asteroid.y-1].index=i;
					asteroid.x-=1;
					asteroid.y-=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.y-=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.x+=1;
					asteroid.y-=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y-1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.x-=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.x+=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.x-=1;
					asteroid.y+=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x-1][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.y+=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
					asteroid.x+=1;
					asteroid.y+=1;
				} else {
					asteroid.interaction(asteroid, map[asteroid.x+1][asteroid.y+1], planetList, travelerList, alienList, blackHoleList, asteroidList, map);
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
	
	public void interaction(Asteroid asteroid, Square square, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, Square[][] map) {
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
		case ('A'):
			asteroid.collisionAsteroid(asteroid, asteroidList.get(square.index), map);
		}
	}

	@Override
	public void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map) {
		planet.collidedAstrd++;
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
		//System.out.println("collision with planet");
	}

	@Override
	public void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map) {
		traveler.inSpace=false;
		traveler.durability=0;
		asteroid.inSpace=false;
		asteroid.destroyedTravShips++;
		traveler.destroyedAstrd++;
		map[traveler.x][traveler.y].type='\0';
		map[traveler.x][traveler.y].index=-1;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
		//System.out.println("collision with traveler");
	}

	@Override
	public void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map) {
		alien.durability-=2;
		alien.destroyedAstrd++;
		if (alien.durability<=0) {
			alien.inSpace=false;
			map[alien.x][alien.y].type='\0';
			map[alien.x][alien.y].index=-1;
			asteroid.destroyedAlienShips++;
		} else {
			alien.collidedAstrd++;
		}
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
		//System.out.println("collision with alien");
	}
	
	public void collisionAsteroid(Asteroid asteroid1, Asteroid asteroid2, Square[][] map) {
		asteroid1.inSpace=false;
		map[asteroid1.x][asteroid1.y].type='\0';
		map[asteroid1.x][asteroid1.y].index=-1;
		asteroid2.inSpace=false;
		map[asteroid2.x][asteroid2.y].type='\0';
		map[asteroid2.x][asteroid2.y].index=-1;
		asteroid1.destroyedAstrd++;
		asteroid2.destroyedAstrd++;
		//System.out.println("collision with asteroid");
	}

	@Override
	public void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map) {
		asteroid.inSpace=false;
		map[asteroid.x][asteroid.y].type='\0';
		map[asteroid.x][asteroid.y].index=-1;
		blackHole.destroyedAstrd++;
		//System.out.println("collision with black hole");
	}
}
