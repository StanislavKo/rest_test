package com.hsd.upwork.calculator;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsd.upwork.calculator.component.RedisBean;
import com.hsd.upwork.calculator.controller.ArithmeticController;
import com.hsd.upwork.calculator.controller.pojo.ArithmeticResponse;
import com.hsd.upwork.calculator.controller.pojo.ResponseCodes;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ArithmeticsEndpointsTest {

	private static final Logger logger = LoggerFactory.getLogger(ArithmeticsEndpointsTest.class);

	@Mock
	private RedisBean cacheBean;
	
	@InjectMocks
	private ArithmeticController controller;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Mockito.when(cacheBean.getValue("add_1_2_3")).thenReturn("6");
		Mockito.when(cacheBean.getValue("add_1_2_4")).thenReturn(null);
		Mockito.when(cacheBean.setValue("add_1_2_4", "7")).thenReturn("7");
		Mockito.when(cacheBean.getValue("add_1_2_")).thenReturn("3");
		Mockito.when(cacheBean.getValue("add_1_3_")).thenReturn(null);
		Mockito.when(cacheBean.setValue("add_1_3_", "4")).thenReturn("4");

		Mockito.when(cacheBean.getValue("subtract_1_2_3")).thenReturn("-4");
		Mockito.when(cacheBean.getValue("subtract_1_2_4")).thenReturn(null);
		Mockito.when(cacheBean.setValue("subtract_1_2_4", "-5")).thenReturn("-5");
		Mockito.when(cacheBean.getValue("subtract_1_2_")).thenReturn("-1");
		Mockito.when(cacheBean.getValue("subtract_1_3_")).thenReturn(null);
		Mockito.when(cacheBean.setValue("subtract_1_3_", "-2")).thenReturn("-2");

		Mockito.when(cacheBean.getValue("multiply_1_2_3")).thenReturn("6");
		Mockito.when(cacheBean.getValue("multiply_1_2_4")).thenReturn(null);
		Mockito.when(cacheBean.setValue("multiply_1_2_4", "8")).thenReturn("8");
		Mockito.when(cacheBean.getValue("multiply_1_2_")).thenReturn("2");
		Mockito.when(cacheBean.getValue("multiply_1_3_")).thenReturn(null);
		Mockito.when(cacheBean.setValue("multiply_1_3_", "3")).thenReturn("3");

		Mockito.when(cacheBean.getValue("divide_111_2_3")).thenReturn("18");
		Mockito.when(cacheBean.getValue("divide_111_2_4")).thenReturn(null);
		Mockito.when(cacheBean.setValue("divide_111_2_4", "13")).thenReturn("13");
		Mockito.when(cacheBean.getValue("divide_111_2_")).thenReturn("55");
		Mockito.when(cacheBean.getValue("divide_111_3_")).thenReturn(null);
		Mockito.when(cacheBean.setValue("divide_111_3_", "37")).thenReturn("37");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_add() {
		ArithmeticResponse response = controller.add("1", "2", Optional.of("3"));
		assertTrue(response.getMessage().equals("6"));
		response = controller.add("1", "2", Optional.of("4"));
		assertTrue(response.getMessage().equals("7"));
		response = controller.add("1", "2", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("3"));
		response = controller.add("1", "3", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("4"));
	}

	@Test
	public void test02_subtract() {
		ArithmeticResponse response = controller.subtract("1", "2", Optional.of("3"));
		assertTrue(response.getMessage().equals("-4"));
		response = controller.subtract("1", "2", Optional.of("4"));
		assertTrue(response.getMessage().equals("-5"));
		response = controller.subtract("1", "2", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("-1"));
		response = controller.subtract("1", "3", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("-2"));
	}

	@Test
	public void test03_multiply() {
		ArithmeticResponse response = controller.multiply("1", "2", Optional.of("3"));
		assertTrue(response.getMessage().equals("6"));
		response = controller.multiply("1", "2", Optional.of("4"));
		assertTrue(response.getMessage().equals("8"));
		response = controller.multiply("1", "2", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("2"));
		response = controller.multiply("1", "3", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("3"));
	}

	@Test
	public void test04_divide() {
		ArithmeticResponse response = controller.divide("111", "2", Optional.of("3"));
		assertTrue(response.getMessage().equals("18"));
		response = controller.divide("111", "2", Optional.of("4"));
		assertTrue(response.getMessage().equals("13"));
		response = controller.divide("111", "2", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("55"));
		response = controller.divide("111", "3", Optional.ofNullable(null));
		assertTrue(response.getMessage().equals("37"));
	}

	@Test
	public void test05_invalid() {
		ArithmeticResponse response = controller.add("1", "asdf", Optional.of("3"));
		assertTrue(response.getCode() == ResponseCodes.ARITHMETIC_FAILED);
	}

}
