package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Item;
//import javafx.geometry.Side;
import game_data.sprites.Player;
import game_engine.Bottom;
import game_engine.Left;
import game_engine.Right;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;
import game_engine.actions.MovableTriggered;

@NameAnnotation(name="Moveable")
public class Movable implements Characteristic{

	  private Sprite mySprite;
		
		
		@ParameterAnnotation(parameters= {"Sprite"})
		public Movable(Sprite aSprite){
			mySprite = aSprite;
		}
		
		
		@Override
		public void execute(Map<Sprite, Side> myCollisionMap) {
			
			boolean impassTop = false;
			boolean impassBottom = false;
			boolean impassLeft = false;
			boolean impassRight = false;
			
			Sprite playerSprite = null;
			Side playerCollisionSide = null;
			
			for(Sprite collidedSprite:myCollisionMap.keySet()){
				if((collidedSprite instanceof Item)){
					for(Characteristic i : collidedSprite.getCharacteristics()){
						if( i instanceof Movable){
							
							Side temp = myCollisionMap.get(collidedSprite);
							System.out.println(temp.toString());
							
							if(temp.isHorizontal()){
								
								if(temp instanceof Left){
									impassLeft = true;
								} 
								else{
									impassRight = true;
								}
							}
							else{
								
								if(temp instanceof Top){
									impassTop = true;
								}
								else{
									
									impassBottom = true;
									
								}
							}
							
						}
					}
				}
				if(collidedSprite instanceof Player){
					playerSprite = collidedSprite;
					playerCollisionSide = myCollisionMap.get(playerSprite);}
				}


			
			if(playerSprite != null){
				if(playerCollisionSide instanceof Left && !impassRight){
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if(playerCollisionSide instanceof Right && !impassLeft){
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if (playerCollisionSide instanceof Top && !impassBottom){
					
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if (playerCollisionSide instanceof Bottom && !impassTop){
					
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				} 
			}
					
				

				
		
		}
		
			
	@Override
	public Characteristic copy(Sprite aSprite) {
		return new Movable(aSprite);
	}

	}
