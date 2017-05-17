package Simulation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Grid extends JPanel{

	float learningrate=0.5f;
	float discountfactor=0.9f;
	int count=0;
	boolean finished=false;
	Node[][]grid=new Node[4][12];
	Node start;	
	Agent agent=new Agent();
	int goal=0;
	int runs=0;

	public Grid(){

		initialize();
		paint();
	}
	public void traverseGrid(Node start){
		runs++;
		while(finished==false){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			start.iscurrent=false;
			Node max=null;
			if(start.visited==false)
				start.visited=true;
			else count++;

			ArrayList<Node> neighbours=QLearning.findNeighbour(this,start);
			max=QLearning.maxQValue(neighbours);	

			agent.setPosition(start.row, start.col,start);
			start.iscurrent=true;
			start.paint();
			QLearning.q_value(this, start);
			System.out.println(agent.getPosition()[0]+" "+agent.getPosition()[1]+" q value: "+grid[start.row][start.col].q_value);

			if(max.isGoal==true){
				QLearning.q_value(this, start);
				System.out.println("Goal reached!");
				goal++;
				finished=true;
			}
			else if(start.isCliff==true){
				QLearning.q_value(this, max);
				System.out.println("agent dead!");
				finished=true;
			}
			else start=max;
		}
		finished=false;
	}

	public void updateQLearning(Node currentNode){
		float q_value=QLearning.q_value(this, currentNode);
		currentNode.q_value=q_value;
	}
	public float getLearningrate() {
		return learningrate;
	}

	public void setLearningrate(int learningrate) {
		this.learningrate = learningrate;
	}

	public float getDiscountfactor() {
		return discountfactor;
	}

	public void setDiscountfactor(int discountfactor) {
		this.discountfactor = discountfactor;
	}
	public void initialize(){
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				grid[i][j]=new Node(i,j);
				grid[i][j].setQ_value(0);
				grid[i][j].setReward(-1);
			}	
		}
		setValues();
	}
	public void setValues(){
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){

				if(i==grid.length-1){
					if(j!=0 && j<grid[0].length-1){
						grid[i][j].setReward(-100);
						grid[i][j].setQ_value(0);
						grid[i][j].setCliff(true);
					}
					if(j==0){
						grid[i][j].setStart(true);
						start=grid[i][j];
					}
					if(j==grid[0].length-1){
						grid[i][j].setGoal(true);
						grid[i][j].setQ_value(100);
					}
				}
				//System.out.println("i: "+i+"j:  "+j+" "+grid[i][j].getReward());
			}
		}
		
		this.setLayout(new GridLayout(grid.length, grid[0].length));
		JFrame frame=new JFrame();
		frame.setPreferredSize(new Dimension(grid[0].length*80,grid.length*80));
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	public void paint(){
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				if(i==grid.length-1 &&j!=0 && j<grid[0].length-1){
					grid[i][j].setBackground(Color.BLUE);
				}
				else grid[i][j].setBackground(Color.WHITE);
				
				this.add(grid[i][j]);
				this.repaint();
				this.validate();
			}
		}
	}
	public void startEpisode(Node start){
		System.out.println("new Episode!");
		this.removeAll();
		paint();
		traverseGrid(start);
		
	}
	public static void main(String[] args) {

		Grid g=new Grid();
		for(int i=0;i<20;i++){
			g.startEpisode(g.start);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Time the goal is reached "+g.goal);
	}
}
