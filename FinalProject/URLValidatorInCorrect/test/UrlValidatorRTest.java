

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
            "https://"};

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
            "go.cc",
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

        for(int i = 0; i < NUM_RAND_TESTS; i++){
            UrlValidator urlVal = new UrlValidator(null, null, 0);
            String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            String authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
            String port = validPorts[new Random().nextInt(validPorts.length)];
            String path = validPaths[new Random().nextInt(validPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;

            assertTrue("URL should pass: " + url, urlVal.isValid(url));
        }

    }

    public void testRandomInvalidSchemes() {
        System.out.println("\n******** TESTING INVALID SCHEMES RANDOMLY ********/");

        for (int i = 0; i < NUM_RAND_TESTS; i++) {
            UrlValidator urlVal = new UrlValidator();
            String scheme = invalidSchemes[new Random().nextInt(invalidSchemes.length)];
            String authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
            String port = validPorts[new Random().nextInt(validPorts.length)];
            String path = validPaths[new Random().nextInt(validPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;
            assertFalse("URL should not pass: " + url, urlVal.isValid(url));

        }
    }
    public void testRandomInvalidAuthorities() {
        System.out.println("\n******** TESTING INVALID AUTHORITIES RANDOMLY ********/");

        for (int i = 0; i < NUM_RAND_TESTS; i++) {
            UrlValidator urlVal = new UrlValidator();
            String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            String authority = invalidAuthorities[new Random().nextInt(invalidAuthorities.length)];
            String port = validPorts[new Random().nextInt(validPorts.length)];
            String path = validPaths[new Random().nextInt(validPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;
            assertFalse("URL should not pass: " + url, urlVal.isValid(url));

        }
    }
    public void testRandomInvalidPorts() {
        System.out.println("\n******** TESTING INVALID PORTS RANDOMLY ********/");

        for (int i = 0; i < NUM_RAND_TESTS; i++) {
            UrlValidator urlVal = new UrlValidator();
            String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            String authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
            String port = invalidPorts[new Random().nextInt(invalidPorts.length)];
            String path = validPaths[new Random().nextInt(validPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;
            assertFalse("URL should not pass: " + url, urlVal.isValid(url));

        }
    }
    public void testRandomInvalidPaths() {
        System.out.println("\n******** TESTING INVALID PATHS RANDOMLY ********/");
        for (int i = 0; i < NUM_RAND_TESTS; i++) {
            UrlValidator urlVal = new UrlValidator();
            String scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            String authority = invalidAuthorities[new Random().nextInt(invalidAuthorities.length)];
            String port = validPorts[new Random().nextInt(validPorts.length)];
            String path = invalidPaths[new Random().nextInt(invalidPaths.length)];
            String query = validQueries[new Random().nextInt(validQueries.length)];
            String url = scheme + authority + port + path + query;
            assertFalse("URL should not pass: " + url, urlVal.isValid(url));

        }
    }

    public void testRandomInvalid(){
        System.out.println("\n******** TESTING INVALID URLS RANDOMLY ********/");
        UrlValidator urlVal = new UrlValidator();

        for(int i = 0; i < NUM_RAND_TESTS; i++){
            String scheme, authority, port, path, query;
            int valid = 1;
            if(new Random().nextInt(2) == 0) {
                scheme = invalidSchemes[new Random().nextInt(invalidSchemes.length)];
                valid = 0;
            }else{
                scheme = validSchemes[new Random().nextInt(validSchemes.length)];
            }
            if(new Random().nextInt(2) == 0) {
                valid = 0;
                authority = invalidAuthorities[new Random().nextInt(invalidAuthorities.length)];
            }else{
                authority = validAuthorities[new Random().nextInt(validAuthorities.length)];
            }
            if(new Random().nextInt(2) == 0) {
                valid = 0;
                port = invalidPorts[new Random().nextInt(invalidPorts.length)];
            }else{
                port = validPorts[new Random().nextInt(validPorts.length)];
            }
            if(new Random().nextInt(2) == 0) {
                valid = 0;
                path = invalidPaths[new Random().nextInt(invalidPaths.length)];
            }else{
                path = validPaths[new Random().nextInt(validPaths.length)];
            }

            query = validQueries[new Random().nextInt(validQueries.length)];

            String url = scheme + authority + port + path + query;
            if(valid == 0)
                assertFalse("URL should not pass: " + url, urlVal.isValid(url));
        }
    }
}