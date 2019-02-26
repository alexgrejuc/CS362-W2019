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

int countDeckTreasure(int player, struct gameState *game) {
  int card, index, count = 0;
  for(index = 0; index < game->deckCount[player]; index++) {
    card = game->deck[player][index];
    switch(card) {
    case copper: count++;
      break;
    case silver: count++;
      break;
    case gold: count++;
      break;
    }
  }
  return count;
}

int countHandTreasure(int player, struct gameState *game) {
  int card, index, count = 0;
  for(index = 0; index < game->handCount[player]; index++) {
    card = game->hand[player][index];
    switch(card) {
    case copper: count++;
      break;
    case silver: count++;
      break;
    case gold: count++;
      break;
    }
  }
  return count;
}

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


    /*if(memcmp(&preG, G, sizeof(struct gameState)) != 0) {
      printf("FAIL side effect found"); 
    }*/ 
    return 0; 
}

// generate a random set of kc (all cards in set are unique)
void rand_kc(int k[]){
    // create a random set of kingdom cards  
    for(int i = 0; i < 10; i++){
      int same = 1; 
      int card; 
      while(same){
        card = rand() % (treasure_map + 1);
        same = 0; 
        for(int j = 0; j < i; j++){
          if(card == k[j]) same = 1;
        }
      }

      k[i] = card;
    }
}

void rand_hand(int player, struct gameState* G){
    G->handCount[player] = rand() % (MAX_HAND + 1); 

    // create a random hand 
    for(int i = 0; i < G->handCount[player]; i++){
      G->hand[player][i] = rand() % (treasure_map + 1);
    }
}

void rand_deck(int player, struct gameState* G){
    G->deckCount[player] = rand() % (MAX_HAND + 1);

    for(int i = 0; i < G->deckCount[player]; i++){
      G->deck[player][i] = rand() % (treasure_map + 1);
    }
}

void rand_discard(int player, struct gameState* G){
    // discardCount is incremented in dominion in a way that can cause seg faults
    // setting the count to MAX / 2 reduces the liklihood of an array out of bounds 
    G->discardCount[player] = rand() % (MAX_HAND / 2);

    for(int i = 0; i < G->deckCount[player]; i++){
      G->discard[player][i] = rand() % (treasure_map + 1);
    }
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
