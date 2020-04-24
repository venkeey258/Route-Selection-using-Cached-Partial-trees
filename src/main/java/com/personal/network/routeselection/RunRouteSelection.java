package com.personal.network.routeselection;

import com.personal.network.createnetwork.definition.CreateNetwork;

import com.personal.network.createnetwork.impl.CreateNetworkImpl;
import com.personal.network.createnetwork.definition.FindRoute;
import com.personal.network.createnetwork.impl.FindRouteImpl;

public class RunRouteSelection {
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	CreateNetwork createNetwork = new CreateNetworkImpl(10,10);
	FindRoute routeFinder = new FindRouteImpl();
	createNetwork.connectNodes(0, 1, 3);
	createNetwork.connectNodes(0, 9, 1);
	createNetwork.connectNodes(1, 2, 1);
	createNetwork.connectNodes(2, 3, 2);
	createNetwork.connectNodes(2, 4, 7);
	createNetwork.connectNodes(3, 4, 1);
	createNetwork.connectNodes(3, 6, 4);
	createNetwork.connectNodes(3, 9, 8);
	createNetwork.connectNodes(4, 5, 2);
	createNetwork.connectNodes(5, 6, 4);
	createNetwork.connectNodes(6, 7, 11);
	createNetwork.connectNodes(6, 8, 3);
	createNetwork.connectNodes(6, 9, 3);
	createNetwork.connectNodes(7, 8, 4);
	createNetwork.connectNodes(8, 9, 8);
	
	
	
	Integer topology[][]=createNetwork.getToplogy();
	Long startTime=System.currentTimeMillis();
	routeFinder.findNetworkRoute(topology, 4, 6);
	Long endTime=System.currentTimeMillis();
	System.out.println("Time Taken"+"Start Time:"+startTime+"End Time"+endTime+"Time Taken"+ Math.subtractExact(endTime, startTime));
	
	

    }


}
