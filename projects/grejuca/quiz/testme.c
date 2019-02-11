/******************************************************************************
** random testing quiz submission 
** Alex Grejuc
** CS 362
******************************************************************************/

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

#define VALID_INPUT_CHAR "[({ ax})]abcdefghijklmnopqrstuvABCDEFGHIJKLMNOPQRSTUVWXYZ" 
#define VALID_STRING_CHARS "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZresetresetresetreset"
char inputStringVal[] = "1234567891"; 

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

	int index_bound = sizeof(valid) / sizeof(char) - 1; 
	int input_len = sizeof(inputStringVal) / sizeof(char); 
	
	input_len = rand() % input_len; 

	// build random input string
	for(int i = 0; i < input_len - 1; i++){
		inputStringVal[i] = valid[rand() % index_bound];
	} 

	inputStringVal[input_len - 1] = '\0'; 
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
