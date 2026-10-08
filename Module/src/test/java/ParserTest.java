import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testPostfixWhole(){
        assertEquals(new NumNode(5), Parser.parsePostFix("5"));
    }

    @Test
    public void testPostfixNegativeDouble(){
        assertEquals(new NumNode(-9.7), Parser.parsePostFix("-9.7"));
    }

    @Test
    public void testPostfixPositiveDouble(){
        assertEquals(new NumNode(2.1), Parser.parsePostFix("+2.1"));
    }

    @Test
    public void testPostfixAddition(){
        assertEquals(new BinopNode('+', new NumNode(2), new NumNode(9)), Parser.parsePostFix("2 9 +"));
    }

    @Test
    public void testPostfixSubstraction(){
        assertEquals(new BinopNode('-', new NumNode(7), new NumNode(3)), Parser.parsePostFix("7 3 -"));
    }

    @Test
    public void testPostfixOperatorError(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("1 6 + *"));
    }

    @Test
    public void testPostfixOperandError(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("8 8"));
    }


}