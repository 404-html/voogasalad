package game_data.characteristics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import game_engine.Bottom;
import game_engine.Left;
import game_engine.Right;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;
import game_engine.actions.MovableTriggered;

import java.util.Map;
import java.util.Set;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
//import javafx.geometry.Side;
import game_data.sprites.Player;

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
				if(!(collidedSprite instanceof Player)){
					for(Characteristic i : collidedSprite.getCharacteristics()){
						if( i instanceof Impassable){
							Side temp = myCollisionMap.get(collidedSprite);
							if(temp.isHorizontal()){
								if(temp instanceof Left){
									impassLeft = true;
								} 
								else{impassRight = true;
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
				else if(collidedSprite instanceof Player){
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
				} }
					
				

				
		
		}
		
			

		@Override
		public Characteristic copy() {
			return new Movable(mySprite);
		}

	}
