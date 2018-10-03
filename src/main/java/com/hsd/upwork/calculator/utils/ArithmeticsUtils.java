package com.hsd.upwork.calculator.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsd.upwork.calculator.exception.IncorrectArgumentException;

public class ArithmeticsUtils {

	private static final Logger logger = LoggerFactory.getLogger(ArithmeticsUtils.class);

	public static int add(String arg1Str, String arg2Str, Optional<String> arg3Str) throws IncorrectArgumentException {
		try {
			verifyArgs(arg1Str, arg2Str, arg3Str);
			return Integer.valueOf(arg1Str) + Integer.valueOf(arg2Str) + Integer.valueOf(arg3Str.orElse("0"));
		} catch (NumberFormatException e) {
			logger.info("add arguments are not integers");
			throw new IncorrectArgumentException(e);
		}
	}

	public static int subtract(String arg1Str, String arg2Str, Optional<String> arg3Str) throws IncorrectArgumentException {
		try {
			verifyArgs(arg1Str, arg2Str, arg3Str);
			return Integer.valueOf(arg1Str) - Integer.valueOf(arg2Str) - Integer.valueOf(arg3Str.orElse("0"));
		} catch (NumberFormatException e) {
			logger.info("subtract arguments are not integers");
			throw new IncorrectArgumentException(e);
		}
	}

	public static int multiply(String arg1Str, String arg2Str, Optional<String> arg3Str) throws IncorrectArgumentException {
		try {
			verifyArgs(arg1Str, arg2Str, arg3Str);
			return Integer.valueOf(arg1Str) * Integer.valueOf(arg2Str) * Integer.valueOf(arg3Str.orElse("1"));
		} catch (NumberFormatException e) {
			logger.info("multiply arguments are not integers");
			throw new IncorrectArgumentException(e);
		}
	}

	public static int divide(String arg1Str, String arg2Str, Optional<String> arg3Str) throws IncorrectArgumentException {
		try {
			verifyArgs(arg1Str, arg2Str, arg3Str);
			if ("0".equals(arg2Str) || "0".equals(arg3Str.orElse("1"))) {
				logger.info("divide argument is zero");
				throw new IncorrectArgumentException();
			}
			return Integer.valueOf(arg1Str) / Integer.valueOf(arg2Str) / Integer.valueOf(arg3Str.orElse("1"));
		} catch (NumberFormatException e) {
			logger.info("divide arguments are not integers");
			throw new IncorrectArgumentException(e);
		}
	}

	/**
	 * Verify that arguments are valid integers.
	 * @param arg1Str
	 * @param arg2Str
	 * @param arg3Str
	 */
	private static void verifyArgs(String arg1Str, String arg2Str, Optional<String> arg3Str) {
		Integer.valueOf(arg1Str);
		Integer.valueOf(arg2Str);
		Integer.valueOf(arg3Str.orElse("0"));
	}

}
