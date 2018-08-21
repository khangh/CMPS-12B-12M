#------------------------------------------------------------------------------
#   File: Makefile
#   Name: Sophia Tacderas
#   Due: 11/18/16, 10 pm
#   Class: CMPS 12M
#   Assignment: lab 6
#   Purpose: Makefile for List ADT.
#   Borrowed online from Examples/Labs/lab6/Makefile.
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = List.java ListInterface.java ListClient.java\
             ListIndexOutOfBoundsException.java
CLASSES    = List.class ListInterface.class ListClient.class List\$$Node.class\
             ListIndexOutOfBoundsException.class

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

clean:
	rm -f $(CLASSES) $(JARFILE)
