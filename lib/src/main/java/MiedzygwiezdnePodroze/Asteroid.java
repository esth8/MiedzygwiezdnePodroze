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

	public void move(Asteroid asteroid, int i, Square[][] map, int spaceSize) {
		switch (asteroid.direction) {
		case "NW": {
			if (asteroid.x!=0 && asteroid.y!=0) {
				if (map[asteroid.x-1][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x-1][asteroid.y-1].type='A';
					map[asteroid.x-1][asteroid.y-1].index=i;
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
				if (map[asteroid.x-1][asteroid.y-1].type=='\0') {
					map[asteroid.x][asteroid.y].type='\0';
					map[asteroid.x][asteroid.y].index=-1;
					map[asteroid.x+1][asteroid.y-1].type='A';
					map[asteroid.x+1][asteroid.y-1].index=i;
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

	@Override
	public void collisionPlanet(Asteroid asteroid, Planet planet, Square[][] map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collisionTraveler(Asteroid asteroid, TravelersSpaceship traveler, Square[][] map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collisionAlien(Asteroid asteroid, AliensSpaceship alien, Square[][] map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beDestroyed(Asteroid asteroid, BlackHole blackHole, Square[][] map) {
		// TODO Auto-generated method stub
		
	}
}
