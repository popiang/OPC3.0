package com.popiang.opc.utilities;

import org.springframework.stereotype.Component;

/*
 * this class handles:
 * 1. adding and removing underscores from event name
 * 2. lowering or uppering case of event name
 */

@Component
public class Concatenation 
{
	//this method adds underscore and lowers the case of event name
	public String underscore(String original)
	{
		char[] chars = original.toCharArray();
		char c;
		int beginIndex = 0;
		
		StringBuilder newString = new StringBuilder();
		
		for(int i = 0; i < chars.length; ++i)
		{
			c = chars[i];
			if(c == ' ')
			{
				newString.append(original.substring(beginIndex, i).toLowerCase());
				newString.append("_");
				beginIndex = i + 1;
			}
			else if(i == chars.length - 1)
			{
				newString.append(original.substring(beginIndex, i + 1).toLowerCase());
			}
		}
		
		return newString.toString();
	}
	
	//this method remove underscore and uppers the case of event name
	public String removeUnderscore(String original)
	{
		char[] chars = original.toCharArray();
		char c;
		int beginIndex = 0;
		
		StringBuilder newString = new StringBuilder();
		
		for(int i = 0; i < chars.length; ++i)
		{
			c = chars[i];
			if(c == '_')
			{
				newString.append(original.substring(beginIndex, i).toUpperCase());
				newString.append(" ");
				beginIndex = i + 1;
			}
			else if(i == chars.length - 1)
			{
				newString.append(original.substring(beginIndex, i + 1).toUpperCase());
			}
		}
		
		return newString.toString();
	}
}
