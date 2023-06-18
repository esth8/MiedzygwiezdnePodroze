package MiedzygwiezdnePodroze;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import MiedzygwiezdnePodroze.Space.Square;

public class Simulation {
	
	private static int landedTravShips;
	private static int landedAlienShips;
	private static int destroyedTravShips;
	private static int destroyedAlienShips;
	
	private static int allLandedShips;
	private static int allDestroyedShips;
	private static int allFights;
	private static int allDestroyedAstrd;

	public static void main(String[] args) throws IOException {		
		Scanner scan = new Scanner(System.in);
		
		// liczba planet, asteroid, statków obcych, statków podróżników i czarnych dziur
		int pl, ast, al, tr, bh;
		
		System.out.print("liczba planet: ");
		pl=scan.nextInt();
		
		System.out.print("liczba czarnych dziur: ");
		bh=scan.nextInt();
		
		System.out.print("liczba asteroid: ");
		ast=scan.nextInt();
		
		System.out.print("liczba statkow podroznikow: ");
		tr=scan.nextInt();
		
		System.out.print("liczba statkow obcych: ");
		al=scan.nextInt();
		
		/*
		pl=3;
		bh=3;
		ast=3;
		tr=3;
		al=3;
		*/
		
		// wymiary planszy
		System.out.print("rozmiar mapy: ");
		int spaceSize=scan.nextInt();
		
		// maksymalna liczba iteracji
		System.out.print("liczba iteracji: ");
		int iter=scan.nextInt();
		
		// liczba wykonan symulacji dla tych samych parametrow
		System.out.print("liczba wykonan symulacji: ");
		int sim=scan.nextInt();
		
		Space space = new Space();
		Planet planet = new Planet();
		BlackHole blackHole = new BlackHole();		
		Asteroid asteroid = new Asteroid();
		TravelersSpaceship traveler = new TravelersSpaceship();
		AliensSpaceship alien = new AliensSpaceship();
		
		// utworzenie pliku, do ktorego beda zapisywane dane zebrane podczas symulacji
		File results = new File ("map"+spaceSize+"pl"+pl+"bh"+bh+"ast"+ast+"tr"+tr+"al"+al+".txt");
		PrintWriter write = new PrintWriter(results);
		
		for (int i=1; i<=sim; i++) {
			landedTravShips=0;
			landedAlienShips=0;
			destroyedTravShips=0;
			destroyedAlienShips=0;
			allLandedShips=0;
			allDestroyedShips=0;
			allFights=0;
			allDestroyedAstrd=0;
			
			//utworzenie mapy
			Square[][] map = space.createSpace(spaceSize);
			
			// utworzenie list obiektow kazdej klasy i umieszczenie ich na planszy
			List<Planet> planetList = planet.createPlanetList(pl);
			planet.planetsInSpace(spaceSize, pl, planetList, map);
			
			List<BlackHole> blackHoleList = blackHole.createBHList(bh);
			blackHole.holesInSpace(spaceSize, bh, blackHoleList, map);
			
			List<Asteroid> asteroidList = asteroid.createAsteroidList(ast);
			asteroid.asteroidsInSpace(spaceSize, ast, asteroidList, map);
			
			List<TravelersSpaceship> travelerList = traveler.createTravelersList(tr);
			traveler.travelersInSpace(spaceSize, tr, travelerList, map);
			
			List<AliensSpaceship> alienList = alien.createAliensList(al);
			alien.aliensInSpace(spaceSize, al, alienList, map);
			
			space.printSpace(map, spaceSize);
			System.out.print("\n");
			
			runSimulation(map, planetList, blackHoleList, asteroidList, travelerList, alienList, iter, spaceSize, results);
			System.out.println("saved in file: " + printInFile(i, results, write) + "\n");
		}
		
		scan.close();
		write.close();
	}
	
