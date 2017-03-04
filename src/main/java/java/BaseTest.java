package java;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager" ,defaultRollback = true )
public class BaseTest
{
    protected Logger logger = Logger.getLogger(getClass());

    @Before
    public void before()
    {
        logger.info("ø™ º≤‚ ‘");
    }

    @After
    public void after()
    {
        logger.info("Ω· ¯≤‚ ‘");
    }
}
