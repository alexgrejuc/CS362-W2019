

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {

    long NUM_RAND_TESTS = 10000;

    ResultPair[] validSchemes = {
            new ResultPair("http://", true),
            new ResultPair("ftp://", true),
            new ResultPair("h3t://", true)};

    ResultPair[] invalidSchemes = {
            new ResultPair("3ht://", false),
            new ResultPair("http:/", false),
            new ResultPair("http:", false),
            new ResultPair("http/", false),
            new ResultPair("://", false)};

    ResultPair[] validAuthorities = {
            new ResultPair("www.google.com", true),
            new ResultPair("www.google.com.", true),
            new ResultPair("go.com", true),
            new ResultPair("go.au", true),
            new ResultPair("0.0.0.0", true),
            new ResultPair("255.255.255.255", true),
            new ResultPair("255.com", true)};

    ResultPair[] invalidAuthorities = {
            new ResultPair("1.2.3.4.5", false),
            new ResultPair("1.2.3.4.", false),
            new ResultPair("1.2.3", false),
            new ResultPair(".1.2.3.4", false),
            new ResultPair("go.a", false),
            new ResultPair("go.a1a", false),
            new ResultPair("go.cc", true),
            new ResultPair("go.1aa", false),
            new ResultPair("aaa.", false),
            new ResultPair(".aaa", false),
            new ResultPair("aaa", false),
            new ResultPair("", false),
            new ResultPair("256.256.256.256", false)};


    ResultPair[] validPorts = {
            new ResultPair(":80", true),
            new ResultPair(":65535", true), // max possible
            new ResultPair(":0", true),
            new ResultPair("", true)};

    ResultPair[] invalidPorts = {
            new ResultPair(":-1", false),
            new ResultPair(":65636", false),
            new ResultPair(":999999999999999999", false),
            new ResultPair(":65a", false),
            new ResultPair(":65536", false)}; // max possible +1

    ResultPair[] validPaths = {
            new ResultPair("/test1", true),
            new ResultPair("/t123", true),
            new ResultPair("/$23", true),
            new ResultPair("/test1/", true),
            new ResultPair("", true),
            new ResultPair("/test1/file", true)};

    ResultPair[] invalidPaths = {
            new ResultPair("/..//file", false),
            new ResultPair("/test1//file", false),
            new ResultPair("/..", false),
            new ResultPair("/../", false)};


    ResultPair[] validQueries = {
            new ResultPair("?action=view", true),
            new ResultPair("?action=edit&mode=up", true),
            new ResultPair("", true)
    };



   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest(){
        //You can use this function to implement your manual testing
	   
   }
   
   
   public void testRandomValid(){
       System.out.println("\n******** TESTING VALID URLS RANDOMLY ********/");
       UrlValidator urlVal = new UrlValidator(null, 0);

       for(int i = 0; i < NUM_RAND_TESTS; i++){
           String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
           String authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
           String port = validPorts[new Random().nextInt(validPorts.length)];
           String path = validPaths[new Random().nextInt(validPaths.length)];
           String query = validQueries[new Random().nextInt(validQueries.length)];
           String url = scheme + authority + port + path + query;
           assertTrue(urlVal.isValid(url));
       }




   }
   
   public void testRandomInvalid(){
       System.out.println("\n******** TESTING INVALID URLS RANDOMLY ********/");

       for(int i = 0; i < NUM_RAND_TESTS; i++){

       }

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
