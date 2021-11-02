package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class QuickFind implements UnionFind {

    private final int[] idValues;
    private int count;

    public QuickFind(int n) {
        this.idValues = new int[n];
        this.count = n;
        fillAllIdsWithIndexValue();
    }

    @Override
    public void union(int p, int q){
        int idOfP = getId(p);
        int idOfQ = getId(q);

        if (idOfP == idOfQ) {
            return;
        }

        replaceIds(idOfP, idOfQ);
        count--;
    }

    @Override
    public boolean connected(int p, int q){
        return idValues[p] == idValues[q];
    }

    @Override
    public int countConnectedComponents(){
        return count;
    }

    @Override
    public int getId(int p){
        return idValues[p];
    }

    /**
     * Initializes all values in array with index value
     */
    private void fillAllIdsWithIndexValue() {
        for (int i=0;i<idValues.length;i++){
            idValues[i] = i;
        }
    }

    /**
     * Replace all nodes who have id of node P with the value of id of node Q
     * @param idOfP id value of node p
     * @param idOfQ id value of node Q
     */
    private void replaceIds(int idOfP, int idOfQ){
        for (int i=0;i<idValues.length;i++){
            if (idValues[i] == idOfP){
                idValues[i] = idOfQ;
            }
        }
    }

}
