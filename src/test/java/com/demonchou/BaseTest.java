//package com.demonchou;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Rule;
//import org.junit.runner.RunWith;
//import org.powermock.modules.agent.PowerMockAgent;
//import org.powermock.modules.junit4.rule.PowerMockRule;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations =
//{"classpath:applicationContext-test.xml"})
//@Ignore
//public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests
//{
//	static
//	{
//		PowerMockAgent.initializeIfNeeded();
//	}
//
//	@Rule
//	public PowerMockRule rule = new PowerMockRule();
//
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Before
//	public void before()
//	{
//		logger.info("开始测试");
//	}
//
//	@After
//	public void after()
//	{
//		logger.info("结束测试");
//	}
//
//}
