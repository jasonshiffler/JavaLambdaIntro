package com.shiffler.lambda;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Lambda_Part4 {


    public static void main(String[] args) {

        //Can add items directly to a stream
        System.out.println(Stream.of(5,10,20,25,30,35,40,50).reduce(0,(x,y) -> x + y));

        //Using primitive values to avoid the overhead of unboxing/boxing.
        int numberArray[] = {5,10,20,25,30,35,40,50};
        Arrays.stream(numberArray).reduce(0,(x,y) -> x + y);
        Arrays.stream(numberArray).sum();

        //Creating a Stream
        System.out.println(IntStream.range(1,10).sum());

        //We can create a stream based on an algorithm
        System.out.println(  IntStream.iterate(1,x -> x + 2).limit(50).peek(System.out::println).sum());

        //Print first 10 even numbers and output the sum
        System.out.println(  IntStream.iterate(2,x -> x + 2).limit(10).peek(System.out::println).sum());

        //Use the boxed() operation to convert the stream to Integers so a list can be created.
        IntStream.iterate(2,x -> x + 2).limit(10).boxed().collect(Collectors.toList());

        //Convert to BigInteger to allow for operations with large numbers
        System.out.println(
                LongStream.range(1,50).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE,BigInteger::multiply));


        //Take a List of Strings and join them into a single string with a specific delimiter.
        List<String> numbers = List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven");
        System.out.println(numbers.stream().collect(Collectors.joining(" ")));

        //Use flatmap to take multiple String Arrays and convert them into a String
        System.out.println(numbers.stream().map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList()));

        //Demonstration of using streams with and without parallel which is able to take advantage of multicore
        //processors

        long time = System.currentTimeMillis();
        System.out.println(LongStream.range(100,1000000000).parallel().sum());

        System.out.format ("It took %d ms for the LongStream sum to run\n", System.currentTimeMillis() -time);

        time = System.currentTimeMillis();

        System.out.println(LongStream.range(100,1000000000).parallel().sum());

        System.out.format("It took %d ms for the LongStream sum to run with parallelization\n",
                System.currentTimeMillis() - time);


        //replaceAll allows us to modify all of the elements in the list
        List<String> courses = List.of("Spring", "Spring Boot", "API", "AWS","Azure","Docker", "Kubernetes");
        List<String> modCourses = new ArrayList<>(courses);
        modCourses.replaceAll(str -> str.toUpperCase());

        //Remove item from the list if the length is less than 6.
        modCourses.removeIf(course -> course.length() <6 );

        //Files can be turned into streams as well. This example prints out the distinct words in the file.
        try {
            Files.lines(Paths.get("./test.txt")).map(str -> str.split(" "))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);

        }
        catch (Exception e){

        }






    }
}
