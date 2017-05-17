package Simulation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QLearning {
	public QLearning(){}
	public static float q_value(Grid grid,Node currentNode){

		ArrayList<Node>neighbours= findNeighbour(grid, currentNode);
		Node max=maxQValue(neighbours);
		float q1=currentNode.q_value;
		float q2=max.q_value;
		int reward=currentNode.reward;
		float result = q1+grid.learningrate*(reward+grid.discountfactor*q2-q1);
		//this.world.setPolicyValue(s1, a1, d);
		grid.grid[currentNode.row][currentNode.col].setQ_value(result);
		/*float result = currentNode.q_value+grid.learningrate*(currentNode.reward+(grid.discountfactor*max.q_value)-currentNode.q_value);
		System.out.println("result: "+result+"current value: "+ currentNode.q_value);*/
		return result;
		
	}
	
	public static ArrayList<Node> findNeighbour(Grid grid, Node currentNode){
		ArrayList<Node>neighbours=new ArrayList<Node>();	

		if(currentNode.row!=0)
			neighbours.add(grid.grid[currentNode.row-1][currentNode.col]);
		if(currentNode.row!=grid.grid.length-1)
			neighbours.add(grid.grid[currentNode.row+1][currentNode.col]);
		if(currentNode.col!=0)
			neighbours.add(grid.grid[currentNode.row][currentNode.col-1]);
		if(currentNode.col!=grid.grid[0].length-1)
			neighbours.add(grid.grid[currentNode.row][currentNode.col+1]);

		return neighbours;
	}
	public static Node maxQValue(ArrayList<Node> neighbours){
		Node max=neighbours.get(1);
		for(Node i : neighbours){	
			if(i.q_value>max.q_value){
				max=i;
			}
		}
		if(max.q_value==0){
			int value= ThreadLocalRandom.current().nextInt(0,neighbours.size());
			return neighbours.get(value);
		}
		else return max;
	}

}
