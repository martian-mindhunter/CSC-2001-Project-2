public class Parser {
    public static AST parsePostFix(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("The input is empty");
        }

        ArrayStack<AST> stack = ArrayStack.emptyStack();

        String[] piece = input.trim().split(" ");

        for (int i = 0; i < piece.length; i += 1) {
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
                if (check == true) {
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
            return stack.pop();
        }

    public static boolean isNumber(String token){
        boolean digit = false;
        boolean dec = false;

        for(int i = 0; i < token.length(); i += 1){
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
}
