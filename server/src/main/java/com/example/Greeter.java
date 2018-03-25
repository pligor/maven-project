package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }

  /**
   * Dummy
   * @param someone the name of the person
   * @return a greeting
   */
  final public String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
