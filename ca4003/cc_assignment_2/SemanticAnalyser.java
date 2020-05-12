//******************************************
// 
/** FILE METADATA */
// 
// File: 		Semantic Analyser.java
//
// Author: 		NIGEL GUVEN 
//
// Description: Semantic Analyser for parser
//
// Date: 		14/12/2019
//
// Due Date: 	16/12/2019
//
//******************************************

import java.io.*;
import java.util.*;

public class SemanticAnalyser implements MyCCParserVisitor 
{

/** Variables */

    static String scope = "global";
	static SymbolTable symboltable;
	
	String error_type_assignment = (" A value was assigned the incorrect type.\n");
	String error_non_numeric_arithmetic_operation = (" Detected non-numeric values in arithmetic operation.");
	String error_type_comparison = (" Detected comparison between values of different types.");
	String error_non_boolean_logical_operation = (" Detected non boolean types in logical operation.");
	public static boolean semanticCheckFlag = true;

/** Get Operator Type Methods */

    private DataType getArithmetic_OperatorType(SimpleNode node, Object data) 
	{
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);

        if((child1DataType == DataType.Number) && (child2DataType == DataType.Number))
		{
			return DataType.Number;
		}
        semanticCheckFlag = false;
        System.out.println(error_non_numeric_arithmetic_operation + node); 
        return DataType.Unknown;
    }
	
	private DataType getEqual_OperatorType(SimpleNode node, Object data) 
	{
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);

        if(child1DataType == child2DataType)
		{
			return child1DataType;
		}
        semanticCheckFlag = false;
        System.out.println(error_type_comparison);
        return DataType.Unknown;
    }

    private DataType getComp_OperatorType(SimpleNode node, Object data) 
	{
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);

        if((child1DataType == DataType.Number) && (child2DataType == DataType.Number))
		{
			return DataType.Boolean;
		}
        semanticCheckFlag = false;
        System.out.println(error_non_numeric_arithmetic_operation + node);
        return DataType.Unknown;
    }
	
	private DataType getLogic_OperatorType(SimpleNode node, Object data) 
	{
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);

        if((child1DataType == DataType.Boolean) && (child2DataType == DataType.Boolean))
		{
			return DataType.Boolean;
		}
        semanticCheckFlag = false;
        System.out.println(error_non_boolean_logical_operation + node);
        return DataType.Unknown;
    }

