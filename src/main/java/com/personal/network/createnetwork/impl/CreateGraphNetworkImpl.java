package com.personal.network.createnetwork.impl;

import com.personal.network.createnetwork.definition.CreateNetwork;

public class CreateGraphNetworkImpl implements CreateNetwork {
    
    
    private Integer networktopology[][];
    static int Edges;
    
    public CreateGraphNetworkImpl(int Edges)
    {
	this.networktopology=new Integer[Edges][Edges];
	Edges=Edges;
    }
    
    @Override
    public String addEdgeNode(int EdgeNode) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String addNetworkNode(int NetworkNode) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String connectNodes(int source, int destination, int weight) {
	// TODO Auto-generated method stub
	networktopology[source][destination]= weight;
	return "success";
    }

    @Override
    public void printTopology() {
	// TODO Auto-generated method stub
	for(int i=0;i<Edges;i++)
	{
	    for(int j=0;j<Edges;j++)
	    {
		System.out.println("["+i + "]"+"["+j+"] :"+networktopology[i][j]+System.lineSeparator());
	    }
	}
    }

    @Override
    public Integer[][] getToplogy() {
	// TODO Auto-generated method stub
	return this.networktopology;
    }

}
