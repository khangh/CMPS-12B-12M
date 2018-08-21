#------------------------------------------------------------------------------
# makefile for charType.c with Macros and valgrind
#------------------------------------------------------------------------------

PROGRAM_NAME = charType
OUTFILE_NAME = out
FLAGS        = -std=c99 -Wall
SOURCES      = $(PROGRAM_NAME).c
OBJECTS      = $(PROGRAM_NAME).o
EXEBIN       = $(PROGRAM_NAME)
OUTFILE      = $(OUTFILE_NAME)

all: $(EXEBIN)

check :
	valgrind --leak-check=full $(EXEBIN) $(SOURCES) $(OUTFILE)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)
