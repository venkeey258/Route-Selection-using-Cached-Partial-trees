package com.personal.network.createnetwork.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.personal.network.createnetwork.definition.FindRoute;
import com.personal.network.routeselection.RouteSelectionProcessImpl;
import com.personal.network.trees.CachedTrees;
import com.personal.network.trees.CreateNCacheTrees;
import com.personal.network.trees.ShortestPath;
import com.personal.network.trees.Tree;

public class FindRouteImpl implements FindRoute {
    
    private  int source;
    private  int destination;
    private  Integer topology[][];

    @Override
    public String findNetworkRoute(Integer[][] topology,int source,int destination) {
	// TODO Auto-generated method stub
	this.source = source;
	this.destination=destination;
	this.topology=topology;
	String result=null;
	
	RouteSelectionProcessImpl routeSelection = new RouteSelectionProcessImpl(); 
	CachedTrees getcachedTrees = new CachedTrees();
	ShortestPath shortestPath = new ShortestPath();
	CreateNCacheTrees createCachedTrees = new CreateNCacheTrees(topology);
	Tree tree = new Tree();
	
	Set<Integer> ONNs=routeSelection.createConnectingNodes(topology,source);
	for(int onn:ONNs)
	{
	    System.out.print("Origin Network Nodes"+onn+System.lineSeparator());
	}
	Set<Integer> DNNs=routeSelection.createConnectingNodes(topology,destination);
	for(int dnn:DNNs)
	{
	    System.out.print("Destination Network Nodes"+dnn+System.lineSeparator());
	}
	Set<Tree> cachedTrees = getcachedTrees.getCachedTrees(topology, ONNs);
	if(cachedTrees.isEmpty()) 
	    {
	    	cachedTrees = createCachedTrees.createTrees(topology, ONNs, DNNs);
	    	result = getcachedTrees.cacheTrees(cachedTrees);
	    }
	Map<String,Tree> bestRouteTrees = shortestPath.getShortestPath(topology, cachedTrees,ONNs);
	Tree leastWeighSourceTree = routeSelection.leastWeightSourceTree(bestRouteTrees,source,0,topology);
	Tree leastWeighDestTree = routeSelection.leastWeightSourceTree(bestRouteTrees,0,destination,topology); 
	System.out.println("Least Weighted Path"+ source );
	tree.preOrderTraversal(leastWeighSourceTree);
	System.out.println(destination);
	return result;
    }

    
    
    

}
