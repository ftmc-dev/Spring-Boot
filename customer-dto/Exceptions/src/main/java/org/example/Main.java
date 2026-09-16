package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        int number1 = 20;
        int number2 = 10;

        int age = 15;
        try {
           int division = number1/number2;
            System.out.println(division);

            if(age < 18){
                throw new IllegalArgumentException("Age must be at least 18");
            }
        }

        catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        finally {
            System.out.println("Calculation attempt finished");
        }
        }
    }
