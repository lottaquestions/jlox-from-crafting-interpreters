package com.craftinginterpreters.lox;


import java.util.Stack;

public class RPNPrinter implements Expr.Visitor<String>{
    String print (Expr expr){
        return expr.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr){
        return expr.value.toString() + " " + expr.name.lexeme.toString() + "=";
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return RPNify(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        Stack<String> argsStack = new Stack<>();

        for (Expr arg : expr.arguments){
            argsStack.push(arg.toString());
        }
        String args="";
        while (!argsStack.isEmpty()){
            args = args + " " + argsStack.pop();
        }
        return args + expr.callee.toString();
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        return null;
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return RPNify(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {
        return null;
    }

    @Override
    public String visitSuperExpr(Expr.Super expr) {
        return null;
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return null;
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
