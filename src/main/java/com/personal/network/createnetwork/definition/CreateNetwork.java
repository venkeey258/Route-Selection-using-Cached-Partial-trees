package com.personal.network.createnetwork.definition;

public interface CreateNetwork {
    
   String addEdgeNode(int EdgeNode);
   String addNetworkNode(int NetworkNode);
   String connectNodes(int EdgeNode,int NetworkNode,int weight);
   void printTopology();
   Integer[][] getToplogy();
}
