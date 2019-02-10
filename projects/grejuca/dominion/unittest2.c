/****************************************************************************
* Unit test for isGameOver()
****************************************************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include "test_helpers.h"
#include <stdio.h>
#include <string.h>
#include "rngs.h"

#define TESTNAME "isGameOver()"
#define TEST_ID_START 1 

int main() {    
    printf ("*** UNIT TEST BEGIN %s ***\n\n", TESTNAME);
    int test_num = TEST_ID_START; 
    
    int seed = 1000;
    int numPlayers = 2;
    
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};

    struct gameState newG; // a blank slate to check against and to reset G
    struct gameState G; // changes are made to G to trigger test results 

    initializeGame(numPlayers, k, seed, &newG); // newG is a blank slate 
    memcpy(&G, &newG, sizeof(struct gameState)); // changes are made to 


    printf ("\n*** UNIT TEST END %s   ***\n", TESTNAME);
    return 0;
}
