package com.info.util;

import java.math.BigDecimal;
import java.math.RoundingMode;




/**
 * @author vijay
 * utility class just to round off decimal to 2 places
 */
public class PrecisionHelper {
	final static int DECIMAL_PLACES = 2;

	public static double round(double value) {
		return round(value, DECIMAL_PLACES);
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
