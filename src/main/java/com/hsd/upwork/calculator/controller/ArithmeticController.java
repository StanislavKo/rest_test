package com.hsd.upwork.calculator.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.upwork.calculator.component.RedisBean;
import com.hsd.upwork.calculator.consts.Consts;
import com.hsd.upwork.calculator.controller.pojo.ArithmeticResponse;
import com.hsd.upwork.calculator.controller.pojo.ResponseCodes;
import com.hsd.upwork.calculator.exception.IncorrectArgumentException;
import com.hsd.upwork.calculator.utils.ArithmeticsUtils;

@RestController
public class ArithmeticController {

	private static final Logger logger = LoggerFactory.getLogger(ArithmeticController.class);

	@Autowired
	private RedisBean cacheBean;

	/**
	 * Summarize arguments
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/add/{arg1}/{arg2}", "/add/{arg1}/{arg2}/{arg3}" }, method = RequestMethod.GET)
	public ArithmeticResponse add(@PathVariable String arg1, @PathVariable String arg2, @PathVariable Optional<String> arg3) {
		String cacheKey = getCacheKey("add", arg1, arg2, arg3);
		try {
			int num = Integer.valueOf(Optional.ofNullable(cacheBean.getValue(cacheKey))
					.orElse(cacheBean.setValue(cacheKey, String.valueOf(ArithmeticsUtils.add(arg1, arg2, arg3)))));
			return new ArithmeticResponse(ResponseCodes.OK, String.valueOf(num));
		} catch (IncorrectArgumentException e) {
			return new ArithmeticResponse(ResponseCodes.ARITHMETIC_FAILED, Consts.ERROR_ILLEGAL_ARGUMENTS);
		}
	}

	/**
	 * Subtract arguments
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/subtract/{arg1}/{arg2}", "/subtract/{arg1}/{arg2}/{arg3}" }, method = RequestMethod.GET)
	public ArithmeticResponse subtract(@PathVariable String arg1, @PathVariable String arg2, @PathVariable Optional<String> arg3) {
		String cacheKey = getCacheKey("subtract", arg1, arg2, arg3);
		try {
			int num = Integer.valueOf(Optional.ofNullable(cacheBean.getValue(cacheKey))
					.orElse(cacheBean.setValue(cacheKey, String.valueOf(ArithmeticsUtils.subtract(arg1, arg2, arg3)))));
			return new ArithmeticResponse(ResponseCodes.OK, String.valueOf(num));
		} catch (IncorrectArgumentException e) {
			return new ArithmeticResponse(ResponseCodes.ARITHMETIC_FAILED, Consts.ERROR_ILLEGAL_ARGUMENTS);
		}
	}

	/**
	 * Multiply arguments
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/multiply/{arg1}/{arg2}", "/multiply/{arg1}/{arg2}/{arg3}" }, method = RequestMethod.GET)
	public ArithmeticResponse multiply(@PathVariable String arg1, @PathVariable String arg2, @PathVariable Optional<String> arg3) {
		String cacheKey = getCacheKey("multiply", arg1, arg2, arg3);
		try {
			int num = Integer.valueOf(Optional.ofNullable(cacheBean.getValue(cacheKey))
					.orElse(cacheBean.setValue(cacheKey, String.valueOf(ArithmeticsUtils.multiply(arg1, arg2, arg3)))));
			return new ArithmeticResponse(ResponseCodes.OK, String.valueOf(num));
		} catch (IncorrectArgumentException e) {
			return new ArithmeticResponse(ResponseCodes.ARITHMETIC_FAILED, Consts.ERROR_ILLEGAL_ARGUMENTS);
		}
	}

	/**
	 * Divide arguments
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/divide/{arg1}/{arg2}", "/divide/{arg1}/{arg2}/{arg3}" }, method = RequestMethod.GET)
	public ArithmeticResponse divide(@PathVariable String arg1, @PathVariable String arg2, @PathVariable Optional<String> arg3) {
		String cacheKey = getCacheKey("divide", arg1, arg2, arg3);
		try {
			int num = Integer.valueOf(Optional.ofNullable(cacheBean.getValue(cacheKey))
					.orElse(cacheBean.setValue(cacheKey, String.valueOf(ArithmeticsUtils.divide(arg1, arg2, arg3)))));
			return new ArithmeticResponse(ResponseCodes.OK, String.valueOf(num));
		} catch (IncorrectArgumentException e) {
			return new ArithmeticResponse(ResponseCodes.ARITHMETIC_FAILED, Consts.ERROR_ILLEGAL_ARGUMENTS);
		}
	}

	private String getCacheKey(String op, String arg1, String arg2, Optional<String> arg3) {
		return String.format("%s_%s_%s_%s", op, arg1, arg2, arg3.orElse(""));
	}

}