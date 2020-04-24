package com.personal.network.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.personal.network.trees.Tree;

public class CreateNCacheTrees {
    
    private Integer topology[][];
    private Set<Integer> originnodes;
    private Set<Integer> destnodes;
    private Set<Tree> Trees;
    private boolean[][] isVisited;
    private Stack<Integer> theStack;
    Tree treetraversal = new Tree();
    
    public CreateNCacheTrees(Integer topology[][])
    {
	this.topology = topology;
	this.Trees=new HashSet<Tree>();
    }
    
    public Set<Tree> createTrees(Integer topology[][],Set<Integer> originnodes,Set<Integer> destnodes)
    {	

	for(int node:originnodes)
	{	
	    System.out.println("Current Origin Node"+node);
	   Trees = createTrees(topology,node,destnodes);
	    
	}
	for(Tree tree:Trees)
	{
	    treetraversal.preOrderTraversal(tree);
	}
	return Trees;
    }

   

    private Set<Tree> createTrees(Integer topology[][], int originnode,Set<Integer> destnodes) {
	// TODO Auto-generated method stub
	for(int destnode:destnodes)
	{   
	    System.out.println("Current Destination Node"+ destnode);
	    Trees= DFS(originnode,destnode,topology);
	}
	return Trees;
    }
    
    private Set<Tree> DFS(int source,int destination,Integer topology[][])
    {
	
	isVisited = new boolean[topology.length][topology.length];
	
	theStack =  new Stack<Integer>();
	for(int i=0;i<topology.length;i++) 
	{
	  for(int j=0;j<topology.length;j++)
	  {
		isVisited[i][j]=false;
	  }
	}	
		isVisited[source][source]=true;
		theStack.push(source);
		createRecursivePaths(theStack,topology,source,destination);
		return Trees; 
		
    }
    
   

    private List<Integer> getUnvisitedVertex(int vertex)
    {		
	List<Integer> routeList = new ArrayList<Integer>();
	
		for(int j=0;j<topology.length;j++)
		{
		    if(topology[vertex][j]!=0 && isVisited[vertex][j] == false ) routeList.add(j);
		 }
		return routeList;
    }
    
    
    
    private Set<Tree>createRecursivePaths(Stack<Integer> theStack,Integer[][] topology,int source,int destination)
    {
		if((source==destination)) return Trees;
		while(!theStack.isEmpty())
		{
		    	List<Integer> routeList= getUnvisitedVertex(theStack.peek());
		    	if(routeList.isEmpty()) {theStack.pop(); if(!theStack.isEmpty()) source=theStack.peek();break; } 
		        else
		    	{
		    	    for(int route:routeList)
		    	    {
		    		isVisited[theStack.peek()][route]=true;
		    		theStack.push(route);
		    		if(routeList.size() >1) Trees = createRecursivePaths(theStack,topology,route,destination);
		    		if(route==destination)
		    			{
		    		    		Tree tree = createPathTrees( source, destination);
		    		    		Trees.add(tree);
		    		    		return Trees;
		    			}
		    	    }
		 
		 }
	 }
	 return Trees;
	     
    }

    private Tree createPathTrees(int source,int destination) {
	// TODO Auto-generated method stub
		Stack<Integer> dupStack = new Stack<Integer>();
	
		dupStack.addAll(theStack);
		Tree tree=new Tree(dupStack.firstElement());
		for(int k=1;k<dupStack.size();k++)
		 { 
		    tree= tree.recurseCreateTree(dupStack.get(k),tree);
		    if(theStack.peek() >= source) theStack.pop();
		 }
		for(int cnt=source++;cnt<dupStack.size();cnt++)
		{
		    for(int j=0;j<topology.length;j++)
		    {
			 isVisited[cnt][j]=false;
		    }
		}
		return tree;
    }

}
