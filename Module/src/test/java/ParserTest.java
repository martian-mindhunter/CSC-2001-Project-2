import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParserTest {

    // Postfix Testing
    @Test
    public void testPostfixWhole(){
        assertEquals(new NumNode(5), Parser.parsePostfix("5"));
    }

    @Test
    public void testPostfixNegativeDouble(){
        assertEquals(new NumNode(-9.7), Parser.parsePostfix("-9.7"));
    }

    @Test
    public void testPostfixPositiveDouble(){
        assertEquals(new NumNode(2.1), Parser.parsePostfix("+2.1"));
    }

    @Test
    public void testPostfixAddition(){
        assertEquals(new BinopNode('+', new NumNode(2), new NumNode(9)), Parser.parsePostfix("2 9 +"));
    }

    @Test
    public void testPostfixSubstraction(){
        assertEquals(new BinopNode('-', new NumNode(7), new NumNode(3)), Parser.parsePostfix("7 3 -"));
    }

    @Test
    public void testPostfixOperatorError(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("1 6 + *"));
    }

    @Test
    public void testPostfixOperandError(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("8 8"));
    }

    // Infix Testing


}