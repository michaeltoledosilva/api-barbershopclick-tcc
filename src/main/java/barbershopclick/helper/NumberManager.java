package barbershopclick.helper;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberManager {
	private static NumberManager instance;

	public NumberManager() {
	}

	public static NumberManager getInstance() {
		if (instance == null) {
			instance = new NumberManager();
		}

		return instance;
	}

	public Boolean isNullOrZero(final BigDecimal... arrayBigDecimal) {
		for (final BigDecimal item : arrayBigDecimal) {
			if (item == null || item.compareTo(BigDecimal.ZERO) == 0) {
				return true;
			}
		}
		return false;
	}

	public Boolean isNullOrZero(final BigInteger... arrayBigInteger) {
		for (final BigInteger item : arrayBigInteger) {
			if (item == null || item.equals(0)) {
				return true;
			}
		}
		return false;
	}

	public Boolean isNullOrZero(final Double... arrayDouble) {
		for (final Double item : arrayDouble) {
			if (item == null || item.equals(0.)) {
				return true;
			}
		}
		return false;
	}

	public Boolean isNullOrZero(final Integer... arrayInteger) {
		for (final Integer item : arrayInteger) {
			if (item == null || item.equals(0)) {
				return true;
			}
		}
		return false;
	}
}
