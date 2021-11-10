package barbershopclick.enums;

public enum CortesEnum {

	NENHUM(0, "Nenhum"), CABELO(1, "Cabelo"), BARBA(2, "Barba"), AMBOS(3, "Ambos");

	public static CortesEnum valueOf(final int value) {
		for (final CortesEnum type : values()) {
			if (type.value == value) {
				return type;
			}
		}
		return CortesEnum.NENHUM;
	}

	private final Integer value;

	private final String text;

	CortesEnum(final Integer value, final String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public Integer getValue() {
		return this.value;
	}

	public Integer value() {
		return this.value;
	}
}
