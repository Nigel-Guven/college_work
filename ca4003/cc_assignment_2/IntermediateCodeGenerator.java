//******************************************
// 
/** FILE METADATA */
// 
// File: 		IntermediateCodeGenerator.java
//
// Author: 		NIGEL GUVEN 
//
// Description: Generates 3 Address Code from Parsed file
//
// Date: 		14/12/2019
//
// Due Date: 	16/12/2019
//
//******************************************

import java.io.*;
import java.util.*;

public class IntermediateCodeGenerator implements MyCCParserVisitor 
{ 

/** Variables */

    static int labelCounter = 1;
    static int tempCount = 1;
	public String three_addr_code = "";
    static boolean requiresLabel = false;

/** Get Instructions and Optional Labels */

    void getInstruction(String instruction) 
	{
        if(requiresLabel) 
		{
           
			three_addr_code += instruction + "\n";
            requiresLabel = false;
        }
        else
		{
			three_addr_code += instruction + "\n";
		}
    }
	
	void getLabel(String label) 
	{
        
		three_addr_code += label + ":\n";
        requiresLabel = true;
        labelCounter++;
    }

/** Visit Helper Function */

	private Object visitHelper(SimpleNode node, Object data) 
	{
        String child1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String child2 = (String) node.jjtGetChild(1).jjtAccept(this, data);

        String temp = "t" + tempCount;
        getInstruction(temp + " = " + child1 + node.value + child2);
        return temp;
    } 

/** Visit Methods */

    public Object visit(SimpleNode node, Object data) 
	{
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(ASTProgram node, Object data) 
	{
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTVariable_Decl node, Object data) 
	{
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTTag node, Object data) 
	{
        return node.value;
    }
	
    public Object visit(ASTConstant_Decl node, Object data) 
	{
        String id = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String item = (String) node.jjtGetChild(2).jjtAccept(this, data);

        getInstruction(id + " = " + item);

        return null;
    }

    public Object visit(ASTConstant_Assignment node, Object data) 
	{
        return node.value + ((String) node.jjtGetChild(0).jjtAccept(this, data));
    }

    public Object visit(ASTFunction node, Object data) 
	{
        SimpleNode id = (SimpleNode) node.jjtGetChild(1);
        getLabel((String) id.value);
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTFunction_Return node, Object data) 
	{
        String in = "ret " + ((String) node.jjtGetChild(0).jjtAccept(this, data));
        getInstruction(in);
        return (Object) in;
    }

    public Object visit(ASTType node, Object data) 
	{
        return node.value;
    }

    public Object visit(ASTParameter_List node, Object data) 
	{
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTNemp_Parameter_List node, Object data) 
	{
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTMain node, Object data) 
	{
        getLabel("main");
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTStatement node, Object data) 
	{
        String stm = (String) node.value;
        String beginLabel;
        String condition;
        switch (stm) 
		{
            case "while":
                beginLabel = "Seq" + labelCounter;
                getLabel(beginLabel);
                condition = (String) node.jjtGetChild(0).jjtAccept(this, data);
                getInstruction("if False \n" + condition + " jmp " + beginLabel);

                node.jjtGetChild(1).jjtAccept(this, data);

                return null;

            case "if": 
                beginLabel = "Seq" + labelCounter;
                String elseLabel = "Seq" + (labelCounter + 1);
                getLabel(beginLabel);
                condition = (String) node.jjtGetChild(0).jjtAccept(this, data);
                getInstruction("if False \n" + condition + " goto " + elseLabel);

                node.jjtGetChild(1).jjtAccept(this, data);
                getLabel(elseLabel);

                return null;

            default: return null;
        }
    }

    public Object visit(ASTAssignment node, Object data) 
	{
        String child1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String child2 = (String) node.jjtGetChild(1).jjtAccept(this, data);

        String in = child1 + node.value + child2;
        getInstruction(in);

        return (Object) in;
    }
	
    public Object visit(ASTNumber node, Object data) 
	{
        return node.value;
    }

    public Object visit(ASTBoolean node, Object data) 
	{
        return node.value;
    }
	
    public Object visit(ASTAddition node, Object data) 
	{
        return visitHelper(node, data);
    }

    public Object visit(ASTSubtraction node, Object data) 
	{
        return visitHelper(node, data);
    }

    public Object visit(ASTEqualOperator node, Object data) 
	{

        return visitHelper(node, data);
    }

    public Object visit(ASTNotEqualOperator node, Object data) 
	{

        return visitHelper(node, data);
    }


    public Object visit(ASTGreaterOperator node, Object data) 
	{

        return visitHelper(node, data);
    }

    public Object visit(ASTGreaterEqualOperator node, Object data) 
	{

        return visitHelper(node, data);
    }
	
	public Object visit(ASTLessOperator node, Object data) 
	{

        return visitHelper(node, data);
    }

    public Object visit(ASTLessEqualOperator node, Object data) 
	{

        return visitHelper(node, data);
    }
	
    public Object visit(ASTOrOp node, Object data) 
	{

        return visitHelper(node, data);
    }

    public Object visit(ASTAndOp node, Object data) 
	{

        return visitHelper(node, data);
    }

    public Object visit(ASTArgument_List node, Object data) 
	{
        for(int index = 0; index < node.jjtGetNumChildren(); index++) 
		{
            getInstruction("Parameter " + ((String) node.jjtGetChild(index).jjtAccept(this, data)));
        }
        return 1;
    }
}

/**********************************************/