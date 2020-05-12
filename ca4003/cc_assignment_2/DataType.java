//******************************************
// 
/** FILE METADATA */
// 
// File: 		DataType.java
//
// Author: 		NIGEL GUVEN 
//
// Description: Data Type Enumerator for MyCCParser
//
// Date: 		14/12/2019
//
// Due Date: 	16/12/2019
//
//******************************************

import java.io.*;
import java.util.*;

/** Data Types of Language */

public enum DataType 
{
	SimpleNode,
	Program,
	Variable_Decl,
	Constant_Decl,
	Assignment,
	Function,
	Function_Return,
	Parameter_List,
	Main,
	Statement,
	IfStatement,
	WhileStatement,
	Number,
	Boolean,
	Arithmetic_Operator,
	Logic_Operator,
	Argument_List,
	Unknown
}

/**********************************************/