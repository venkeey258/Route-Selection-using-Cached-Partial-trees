package com.personal.network.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.personal.network.config.RedisConfig;

import redis.clients.jedis.Jedis;

public class Tree implements Comparable<Tree> {
    
    private Integer data;
    private Tree left,right;
    private Integer weight;
    private List<Integer> treeElements=new ArrayList<Integer>();
  
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Tree()
    {
	
    }
    
    public Tree(Integer data)
    {
	this.data = data;
	this.left=null;
	this.right=null;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
    
    public Tree recurseCreateTree(Integer data, Tree tree) {
   	// TODO Auto-generated method stub
	
	if(tree == null) tree = new Tree(data);
	else inserthelper(data,tree);
	return tree;
       }
    
    private Tree inserthelper(Integer data, Tree tree) {
	// TODO Auto-generated method stub
	if(tree.data > data)
		{
	    		if(tree.getLeft() == null) tree.setLeft(new Tree(data));
	    		else recurseCreateTree(data,tree.getLeft());
	    	}
	else if(tree.data < data)
		{
	    		if(tree.getRight() == null) tree.setRight(new Tree(data));
	    		else recurseCreateTree(data,tree.getRight());
	    		
		}
	  return tree;
    }
    
    public void preOrderTraversal(Tree tree) {
   	// TODO Auto-generated method stub
	
	if(tree!=null)
	{
   	System.out.println("Pre-Order Traversal"+tree.getData());
   	preOrderTraversal(tree.getLeft());
   	preOrderTraversal(tree.getRight());
	}
   	
       }
    
    public void savePreOrderTree(Tree tree,Jedis jedis,Integer data) {
   	// TODO Auto-generated method stub
	
	if(tree!=null)
	{
   	System.out.println("Pre-Order Traversal"+tree.getData());
   	jedis.rpush("SubTree-"+data, tree.getData().toString());
   	
   	savePreOrderTree(tree.getLeft(),jedis,data);
   	savePreOrderTree(tree.getRight(),jedis,data);
	}
   	
       }
    
    public List<Integer> totalTreeWeight(Tree tree)
    {
	
	if(tree != null)
	{
	    treeElements.add(tree.getData());
	    totalTreeWeight(tree.getLeft());
	    totalTreeWeight(tree.getRight());
	}
	return treeElements;
    }

    @Override
    public int compareTo(Tree tree) {
	// TODO Auto-generated method stub
	if(this.data  < tree.data ) return -1;
	else if(this.data > tree.data) return 1;
	return 0;
    }

   public Tree getDeepestNodeofTree(Tree tree)
   {
       Tree deepNodeTree =null;
       if(tree == null) return null;
       Queue<Tree> q = new LinkedList<Tree>();
       q.offer(tree);
       while(!q.isEmpty())
       {
	   deepNodeTree = q.poll();
	   if(deepNodeTree.getLeft()!=null) q.offer(deepNodeTree.getLeft());
	   if(deepNodeTree.getRight() != null) q.offer(deepNodeTree.getRight());
	}
       return deepNodeTree;
       
       
   }
    
  
    

}
