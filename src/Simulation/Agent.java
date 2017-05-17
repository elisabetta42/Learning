package Simulation;

import java.awt.Color;
import java.util.List;


public class Agent{
	int [] position=new int[2];
	public Agent(){}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int row, int col,Node n) {	
		n.setBackground(Color.RED);
		this.position[0] = row;
		this.position[1] = col;
	}

	public void goLeft(){

	}
	public void goRight(){

	}
	public void goDown(){

	}
	public void goUp(){

	}



}
