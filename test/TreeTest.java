import org.junit.Test;

import java.util.ArrayList;

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

}