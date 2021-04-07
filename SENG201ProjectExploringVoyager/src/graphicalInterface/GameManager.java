package graphicalInterface;

import java.util.ArrayList;

import allitems.Item;
import exceptions.TooManyCharactersException;
import main.GameEnvironment;
import matter.CrewMember;
import matter.Outpost;
import matter.SpaceShip;

public class GameManager {
	private int totalDays;
	private String shipName;
	private SpaceShip playerShip;
	private ArrayList<CrewMember> crewList = new ArrayList<CrewMember>();
	private ArrayList<CrewMember> selectableCrewList = new ArrayList<CrewMember>();
	private ArrayList<CrewMember> unchangableSelectableCrewList = new ArrayList<CrewMember>();
	private int currentCrewIndex = 0;
	private int crewCount = 0;
	private GameEnvironment game;
	private int selectCrewIndex;
	private int score;
	private Outpost currentOutpost;
	private String deathMessage;
	private boolean completedGame;
	
	private int usedCrewIndex;
	private int onCrewIndex;
	private Item selectedItem;
	
	public int getUsedCrewIndex() { 
		return usedCrewIndex;
	}
	public int getOnCrewIndex() {
		return onCrewIndex;
	}
	public Item getSelectedItem() {
		return selectedItem;
	}
	public boolean getCompletedGame() {
		return completedGame;
	}
	public void setCompletedGame(boolean tempGame) {
		completedGame = tempGame;
	}
	
	public void setUsedCrewIndex(int setIndex) {
		usedCrewIndex = setIndex;
	}
	public void setOnCrewIndex(int setIndex) {
		onCrewIndex = setIndex;
	}
	public void setSelectedItem(Item setItem) {
		selectedItem = setItem;
	}
	public String getDeathMessage() {
		return deathMessage;
	}
	public void setDeathMessage(String tempMessage) {
		deathMessage = tempMessage;
	}
	public void setCurrentOutpost(Outpost tempOutpost) {
		currentOutpost = tempOutpost;
	}
	public Outpost getCurrentOutpost() {
		return currentOutpost; 
	}
	
	public void confirmAddCrewMember(String crewName) throws TooManyCharactersException{
		CrewMember confirmedCrew = selectableCrewList.get(selectCrewIndex);
		if(crewName.equals("") == false) {
			if (crewName.length() < 15) {
				confirmedCrew.setName(crewName);
			} else {
				throw new TooManyCharactersException("Please enter a name that is less than 15 characters.");
			}
		}
		crewList.add(confirmedCrew);
		addToCrewCount();
	}
	
	public boolean allocateStatPoints(String changingStat, boolean incOrDec) {
		return selectableCrewList.get(selectCrewIndex).changeStat(changingStat, incOrDec, unchangableSelectableCrewList.get(selectCrewIndex));
	}
	
	public Item[] gettingAllItems() {
		return game.getPlayerShip().getAllItems();
	}
	
	public void launchStartScreen() {
		StartScreen mainWindow = new StartScreen(this);
	}
	public void closeStartScreen(StartScreen mainWindow) {
		mainWindow.closeWindow();
		getGame().generateNumberStarPieces(totalDays);
		launchSelectShipScreen();
	}
	
	public void launchSelectShipScreen() {
		SelectShipScreen mainWindow = new SelectShipScreen(this);
	}
	public void closeSelectShipScreen(SelectShipScreen mainWindow) {
		mainWindow.closeWindow();
		launchSelectCrewScreen();
	}
	public void launchSelectCrewScreen() {
		SelectCrewScreen mainWindow = new SelectCrewScreen(this);
	}
	
	public void closeSelectCrewScreen(SelectCrewScreen mainWindow) {
		mainWindow.closeWindow();
		game.generateGame(totalDays, playerShip, shipName, crewList);
		launchMainShipScreen();
	}
	public void closeMainShipScreenToEndScreen(MainShipScreen mainWindow) {
		launchEndScreen();
	}
	public void launchEndScreen() {
		EndScreen mainWindow = new EndScreen(this);
	}
	
	public void decreaseUnallocatedStatPoints(CrewMember crew) {
		crew.removeUnallocatedStatPoints();
	}
	public void increaseUnallocatedStatPoints(CrewMember crew) {
		crew.addUnallocatedStatPoints();
	}
	
