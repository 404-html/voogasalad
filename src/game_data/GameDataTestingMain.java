package game_data;

import java.io.File;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.model.presets.TestSprite;
import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import util.XMLTranslator;
import game_data.sprites.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class GameDataTestingMain {

	public static void main(String[] args){
		//Sprite aSprite = new Player(new Location(0,0,0), 100, 100, "Addison", "path/spriteImage.jpg");
		//Level aLevel = new Level("Level1", 500, 8000, "path/backgroundImage.jpg");
		//aLevel.addNewSprite(aSprite);
		
		//aGame.addNewLevel(aLevel);
		//File aFile = new File("XMLGameFiles/TestXMLFilea.xml");
		//XMLTranslator myXMLSaver = new XMLTranslator();
		//aGame.saveGameAs("XMLGameFiles/","TestXMLFilea");
		//Game bGame = (Game) myXMLSaver.deserialize(aFile);
		//Sprite bSprite = aSprite.clone();
		//bSprite.setMyLocation(new Location(100,100,90));
		//bGame.myLevels.get(0).addNewSprite(bSprite);
		//System.out.println(myXMLSaver.serialize(bGame));
		//myXMLSaver.saveToFile(bGame, "XMLGameFiles/", "TestXMLFileb");
		GameDataTestingMain gameDataTestingMain = new GameDataTestingMain();
		TestGame testGame = gameDataTestingMain.new TestGame();
		Game aGame = testGame.getGame();
		aGame.setName("NewTesterBlah");
		
		XMLTranslator gameSaver = new XMLTranslator();
		gameSaver.saveToFile(aGame, "XMLGameFiles/", "TestXMLFilea");
		System.out.println(gameSaver.serialize(aGame));
		Game loadedGame =(Game) gameSaver.loadFromFile("XMLGameFiles/", "TestXMLFilea");
		
		
	}
	
	protected class TestGame{
		
		private Game aGame;
		private Pane myContainer;
		/**
		 * 
		 */
		public TestGame() {
			
			System.out.println("Testing");
			this.aGame = new Game("Mario");
			Level createdLevel = new Level("TestLevel", 500, 500, "author/images/duke.gif");
			this.aGame.addNewLevel(createdLevel);
			createdLevel.addNewSprite(TestSprite.MARIO.getSprite());
			
			this.myContainer = new Pane();
			initListener(aGame);
		}
		
		protected void initListener(Game aGame) {
			aGame.addListener((game) -> {
				Level currentLevel = aGame.getCurrentLevel();
				if (currentLevel != null)
					updateLevel(currentLevel);
			});
		}
		
		public Game getGame(){
			return this.aGame;
		}
		
		private void updateLevel(Level aLevel) {
			System.out.println("Updating Level");
			updatePane(aLevel);
			aLevel.addListener((level) -> {
				updatePane(aLevel);
				System.out.println("Updated");
			});
		}

		private void updatePane(Level aLevel) {
			myContainer.getChildren().clear();
			if (aLevel.getBackgroundImageFilePath() != null)
				setBackgroundImage(aLevel.getBackgroundImageFilePath());
			aLevel.getMySpriteList().forEach((sprite) -> {
				/*DraggableSprite draggableSprite = new ConcreteMovableSprite(sprite);
				draggableSprite.getImageView().setLayoutX(sprite.getMyLocation().getXLocation());
				draggableSprite.getImageView().setLayoutY(sprite.getMyLocation().getYLocation());
				myContainer.getChildren().add(draggableSprite.getImageView());*/
			});
		}
		
		private void setBackgroundImage(String filePath) {
			Level currentLevel = this.aGame.getCurrentLevel();
			System.out.println("Dummy background");
			//Image image = new Image(filePath, currentLevel.getWidth(), currentLevel.getHeight(), false, false);
			/*BackgroundImage backIm = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,
					new BackgroundSize(currentLevel.getWidth(), currentLevel.getHeight(), false, false, false, true));

			myContainer.setBackground(new Background(backIm));
			myContainer.setMinSize(image.getWidth(), image.getHeight());*/
		}
		
	}
	
	
}
