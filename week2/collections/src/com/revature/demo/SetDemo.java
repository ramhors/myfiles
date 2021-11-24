package com.revature.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class SetDemo {

	public static void demo() {

		List<String> firstList = new ArrayList<String>();

		List<String> secondList = new ArrayList<String>();

		firstList.add("Mango");
		firstList.add("Peach");
		firstList.add("Pears");
		firstList.add("Banana");
		firstList.add("Banana");
		firstList.add("Avocado");
		firstList.add("Pine Apple");
		firstList.add("Litchis");
		firstList.add("Coconut");

		System.out.println("List of string: " + firstList);
		System.out.println("Number of elements: " + firstList.size());

		/********************************
		 * Removing the first occurrence of banana
		 ********************************/
		firstList.remove(new String("Banana"));
		System.out.println("Number of elements: " + firstList.size());

		/********************************
		 * Traversing a list
		 *******************************/
		for (int i = 0; i < firstList.size(); i++) {
			System.out.println("Index: " + i + " " + firstList.get(i));
		}

		/********************************************
		 * Using iterator to remove occurrence of an element in string collection
		 **********************************************/
		Iterator<String> iter = firstList.iterator();
		while (iter.hasNext()) { // While the iterator still has elements to continue iterating over.
			// moving the cursor to the next element with inter.ext();
			String nextElement = iter.next();
			if (nextElement.equals("Banana")) {
				iter.remove();
			} else if (nextElement.equals("Mango")) {
				iter.remove();

			}
		}
		System.out.println("Removing all banana and Mango: " + firstList);

		/**********************************************************************
		 * Collection an interface that is part of the collection API hierarchy
		 * Collection: a class that NOTHNIG else inherit.It is simply class that we can
		 * use Utility class: A class that is not used as a blueprint for object,but
		 * instead contains static methods that might be useful to use
		 ******************************************************************************/
		Collections.reverse(firstList);
		System.out.println("New list: " + firstList);

		/***********************************
		 * Sorting the list
		 **************************************/
		Collections.sort(firstList);
		System.out.println("Sorted result: " + firstList);

		/*********************************************
		 * Shuffling the list
		 ******************************************/
		Collections.shuffle(firstList);
		System.out.println("Shuffling result: " + firstList);
		
		/*********************************************
		 * Rotating an elements in a list by distance + number
		 *********************************************/
		Collections.rotate(firstList,2);
		System.out.println("Rotation result: " + firstList);
		
		/*********************************************
		 * Using the replaceAll function using Lambda
		 *********************************************/
		firstList.replaceAll(e -> e.toUpperCase());
		System.out.println("Make everything to uppercase " + firstList);
		
		/*****************************************************
		 * Applying a lambda in a for each to print value 
		 * in a collection		 * 
		 ****************************************************/
		firstList.forEach( (n) -> System.out.println("\nLambda result: " + n));
		
		List<String> mySecondList = new ArrayList<String>();
		mySecondList.add("red");
		mySecondList.add("yellow");
		
		/*****************************************************
		 * Overriding or replacing the value in the list
		 * using the method fill()
		 *****************************************************/
		 Collections.fill(mySecondList,"Green");
		 System.out.println("Replacing :" + mySecondList);
	}
}
