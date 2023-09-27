import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A template for a test class in case you decide to write any.
 * The basic structure is the same as pytest which you've used in the past.
 * We'll talk more about testing later in the term.
 */
public class TreeTest {

    @Test(timeout = 500)
    public void emptyTest() {
        Tree tree1 = new Tree(12, null);
        Tree tree2 = new Tree(null, null);
        assertTrue(tree2.is_empty());
        assertFalse(tree1.is_empty());
    }

    @Test(timeout = 2000)
    public void lenTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(18, null), new Tree(8, null))));
        Tree tree2 = new Tree(null, null);
        assertEquals(3, tree1.len());
        assertEquals(0, tree2.len());
    }

    @Test(timeout = 2000)
    public void countTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(18, null), new Tree(18, null))));
        Tree tree2 = new Tree(null, null);
        assertEquals(2, tree1.count(18));
        assertEquals(0, tree2.count(9));
    }

    @Test(timeout = 2000)
    public void strTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(16, null), new Tree(18, null))));
        Tree tree2 = new Tree(null, null);
        assertEquals("12\n   16\n   18\n", tree1.toString());
        assertEquals("", tree2.toString());
    }

    @Test(timeout = 2000)
    public void averageTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree2 = new Tree(null, null);
        assertEquals(10.0, tree1.average(), 0.0);
        assertEquals(0.0, tree2.average(), 0.0);
    }

    @Test
    public void equalsTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree2 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree3 = new Tree(8, null);

        assertEquals(tree1, tree2);
        assertNotEquals(tree1, tree3);
    }

    @Test
    public void containsTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree2 = new Tree(null, null);

        assertTrue(tree1.contains(12));
        assertFalse(tree2.contains(92));
    }

    @Test
    public void leavesTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree2 = new Tree(null, null);

        assertEquals(new ArrayList<>(Arrays.asList(8, 10)), tree1.leaves());
        assertEquals(new ArrayList<>(), tree2.leaves());
    }

    @Test
    public void deleteItemTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree2 = new Tree(null, null);

        assertTrue(tree1.delete_item(10));
        assertFalse(tree2.delete_item(92));
    }

    @Test
    public void insertTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree1expect1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, new ArrayList<>(List.of(new Tree(20, null)))), new Tree(10, null))));
        Tree tree1expect2 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, new ArrayList<>(List.of(new Tree(20, null)))))));
        Tree tree1expect3 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null), new Tree(20, null))));
        Tree tree2 = new Tree(null, null);
        Tree tree2expect = new Tree(92, null);
        Tree tree2expect2 = new Tree(92, new ArrayList<>(List.of(new Tree(11, null))));
        tree1.insert(20);
        tree2.insert(92);
        try {
            assertEquals(tree1expect1, tree1);
        } catch (Exception e) {
            try {
                assertEquals(tree1expect2, tree1);
            } catch (Exception e2) {
                try {
                    assertEquals(tree1expect3, tree1);
                } finally {
                    ;
                }
            }
        }
        assertEquals(tree2expect, tree2);
        tree2.insert(11);
        assertEquals(tree2expect2, tree2);
    }

    @Test
    public void insertChildTest() {
        Tree tree1 = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, null), new Tree(10, null))));
        Tree tree1expect = new Tree(12, new ArrayList<>(Arrays.asList(new Tree(8, new ArrayList<>(List.of(new Tree(20, null)))), new Tree(10, null))));
        Tree tree2 = new Tree(null, null);
        Tree tree2expect = new Tree(92, null);
        tree1.insertChild(20, 8);
        tree2.insertChild(92, null);
        assertEquals(tree1expect, tree1);
        assertNotEquals(tree2expect, tree2);
    }

}