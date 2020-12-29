# calculator
Given an input of a numeric expression in form of a String, compute its result as would be if using a calculator, supporting +, -, *, /, ^ operations for addition, subtraction, multiplication, division and power (only support integer powers currently) respectively.

Currently support 3-level bracket too.

Code written in Java.

What's new - 29/12/2020
-----------------------
Round brackets (parentheses) (), square brackets \[\], and curly braces {} are supported as 3 levels of brackets. (Please see note below on how to use.)

How to use
----------
After cloning the whole repository, please edit the expression ```inp``` in the ```main``` method in ```Application.java```.

Note:
1. Please enter expression without any spacing in between.
2. Please use () for first level bracket, \[\] for second and {} for third, i.e. within the round brackets () there should not be any higher level brackets.
3. Please do not omit the * for multiplying with brackets (e.g. ```3(1+2)``` is not supported, please use ```3*(1+2)```).
4. By default, the power operates on the previous value only (e.g. ```2+3^5``` means add 2 to the 5th power of 3). Please use a bracket to indicate otherwise.

Features in development
-----------------------
1. Support decimal number as power.
2. User interface / web app for inputting expression in ```main``` method in ```Application.java```.
