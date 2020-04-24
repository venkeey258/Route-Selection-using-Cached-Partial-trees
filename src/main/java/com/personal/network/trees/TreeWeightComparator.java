package com.personal.network.trees;

import java.util.Comparator;

public class TreeWeightComparator implements Comparator<Tree> {

    @Override
    public int compare(Tree t1, Tree t2) {
	// TODO Auto-generated method stub
	if (t1.getWeight() < t2.getWeight()) return -1;
	else if(t1.getWeight() > t2.getWeight()) return 1;
	return 0;
    }

}
