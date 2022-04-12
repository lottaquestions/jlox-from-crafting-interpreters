package com.craftinginterpreters.lox;


public class RPNPrinter implements Expr.Visitor<String>{
    String print (Expr expr){
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return RPNify(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return RPNify("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return RPNify(expr.operator.lexeme, expr.right);
    }

    @Override
    public String  visitVariableExpr(Expr.Variable expr){ return expr.name.toString();}


        private String RPNify(String name, Expr... exprs){
        StringBuilder builder = new StringBuilder();

        for (Expr expr : exprs){
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(name);
        return builder.toString();
    }
}
