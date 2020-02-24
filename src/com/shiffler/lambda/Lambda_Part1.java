/*
This class contains examples of using streams with lambda expressions along with samples of various operations
that can be performed on streams..
 */

package com.shiffler.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda_Part1 {


    public static void main(String[] args) {

        //Create a List of Integers for sample methods
        List<Integer> numbers = List.of(45,78,34,12,43,76,89,76,45,34,3,45,7,13,2,43,78);

        //Create a List of Strings
        List<String> names = List.of("James", "Guido", "Yukihiro", "Anders",
                "Dennis", "Ken", "Larry", "Bjarne", "John", "Brendan" );

        System.out.println("***Basic Print Numbers***");
        printAllNumbersMethRef(numbers);

        System.out.println("***Adding a Filter***");
        printAllEvenNumbers(numbers);

        System.out.println("***Using distinct to get rid of duplicates***");
        printDistinctEvenNumbers(numbers);

        System.out.println("***Using sorted to print numbers in order***");
        printSortedDistinctEvenNumbers(numbers);

        System.out.println("***Using sorted with a Comparator to print numbers in order***");
        printSortedCompareDistinctEvenNumbers(numbers);

        System.out.println("***Adding a Map to Square the numbers***");
        printSquaredEvenNumbers(numbers);

        System.out.println("***We created a new List and then printed it***");
        createEvenSquaredList(numbers);

        System.out.println("***Adding reduce to sum the squares***");
        printSumEvenSquares(numbers);

        System.out.println("***Using Reduce to find the smallest number***");
        printSmallestNumber(numbers);

        System.out.println("***Print Strings with a matching substring***");
        printStringsContaining(names,"en" );

        System.out.println("***Print Strings with a minimum length***");
        printStringsWithLengthAtLeast(names,6 );

        System.out.println("***Print Strings with a minimum length and a custom sort***");
        printStringsWithLengthAtLeastCustomSorted(names,2);

        System.out.println("***Printing length of Strings with a minimum length***");
        printStringsWithLengthAtLeastPrintLength(names,6 );

    }

    /**
     * A basic demonstration of printing a list of numbers using streams
     * The List is turned into a stream and then a method reference is used
     * to print out each number
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printAllNumbersMethRef(List<Integer> numbers){

        //The forEach is a terminal operation that returns void

        numbers.stream().forEach(System.out::println);

    }

    /**
     * Adding a filter with a Lambda expression so that only even
     * numbers will be printed.
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printAllEvenNumbers(List<Integer> numbers){

        //Filter is an intermediate operation that returns a stream.

        numbers.stream().filter(x -> x % 2 == 0).forEach(System.out::println);

    }

    /**
     * Adding distinct method so that no duplicate numbers will be
     * printed.
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printDistinctEvenNumbers(List<Integer> numbers){

        numbers.stream().distinct().filter(x -> x % 2 == 0).forEach(System.out::println);

    }

    /**
     * Adding sorted method so that numbers are printed in sorted order.     *
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printSortedDistinctEvenNumbers(List<Integer> numbers){

        numbers.stream().distinct().filter(x -> x % 2 == 0).sorted().forEach(System.out::println);

    }

    /**
     * Using a Comparator to customize the way sorting is done.
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printSortedCompareDistinctEvenNumbers(List<Integer> numbers){

        numbers.stream().distinct().filter(x -> x % 2 == 0)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

    }


    /**
     * Adding a map with a Lambda expression so that all of our even numbers
     * will be squared
     * @param numbers - The List of numbers that we'll be printing out
     */
    private static void printSquaredEvenNumbers(List<Integer> numbers){

        numbers.stream().filter(x -> x % 2 == 0).map(x -> x*x).forEach(System.out::println);

    }

    /**
     * Adds in reduce functionality so we can sum all of the even squares
     * @param numbers - The List of numbers
     */
    private static void printSumEvenSquares(List<Integer> numbers){

        //The lambda function for reduce takes two parameters so it requires them to be surrounded by ()

       int sum = numbers.stream().filter(x -> x % 2 == 0).map(x -> x*x).reduce( 0,(x,y) -> x + y );
        System.out.format("The sum of even squares is %d\n\n", sum);
    }

    /**
     * Creates a new List of squares based on the List that is passed in and then the List
     * is printed for show.
     * @param numbers
     */
    private static void createEvenSquaredList(List<Integer> numbers){

        List<Integer> newNum = numbers.stream().filter(x -> x % 2 == 0).map(x -> x*x).collect(Collectors.toList());
        newNum.stream().forEach(System.out::println);

    }


    /**
     * Prints the smallest number in a list using the map function
     * @param numbers - The List of numbers
     */
    private static void printSmallestNumber(List<Integer> numbers){

        int smallest = numbers.stream().reduce(Integer.MAX_VALUE,(x,y) -> x<y ? x: y);

        System.out.format("The smallest number in the list is %d\n\n", smallest);
    }


    /**
     * Takes in a List of Strings and prints out Strings containing a certain substring
     * @param stringList - A List containing various Strings
     * @param contains - The parameter we're matching on
     */
    private static void printStringsContaining(List<String> stringList, String contains){

        stringList.stream().filter(s -> s.contains(contains)).forEach(System.out::println);

    }

    /**
     * Takes in a List of Strings and prints out Strings containing a certain substring
     * @param stringList - A List containing various Strings
     * @param contains - The parameter we're matching on
     */
    private static void printNamesContaining(List<String> stringList, String contains){

        stringList.stream().filter(s -> s.contains(contains)).forEach(System.out::println);

    }

    /**
     * Takes in a List of Strings and prints out Strings that are at least a certain length
     * @param stringList - A List containing various Strings
     * @param length - The minimum length required to print a string
     */
    private static void printStringsWithLengthAtLeast(List<String> stringList, int length){

        stringList.stream().filter(s -> s.length() >= length).forEach(System.out::println);

    }

    /**
     * Adding in a Comparator that sorts the Strings by length
     * @param stringList - A List containing various Strings
     * @param length - The minimum length required to print a string
     */
    private static void printStringsWithLengthAtLeastCustomSorted(List<String> stringList, int length){

        stringList.stream().filter(s -> s.length() >= length)
                .sorted(Comparator.comparing(str -> str.length()))
                .forEach(System.out::println);

    }

    /**
     * Takes in a List of Strings and prints out Strings that are at least a certain length and then
     * prints the length of those strings. Adding in map functionality
     * @param stringList - A List containing various Strings
     * @param length - The minimum length required to print a string
     */
    private static void printStringsWithLengthAtLeastPrintLength(List<String> stringList, int length){

        stringList.stream().filter(s -> s.length() >= length).map(str -> str.length()).forEach(System.out::println);

    }

}
