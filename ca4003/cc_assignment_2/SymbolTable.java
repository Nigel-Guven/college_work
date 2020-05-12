//******************************************
// 
/** FILE METADATA */
// 
// File: 		SymbolTable.java
//
// Author: 		NIGEL GUVEN 
//
// Description: Symbol Table for MyCCParser
//
// Date: 		14/12/2019
//
// Due Date: 	16/12/2019
//
//******************************************

import java.io.*;
import java.util.*;

public class SymbolTable extends Object 
{
	
/** Variables */

    Hashtable<String, LinkedList<String>> symboltable;
	Hashtable<String, String> attributes;
    Hashtable<String, String> types;
    
/** Instantiator */	

    SymbolTable() 
	{
        symboltable = new Hashtable<>();
		attributes = new Hashtable<>();
        types = new Hashtable<>();
        symboltable.put("global", new LinkedList<>());
    }

/** Put method */

    public void put(String id, String type, String attribute, String scope) 
	{
        LinkedList<String> temp = symboltable.get(scope);
        if (temp == null) 
		{ 
            temp = new LinkedList<>();
            temp.add(id);
            symboltable.put(scope, temp);
        }
        else 
		{
            temp.addFirst(id);   
        }
        types.put(id + scope, type);
        attributes.put(id + scope, attribute);
    }

/** Finder Node variables */

	public String typeFinder(String id, String scope) 
	{
        return types.get(id + scope);
    }

    public String attributeFinder(String id, String scope) 
	{
        return attributes.get(id + scope);
    }

/** Scope Check */

    public boolean isInScope(String id, String scope) 
	{
        LinkedList<String> temp = symboltable.get(scope);
        if (temp == null)
		{			
			return false; 
		}
        if (temp.contains(id)) 
		{	
			return true;
		}
        return false;
    }
	
/** Print Table */	
	
	public void print() 
	{
        String scope;
        Enumeration enumeration = symboltable.keys();
		System.out.println(" ID\t| Type \t\t| Attribute");
        while (enumeration.hasMoreElements()) 
		{
            scope = (String) enumeration.nextElement();
           
            LinkedList<String> temp = symboltable.get(scope);
            for (String id : temp) 
			{
				String attribute = attributes.get(id + scope);
				String type = types.get(id + scope);
                System.out.println(" "+id + "\t  " +type + "\t  " + attribute);			
            }
        }
    }
}

/**********************************************/