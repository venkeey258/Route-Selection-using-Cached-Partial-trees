package com.personal.network.createnetwork.impl;

import java.util.Arrays;
import com.personal.network.createnetwork.definition.CreateNetwork;



public class CreateNetworkImpl implements CreateNetwork {
    
    private Integer networktopology[][];
    static int EdgesCount;
    static int NetworkNodesCount; 
    
    public CreateNetworkImpl(int EdgeNode,int NetworkNode)
    {
	this.networktopology = new Integer[EdgeNode][NetworkNode];
	EdgesCount=EdgeNode;
	NetworkNodesCount=NetworkNode;
	
	for(int i=0;i<EdgesCount;i++)
	{
	    for(int j=0;j<NetworkNodesCount;j++)
	    {
		networktopology[i][j]=0;
	    }
	}
    }

    @Override
    public String addEdgeNode(int EdgeNode) {
	// TODO Auto-generated method stub
	return "success";
    }

    @Override
    public String addNetworkNode(int NetworkNode) {
	// TODO Auto-generated method stub
	return "success";
    }

    @Override
    public String connectNodes(int EdgeNode, int NetworkNode,int weight) {
	// TODO Auto-generated method stub
	networktopology[EdgeNode][NetworkNode]= weight;
	return "success";
    }

    @Override
    public String toString() {
	return "CreateNetworkImpl [networktopology=" + Arrays.toString(networktopology) + "]";
    }
    
    public void printTopology()
    {
	for(int i=0;i<EdgesCount;i++)
	{
	    for(int j=0;j<NetworkNodesCount;j++)
	    {
		System.out.println("["+i + "]"+"["+j+"] :"+networktopology[i][j]+"\n");
	    }
	}
    }
    
   
    @Override
    public Integer[][] getToplogy() {
	// TODO Auto-generated method stub
	return this.networktopology;
    }
    
    
    
    
   

}
