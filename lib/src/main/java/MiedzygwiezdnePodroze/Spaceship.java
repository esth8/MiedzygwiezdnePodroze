package MiedzygwiezdnePodroze;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Spaceship implements ISpaceship{
	public int x;
	public int y;
	public boolean inSpace;
	public int durability;
	public int enduredBattles;
	public int destroyedAstrd; // statek zderzył się z asteroidą, nie musiał tego przetrwać
	public char type;
	
	@Override
	public void move(Spaceship spaceship, int i, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<Asteroid> asteroidList, List<BlackHole> blackHoleList, Square[][] map, int spaceSize) {
		for (int yy=-1; yy<=1; yy++) {
			for (int xx=-1; xx<=1; xx++) {
				if (xx==0 && yy==0) {
				} else {
					if (spaceship.x+xx!=-1 && spaceship.y+yy!=-1 && spaceship.x+xx!=spaceSize && spaceship.y+yy!=spaceSize) {
						if (map[spaceship.x+xx][y+yy].type=='P') {
							spaceship.land(spaceship, planetList.get(map[spaceship.x+xx][y+yy].index), map);
							return;
						}
					}
				}
			}
		}
		
		int direction=RandomNumber.generateRandom(4); 	// 0 - ruch w gore planszy, 1 - w lewo, 2 - w prawo, 3 - w dol
		//System.out.println("direction: " + direction);
		switch (direction) {
		case (0): {
			if (spaceship.y!=0) {
				if (map[spaceship.x][spaceship.y-1].type=='\0') { 	// 
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x][spaceship.y-1].type=spaceship.type;
					map[spaceship.x][spaceship.y-1].index=i;
					spaceship.y-=1;
				} else {
					spaceship.interaction(spaceship, i, map[spaceship.x][spaceship.y-1], planetList, travelerList, alienList, asteroidList, blackHoleList, map);
				}
			} else {
				map[spaceship.x][spaceship.y].type='\0';
				map[spaceship.x][spaceship.y].index=-1;
				spaceship.inSpace=false;
				//System.out.println("out of space");
			}
			break;
		}
		case (1): {
			if (spaceship.x!=0) {
				if (map[spaceship.x-1][spaceship.y].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x-1][spaceship.y].type=spaceship.type;
					map[spaceship.x-1][spaceship.y].index=i;
					spaceship.x-=1;
				} else {
					spaceship.interaction(spaceship, i, map[spaceship.x-1][spaceship.y], planetList, travelerList, alienList, asteroidList, blackHoleList, map);
				}
			} else {
				map[spaceship.x][spaceship.y].type='\0';
				map[spaceship.x][spaceship.y].index=-1;
				spaceship.inSpace=false;
				//System.out.println("out of space");
			}
			break;
		}
		case (2): {
			if (spaceship.x!=spaceSize-1) {
				if (map[spaceship.x+1][spaceship.y].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x+1][spaceship.y].type=spaceship.type;
					map[spaceship.x+1][spaceship.y].index=i;
					spaceship.x+=1;
				} else {
					spaceship.interaction(spaceship, i, map[spaceship.x+1][spaceship.y], planetList, travelerList, alienList, asteroidList, blackHoleList, map);
				}
			} else {
				map[spaceship.x][spaceship.y].type='\0';
				map[spaceship.x][spaceship.y].index=-1;
				spaceship.inSpace=false;
				//System.out.println("out of space");
			}
			break;
		}	
		case (3): {
			if (spaceship.y!=spaceSize-1) {
				if (map[spaceship.x][spaceship.y+1].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x][spaceship.y+1].type=spaceship.type;
					map[spaceship.x][spaceship.y+1].index=i;
					spaceship.y+=1;
				} else {
					spaceship.interaction(spaceship, i, map[spaceship.x][spaceship.y+1], planetList, travelerList, alienList, asteroidList, blackHoleList, map);
				}
			} else {
				map[spaceship.x][spaceship.y].type='\0';
				map[spaceship.x][spaceship.y].index=-1;
				spaceship.inSpace=false;
				//System.out.println("out of space");
			}
			break;
		}
		}
	}
	
	@Override
	public void interaction(Spaceship spaceship, int i, Square square, List<Planet> planetList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, List<Asteroid> asteroidList, List<BlackHole> blackHoleList, Square[][] map) {
		switch (square.type) {
		/*case ('P'):
			spaceship.land(spaceship, planetList.get(square.index), map);
			break;*/
		case ('T'):
			if (spaceship.type=='L') {
				spaceship.fight(travelerList.get(square.index), alienList.get(i), 'L', map);
			} else {
				
			}
			break;
		case ('L'):
			if (spaceship.type=='T') {
				spaceship.fight(travelerList.get(i), alienList.get(square.index), 'T', map);
			} else {
				
			}
			break;
		case ('H'):
			spaceship.beDestroyed(spaceship, blackHoleList.get(square.index), map);
			break;
		case ('A'):
			if (spaceship.type=='T') {
				asteroidList.get(square.index).collisionTraveler(asteroidList.get(square.index), travelerList.get(i), map);
			} else {
				asteroidList.get(square.index).collisionAlien(asteroidList.get(square.index), alienList.get(i), map);
			}
			break;
		}
	}
	
	@Override
	public void land(Spaceship spaceship, Planet planet, Square[][] map) {
		spaceship.inSpace=false;
		map[spaceship.x][spaceship.y].type='\0';
		map[spaceship.x][spaceship.y].index=-1;
		if (spaceship.type=='T') {
			planet.landedTravShips++;
		} else {
			planet.landedAlienShips++;
		}
		//System.out.println("landed");
	}
	
	@Override
	public void fight(TravelersSpaceship traveler, AliensSpaceship alien, char atk1st, Square[][] map) {
		 if (traveler.durability==alien.durability) {
			 if (atk1st=='T') {
				 traveler.durability=1;
				 travelerWon(traveler,alien,map);
			 } else {
				 alien.durability=1;
				 alienWon(traveler,alien,map);
			 }
		 } else {
			 if (traveler.durability<alien.durability) {
				 if (atk1st=='T') {
					 alien.durability-=traveler.durability;
				 } else {
					 alien.durability-=traveler.durability-1;
				 }
				 alienWon(traveler,alien,map);
			 } else {
				 if (atk1st=='A') {
					 traveler.durability-=alien.durability;
				 } else {
					 traveler.durability-=alien.durability-1;
				 }
				 travelerWon(traveler,alien,map);
			 }
		 }
		 //System.out.println("fight");
	}
	
	@Override
	public void travelerWon(TravelersSpaceship traveler, AliensSpaceship alien, Square[][] map) {
		alien.durability=0;
		alien.inSpace=false;
		map[alien.x][alien.y].type='\0';
		map[alien.x][alien.y].index=-1;
		traveler.enduredBattles++;
	}
	
	@Override
	public void alienWon(TravelersSpaceship traveler, AliensSpaceship alien, Square[][] map) {
		traveler.durability=0;
		traveler.inSpace=false;
		map[traveler.x][traveler.y].type='\0';
		map[traveler.x][traveler.y].index=-1;
		alien.enduredBattles++;
	}
	
	@Override
	public void beDestroyed(Spaceship spaceship, BlackHole blackHole, Square[][] map) {
		spaceship.inSpace=false;
		map[spaceship.x][spaceship.y].type='\0';
		map[spaceship.x][spaceship.y].index=-1;
		spaceship.durability=0;
		if (spaceship.type=='T') {
			blackHole.destroyedTravShips++;
		} else {
			blackHole.destroyedAlienShips++;
		}
		//System.out.println("destroyed by black hole");
	}
	
	
}
