/******************************************************************************
 * The programming-based testing portion for final project part B
 *****************************************************************************/

import junit.framework.TestCase;

public class UrlValidatorPTest extends TestCase {

    public UrlValidatorPTest(String testName) {
        super(testName);
    }

    public void testNull()
    {
        var v = new UrlValidator();

        assertFalse(v.isValid(null));
    }

    public void testValidAssortedUrls()
    {
        String[] schemes = {"https"};
        var v = new UrlValidator(schemes, UrlValidator.ALLOW_ALL_SCHEMES);

        String[] urls = {
          "https://mail.google.com/mail/u/0/#inbox", "https://github.com/", "https://github.com/alexgrejuc/CS362-W2019",
                "https://github.com/alexgrejuc/CS362-W2019/branches", "https://oregonstate.instructure.com/courses/1706563/discussion_topics/8488233",
                "https://www.google.com/search?client=ubuntu&channel=fs&q=array+of+strings+java&ie=utf-8&oe=utf-8",
                "https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax",
                "https://www.google.com/search?client=ubuntu&channel=fs&q=components+of+a+url&ie=utf-8&oe=utf-8",
                "https://www.wikipedia.org/",
                "https://zh.wikipedia.org/wiki/Wikipedia:%E9%A6%96%E9%A1%B5",
                "https://de.wikipedia.org/wiki/Wikipedia:Hauptseite",
                "https://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal",
                "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna",
                "https://es.wikipedia.org/wiki/Wikipedia:Portada"
        };
        for(String url : urls){
            assertTrue(v.isValid(url));
        }
    }

