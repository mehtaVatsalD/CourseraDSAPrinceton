package com.gogocodes.unionfind;

public interface UnionFind {

    void union(int p, int q);

    boolean connected(int p, int q);

    int countConnectedComponents();

    int getId(int p);

}
