public record BinopNode(char operator, AST left, AST right) implements AST{
    //calculates the value of each side and executes the respective operator that the
    //expression has
    public double eval(){
        double leftV = left.eval();
        double rightV = right.eval();

        if(operator == '+'){
            return leftV + rightV;
        }
        else if(operator == '-'){
            return leftV - rightV;
        }
        else if(operator == '*'){
            return leftV * rightV;
        }
        else if(operator == '/'){
            return leftV / rightV;
        }
        else if(operator == '^'){
            return Math.pow(leftV,rightV);
        }
        else{
            throw new IllegalArgumentException("That is not an accepted operator.");
        }


    }
}


