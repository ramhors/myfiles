package com.revature.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class GenericTester {

	public static void main(String[] args) {
		
		List<Integer> integerList = new ArrayList<Integer>();
		
		integerList.add(Integer.valueOf(10));
		integerList.add(Integer.valueOf(11));
		integerList.add(Integer.valueOf(67));
		integerList.add(Integer.valueOf(324));
		
		List<String>stringList = new ArrayList<String>();
		List<String>myFriends = new ArrayList<String>();
		
		
		stringList.add("Holla mi amigo");
		stringList.add("Bonjour mon ami");
		stringList.add("Manahoana");
		
		myFriends.add("Patrick");
		myFriends.add("Leon");
		myFriends.add("Malica");
		myFriends.add("Korri");		
		
		System.out.printf("Integer Value :%d\n", integerList.get(0));
		System.out.printf("String  Value :%s\n", stringList.get(0) + "\n");
		
		for(Integer data: integerList) {
			System.out.printf("Integer Value :%d\n", data);
		}
		System.out.print("\nInteger List: " );
		//Another way to print the list out
		for(Integer data: integerList) {
							
			System.out.print(data + " ");
		}
		
	}

}
