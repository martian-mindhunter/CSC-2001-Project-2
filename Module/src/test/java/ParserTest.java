import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParsePostFix1(){
        assertEquals(new NumNode(5), Parser.parsePostFix("5"));
    }

    @Test
    public void testParsePostFix2(){
        assertEquals(new NumNode(-9.7), Parser.parsePostFix("-9.7"));
    }

    @Test
    public void testParsePostFix3(){
        assertEquals(new NumNode(2.1), Parser.parsePostFix("+2.1"));
    }

    @Test
    public void testParsePostFix4(){
        assertEquals(new BinopNode('+', new NumNode(2), new NumNode(9)), Parser.parsePostFix("2 9 +"));
    }

    @Test
    public void testParsePostFix5(){
        assertEquals(new BinopNode('-', new NumNode(7), new NumNode(3)), Parser.parsePostFix("7 3 -"));
    }

    @Test
    public void testParsePostFix6(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("1 6 + *"));
    }

    @Test
    public void ParsePostFix(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("8 8"));
    }


}
