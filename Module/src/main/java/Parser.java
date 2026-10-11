//import java.util.Arrays;

public class Parser {

    // Takes a String input & returns an AST
    public static AST parsePostfix(String input) {
        if(input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        ArrayStack<AST> stack = ArrayStack.emptyStack();

        String[] piece = input.split(" ");

        for(int i = 0; i < piece.length; i++){
            String token = piece[i];

            if(isOperator(token)){
               pushOperator(token.charAt(0), stack);
            } else {
                boolean check = isNumber(token);
                if (check) {
                    double num = Double.parseDouble(token);
                    stack.push(new NumNode(num));
                } else {
                    throw new IllegalArgumentException("invalid token");
                }
            }
        }
        if (stack.size() > 1) {
            throw new IllegalArgumentException("too many operands");
        }
//        System.out.println(Arrays.toString(piece));
        return stack.pop();
    }

    public static boolean isOperator(String token){
        return (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"));
    }

    public static void pushOperator(char token, ArrayStack<AST> stack){
        if (stack.size() < 2) {
            throw new IllegalArgumentException("insufficient operands");
        }

        AST right = stack.pop();
        AST left = stack.pop();

        BinopNode node = new BinopNode(token, left, right);
        stack.push(node);
    }

    // Checks if a char of given String is a number or decimal point & returns boolean
    public static boolean isNumber(String token){
        boolean digit = false;
        boolean dec = false;

        for(int i = 0; i < token.length(); i++){
            char character = token.charAt(i);

            if(character >= '0' && character <= '9'){
                digit = true;
            } else if(character == '.' && !dec){
                dec = true;
            } else if(i == 0 && (character == '-' || character == '+')){

            }else{
                return false;
            }

        }
        return digit;
    }

    public static void pushNumber(String token, ArrayStack<AST> stack){
        double num = Double.parseDouble(token);
        stack.push(new NumNode(num));
    }

    // Takes a String input & processes in left to right order w/ parantheses in mind
    public static AST parseInfix(String input){
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        ArrayStack<AST> exprStack = ArrayStack.emptyStack();
        ArrayStack<String> opStack = ArrayStack.emptyStack();

        String[] tokens = input.split(" ");

        for( String token : tokens ){
            if(isNumber(token)){
                pushNumber(token, exprStack);
            } else if(token.equals("(")){

                opStack.push(token);

            } else if(token.equals(")")){

                while(!opStack.isEmpty() && !(opStack.peek().equals("("))){
                    pushOperator(opStack.pop().charAt(0), exprStack);
                }
                if(opStack.isEmpty()){
                    throw new IllegalArgumentException("mismatched close paren");
                }
                opStack.pop();

            } else if(isOperator(token)){
                while(!opStack.isEmpty() && applyFirst(opStack.peek(), token)){
                    pushOperator(opStack.pop().charAt(0), exprStack);
                }
                opStack.push(token);
            } else {
                throw new IllegalArgumentException("invalid token");
            }
        }

//        return exprStack.pop();
        while( !opStack.isEmpty() ){
            String op = opStack.pop();
            if(op.equals("(")) {
                throw new IllegalArgumentException("mismatched open paren");
            }
            pushOperator(op.charAt(0), exprStack);
        }
        if (exprStack.isEmpty()) {
            throw new IllegalArgumentException("insufficient operands");
        }
        AST result = exprStack.pop();
        if (!exprStack.isEmpty()) {
            throw new IllegalArgumentException("too many operands");
        } else {
            return result;
        }

    }

    // Helper method to find which operator to apply
    public static boolean applyFirst(String top, String currentToken){
        return (precedence(top) > precedence(currentToken)) || (precedence(top) == precedence(currentToken) && !currentToken.equals("^"));
    }

    // Helper method to determine operator precedence
    public static int precedence(String op){
        if(op.equals("^")){
            return 3;
        } else if(op.equals("*") || op.equals("/")){
            return 2;
        } else if(op.equals("+") || op.equals("-")){
            return 1;
        } else {
            return 0;
        }
    }

}