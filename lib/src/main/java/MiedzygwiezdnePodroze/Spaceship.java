package MiedzygwiezdnePodroze;

import MiedzygwiezdnePodroze.Space.Square;

public class Spaceship implements ISpaceship{
	public int x;
	public int y;
	public boolean inSpace;
	public int durability;
	public int enduredBattles;
	public char type;
	
	@Override
	public void move(Spaceship spaceship, int i, Square[][] map, int spaceSize) {
		int direction=RandomNumber.generateRandom(4); 	// 0 - ruch w gore planszy, 1 - w lewo, 2 - w prawo, 3 - w dol
		boolean moved=false;
		
		while (moved==false) {
			if (direction==0) {
				if (spaceship.y!=0 && map[spaceship.x][spaceship.y-1].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x][spaceship.y-1].type=spaceship.type;
					map[spaceship.x][spaceship.y-1].index=i;
					moved=true;
				} else {
					direction++;
				}
			}
			if (direction==1) {
				if (spaceship.x!=0 && map[spaceship.x-1][spaceship.y].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x-1][spaceship.y].type=spaceship.type;
					map[spaceship.x-1][spaceship.y].index=i;
					moved=true;
				} else {
					direction++;
				}
			}
			if (direction==2) {
				if (spaceship.x<spaceSize && map[spaceship.x+1][spaceship.y].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x+1][spaceship.y].type=spaceship.type;
					map[spaceship.x+1][spaceship.y].index=i;
					moved=true;
				} else {
					direction++;
				}
			}
			if (direction==3) {
				if (spaceship.y<spaceSize && map[spaceship.x][spaceship.y+1].type=='\0') {
					map[spaceship.x][spaceship.y].type='\0';
					map[spaceship.x][spaceship.y].index=-1;
					map[spaceship.x][spaceship.y+1].type=spaceship.type;
					map[spaceship.x][spaceship.y+1].index=i;
					moved=true;
				} else {
					//direction=1;
				}
			}
		}
		
	}
	
	@Override
	public void land(Spaceship spaceship, Planet planet, Square[][] map) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fight(TravelersSpaceship travelers, AliensSpaceship alien, Square[][] map) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beDestroyed(Spaceship spaceship, BlackHole blackHole, Square[][] map) {
		// TODO Auto-generated method stub
		
	}
	
	
}
