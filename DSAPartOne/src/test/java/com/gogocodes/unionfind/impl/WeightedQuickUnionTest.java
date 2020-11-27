package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.UnionFind;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeightedQuickUnionTest {

    private final UnionFind uf = new WeightedQuickUnion(10);

    @Test
    public void testWeightedQuickUnion(){
        uf.union(6, 8);
        uf.union(7, 9);
        uf.union(9, 8);
        uf.union(0, 1);
        uf.union(1, 3);
        assertEquals(1, uf.getId(3));
        assertEquals(1, uf.getId(1));
        assertEquals(1, uf.getId(0));

        uf.union(2, 4);
        uf.union(5, 4);

        assertEquals(3, uf.countConnectedComponents());

        assertTrue(uf.connected(6, 7));
        assertTrue(uf.connected(2, 5));
        assertTrue(uf.connected(3, 0));
        assertFalse(uf.connected(0, 5));
        assertFalse(uf.connected(5, 8));

        assertEquals(4, uf.getId(5));
        uf.union(7, 2);
        assertTrue(uf.connected(5, 8));
        assertEquals(8, uf.getId(7));
        assertEquals(8, uf.getId(5));
        assertEquals(2, uf.countConnectedComponents());

    }

}
