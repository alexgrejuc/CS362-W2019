/****************************************************************************
* Random tests for adventurerEffect
****************************************************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include "test_helpers.h"
#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
#include <math.h> 
#include "rngs.h"
#include <time.h> 

#define TESTNAME "adventurerEffect()"
#define TEST_ID_START 1 

int testAdventurer(int player, struct gameState* G){
    struct gameState preG;
    memcpy(&preG, G, sizeof(struct gameState)); 

    int treasureCount = countDeckTreasure(player, G); 
    adventurerEffect(player, G);

    if(treasureCount == 0 || treasureCount == 1){
        assert_print_err(countHandTreasure(player, &preG) + treasureCount, countHandTreasure(player, G), "treasure increase");
    }
    else{
        assert_print_err(countHandTreasure(player, &preG) + 2, countHandTreasure(player, G), "treasure increase");  
    }

    // deck, hand, discard (and counts) can all be expected to change 
    // set them to be equal to avoid false trigger of side effect error 
    // copy preG into G because G may have some out of bounds counts due to buggy code 
    memcpy(G->deck[player], preG.deck[player], sizeof(int) * MAX_DECK);
    G->deckCount[player] = preG.deckCount[player]; 

    memcpy(G->hand[player], preG.hand[player], sizeof(int) * MAX_HAND);
    G->handCount[player] = preG.handCount[player];

    memcpy(G->discard[player], preG.discard[player], sizeof(int) * MAX_DECK);
    G->discardCount[player] = preG.discardCount[player];

    if(memcmp(&preG, G, sizeof(struct gameState)) != 0) {
      printf("\tFAIL side effect found\n"); 
    }
    return 0; 
}

int main() {
  printf ("*** RANDOM TEST BEGIN %s ***\n\n", TESTNAME);
  srand(time(NULL));    
  struct gameState G; 

  for(int i = 0; i < 2000; i++){
    int k[10]; 
    rand_kc(k); // randomize kingom card set 
    
    // randomize players 
    int numPlayers = (rand() % MAX_PLAYERS) + 1; // add 1 to avoid 0 players 
    int player = rand() % numPlayers;

    int seed = rand(); 

    initializeGame(numPlayers, k, seed, &G);  // init game to properly set everything

    // further randomize the game  
    rand_hand(player, &G);
    rand_deck(player, &G);
    rand_discard(player, &G);  

    G.whoseTurn = player; 
    testAdventurer(player, &G);
  }
    return 0;
}
