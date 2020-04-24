package com.personal.network.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortestPath {
    
    public Map<String,Tree> getShortestPath(Integer[][] topology,Set<Tree> cachedTrees,Set<Integer> ONNs)
    {	
	List<Integer> treeElements=new ArrayList<Integer>();
	Set<Tree> bestRouteTrees = new HashSet<Tree>();
	int weight;
	TreeWeightComparator treeWeightComparator = new TreeWeightComparator();
	Map<String,Tree> routeMap = new HashMap<String,Tree>();
	PriorityQueue<Tree> pQueue = new PriorityQueue<Tree>(treeWeightComparator);
	
	for(Tree tree:cachedTrees)
	{
	    weight=0;
	    treeElements = tree.totalTreeWeight(tree);
	  
	    for(int cnt=0;cnt<treeElements.size()-1;cnt++)
	    {
		weight = weight+topology[treeElements.get(cnt)][treeElements.get(cnt+1)];
	    }
		tree.setWeight(weight);
		if(routeMap.isEmpty())  routeMap.put((tree.getData()+"-"+tree.getDeepestNodeofTree(tree).getData()), tree);
		else 
		{
		   Tree tempTree = routeMap.get((tree.getData()+"-"+tree.getDeepestNodeofTree(tree).getData()));
		   if(tempTree != null && tempTree.getDeepestNodeofTree(tempTree).getData() == tree.getDeepestNodeofTree(tree).getData() ) 
		   {
		       if(tempTree.getWeight() > tree.getWeight()) routeMap.put((tree.getData()+"-"+tree.getDeepestNodeofTree(tree).getData()), tree);
		   }
		   else routeMap.put((tree.getData()+"-"+tree.getDeepestNodeofTree(tree).getData()), tree);
		}
	    
	   
	}
	 return routeMap;
	}
}