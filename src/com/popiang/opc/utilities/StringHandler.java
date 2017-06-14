package com.popiang.opc.utilities;

import org.springframework.stereotype.Component;

@Component
public class StringHandler 
{
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
}
