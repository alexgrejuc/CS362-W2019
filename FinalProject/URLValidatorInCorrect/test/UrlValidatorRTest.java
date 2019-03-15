

import junit.framework.TestCase;

import java.util.Random;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorRTest extends TestCase {
    public UrlValidatorRTest(String testName) {
        super(testName);
    }


    long NUM_RAND_TESTS = 10000;

    String[] validSchemes = {
            "http://",
            "ftp://",
            "h3t://"};

    String[] invalidSchemes = {
            "3ht://",
            "http:/",
            "http:",
            "http/",
            "://"};

    String[] validAuthorities = {
            "www.google.com",
            "www.google.com.",
            "go.com",
            "go.au",
            "0.0.0.0",
            "255.255.255.255",
            "255.com"};

    String[] invalidAuthorities = {
            "1.2.3.4.5",
            "1.2.3.4.",
            "1.2.3",
            ".1.2.3.4",
            "go.a",
            "go.a1a",
            "go.cc",
            "go.1aa",
            "aaa.",
            ".aaa",
            "aaa",
            "",
            "256.256.256.256"};


    String[] validPorts = {
            ":80",
            ":65535", // max possible
            ":0",
            ""};

    String[] invalidPorts = {
            ":-1",
            ":65636",
            ":999999999999999999",
            ":65a",
            ":65536"}; // max possible +1

    String[] validPaths = {
            "/test1",
            "/t123",
            "/$23",
            "/test1/",
            "",
            "/test1/file"};

    String[] invalidPaths = {
            "/..//file",
            "/test1//file",
            "/..",
            "/../"};


    String[] validQueries = {
            "?action=view",
            "?action=edit&mode=up",
            ""
    };





    public void testRandomValid(){
        System.out.println("\n******** TESTING VALID URLS RANDOMLY ********/");
        UrlValidator urlVal = new UrlValidator(null, null, 0);
        assertTrue(urlVal.isValid("http://www.google.com"));
        //assertTrue(urlVal.isValid("http://www.google.com/"));
        /*
        for(int i = 0; i < NUM_RAND_TESTS; i++){
            String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            String authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
            String port = validPorts[new Random().nextInt(validPorts.length)];
            String path = validPaths[new Random().nextInt(validPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;
            assertTrue(urlVal.isValid(url));
        }
        */
    }

    public void testRandomInvalid(){
        System.out.println("\n******** TESTING INVALID URLS RANDOMLY ********/");

        for(int i = 0; i < NUM_RAND_TESTS; i++){

        }

    }



}
