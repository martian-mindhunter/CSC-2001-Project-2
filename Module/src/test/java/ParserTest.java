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
    @Test
    public void testInfixWhole(){
        assertEquals(new NumNode(5), Parser.parsePostfix("5"));
    }

    @Test
    public void testInfixNegativeDouble(){
        assertEquals(new NumNode(-9.7), Parser.parseInfix("-9.7"));
    }

    @Test
    public void testInfixPositiveDouble(){
        assertEquals(new NumNode(2.1), Parser.parseInfix("+2.1"));
    }

    @Test
    public void testInfixAddition(){
        assertEquals(new BinopNode('+', new NumNode(2), new NumNode(9)), Parser.parseInfix("2 + 9"));
        assertEquals(new BinopNode('+', new NumNode(2), new NumNode(9)), Parser.parseInfix("2 9 +"));
    }

    @Test
    public void testInfixSubtraction(){
        assertEquals(new BinopNode('-', new NumNode(7), new NumNode(3)), Parser.parseInfix("7 3 -"));
        assertEquals(new BinopNode('-', new NumNode(7), new NumNode(3)), Parser.parseInfix("7 - 3"));
    }

    @Test
    public void testInfixMultiplication(){
        assertEquals(new BinopNode('*', new NumNode(2), new NumNode(3)), Parser.parseInfix("2 * 3"));
    }

    @Test
    public void testInfixDivision(){
        assertEquals(new BinopNode('/', new NumNode(6), new NumNode(3)), Parser.parseInfix("6 / 3"));
    }

    @Test
    public void testInfixExponent(){
        assertEquals(new BinopNode('^', new NumNode(6), new NumNode(3)), Parser.parseInfix("6 ^ 3"));
    }

    @Test
    public void testInfixPrecedenceHighSecond() {
        // 1 + 2 * 3  ->  1 + (2 * 3)
        assertEquals(new BinopNode('+', new NumNode(1), new BinopNode('*', new NumNode(2), new NumNode(3))), Parser.parseInfix("1 + 2 * 3"));
    }

    @Test
    public void testInfixPrecedenceHighFirst() {
        // 2 * 3 + 4  ->  (2 * 3) + 4
        assertEquals(new BinopNode('+', new BinopNode('*', new NumNode(2), new NumNode(3)), new NumNode(4)), Parser.parseInfix("2 * 3 + 4"));
    }

    @Test
    public void testInfixExponentBeatsMultiply() {
        // 2 * 4 ^ 3  ->  2 * (4 ^ 3)
        assertEquals(new BinopNode('*', new NumNode(2), new BinopNode('^', new NumNode(4), new NumNode(3))), Parser.parseInfix("2 * 4 ^ 3"));
    }

    // Helper method for Infix Error Testing
    public static void assertInfixError(String input, String message){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Parser.parseInfix(input));
        assertEquals(message, e.getMessage());
    }

    @Test
    public void testInfixEmptyInput() {
        assertInfixError("", "empty input");
        assertInfixError("   ", "empty input");
    }

    @Test
    public void testInfixInvalidToken() {
        assertInfixError("2 + a", "invalid token");
        assertInfixError("1.2.3 + 1", "invalid token");
    }

    @Test
    public void testInfixInsufficientOperands() {
        assertInfixError("2 +", "insufficient operands");
        assertInfixError("+ 2", "insufficient operands");
        assertInfixError("( )", "insufficient operands");
    }

    @Test
    public void testInfixTooManyOperands() {
        assertInfixError("2 + 3 4", "too many operands");
        assertInfixError("8 8", "too many operands");
    }

    @Test
    public void testInfixMismatchedOpenParen() {
        assertInfixError("( 2 + 3", "mismatched open paren");
        assertInfixError("( ( 2 + 3 )", "mismatched open paren");
    }

    @Test
    public void testInfixMismatchedCloseParen() {
        assertInfixError("2 + 3 )", "mismatched close paren");
        assertInfixError(")", "mismatched close paren");
    }

}