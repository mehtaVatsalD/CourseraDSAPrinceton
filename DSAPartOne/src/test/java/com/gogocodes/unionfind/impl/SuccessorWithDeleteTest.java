package com.gogocodes.unionfind.impl;

import com.gogocodes.unionfind.SuccessorWithDelete;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuccessorWithDeleteTest {

    private SuccessorWithDelete successorWithDelete;

    @Before
    public void initTest() {
        this.successorWithDelete = new SuccessorWithDeleteUsingUF(10);
    }

    @Test
    public void testSuccessorWithDelete() {
        for (int i = 0; i < 9; i++) {
            assertEquals(i+1, successorWithDelete.successor(i));
        }
        assertEquals(-1, successorWithDelete.successor(9));

        successorWithDelete.delete(5);
        assertEquals(-1, successorWithDelete.successor(5)); // not existing so -1
        assertEquals(6, successorWithDelete.successor(4)); // 5 is removed so successor of 4 should be 6
        assertEquals(7, successorWithDelete.successor(6)); // successor of 6 should still be 7

        successorWithDelete.delete(5);//idempotency check
        assertEquals(-1, successorWithDelete.successor(5));
        assertEquals(6, successorWithDelete.successor(4));
        assertEquals(7, successorWithDelete.successor(6));

        successorWithDelete.delete(7);
        assertEquals(-1, successorWithDelete.successor(5));
        assertEquals(-1, successorWithDelete.successor(7));
        assertEquals(6, successorWithDelete.successor(4));
        assertEquals(8, successorWithDelete.successor(6));

        successorWithDelete.delete(6);
        assertEquals(-1, successorWithDelete.successor(5));
        assertEquals(-1, successorWithDelete.successor(7));
        assertEquals(-1, successorWithDelete.successor(6));
        assertEquals(8, successorWithDelete.successor(4));

        successorWithDelete.delete(9);
        assertEquals(-1, successorWithDelete.successor(9));
        assertEquals(-1, successorWithDelete.successor(8));
        assertEquals(8, successorWithDelete.successor(4));

        successorWithDelete.delete(2);
        assertEquals(3, successorWithDelete.successor(1));
        assertEquals(4, successorWithDelete.successor(3));

        successorWithDelete.delete(4);
        assertEquals(-1, successorWithDelete.successor(4));
        assertEquals(8, successorWithDelete.successor(3));

        successorWithDelete.delete(3);
        successorWithDelete.delete(1);
        successorWithDelete.delete(0);

        for (int i = 0; i <= 9; i++) {
            assertEquals(-1, successorWithDelete.successor(i));
        }


    }

}
