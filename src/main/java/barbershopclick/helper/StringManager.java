package barbershopclick.helper;

public class StringManager {

	private static StringManager instance;

	public StringManager() {
	}

	public static StringManager getInstance() {
		if (instance == null) {
			instance = new StringManager();
		}
		return instance;
	}

	public boolean isNullOrEmpty(final String... arrayStrings) {
		for (final String item : arrayStrings) {
			if (item == null || item.equals("")) {
				return true;
			}
		}
		return false;
	}

}
