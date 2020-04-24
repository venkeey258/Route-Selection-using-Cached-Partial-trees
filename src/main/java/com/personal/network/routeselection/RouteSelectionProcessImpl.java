package com.personal.network.routeselection;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.personal.network.trees.Tree;

public class RouteSelectionProcessImpl {

    
    public  Set<Integer> createConnectingNodes(Integer[][] topology, int key) {
	// TODO Auto-generated method stub
	Set<Integer> ConnectingNodes = new TreeSet<Integer>();
	for(int i=0;i<topology.length ;i++)
	{
	    for(int j=0;j<topology.length;j++)
	    {
		if(topology[i][j] != 0 && (i==key || j==key))
		{
		
		    if(i==key) ConnectingNodes.add(j);
		    else if(j==key) ConnectingNodes.add(i);
		}
	    }
	}
	return ConnectingNodes;
    }
 
    public Tree leastWeightSourceTree(Map<String,Tree> bestRouteTrees,Integer source,Integer destination,Integer[][] topology) 
    {
	
	Queue<Tree> finalTree = new LinkedList<Tree>();
	int addweight =0;
	for(Map.Entry<String, Tree> entry: bestRouteTrees.entrySet())
	{
	   if(source > 0) addweight = topology[source][entry.getValue().getData()] ;
	   else if(destination > 0) addweight = topology[entry.getValue().getDeepestNodeofTree(entry.getValue()).getData()][destination] ;
	    if(addweight !=0)
	    {
	    int bestrouteweight = entry.getValue().getWeight();
	    entry.getValue().setWeight(Math.addExact(addweight,bestrouteweight));
	    	if(finalTree.isEmpty()) finalTree.add(entry.getValue());
	    	else
	    	{
	    	    if(entry.getValue().getWeight() < finalTree.peek().getWeight())
	    	    {
	    		finalTree.poll();
	    		finalTree.offer(entry.getValue());
	    	    }
	    	}
	    }
	}
   
    return finalTree.poll();
    }
}
