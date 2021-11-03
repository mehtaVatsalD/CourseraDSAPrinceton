package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.SuccessorWithDelete;

/**
 * Implements Successor with Delete interview question problem using union find data structure
 */
public class SuccessorWithDeleteUsingUF implements SuccessorWithDelete {

    private final WeightedQuickUnionPC uf;
    private final int[] maxValues;
    private final boolean[] removedValues;

    public SuccessorWithDeleteUsingUF(int n) {
        this.uf = new WeightedQuickUnionPC(n);
        this.maxValues = new int[n];
        this.removedValues = new boolean[n];
        initializeDataStructures();
    }

    private void merge(int p, int q) {
        int parentOfP = uf.getId(p);
        int parentOfQ = uf.getId(q);
        uf.union(p, q);
        maxValues[parentOfP] = maxValues[parentOfQ] = Math.max(maxValues[parentOfP], maxValues[parentOfQ]);
    }

    private int findMax(int p) {
        int parentOfP = uf.getId(p);
        return maxValues[parentOfP];
    }

    @Override
    public void delete(int p) {
        removedValues[p] = true;
        if (p+1 < uf.size()) {
            merge(p, p+1);
        }
    }

    @Override
    public int successor(int p) {
        // for removed values and for values at last successor will not be present hence we return -1
        if (p >= uf.size() - 1 || removedValues[p]) {
            return -1;
        }
        // finding max in set of neighbour element element
        // if element exists then it would not have been merged and hence maxValue would be returned as next element itself
        // if it would have been deleted then will return max value at its root
        // also need to check if maxValue obtained is not deleted. If deleted then need to return -1;
        int max = findMax(p+1);
        return !removedValues[max] ? max : -1;
    }

    /**
     * Initializes all values in array with index value
     */
    private void initializeDataStructures() {
        for (int i=0;i<uf.size();i++){
            maxValues[i] = i;
        }
    }

}
