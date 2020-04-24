package com.personal.network.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.network.config.RedisConfig;
import com.personal.network.trees.Tree;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;


public class CachedTrees {
    
    private Integer topology[][];
    private Set<Integer> nodes;
    private Tree tree;
    
    //@Autowired
    RedisConfig redisClient = new RedisConfig();
    
    public Set<Tree> getCachedTrees(Integer[][] topology,Set<Integer> nodes)
    {
	 Set<Tree> cachedTrees = new HashSet<Tree>();
	 Jedis jedis = new Jedis(new HostAndPort("127.0.0.1",6379));
	for(int node:nodes)
	{
	    //Search in Cache for Cached tree if exists returns a List of Trees
	  List<String> linkedTree = new LinkedList<String>();
	  linkedTree = jedis.lrange("SubTree-"+node, 0, topology.length*topology.length);
	  for(int i =0;i<linkedTree.size();++i)
	  {
	      if(Integer.parseInt(linkedTree.get(i))==node )
	      {
		  if(tree!=null) 
		      {
		      	cachedTrees.add(tree); 
		      	tree=null;
		      }
		tree = new Tree(node);
		
	      }
	      else tree=tree.recurseCreateTree(Integer.parseInt(linkedTree.get(i)), tree);
		  
	  }
	  if(tree != null) cachedTrees.add(tree); 
	  
	    
	}
	return cachedTrees;
    }
    
    public String cacheTrees(Set<Tree> trees)
    {
	Jedis jedis = new Jedis(new HostAndPort("127.0.0.1",6379));
	int count=0;
    
	for(Tree tree:trees)
	 {
	  // count++;
	   tree.savePreOrderTree(tree,jedis,tree.getData());
	  }
	
	
	return "success";
    }
    
    
    
}