//import java.util.Arrays;

public class Parser {

    // Takes a String input & returns an AST
    public static AST parsePostfix(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("The input is empty");
        }

        ArrayStack<AST> stack = ArrayStack.emptyStack();

        String[] piece = input.split(" ");
//        System.out.println(Arrays.toString(piece));

        for(int i = 0; i < piece.length; i++){
            String token = piece[i];

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough symbols for the given equation");
                }

                AST right = stack.pop();
                AST left = stack.pop();

                BinopNode node = new BinopNode(token.charAt(0), left, right);
                stack.push(node);
            } else {
                boolean check = isNumber(token);
                if (check) {
                    double num = Double.parseDouble(token);
                    stack.push(new NumNode(num));
                } else {
                    throw new IllegalArgumentException("The token is invalid");
                }
            }
        }
        if (stack.size() > 1) {
            throw new IllegalArgumentException("There are more operators than needed");
        }
//        System.out.println(Arrays.toString(piece));
        return stack.pop();
    }

    // Checks if a char of given String is a number or decimal point & returns boolean
    public static boolean isNumber(String token){
        boolean digit = false;
        boolean dec = false;

        for(int i = 0; i < token.length(); i++){
            char character = token.charAt(i);

            if(character >= '0' && character <= '9'){
                digit = true;
            } else if(character == '.' && dec == false){
                dec = true;
            } else if(i == 0 && (character == '-' || character == '+')){

            }else{
                return false;
            }

        }
        return digit;
    }

    // Takes a String input & processes in left to right order w/ parantheses in mind
    public static AST parseInfix(String input){
        return null; // placeholder for commit
    }
}