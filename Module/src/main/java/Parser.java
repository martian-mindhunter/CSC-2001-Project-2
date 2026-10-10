//import java.util.Arrays;

public class Parser {

    // Takes a String input & returns an AST
    public static AST parsePostfix(String input) {
        if(input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        ArrayStack<AST> stack = ArrayStack.emptyStack();

        String[] piece = input.split(" ");
//        System.out.println(Arrays.toString(piece));

        for(int i = 0; i < piece.length; i++){
            String token = piece[i];

            if(isOperator(token)){
//                if (stack.size() < 2) {
//                    throw new IllegalArgumentException("insufficient operands");
//                }
//
//                AST right = stack.pop();
//                AST left = stack.pop();
//
//                BinopNode node = new BinopNode(token.charAt(0), left, right);
//                stack.push(node);
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
        boolean check = isNumber(token);
        if (check) {
            double num = Double.parseDouble(token);
            stack.push(new NumNode(num));
        } else {
            throw new IllegalArgumentException("The token is invalid");
        }
    }

    // Takes a String input & processes in left to right order w/ parantheses in mind
    public static AST parseInfix(String input){
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("The input is empty");
        }

        ArrayStack<AST> numStack = ArrayStack.emptyStack();
        ArrayStack<AST> opStack = ArrayStack.emptyStack();
        ArrayStack<AST> stack = ArrayStack.emptyStack();

        String[] tokens = input.split(" ");

        for( String token : tokens ){
            if(isNumber(token)){
                pushNumber(token, numStack);
            } else if(token.equals("(")){
                pushOperator(token.charAt(0), opStack);
            } else if(token.equals(")")){

                if(!opStack.isEmpty()) {
                    while(!(opStack.peek().equals("("))){
                        if(isNumber(token)){
                            pushNumber(token, numStack);
                        } else if(isOperator(token)){
                            pushOperator(token.charAt(0), opStack);
                        }
                        opStack.pop();
                    }
                } else {
                    throw new IllegalArgumentException("mismatched close paren");
                }
                opStack.pop();

            } else if(isOperator(token)){
                pushOperator(token.charAt(0), opStack);
            } else {
                throw new IllegalArgumentException("invalid token");
            }
            stack.push();
        }



    }
}