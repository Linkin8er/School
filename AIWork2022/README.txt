You'll find the following files:

docs.zip: This file contains the javadocs for the Java classes contained in puzzle.jar.  
          You will need the documentation to see what the classes are, and their methods, etc.
		 
puzzle.jar: This jar file contains a few classes related to the 8 puzzle.  It does not contain the
          sourcecode, only the compiled .class files.  See details later.
		  
SomeExamples.java: Source code with some examples showing how to use some of the classes and methods
          in the puzzle.jar file.
		  
		  

Two ways to use the puzzle.jar file:

In Eclipse:
Someplace in the menus, there is an option to configure your build path for your project.
Configure your buildpath to add the puzzle.jar file.


Compiling from the command line:
If you're comfortable compiling from the command line, put puzzle.jar in the same
folder as your .java files, and you can use the command line option cp to put the
puzzle.jar file in the class path.  Here is an example for compiling SomeExamples.java:

javac -cp ".;puzzle.jar" SomeExamples.java

If you have multiple .java files to compile, you can also do something like:

javac -cp ".;puzzle.jar" *.java

Both of these examples assume that your current working directory is the directory
where your .java files and the jar file are located. It also assumes Windows is your 
operating system. Change the ; to a : on Linux or Macs.


Running from the command line:
The jar file that I have provided does not contain any classes that have a main method.
The code that you will write will have a main method.  For example, see the SomeExamples.java.
To run SomeExamples.java, you can do the following from the command line:

java -cp ".;puzzle.jar" SomeExamples

Note that it is just SomeExamples (no .java and no .class).  This assumes that you've
already compiled and that the SomeExamples.class file is in your current directory.
Assuming that SomeExamples has a main method, the above will run it.

You may need to adjust the above a bit if you organize your code into packages.
 


