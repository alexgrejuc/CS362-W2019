/******************************************************************************
** random testing quiz submission 
** Alex Grejuc
** CS 362
******************************************************************************/

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

#define VALID_INPUT_CHAR "[({ ax})]abcdefghijklmnopqrstuv" 
#define VALID_STRING_CHARS "abcdefghijklmnopqrstuvwxyz"
#define KEY_STR	"reset"  
char inputStringVal[6] = "temps"; 

// randomly picks one of the VALID_INPUT_CHARS
char inputChar()
{
	char valid[] = VALID_INPUT_CHAR;
	int len = sizeof(valid) / sizeof(char) - 1; // exclude null terminator 
	
	return valid[rand() % len]; 	
}

// either builds up a string from VALID_STRING_CHARS
// or returns reset directly
char *inputString()
{
	char valid[] = VALID_STRING_CHARS; // stores built up input string
	char valid_key[] = KEY_STR; // returns reset directly  

	int index_bound = sizeof(valid) / sizeof(char) - 1; 
	int input_len = sizeof(inputStringVal) / sizeof(char) - 1; 
	 
	int choice = random() % 10000; 

	if(choice == 0) return KEY_STR; 

	// build random input string
 	else {
		for(int i = 0; i < input_len - 1; i++){
			inputStringVal[i] = valid[random() % index_bound];
		} 
	} 

    return inputStringVal;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error \n");
      exit(200);
    }
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}
