package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class QuickFind implements UnionFind {

    private final int[] idValues;

    public QuickFind(int n) {
        this.idValues = new int[n];
        fillAllIdsWithIndexValue();
    }

    @Override
    public void union(int p, int q){
        int idOfP = getId(p);
        int idOfQ = getId(q);
        replaceIds(idOfP, idOfQ);
    }

    @Override
    public boolean connected(int p, int q){
        return idValues[p] == idValues[q];
    }

    @Override
    public int countConnectedComponents(){
        return getUniqueIdCount();
    }

    /**
     * get unique ids present inside idValues
     */
    private int getUniqueIdCount(){
        Map<Integer, Integer> idCountMap = new HashMap<>();
        int idCount;
        for (int idValue : idValues) {
            idCount = idCountMap.getOrDefault(idValue, 0);
            idCountMap.put(idValue, ++idCount);
        }
        return idCountMap.size();
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
