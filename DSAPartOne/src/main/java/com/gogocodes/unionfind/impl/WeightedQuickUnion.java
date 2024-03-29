package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class WeightedQuickUnion implements UnionFind {

    private final int[] idValues;
    private final int[] treeSizes;
    private int count;

    public WeightedQuickUnion(int n) {
        this.idValues = new int[n];
        this.treeSizes = new int[n];
        this.count = n;
        initializeDataStructures();
    }

    @Override
    public void union(int p, int q){
        int parentOfP = getId(p);
        int parentOfQ = getId(q);

        if (parentOfP == parentOfQ) {
            return;
        }

        if(treeSizes[parentOfQ] < treeSizes[parentOfP]){
            idValues[parentOfQ] = parentOfP;
            treeSizes[parentOfP] += treeSizes[parentOfQ];
        }
        else {
            idValues[parentOfP] = parentOfQ;
            treeSizes[parentOfQ] += treeSizes[parentOfP];
        }
        count--;
    }

    @Override
    public boolean connected(int p, int q){
        int parentOfP = getId(p);
        int parentOfQ = getId(q);
        return parentOfP == parentOfQ;
    }

    @Override
    public int countConnectedComponents(){
        return count;
    }

    /**
     * returns to root of node passed in as parameter
     * @param p node for which root node is to be find
     * @return id of root
     */
    @Override
    public int getId(int p){
        int toSearch = p;
        while(toSearch != idValues[toSearch]){
            toSearch = idValues[toSearch];
        }
        return toSearch;
    }

    /**
     * Initializes all values in array with index value
     */
    private void initializeDataStructures() {
        for (int i=0;i<idValues.length;i++){
            idValues[i] = i;
            treeSizes[i] = 1;
        }
    }

}
