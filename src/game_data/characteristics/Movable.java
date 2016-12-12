package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
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
				/*if(!(collidedSprite instanceof Player)){
					System.out.println("notplayer");
					for(Characteristic i : collidedSprite.getCharacteristics()){
						if( i instanceof Impassable){
							System.out.println("impassable");
							Side temp = myCollisionMap.get(collidedSprite);
							System.out.println(temp.toString());
							if(temp.isHorizontal()){
								System.out.println("horizontal");
								if(temp instanceof Left){
									impassLeft = true;
								} 
								else{
									impassRight = true;
								}
							}
							else{
								System.out.println("vertical");
								if(temp instanceof Top){
									impassTop = true;
								}
								else{
									
									impassBottom = true;
									
								}
							}
							
						}
					}
				}*/
				if(collidedSprite instanceof Player){
					playerSprite = collidedSprite;
					playerCollisionSide = myCollisionMap.get(playerSprite);}
				}

			//System.out.println(impassTop);
			//System.out.println(impassBottom);
			if(playerSprite != null){
				if(playerCollisionSide instanceof Left && !impassLeft){
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if(playerCollisionSide instanceof Right && !impassRight){
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if (playerCollisionSide instanceof Top && !impassTop){
					//System.out.println("top");
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				}
				else if (playerCollisionSide instanceof Bottom && !impassBottom){
					Action myAction = new MovableTriggered(mySprite, playerSprite, playerCollisionSide);
					myAction.act();
				} 
			}
					
				

				
		
		}
		
			

		@Override
		public Characteristic copy() {
			return new Movable(mySprite);
		}

	}
