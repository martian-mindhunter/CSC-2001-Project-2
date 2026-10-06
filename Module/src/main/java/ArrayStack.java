class ArrayStack {

    AST rightBranch = new BinopNode('+', new NumNode(3), new NumNode(4));

    AST tree = new BinopNode('*', new NumNode(2), rightBranch);

}
