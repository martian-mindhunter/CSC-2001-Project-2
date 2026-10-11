import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ASTTest {
    @Test
    public void testEvalNumNode(){
        assertEquals(5.0,new NumNode(5).eval());
        assertEquals(-3.5,new NumNode(-3.5).eval());
    }

    @Test
    public void testEvalBinopNodeAdd(){
        //positive
        assertEquals(14, new BinopNode('+',new NumNode(5),new NumNode(9)).eval());

        //negative
        assertEquals(-8, new BinopNode('+',new NumNode(3),new NumNode(-11)).eval());

    }

    @Test
    public void testEvalBinopNodeSub(){
        //negative
        assertEquals(-4, new BinopNode('-', new NumNode(3), new NumNode(7)).eval());

        //positive
        assertEquals(19, new BinopNode('-', new NumNode(28), new NumNode(9)).eval());

    }

    @Test
    public void testEvalBinopNodeMult(){
        //positive
        assertEquals(35,new BinopNode('*', new NumNode(5), new NumNode(7)).eval());

        //negative
        assertEquals(-140,new BinopNode('*', new NumNode(20), new NumNode(-7)).eval());

    }

    @Test
    public void testEvalBinopNodeDiv(){
        //positive
        assertEquals(7, new BinopNode('/', new NumNode(42), new NumNode(6)).eval());

        //negative
        assertEquals(-12, new BinopNode('/', new NumNode(-108), new NumNode(9)).eval());
    }

    @Test
    public void testEvalBinopNodePow(){
        //positive
        assertEquals(32, new BinopNode('^', new NumNode(2), new NumNode(5)).eval());

        //negative
        assertEquals(0.125, new BinopNode('^', new NumNode(2), new NumNode(-3)).eval());
    }

    @Test
    public void testEvalBinopError(){
        assertThrows(IllegalArgumentException.class, () -> new BinopNode('?', new NumNode(2), new NumNode(3)).eval());
    }

    @Test
    public void testEvalParse(){
        assertEquals(14.0 ,Parser.parseInfix("2 * ( 3 + 4 )").eval());
    }

    @Test
    public void testPostfixEval() {
        assertEquals(14.0, Parser.parsePostFix("2 3 4 + *").eval());
    }
}