/** Visit Methods */

    public Object visit(SimpleNode node, Object data) 
	{
        throw new RuntimeException();
    }

    public Object visit(ASTProgram node, Object data) 
	{
        symboltable = (SymbolTable) data;
        node.childrenAccept(this, data);
   
        System.out.println();
        if(semanticCheckFlag==true)
		{
			System.out.println(" Program has passed Semantic Analysis Successfully.\n");
		}
        return DataType.Program;
    }

    public Object visit(ASTVariable_Decl node, Object data) 
	{
        node.childrenAccept(this, data);
        return DataType.Variable_Decl;
    }
	
    public Object visit(ASTTag node, Object data) 
	{
        
        SimpleNode parent = (SimpleNode) node.jjtGetParent();
        String type = parent.toString();
        
        if(type != "Variable_Decl" && type != "Constant_Decl" && type != "Function") 
		{
            String item = (String) node.jjtGetValue();
            boolean scopeCheck = symboltable.isInScope(item, scope);
            if(scopeCheck) 
			{ 
                String datatype = symboltable.typeFinder(item, scope);
                switch (datatype) 
				{
                    case "integer": return DataType.Number;
                    case "boolean": return DataType.Boolean;
                    default: return DataType.Unknown;
                }
            } 
			else 
			{ 
                boolean inGlobalScope = symboltable.isInScope(item, "global");
                if(inGlobalScope) 
				{
                    String datatype = symboltable.typeFinder(item, "global");
                    switch (datatype) 
					{
                        case "integer": return DataType.Number;
                        case "boolean": return DataType.Boolean;
                        default: return DataType.Unknown;
                    }
                }
            }
        }

        return DataType.Unknown;
    }
	
    public Object visit(ASTConstant_Decl node, Object data) 
	{
        node.childrenAccept(this, data);
        SimpleNode simplenode = (SimpleNode) node.jjtGetChild(0);
        DataType child1 = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        DataType child2 = (DataType) node.jjtGetChild(2).jjtAccept(this, data);

        if(child1 != child2) 
		{
            semanticCheckFlag = false;
            System.out.println(error_type_assignment + simplenode.jjtGetValue());
        }

        return DataType.Constant_Decl;
    }

    public Object visit(ASTConstant_Assignment node, Object data) 
	{
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        return child1DataType;
    }

    public Object visit(ASTFunction node, Object data) 
	{
        SimpleNode id = (SimpleNode) node.jjtGetChild(1);
        scope = (String) id.value;
        node.childrenAccept(this, data);
        return DataType.Function;
    }

    public Object visit(ASTFunction_Return node, Object data) 
	{
        node.childrenAccept(this, data);
        scope = "global";
        return DataType.Unknown; 
    }

    public Object visit(ASTType node, Object data) 
	{
        String item = (String) node.value;
        if(item.equals("boolean"))
		{
			return DataType.Boolean;
		}
        if(item.equals("integer"))
		{
			return DataType.Number;
		}
        return DataType.Unknown;
    }
    
    public Object visit(ASTParameter_List node, Object data) 
	{
        node.childrenAccept(this, data);
        return DataType.Parameter_List;
    }

    public Object visit(ASTNemp_Parameter_List node, Object data) 
	{
        node.childrenAccept(this, data);
        return DataType.Parameter_List;
    }

    public Object visit(ASTMain node, Object data) 
	{
        scope = "main";
        node.childrenAccept(this, data);

        scope = "global";
        return DataType.Main;
    }

    public Object visit(ASTStatement node, Object data) 
	{
        node.childrenAccept(this, data);
        return DataType.Statement;
    }

    public Object visit(ASTAssignment node, Object data) 
	{
        SimpleNode child1 = (SimpleNode) node.jjtGetChild(0);
        SimpleNode child2 = (SimpleNode) node.jjtGetChild(1);
        DataType child1DataType = (DataType) child1.jjtAccept(this, data);
        DataType child2DataType = (DataType) child2.jjtAccept(this, data);

        if(child1DataType != child2DataType) 
		{
            semanticCheckFlag = false;
            System.out.println(error_type_assignment);
            
        }
        node.childrenAccept(this, data);
        return DataType.Assignment;
    }

    public Object visit(ASTNumber node, Object data) 
	{
        return DataType.Number;
    }

    public Object visit(ASTBoolean node, Object data) 
	{
        return DataType.Boolean;
    }

    public Object visit(ASTAddition node, Object data) 
	{
        DataType datatype = getArithmetic_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTSubtraction node, Object data) 
	{
        DataType datatype = getArithmetic_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTEqualOperator node, Object data) 
	{
        DataType datatype = getEqual_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTNotEqualOperator node, Object data) 
	{
        DataType datatype = getEqual_OperatorType(node, data);
        return datatype;
    }
	
	public Object visit(ASTGreaterOperator node, Object data) 
	{
        DataType datatype = getComp_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTGreaterEqualOperator node, Object data) 
	{
        DataType datatype = getComp_OperatorType(node, data);
        return datatype;
    }
	
    public Object visit(ASTLessOperator node, Object data) 
	{
        DataType datatype = getComp_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTLessEqualOperator node, Object data) 
	{
        DataType datatype = getComp_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTOrOp node, Object data) 
	{
        DataType datatype = getLogic_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTAndOp node, Object data) 
	{
        DataType datatype = getLogic_OperatorType(node, data);
        return datatype;
    }

    public Object visit(ASTArgument_List node, Object data) 
	{
        node.childrenAccept(this, data);
        return DataType.Argument_List;
    }
}

/**********************************************/