	public void setTotalDays(int tempDays) {
		totalDays = tempDays; 
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setShipName(String tempShipName) throws TooManyCharactersException {
		if (tempShipName.length() < 15) {
			shipName = tempShipName;
		} else {
			throw new TooManyCharactersException("Please enter a name that is less than 15 characters.");
		}
	}
	public String getShipName() {
		return shipName;
	}

	public void setPlayerShip(SpaceShip tempShip) {
		playerShip = tempShip;
	}
	public SpaceShip getPlayerShip() {
		return playerShip;
	}
	
	public void setCurrentCrewIndex(int tempNumber) {
		currentCrewIndex = tempNumber;
	}
	public int getCurrentCrewIndex() {
		return currentCrewIndex;
	}
	public ArrayList<CrewMember> getSelectableCrewList() {
		return selectableCrewList;
	}
	public void setSelectableCrewList() {
		selectableCrewList = getGame().generateCrewMembers();
	}
	public ArrayList<CrewMember> getUnchangableSelectableCrewList() {
		return unchangableSelectableCrewList;
	}
	public void setUnchangableSelectableCrewList() {
		unchangableSelectableCrewList = getGame().generateCrewMembers();
	}
	public ArrayList<CrewMember> getCrewList() {
		return crewList;
	}
	public void setCrewList(ArrayList<CrewMember> tempCrewList) { //probably redundant
		crewList = tempCrewList;
	}
	public SpaceShip[] getShipHangar() {
		return getGame().selectSpaceShip();
	}
	public void addToCrewCount() {
		crewCount++;
	}
	public int getCrewCount() {
		return crewCount;
	}
	public int getSelectCrewIndex() {
		return selectCrewIndex;
	}
	public void setSelectCrewIndex(int setNum) {
		selectCrewIndex = setNum;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int tempScore) {
		score = tempScore;
	}
	public void launchMainShipScreen() {
		MainShipScreen mainWindow = new MainShipScreen(this);
	}
	public void closeMainShipScreen(MainShipScreen mainWindow) {
		mainWindow.closeWindow();
	}
	public void launchTraverseScreen() {
		TraverseScreen mainWindow = new TraverseScreen(this);
	}
	public void closeTraverseScreen(TraverseScreen mainWindow) {
		mainWindow.closeWindow();
		launchMainShipScreen();
	}
	public void launchItemMenuScreen() {
		ItemMenuScreen mainWindow = new ItemMenuScreen(this);
	}
	public void closeItemMenuScreen(ItemMenuScreen mainWindow) {
		mainWindow.closeWindow();
	}
	public void launchSelectItemScreen() {
		SelectItemScreen mainWindow = new SelectItemScreen(this);
	}
	public void closeSelectItemScreen(SelectItemScreen mainWindow) {
		mainWindow.closeWindow();
		launchMainShipScreen();
	}
	public void launchExploreScreen() {
		ExploreScreen mainWindow = new ExploreScreen(this);
	}
	public void closeExploreScreen(ExploreScreen mainWindow) {
		mainWindow.closeWindow();
		launchMainShipScreen();
	}
	public void closeOutpostScreenToViewItems(OutpostScreen mainWindow) {
		mainWindow.closeWindow();
		launchItemMenuScreen();
	}
	public void launchGameOverScreen() {
		GameOverScreen mainWindow = new GameOverScreen(this);
	}
	
	public void closeMainShipScreenToGameOver(MainShipScreen mainWindow) {
		mainWindow.closeWindow();
		launchGameOverScreen();
	}
	public void closeExploreScreenToGameOver(ExploreScreen mainWindow) {
		mainWindow.closeWindow();
		launchGameOverScreen();
	}
	public void closeOutpostScreenToGameOver(OutpostScreen mainWindow) {
		mainWindow.closeWindow();
		launchGameOverScreen();
	}
	
	public void closeTraverseScreenToGameOver(TraverseScreen mainWindow) {
		mainWindow.closeWindow();
		launchGameOverScreen();
	}
	public GameEnvironment getGame() {
		return game;
	}
	public void setGame(GameEnvironment tempGame) {
		game = tempGame;
	}
	public void closeExploreScreenToOutpost(ExploreScreen mainWindow) {
		mainWindow.closeWindow();
		launchOutpostScreen();
		
	}
	public void launchOutpostScreen() {
		OutpostScreen mainWindow = new OutpostScreen(this);
	}
	public void closeOutpostScreen(OutpostScreen mainWindow) {
		mainWindow.closeWindow();
		launchExploreScreen();
	}

	public static void main(String[] args) {
		GameManager manager =  new GameManager();
		manager.setGame(new GameEnvironment());
		manager.launchStartScreen();
	}
	
		
}