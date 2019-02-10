#include "test_helpers.h"
#include <stdio.h> 

void assert_print(int expected, int actual, char* message, int* num){
    printf("\tTest %i ", *num);
    (*num)++; 
	if(expected == actual) printf("PASS %s\n", message); 
	else printf("FAIL %s | EXPECTED: %i | ACTUAL: %i\n", message, expected, actual); 
}
