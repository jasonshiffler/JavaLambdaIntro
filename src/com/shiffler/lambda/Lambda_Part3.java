package com.shiffler.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course {

    private String name;
    private String category;
    private Integer numStudents;
    private Integer reviewScore;

    public Course(String name, String category, Integer numStudents, Integer reviewScore) {
        this.name = name;
        this.category = category;
        this.numStudents = numStudents;
        this.reviewScore = reviewScore;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", numStudents='" + numStudents + '\'' +
                ", reviewScore='" + reviewScore + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(Integer numStudents) {
        this.numStudents = numStudents;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }
}


public class Lambda_Part3 {

    public static void main(String[] args) {
      List<Course> courses =  List.of(
                new Course("Spring","Framework",20_000,98),
                new Course("Spring Boot","Framework",21_000,95),
                new Course("Beginning Java","Language",25_000,93),
                new Course("Java 11","Language",27_000,96),
                new Course("AWS","Cloud",27_000,89),
                new Course("PCF","Cloud",15_000,99)
        );

        System.out.println("***Do all courses have a score greater than 90?***");
        System.out.println(courses.stream().allMatch(course -> course.getReviewScore() >90));

        Predicate<Course> courseOver95Predicate = course -> course.getReviewScore() > 95;
        System.out.println("***Is it true than none of the courses have a score greater than 95?***");
        System.out.println(courses.stream().noneMatch(courseOver95Predicate));

        Predicate<Course> studentsOver10KPredicate = course -> course.getNumStudents() > 10_000;
        System.out.println("***Do any courses have more than 10,000 students?***");
        System.out.println(courses.stream().anyMatch(studentsOver10KPredicate));

        Comparator<Course> comparingByNumStudentsIncreasing = Comparator.comparing(Course::getNumStudents);
        System.out.println("***Printing out courses sorted by number of students in increasing order***");
        courses.stream().sorted(comparingByNumStudentsIncreasing).forEach(System.out::println);

        System.out.println("\n");
        Comparator<Course> comparingByNumStudentsDecreasing = Comparator.comparing(Course::getNumStudents).reversed();
        System.out.println("***Printing out courses sorted by number of students in decreasing order***");
        courses.stream().sorted(comparingByNumStudentsDecreasing).forEach(System.out::println);

        System.out.println("\n");
        Comparator<Course> comparingByNumStudentsDecreasingAndReviewScore = Comparator.comparing(Course::getNumStudents).
                thenComparing(Course::getReviewScore).reversed();
        System.out.println("***Printing out courses sorted by number of students in decreasing order and review Score***");
        courses.stream().sorted(comparingByNumStudentsDecreasingAndReviewScore).forEach(System.out::println);

        System.out.println("\n");
        System.out.println("***Limit courses to the top five results***");
        courses.stream().sorted(comparingByNumStudentsDecreasingAndReviewScore).limit(5L).forEach(System.out::println);

        System.out.println("\n");
        System.out.println("***Skip the first two results***");
        courses.stream().sorted(comparingByNumStudentsDecreasingAndReviewScore).skip(2L).forEach(System.out::println);

        System.out.println("\n");
        System.out.println("***Print results util we find one with a score less than 93***");
        courses.stream().takeWhile(course -> course.getReviewScore() > 92).forEach(System.out::println);

        System.out.println("\n");
        System.out.println("***Don't print results util we find one with a score less than 93***");
        courses.stream().dropWhile(course -> course.getReviewScore() > 92).forEach(System.out::println);


        System.out.println("\n");
        System.out.println("***Print the course with the largest number of students using max()***");
        System.out.println(courses.stream().max(Comparator.comparing(Course::getNumStudents)));

        System.out.println("\n");
        System.out.println("***Print the course with the least number of students using min()***");
        System.out.println(courses.stream().min(Comparator.comparing(Course::getNumStudents)));


        System.out.println("\n");
        System.out.println("***Print the number of courses with more than 21000 students***");
        System.out.println(courses.stream().filter(course -> course.getNumStudents() > 21_000).count());


        System.out.println("\n");
        System.out.println("***Print the courses grouped by category***");
        System.out.println(courses.stream().collect(Collectors.groupingBy(Course::getCategory)));

        System.out.println("\n");
        System.out.println("***Print the number of courses in each category***");
        System.out.println(courses.stream().collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));

    }

}
