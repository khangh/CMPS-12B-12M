# Makefile that compiles Recursion.java
JAVASRC = Recursion.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Recursion
CLASSES = Recursion.class
JARFILE = Recursion

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