	public static void runSimulation(Square[][] map, List<Planet> planetList, List<BlackHole> blackHoleList, List<Asteroid> asteroidList, List<TravelersSpaceship> travelerList, List<AliensSpaceship> alienList, int iter, int spaceSize, File results) throws IOException {
		//Space space = new Space();
		
		int iteration=0;
		int shipsInSpace=travelerList.size()+alienList.size();
		
		while (iteration<iter && shipsInSpace>0) {
			shipsInSpace=0;
			for (int i=0; i<travelerList.size(); i++) {
				if (travelerList.get(i).inSpace==true) {
					//System.out.println("traveler " + i + " dur: " + travelerList.get(i).durability + " x: " + travelerList.get(i).x + " y: " + travelerList.get(i).y);
					travelerList.get(i).move(travelerList.get(i), i, planetList, travelerList, alienList, asteroidList, blackHoleList, map, spaceSize);
					//System.out.println("traveler " + i + " dur: " + travelerList.get(i).durability);
					//space.printSpace(map, spaceSize);
				}
			}
			for (int i=0; i<alienList.size(); i++) {
				if (alienList.get(i).inSpace==true) {
					//System.out.println("alien " + i + " dur: " + alienList.get(i).durability + " x: " + alienList.get(i).x + " y: " + alienList.get(i).y);
					alienList.get(i).move(alienList.get(i), i, planetList, travelerList, alienList, asteroidList, blackHoleList, map, spaceSize);
					//System.out.println("alien " + i + " dur: " + alienList.get(i).durability);
					//space.printSpace(map, spaceSize);
				}
			}
			for (int i=0; i<asteroidList.size(); i++) {
				if (asteroidList.get(i).inSpace==true) {
					//System.out.println("asteroid " + i + " x: " + asteroidList.get(i).x + " y: " + asteroidList.get(i).y);
					asteroidList.get(i).move(asteroidList.get(i), i, planetList, travelerList, alienList, blackHoleList, asteroidList, map, spaceSize);
					//System.out.println("asteroid " + i + " dir: " + asteroidList.get(i).direction);
					//space.printSpace(map, spaceSize);
				}
			}
			for (int i=0; i<travelerList.size(); i++) {
				if (travelerList.get(i).inSpace==true) {
					shipsInSpace++;
				}
			}
			for (int i=0; i<alienList.size(); i++) {
				if (alienList.get(i).inSpace==true) {
					shipsInSpace++;
				}
			}
			
			iteration++;
			
			//space.printSpace(map, spaceSize);
			System.out.println("ships in space: " + shipsInSpace + "\n");
		}
		
		for (int i=0; i<travelerList.size(); i++) {
			allFights+=travelerList.get(i).enduredBattles;
			destroyedAlienShips+=travelerList.get(i).enduredBattles;
			allDestroyedAstrd+=travelerList.get(i).destroyedAstrd;
		}
			
		for (int i=0; i<alienList.size(); i++) {
			allFights+=alienList.get(i).enduredBattles;
			destroyedTravShips+=alienList.get(i).enduredBattles;
			allDestroyedAstrd+=alienList.get(i).destroyedAstrd;
		}
			
		for (int i=0; i<planetList.size(); i++) {
			landedTravShips+=planetList.get(i).landedTravShips;
			landedAlienShips+=planetList.get(i).landedAlienShips;
			allDestroyedAstrd+=planetList.get(i).collidedAstrd;
		}
			
		for (int i=0; i<blackHoleList.size(); i++) {
			destroyedTravShips+=blackHoleList.get(i).destroyedTravShips;
			destroyedAlienShips+=blackHoleList.get(i).destroyedAlienShips;
			allDestroyedAstrd+=blackHoleList.get(i).destroyedAstrd;
		}
			
		for (int i=0; i<asteroidList.size(); i++) {
			destroyedTravShips+=asteroidList.get(i).destroyedTravShips;
			destroyedAlienShips+=asteroidList.get(i).destroyedAlienShips;
			allDestroyedAstrd+=asteroidList.get(i).destroyedAstrd;
		}
			
		allLandedShips=landedTravShips+landedAlienShips;
		allDestroyedShips=destroyedTravShips+destroyedAlienShips;
		
		System.out.println(allLandedShips + "," + allDestroyedShips + "," + allFights + "," + allDestroyedAstrd);
	}
	
	private static int printInFile (int simNumber, File results, PrintWriter write) throws IOException {
		if (!results.exists()) {
			return -1;
		} else {
			write.println(simNumber+","+landedTravShips+","+landedAlienShips+","+allLandedShips+","+destroyedTravShips+","+destroyedAlienShips+","+allDestroyedShips+","+allFights+","+allDestroyedAstrd);				
			return 0;
		}
	}
}
