# About

Coding test for ThoughtWorks. Took approximately 10 hours over 2 days to complete.

# Rules

1. Submit the solution to only one problem
2. Java, Ruby, C#, Python, Clojure, Scala or JavaScript
3. Application must run...and with input via text file
4. No external libraries (except for testing or build tools)
5. No executables to be supplied
6. Brief explanation of design, assumptions, detailed instructions to running, and if working against test data
7. Production-ready code (e.g. maintainable, extensible), not a bare minimum algorithm
8. Don't share or publicise solution
9. Compress files into single ZIP and submit to: http://jobs.thoughtworks.com/DataCompletionRequest?uid=0qEaJ7nzEdlmn6vZ&jobId=790
10. Generally 72 hours to complete task

# Problem

### Merchant's Guide To The Galaxy

You decided to give up on earth after the latest financial collapse left 99.99% of the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in your account, you are able to afford to rent a spaceship, leave earth, and fly all over the galaxy to sell common metals and dirt (which apparently is worth a lot).
Buying and selling over the galaxy requires you to convert numbers and units, and you decided to write a program to help you.
The numbers used for intergalactic transactions follows similar convention to the Roman numerals and you have painstakingly collected the appropriate translation between them.

**Basics**

* Assign Symbols (Roman numerals) to variables
* Assign Credits (decimals) to variables
* Convert from Roman numerals to decimal
* Handle invalid queries appropriately

# Overview

* TDD using JUnit, Hamcrest and Mockito (92% coverage). Unit and integration testing -- passes supplied test data.
* Tools: IDEA 14 (static code analysis) and Git (local)
* System components:
** MerchantConverterApp: Runnable, loads input file and passes to Parser
** InputParser: Parses each line and calls relevant Repository and Writer interface function
** Repository: Interface and concrete implementations for setting Symbols (i.e. Roman numerals) or Credits
** Writer: Interface and concrete implementations for getting and outputting Symbols or Credits
** SymbolInterpreter: Conversion from Roman to Arabic numerals
** RepositoryUtil: Helper method used by different classes

# Design and Assumptions

* Maven project with pom.xml for dependency management (testing libraries)
* 'Clean Code' principles (e.g. naming, only necessary code comments, DRY, SRP, small functions, avoid magic numbers...)
* In Roman numerals VVV, LLL and DDD are invalid, which is reflected in the Interpreter.
* Use of Repository and Writer interfaces for polymorphism of common functions with different implementations
* Credits are stored assuming a quantity of 1 -- this is then multiplied as required for output
* Credits are stored as BigDecimal, but output with no decimal places (in order to conform to test output)

# Enhancements

* String interpolation instead of concatenation
* Additional unit tests for private functions (change to protected). Underlying implementation so not tested-first with TDD.
* Better name for 'Repository' (not happy with it)
* Increased validation (e.g. case insensitive, boundary conditions, invalid data in correct format) and error handling
* Integration test not to have a hard-coded file path
* Factory for selecting Repository and Writer objects

# How to Run

1. java MerchantConverterApp
