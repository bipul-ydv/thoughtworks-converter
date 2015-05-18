# About

Coding test for ThoughtWorks. Rules:

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

** Basics **

* Assign Symbols (Roman numerals) to variables
* Assign Credits (decimals) to variables
* Convert from Roman numerals to decimal
* Handle invalid queries appropriately

# Comments

Code coverage: 92%
Static code analysis: IDEA
Integration test run separate!
Don't love my naming of 'repository'
Test private (protected) functions in CreditRepository? Not TDD -- came later.
Invalid roman: VVV, LLL, DDD
better validation (e.g. filepath) and error handling, boundary conditions, invalid data correct format (e.g. non assigned vars)
input strings not case insensitive
creditwriter rounding scale 0 decimals...