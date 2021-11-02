package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class QuickUnion implements UnionFind {

    private final int[] idValues;
    private int count;

    public QuickUnion(int n) {
        this.idValues = new int[n];
        this.count = n;
        fillAllIdsWithIndexValue();
    }

    @Override
    public void union(int p, int q){
        int parentOfP = getId(p);
        int parentOfQ = getId(q);

        if (parentOfP == parentOfQ) {
            return;
        }

        idValues[parentOfP] = parentOfQ;
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
    private void fillAllIdsWithIndexValue() {
        for (int i=0;i<idValues.length;i++){
            idValues[i] = i;
        }
    }

}
