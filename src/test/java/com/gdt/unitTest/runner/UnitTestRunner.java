package com.gdt.unitTest.runner;

import com.gdt.unitTest.httpClientTest.BasicFunctionalityTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicFunctionalityTest.class
})

public class UnitTestRunner {

}
