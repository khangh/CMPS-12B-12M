#--------------------------------------------------------
# A simple Makefile that runs HelloUser.java
# Run by typing make -f Makefile in the terminal.
# File name: Makefile
# Name: Sophia Tacderas
# ID: *******
# Class: CMPS 12M
#--------------------------------------------------------
HelloUser: HelloUser.class
	echo Main-class: HelloUser > Manifest
	jar cvfm HelloUser Manifest HelloUser.class
	rm Manifest
	chmod +x HelloUser

HelloUser.class: HelloUser.java
	javac -Xlint HelloUser.java

clean:
	rm -f HelloUser HelloUser.class
