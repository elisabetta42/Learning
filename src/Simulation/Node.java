package Simulation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Node extends JPanel{
	float q_value;
	int reward;
	boolean isStart,isGoal,isCliff,visited,iscurrent;
	int row,col;
	
	public Node(int row,int col){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.row=row;
		this.col=col;
		q_value=0.0f;
		reward=-1;
		isStart=false;
		isGoal=false;
		isCliff=false;
		visited=false;
		iscurrent=false;
	}
	public void paint(){

	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}

	public boolean isCliff() {
		return isCliff;
	}
	public void setCliff(boolean isCliff) {
		this.isCliff = isCliff;
	}

	public float getQ_value() {
		return q_value;
	}

	public void setQ_value(float q_value) {
		this.q_value = q_value;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
	public String toString(){
		return "row: "+this.row+" "+"col: "+this.col+"q_value "+q_value;
	}

}
