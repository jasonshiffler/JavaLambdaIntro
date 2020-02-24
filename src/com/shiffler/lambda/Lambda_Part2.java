/*
This class has samples around using functional interfaces and assigning lambda expressions to variables
 */

package com.shiffler.lambda;


import java.util.List;
import java.util.Random;
import java.util.function.*;


public class Lambda_Part2 {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(34, 1, 72, 93, 90, 82, 79);

        /*
        Demonstrating how different Lambda functions can be passed in as a variable. The functions have different
        types depending on what type of data is passed in and what type of data is returned.
         */

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0; //Predicates return a boolean
        Function<Integer, Integer> squareFunction = x -> x * x; //Functions can ingest and output various types
        Consumer<Integer> sysoutConsumer = System.out::println; //Consumers have a void return

        numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(sysoutConsumer);


    /*
    Demonstration of how Lambda's are working using Anonymous Classes. Lambda expressions are objects.
    Functional Interfaces such as Predicated can only have one method.
     */

        Predicate<Integer> predDemo = new Predicate<Integer>() {

            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        };


    /*
    Another sample of Functional interfaces being used in different formats.
     */

        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;

        BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            }
        };


        Supplier<Integer> rand = () -> {
            Random random = new Random();
            return random.nextInt(10000);
        };


        System.out.println("***Print out a random number using a Supplier***");
        System.out.println(rand.get());


        /*Sample of a BiFunction which takes two arguments*/
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;

        /*Sample of a BiPredicate which takes two arguments*/
        BiPredicate<Integer, Integer> biPredicate = (x, y) -> x > y;

        /*Sample of a BiConsumer which takes two arguments*/
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.format("The two numbers are %d and %d", x, y);

        System.out.println("***Running the BiConsumer***");
        biConsumer.accept(1,2);

    }
}
