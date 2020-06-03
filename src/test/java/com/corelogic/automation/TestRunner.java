package com.corelogic.automation;

import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.corelogic.utaf.main.MainDriver;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class TestRunner
{
    //~ Methods --------------------------------------------------------------------------

    @Parameters({"env",
        "testlevel",
        "suite",
        "browser",
        "emailto",
        "host",
        "port"
    })
    @BeforeSuite()
    public static void test(
        @Optional("")
    String                  env,
        @Optional("")
    String                  testLevel,
        @Optional("")
    String                  suiteName,
        @Optional("")
    String                  browser,
        @Optional("")
    String                  emailto,
        @Optional("")
    String                  host,
        @Optional("")
    String                  port)
    {
        MainDriver main = new MainDriver();
        main.setCommand(new Keywords());
        main.setBaseLogin(new BaseLogin());
        main.mainTestDriver(env, testLevel, suiteName, browser, emailto, host, port);
    }
}