    public void testMissingComponentUrls()
    {
        String[] schemes = {"https"};
        var v = new UrlValidator(schemes, 0);

        String[] urls = {
                "mail.google.com/mail/u/0/#inbox",
                "https://",
                "https://alexgrejuc/CS362-W2019",
        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testInvalidSchemeUrls()
    {
        var v = new UrlValidator();

        String[] urls = {
                "ttps://mail.google.com/mail/u/0/#inbox",
                "htttps://github.com/",
                " ht tps://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal",
                "ftps://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna",
                "fttps://es.wikipedia.org/wiki/Wikipedia:Portada",
                "https//www.google.com:65536/search?client=ubuntu&channel=fs&q=components+of+a+url&ie=utf-8&oe=utf-8",
                "https:/es.wikipedia.org/wiki/Wikipedia:Portada"
        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testInvalidAuthUrls()
    {
        var v = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

        String[] urls = {
                "https://newsycombinatorcom/hide?id=19395578&auth=af91ad4fc34b79d64cf68047ac130a9d95ded610&goto=news",
                "https://73.25.177.232.300/vote?id=19389983&how=up&auth=b52c059e09a9504c44c4f1e4ccdd0b1525b5233a&goto=news",
                "https://1/a/5wcFx8M",
                "https://73.25.177.2324/from?site=imgur.com",
                "https://news..ycombinator.com/user?id=IFR",
        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testInvalidPortUrls()
    {
        var v = new UrlValidator();

        String[] urls = {
            "https://news.ycombinator.com:-1/user?id=IFR",
            "https://news.ycombinator.com:-0/item?id=19389983",
            "https://news.ycombinator.com:65536/hide?id=19389983&auth=b52c059e09a9504c44c4f1e4ccdd0b1525b5233a&goto=news",
            "https://news.ycombinator.com:./vote?id=19391476&how=up&auth=d0790fd5798595fec45d57580c02d2f5a2ed2fe1&goto=news",
            "https://motherboard.vice.com:ab/en_us/article/yw84q7/darpa-is-building-a-dollar10-million-open-source-secure-voting-system",
            "https://news.ycombinator.com:1:2/from?site=vice.com",

        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testInvalidPathUrls()
    {
        var v = new UrlValidator();

        String[] urls = {
                "https://news.ycombinator.com/user//me",
                "https://news.ycombinator.com/..",
                "https://news.ycombinator.com/user\\\\a",
                "https://news.ycombinator.com/user/#/",
                "https://livestream.tesla.com/",
        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testInValidAssortedUrls()
    {
        String[] schemes = {"https"};
        var v = new UrlValidator(schemes, 0);

        String[] urls = {

                "httpsgithub.com/alexgrejuc/CS362-W2019",
                "https:/github.com/alexgrejuc/CS362-W2019/branches",
                "https://oregonstate.instructure.com::70/courses/1706563/discussion_topics/8488233",
                "https://-1.-1-.-1.-1.-1.-1?client=ubuntu&channel=fs&q=array+of+strings+java&ie=utf-8&oe=utf-8",
                "https://.aaa/java/java-string-array-reference-java-5-for-loop-syntax",
                "https://www.google.com:65536/search?client=ubuntu&channel=fs&q=components+of+a+url&ie=utf-8&oe=utf-8",
                "https://www.wikipedia.org:70000000/",
                "https://zh.wikipedia.org/wiki//Wikipedia:%E9%A6%96%E9%A1%B5",
                "https://de.wikipedia.org/wiki/\\/Wikipedia:Hauptseite",
                "https://fr.wikipedia.org/wiki/::://Wikip%C3%A9dia:Accueil_principal",
                "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna/#",
                "https://es.wikipedia.org/wiki/Wikipedia:Portada/.."
        };
        for(String url : urls){
            assertFalse(v.isValid(url));
        }
    }

    public void testValidLinuxCommandUrls()
    {
        var v = new UrlValidator();

        String[] urls = {
            "http://linuxcommand.org/index.php",
            "http://linuxcommand.org/lc3_learning_the_shell.php",
            "http://linuxcommand.org/lc3_writing_shell_scripts.php",
            "http://linuxcommand.org/lc3_resources.php",
            "http://linuxcommand.org/tlcl.php",
            "http://linuxcommand.org/lc3_adventures.php",
            "http://lcorg.blogspot.com/",
            "http://linuxcommand.org/lc3_wss0110.php",
            "http://linuxcommand.org/lc3_writing_shell_scripts.php#contents",
            "http://linuxcommand.org/lc3_wss0130.php",
            "http://www.gnu.org/",
            "http://linuxcommand.org/lc3_wss0120.php#top"
        };

        for(String url : urls){
            assertTrue(v.isValid(url));
        }
    }


    public void testValidRedditUrls()
    {
        var v = new UrlValidator();

        String[] urls = {
                "https://www.reddit.com/#content",
                "https://www.reddit.com/r/Dashboard/",
                "https://www.reddit.com/",
                "https://www.reddit.com/r/popular/",
                "https://www.reddit.com/r/all/",
                "https://www.reddit.com/r/random/",
                "https://www.reddit.com/r/myrandom/",
                "https://www.reddit.com/users/",
                "https://www.reddit.com/r/friends/",
                "https://www.reddit.com/r/mod/",
                "https://www.reddit.com/r/mod/about/modqueue",
                "https://www.reddit.com/user/me/saved",
                "https://www.reddit.com/#res:settings/subredditManager",
                "https://www.reddit.com/hot/",
                "https://www.reddit.com/new/",
                "https://www.reddit.com/rising/",
                "https://www.reddit.com/controversial/",
                "https://www.reddit.com/top/",
                "https://www.reddit.com/gilded/",
                "https://www.reddit.com/wiki/",
                "https://www.reddit.com/message/inbox/",
                "https://www.reddit.com/chat",
                "https://www.reddit.com/message/moderator/",
                "https://www.reddit.com/prefs/",
                "https://www.reddit.com/#",
                "https://www.reddit.com/wiki/search",
                "https://www.reddit.com/submit",
                "https://www.reddit.com/submit?selftext=true",
                "https://www.reddit.com/subreddits/create",
                "https://www.reddit.com/account-activity",
                "https://www.reddit.com/explore",
                "https://www.reddit.com/me/m/soccer/",
                "https://www.reddit.com/r/multihub/",
                "https://www.reddit.com/me/f/all",
                "https://www.reddit.com/r/mod",
                "https://www.reddit.com/user/reddit/m/gold",
                "https://alb.reddit.com/c?q=CgADBkmO_9qK5pwKAAUhUDfu4QFF_goABiFQN-7hAEX-CAAHAAAAAgoADAfvkPeRWrfUAA==&s=yGNP7wRaKmhSeKckXU31wVyJI2GcWp0Iw8cnW88eqc0=&r=aHR0cHM6Ly90cmlwbGVieXRlLmNvbS8_cmVmPXJfMjAxODA4MTdfdDFfaW50X3VzX2dlbl9taWtlX3F1b3Rl",
                "https://www.reddit.com/user/Triplebyte_official",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_986hea",
                "https://www.reddit.com/r/HumanTippyTaps/",
                "https://www.reddit.com/r/ExpectedThanos/",
                "https://www.reddit.com/r/itsaunixsystem/",
                "https://www.reddit.com/r/dwarffortress/",
                "https://www.reddit.com/r/Animalsthatlovemagic/",
                "https://www.reddit.com/r/trendingsubreddits/comments/b0vx0c/trending_subreddits_for_20190314/",
                "https://i.redd.it/x1qj9yhas3m21.jpg",
                "https://www.reddit.com/domain/i.redd.it/",
                "https://www.reddit.com/user/Tollo92",
                "https://www.reddit.com/r/photoshopbattles/",
                "https://www.reddit.com/r/photoshopbattles/comments/b11ouj/psbattle_this_ac_unit_next_to_a_mirror/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b11ouj",
                "https://www.reddit.com/r/outrun/comments/b15c2i/masterpiece_one/",
                "https://www.reddit.com/user/ContentAd2",
                "https://www.reddit.com/r/outrun/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b15c2i",
                "https://www.reddit.com/r/unixporn/comments/b0ydlq/subtle_lost/",
                "https://www.reddit.com/user/szorfein",
                "https://www.reddit.com/r/unixporn/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b0ydlq",
                "https://www.reddit.com/r/AskHistorians/comments/b10bip/i_was_just_reading_about_the_roman_takeover_of/",
                "https://www.reddit.com/r/AskHistorians/",
                "https://www.reddit.com/user/KatsumotoKurier",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b10bip",
                "https://www.reddit.com/r/ProsePorn/comments/b12qzg/louisferdinand_c%C3%A9line_journey_to_the_end_of_the/",
                "https://www.reddit.com/r/ProsePorn/",
                "https://www.reddit.com/user/NightClerk",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b12qzg",
                "https://www.reddit.com/r/explainlikeimfive/comments/b1052y/eli5_when_flights_get_cancelled_because_of_heavy/",
                "https://www.reddit.com/r/explainlikeimfive/",
                "https://www.reddit.com/user/Mr_Gimenez",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b1052y",
                "https://i.imgur.com/NLSGHjw.gifv",
                "https://www.reddit.com/domain/i.imgur.com/",
                "https://www.reddit.com/user/MyNameGifOreilly",
                "https://www.reddit.com/r/woahdude/",
                "https://www.reddit.com/r/woahdude/comments/b129j2/shark_egg_case_that_washed_ashore_with_a_alive/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b129j2",
                "https://www.reddit.com/r/IAmA/comments/b10diy/hi_i_am_annabelle_timsit_the_quartz_journalist/",
                "https://www.reddit.com/r/IAmA/",
                "https://www.reddit.com/user/QuartzNews",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b10diy",
                "http://articles.latimes.com/2005/oct/20/local/me-heiress20",
                "https://www.reddit.com/domain/articles.latimes.com/",
                "https://www.reddit.com/user/Xerxestheokay",
                "https://www.reddit.com/r/todayilearned/",
                "https://www.reddit.com/r/todayilearned/comments/b12z0c/til_a_walmart_heiress_gave_back_her_usc_degree/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b12z0c",
                "https://i.redd.it/7yd4qakwt2m21.jpg",
                "https://www.reddit.com/user/EcthelionElf",
                "https://www.reddit.com/r/Romania/",
                "https://www.reddit.com/r/Romania/comments/b0zn46/ce_merit%C4%83_rom%C3%A2nia/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b0zn46",
                "https://streamja.com/dZBy",
                "https://www.reddit.com/domain/streamja.com/",
                "https://www.reddit.com/user/Meladroit40",
                "https://www.reddit.com/r/soccer/",
                "https://www.reddit.com/r/soccer/comments/b152fb/inter_01_frankfurt_01_on_agg_luka_jovic_5/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b152fb",
                "https://i.redd.it/y1f0v4n424m21.jpg",
                "https://www.reddit.com/user/shekel_merchant",
                "https://www.reddit.com/r/ProgrammerHumor/",
                "https://www.reddit.com/r/ProgrammerHumor/comments/b13be5/the_new_bloomberg_cover_is_a_work_of_art/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b13be5",
                "https://i.redd.it/y14opx41d4m21.jpg",
                "https://www.reddit.com/user/ThirtySixthStallion",
                "https://www.reddit.com/r/Portland/",
                "https://www.reddit.com/r/Portland/comments/b134hy/whoever_lost_their_baked_potatoes_on_mlk_i_found/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b134hy",
                "https://www.reddit.com/r/Coffee/comments/b14heg/coffee_in_barcelona/",
                "https://www.reddit.com/r/Coffee/",
                "https://www.reddit.com/user/Robin1762",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b14heg",
                "https://www.youtube.com/watch?v=EIK-YIZzk9c&feature=youtu.be",
                "https://www.reddit.com/domain/youtube.com/",
                "https://www.reddit.com/user/arcticricard",
                "https://www.reddit.com/r/youtubehaiku/",
                "https://www.reddit.com/r/youtubehaiku/comments/b13hoe/el_diego_poetry/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b13hoe",
                "https://www.theguardian.com/books/2019/mar/13/benedict-cumberbatch-power-of-letters-thom-yorke-noel-fielding-letters-live",
                "https://www.reddit.com/domain/theguardian.com/",
                "https://www.reddit.com/user/FearlessCranberry",
                "https://www.reddit.com/r/books/",
                "https://www.reddit.com/r/books/comments/b113nk/benedict_cumberbatch_on_the_explosive_power_of/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b113nk",
                "https://haskellweekly.news/issues/150.html",
                "https://www.reddit.com/domain/haskellweekly.news/",
                "https://www.reddit.com/user/haskellweekly",
                "https://www.reddit.com/r/haskell/",
                "https://www.reddit.com/r/haskell/comments/b1104y/issue_150_haskell_weekly/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b1104y",
                "https://streamja.com/5Zdg",
                "https://www.reddit.com/r/soccer/gilded",
                "https://www.reddit.com/r/soccer/comments/b16onj/benfica_30_dinamo_zagreb_31_on_agg_alex_grimaldo/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b16onj",
                "https://www.reddit.com/r/AskReddit/comments/b0zxa7/whats_the_human_equivalent_of_koalas_cant/",
                "https://www.reddit.com/r/AskReddit/",
                "https://www.reddit.com/user/RedBraixen",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b0zxa7",
                "https://i.redd.it/e3u7mol7l4m21.jpg",
                "https://www.reddit.com/user/Drifter776",
                "https://www.reddit.com/r/Romania/comments/b13os3/sus_e_bucure%C8%99tiul_jos_europa_civilizat%C4%83/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b13os3",
                "https://www.reddit.com/r/unixporn/comments/b13ylk/dwm_transitioning_to_suckless/",
                "https://www.reddit.com/user/N0_0NE32",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b13ylk",
                "https://www.telegraph.co.uk/news/uknews/theroyalfamily/11738382/Queen-stops-breeding-corgis-as-she-doesnt-want-to-leave-any-behind.html",
                "https://www.reddit.com/domain/telegraph.co.uk/",
                "https://www.reddit.com/user/Grungemaster",
                "https://www.reddit.com/r/todayilearned/comments/b13us2/til_queen_elizabeth_ii_stopped_breeding_welsh/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b13us2",
                "https://www.youtube.com/watch?v=NcJOiQlzlXQ",
                "https://www.reddit.com/user/rampion",
                "https://www.reddit.com/r/haskell/comments/b16ret/john_hughes_building_on_developers_intuitions/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b16ret",
                "https://www.reddit.com/r/AskReddit/comments/b0zjdk/whats_a_real_fact_that_some_people_dont_want_to/",
                "https://www.reddit.com/user/WifeKidsJob2",
                "https://www.reddit.com/r/AskReddit/gilded",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b0zjdk",
                "https://streamja.com/n7NA",
                "https://www.reddit.com/user/paicmhsc",
                "https://www.reddit.com/r/soccer/comments/b164cd/arsenal_30_rennes_aubameyang_72_agg_43/",
                "https://www.reddit.com/gold?goldtype=gift&months=1&thing=t3_b164cd",
                "https://www.reddit.com/?count=25&after=t3_b164cd",
                "https://www.reddit.com/#res:settings/neverEndingReddit",
                "https://redditblog.com/",
                "https://www.redditinc.com/",
                "https://www.redditinc.com/advertising",
                "https://www.redditinc.com/careers",
                "https://www.reddit.com/rules/",
                "https://www.reddithelp.com/",
                "https://www.reddit.com/wiki/reddiquette/",
                "https://www.reddit.com/help/healthycommunities/",
                "https://www.reddit.com/contact/",
                "https://itunes.apple.com/us/app/reddit-the-official-app/id1064216828?mt=8",
                "https://play.google.com/store/apps/details?id=com.reddit.frontpage",
                "https://www.reddit.com/premium/",
                "https://www.reddit.com/coins/",
                "https://redditgifts.com/",
                "https://www.reddit.com/help/useragreement",
                "https://www.reddit.com/help/privacypolicy",
                "https://www.reddit.com/subreddits/",
                "https://www.reddit.com/#header"
        };

        for(String url : urls){
            assertTrue(v.isValid(url));
        }
    }

    public void testValidGoogleUrls()
    {
        String[] schemes = {"https"};
        var v = new UrlValidator(schemes, 0);

        String[] urls = {
                "https://support.google.com/websearch/answer/181196?hl=en",
                "https://www.google.com/intl/en/about/products?tab=wh",
                "https://myaccount.google.com/?utm_source=OGB&tab=wk&authuser=0&utm_medium=app",
                "https://mail.google.com/mail/?tab=wm&authuser=0",
                "https://drive.google.com/?tab=wo&authuser=0",
                "https://docs.google.com/document/?usp=docs_ald&authuser=0",
                "https://docs.google.com/spreadsheets/?usp=sheets_ald&authuser=0",
                "https://docs.google.com/presentation/?usp=slides_ald&authuser=0",
                "https://www.google.com/calendar?tab=wc&authuser=0",
                "https://meet.google.com/?pli=1&authuser=0",
                "https://plus.google.com/u/0/?gpsrc=ogpy0&tab=wX",
                "https://sites.google.com/?tab=w3&authuser=0",
                "https://contacts.google.com/?hl=en&tab=wC&authuser=0",
                "https://www.google.com/search?q=cats&oq=cats&aqs=chrome..69i57j0l5.765j0j7&sourceid=chrome&ie=UTF-8#",
                "https://groups.google.com/d/homeredir?hl=en&tab=wg&authuser=0",
                "https://www.youtube.com/?gl=US&authuser=0",
                "https://maps.google.com/maps?hl=en&tab=wl&authuser=0",
                "https://news.google.com/nwshp?hl=en&tab=wn&authuser=0",
                "https://ads.google.com/home/?subid=ww-ww-et-g-aw-a-vasquette_ads_1!o2",
                "https://photos.google.com/?tab=wq&authuser=0&pageId=none",
                "https://translate.google.com/?hl=en&tab=wT&authuser=0",
                "https://ediscovery.google.com/discovery/",
                "https://hangouts.google.com/?authuser=0",
                "https://docs.google.com/forms/?authuser=0&usp=forms_ald",
                "https://keep.google.com/u/0",
                "https://cloudsearch.google.com/cloudsearch",
                "https://earth.google.com/web/?authuser=0",
                "https://www.google.com/save?authuser=0",
                "https://www.google.com/enterprise/marketplace/search?authuser=0",
                "https://accounts.google.com/SignOutOptions?hl=en&continue=https://www.google.com/search%3Fq%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8",
                "https://www.google.com/support/accounts/bin/answer.py?answer=181692&hl=en",
                "https://myaccount.google.com/?utm_source=OGB&tab=wk&authuser=0",
                "https://myaccount.google.com/privacypolicy",
                "https://myaccount.google.com/?utm_source=OGB&tab=wk&authuser=0&utm_medium=act",
                "https://www.google.com/webhp?authuser=0",
                "https://www.google.com/webhp?authuser=1",
                "https://www.google.com/webhp?authuser=2",
                "https://www.google.com/webhp?authuser=3",
                "https://myaccount.google.com/brandaccounts?authuser=0&continue=https://www.google.com/search%3Fq%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8&service=https://www.google.com/webhp%3Fauthuser%3D%24authuser",
                "https://accounts.google.com/AddSession?hl=en&continue=https://www.google.com/search%3Fq%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8",
                "https://accounts.google.com/Logout?hl=en&continue=https://www.google.com/search%3Fq%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8&timeStmp=1552610874&secTok=.AG5fkS_okGF1wh9O9o_gk1f7tZQMlDMzOw",
                "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQPAgH",
                "https://www.google.com/search?q=cats&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIDigB",
                "https://www.google.com/search?q=cats&source=lnms&tbm=vid&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIDygC",
                "https://www.google.com/search?q=cats&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIECgD",
                "https://www.google.com/search?q=cats&source=lnms&tbm=shop&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIESgE",
                "https://maps.google.com/maps?q=cats&um=1&ie=UTF-8&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIEygA",
                "https://www.google.com/search?q=cats&source=lnms&tbm=bks&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIFCgB",
                "https://www.google.com/flights?q=cats&source=lnms&tbm=flm&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIFSgC",
                "https://www.google.com/search?q=cats&source=lnms&tbm=fin&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_AUIFigD",
                "https://www.google.com/preferences",
                "https://www.google.com/preferences?hl=en&prev=https://www.google.com/search?q%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8",
                "https://www.google.com/preferences?hl=en&prev=https://www.google.com/search?q%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8#languages",
                "https://www.google.com/setprefs?safeui=on&sig=0_mwIaeMt62JaT26C5nlf5xjayuX8%3D&prev=https://www.google.com/search?q%3Dcats%26oq%3Dcats%26aqs%3Dchrome..69i57j0l5.765j0j7%26sourceid%3Dchrome%26ie%3DUTF-8",
                "https://www.google.com/advanced_search?q=cats&hl=en",
                "https://myactivity.google.com/privacyadvisor/search?utm_source=googlemenu",
                "https://www.google.com/history/?hl=en",
                "https://support.google.com/websearch/?source=g&hl=en",
                "https://www.google.com/search?q=cats&source=lnt&tbs=qdr:h&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://www.google.com/search?q=cats&source=lnt&tbs=qdr:d&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://www.google.com/search?q=cats&source=lnt&tbs=qdr:w&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://www.google.com/search?q=cats&source=lnt&tbs=qdr:m&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://www.google.com/search?q=cats&source=lnt&tbs=qdr:y&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://www.google.com/search?q=cats&source=lnt&tbs=li:1&sa=X&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQpwUIJA",
                "https://coleandmarmalade.com/2019/03/14/stray-ginger-cat-has-mange-so-bad-he-cant-see-now-look-what-a-little-love-has-done-for-him/",
                "https://www.wfsb.com/news/hearing-to-be-held-on-proposal-to-register-pet-cats/article_255f08ac-45c8-11e9-b996-a7fc9bed718f.html",
                "https://slate.com/human-interest/2019/03/cat-moving-cross-country-flying-driving-tsa-pet-advice.html",
                "https://dailyvoice.com/connecticut/fairfield/news/proposed-connecticut-cat-tax-sparks-outrage/749522/",
                "https://www.al.com/news/montgomery/2019/03/dogs-cats-homeless-after-alabama-tornadoes-find-homes-in-oregon-tennessee.html",
                "https://www.vulture.com/2019/03/carly-rae-jepsens-now-that-i-found-you-music-video.html",
                "https://io9.gizmodo.com/captain-marvels-cat-actors-had-trouble-performing-with-1833267933",
                "https://boston.cbslocal.com/2019/03/14/cat-missing-5-years-larry-essex-reunited/",
                "https://www.themoscowtimes.com/2019/03/14/russias-cat-city-installs-feline-traffic-lights-a64799",
                "https://www.wivb.com/news/local-news/proposed-law-would-allow-municipal-shelters-to-adopt-out-cats-sooner/1847306074",
                "https://www.google.com/search?q=cats&tbm=nws&source=univ&tbo=u&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQt8YBKAF6BAgAEC4",
                "https://www.catsthemusical.com/",
                "https://webcache.googleusercontent.com/search?q=cache:G2yzDVonCCcJ:https://www.catsthemusical.com/+&cd=11&hl=en&ct=clnk&gl=us",
                "http://www.vetstreet.com/cats/",
                "http://webcache.googleusercontent.com/search?q=cache:TZjlhnpEfcwJ:www.vetstreet.com/cats/+&cd=12&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:www.vetstreet.com/cats/+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzALegQIDhAF",
                "https://www.purina.com/cats/cat-breeds",
                "https://webcache.googleusercontent.com/search?q=cache:dP37fI7ZqvAJ:https://www.purina.com/cats/cat-breeds+&cd=13&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:https://www.purina.com/cats/cat-breeds+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzAMegQIDRAG",
                "https://www.youtube.com/watch?v=5dsGWM5XGdg",
                "https://www.youtube.com/watch?v=hY7m5jjJ9mM",
                "https://mashable.com/video/cats-freak-out-over-auto-tuned-meowing/",
                "https://www.youtube.com/watch?v=DXUAyRRkI6k",
                "https://www.youtube.com/watch?v=vASw0m6YdWs",
                "https://www.youtube.com/watch?v=bDuLeXx2Gv0",
                "https://www.youtube.com/watch?v=Jsj-hDW9bS8",
                "https://www.youtube.com/watch?v=5530I_pYjbo",
                "https://www.youtube.com/watch?v=iRXJXaLV0n4",
                "https://en.wikipedia.org/wiki/Cats_(musical)",
                "https://webcache.googleusercontent.com/search?q=cache:DPQd28_N2r0J:https://en.wikipedia.org/wiki/Cats_(musical)+&cd=24&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:https://en.wikipedia.org/wiki/Cats_(musical)+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzAXegQIChAF",
                "https://en.wikipedia.org/wiki/Andrew_Lloyd_Webber",
                "https://en.wikipedia.org/wiki/New_London_Theatre",
                "https://en.wikipedia.org/wiki/Laurence_Olivier_Award_for_Best_New_Musical",
                "https://en.wikipedia.org/wiki/Old_Possum%27s_Book_of_Practical_Cats",
                "https://en.wikipedia.org/wiki/Jellicle_cats",
                "https://en.wikipedia.org/wiki/Category:Cats_(musical)",
                "https://en.wikipedia.org/wiki/Cats_(1998_film)",
                "https://en.wikipedia.org/wiki/Kennelly%E2%80%93Heaviside_layer",
                "https://www.pet-happy.com/how-many-cat-breeds-are-there/",
                "https://www.google.com/search?q=How+many+cat+breeds+are+there+2018%3F&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQzmd6BAgHEAo",
                "https://www.google.com/search?q=What+does+Jellicle+Cats+mean%3F&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQzmd6BAgHEBI",
                "https://cattime.com/cat-facts/how-to/18459-5-ways-to-show-your-cats-love-that-they-can-understand",
                "https://www.google.com/search?q=How+do+you+tell+your+cat+you+love+them%3F&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQzmd6BAgHEBo",
                "http://mentalfloss.com/article/51154/10-scientific-benefits-being-cat-owner",
                "https://www.google.com/search?q=Why+are+cats+good+pets%3F&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQzmd6BAgHECI",
                "https://www.webmd.com/pets/cats/default.htm",
                "https://webcache.googleusercontent.com/search?q=cache:1TemeU_E0JQJ:https://www.webmd.com/pets/cats/default.htm+&cd=33&hl=en&ct=clnk&gl=us",
                "https://www.petfinder.com/cats/",
                "https://webcache.googleusercontent.com/search?q=cache:Yz37EcZzoLYJ:https://www.petfinder.com/cats/+&cd=34&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:https://www.petfinder.com/cats/+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzAhegQICBAF",
                "http://www.animalplanet.com/pets/cats/",
                "http://webcache.googleusercontent.com/search?q=cache:5VtO_DzNmqgJ:www.animalplanet.com/pets/cats/+&cd=35&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:www.animalplanet.com/pets/cats/+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzAiegQIDxAF",
                "https://www.youtube.com/user/catsmusical",
                "https://webcache.googleusercontent.com/search?q=cache:29KiNU-nDgUJ:https://www.youtube.com/user/catsmusical+&cd=36&hl=en&ct=clnk&gl=us",
                "https://www.google.com/search?q=related:https://www.youtube.com/user/catsmusical+cats&tbo=1&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQHzAjegQIDBAF",
                "https://www.google.com/search?q=cats+musical&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoAHoECAUQAQ",
                "https://www.google.com/search?q=cats+breeds&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoAXoECAUQAg",
                "https://www.google.com/search?q=cats+movie&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoAnoECAUQAw",
                "https://www.google.com/search?q=cats+broadway&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoA3oECAUQBA",
                "https://www.google.com/search?q=cute+cats&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoBHoECAUQBQ",
                "https://www.google.com/search?q=cats+the+musical+tour+2018&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoBXoECAUQBg",
                "https://www.google.com/search?q=cats+musical+characters&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoBnoECAUQBw",
                "https://www.google.com/search?q=cats+musical+london&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ1QIoB3oECAUQCA",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=10&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCPgB",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=20&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCPoB",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=30&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCPwB",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=40&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCP4B",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=50&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCIAC",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=60&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCIIC",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=70&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCIQC",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=80&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCIYC",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=90&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8tMDCIgC",
                "https://www.google.com/search?q=cats&ei=OvaKXM39G5fr-gTq07WYBw&start=10&sa=N&ved=0ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ8NMDCIoC",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=hMjxy8pUhhc4QM%253A%252C3aBlXpmFZqFG2M%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kQPxRcBpNpDNC37rLlof7N5GRi2eg&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_B0wJHoECAQQBg#imgrc=hMjxy8pUhhc4QM:",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=hM7lHKPVzV8OWM%253A%252C5VtO_DzNmqgMwM%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kSYV_21yDj3r9_iuMUZvva46UJXbQ&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_h0wJXoECAQQCA#imgrc=hM7lHKPVzV8OWM:",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=vCUeFAeHuXkdzM%253A%252CTZjlhnpEfcyulM%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kRDvJjy5JP-hCOEBvLX2T8DzcPw2g&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_h0wJnoECAQQCg#imgrc=vCUeFAeHuXkdzM:",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=WJTnHdXVzqil9M%253A%252CmvBf4-NuAmclGM%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kTl9WO3jO_GvoaPd7HNT6zE3cJl2w&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_h0wJ3oECAQQDA#imgrc=WJTnHdXVzqil9M:",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=05qMyLkCO8jcmM%253A%252CYz37EcZzoLb_6M%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kRLBn8qY_ozBOmAHDEfm1H6-j4OPw&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_h0wKHoECAQQDg#imgrc=05qMyLkCO8jcmM:",
                "https://www.google.com/search?q=cats&tbm=isch&source=iu&ictx=1&fir=X_ZhD0FDUhMsqM%253A%252CTZjlhnpEfcyulM%252C%252Fm%252F01yrx&vet=1&usg=AI4_-kR7ps4Jjq01N4tYlKmHBhgEoqsMNA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ_h0wKXoECAQQEA#imgrc=X_ZhD0FDUhMsqM:",
                "https://www.google.com/search?q=cats&tbm=isch&source=univ&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQiR56BAgEEBE",
                "https://en.wikipedia.org/wiki/Cat",
                "https://www.google.com/search?q=cat+lifespan&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtDSyU620k_KzM_JT6_Uzy9KT8zLLM6NT85JLC7OTMtMTizJzM-zyslMSy0uSMxbxMoDFFGAcQH6bPZNSQAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAtegQIAhAG",
                "https://www.google.com/search?q=cat+gestation+period&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtAyyU620k_KzM_JT6_Uzy9KT8zLLM6NT85JLC7OTMtMTizJzM-zSk8tLgGzFApSizLzUxaxigBlFNCFAYym0MBZAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAuegQIAhAJ",
                "https://www.google.com/search?q=cat+daily+sleep&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtDSz0620k_KzM_JT6_Uzy9KT8zLLM6NT85JLC7OTMtMTizJzM-zSknMzKlUKM5JTS1YxMoPFFRAEgEAkqwuTU8AAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAvegQIAhAM",
                "https://www.google.com/search?q=cat+mass&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtDSyE620k_KzM_JT6_Uzy9KT8zLLM6NT85JLC7OTMtMTizJzM-zygXyFrFyAHkKICYAJeZmhUEAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAwegQIAhAP",
                "https://www.google.com/search?q=cat+scientific+name&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtCyz0620k_KzM_JT6_Uzy9KT8zLLM6NT85JLC7OTMtMTizJzM-zKk7OTM0rAfEV8hJzUxUS81IUclOBSvPSF7EKAxUpoKkAAAdi-UFjAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAxegQIAhAS",
                "https://www.google.com/search?q=cat+did+you+know&stick=H4sIAAAAAAAAAOPgE-LQz9U3MKwsqtCSyU620k_Oz83Nz9MvyS_ITLZKK81LS0wuKY7PTl7EKpCcWKKQkpmiUJlfqpCdl18OAPfUG9w9AAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6BMoADAyegQIAhAV",
                "https://www.facttoss.com/amazing-interesting-facts-cats/",
                "https://www.google.com/search?q=cat+breeds&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlCCs7TUspOt9JMy83Py0yv1U_JzU4tLMpMTS1JT4hPzMnMTc6ySilJTU4oXsXIBRRUgHABAp7KUSwAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQMSgAMDN6BAgDEAE",
                "https://www.google.com/search?q=cat+breeds&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlCCs7TUspOt9JMy83Py0yv1U_JzU4tLMpMTS1JT4hPzMnMTc6ySilJTU4oXsXIBRRUgHABAp7KUSwAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQzTooATAzegQIAxAC",
                "https://www.google.com/search?q=Russian+Blue&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlDiBLOMkpONtPj9i9IT8zKLc0PynYpSU1MWsfIElRYXZybmKTjllKYCAGfqRns3AAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQxA0wM3oECAMQBQ",
                "https://www.google.com/search?q=Persian+cat&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlACs7JMzMq0-P2L0hPzMotzQ_KdilJTUxaxcgekFhVnJuYpJCeWAAAQE9udNQAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQxA0wM3oECAMQBw",
                "https://www.google.com/search?q=Siamese+cat&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlACs9LjjYu0-P2L0hPzMotzQ_KdilJTUxaxcgdnJuamFqcqJCeWAADcNbVCNQAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQxA0wM3oECAMQCQ",
                "https://www.google.com/search?q=Scottish+Fold&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlDiBLOMsjIstfj9i9IT8zKLc0PynYpSU1MWsfIGJ-eXlGQWZyi45eekAABPfjkfOAAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQxA0wM3oECAMQCw",
                "https://www.google.com/search?q=Maine+Coon&stick=H4sIAAAAAAAAAONgFuLQz9U3MKwsqlACs7LMDLK0-P2L0hPzMotzQ_KdilJTUxaxcvkmZualKjjn5-cBADSbVe40AAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQxA0wM3oECAMQDQ",
                "https://www.google.com/search?q=Cats+(musical)&stick=H4sIAAAAAAAAAONgecSYwC3w8sc9YanwSWtOXmMM5OIKzsgvd80rySypFFLjYoOyZLh4pTj1c_UNDNMyDOM1GKS4uRBcJWkj_l2Xpp1j4xR0v7NbZdp1L4emfSsOsbFwMAow8Cxi5XNOLClW0MgtLc5MTszRBACcuAM-dgAAAA&sa=X&ved=2ahUKEwjNgpad9oLhAhWXtZ4KHeppDXMQ6RMwNXoECAYQBA",
                "https://support.google.com/websearch?p=ws_settings_location&hl=en",
                "https://support.google.com/websearch/?p=ws_results_help&hl=en&fg=1",
                "https://www.google.com/intl/en_us/policies/privacy/?fg=1",
                "https://www.google.com/intl/en_us/policies/terms/?fg=1",
        };
        for(String url : urls) {
            assertTrue(v.isValid(url));
        }
    }

    public void testValidSchemes()
    {
        var v = new UrlValidator();

        String[] schemes = {"http", "https", "ftp"};

        for (String scheme:schemes) assertTrue(v.isValidScheme(scheme));
    }

    public void testInValidSchemes()
    {
        var v = new UrlValidator();

        String[] schemes = {"httpz", "", "_ftp", null, "\\", "..."};

        for (String scheme:schemes) assertFalse(v.isValidScheme(scheme));
    }

    public void testOptions(){
        long options = UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_LOCAL_URLS;

        var v = new UrlValidator(options);

        String[] urls = {
            "http://linuxcommand.org//index.php",
            "http://linuxcommand.org//lc3_learning_the_shell.php",
            "http://localhost/",
            "http://localhost:3000/",
            "file:///~User/2ndFile.html"
        };

        for(String url : urls) {
            assertTrue(v.isValid(url));
        }


    }
}
