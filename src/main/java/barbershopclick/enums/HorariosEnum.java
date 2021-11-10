package barbershopclick.enums;

public enum HorariosEnum {
	OITO(8, "08:00"), NOVE(9, "09:00"), DEZ(10, "10:00"), ONZE(11, "11:00"), TREZE(13, "13:00"), QUATORZE(14, "14:00"),
	QUINZE(15, "15:00"), DEZESSEIS(16, "16:00"), DEZESSETE(17, "17:00");

	public static HorariosEnum valueOf(final int value) {
		for (final HorariosEnum type : values()) {
			if (type.value == value) {
				return type;
			}
		}
		return HorariosEnum.OITO;
	}

	private final Integer value;

	private final String text;

	HorariosEnum(final Integer value, final String text) {
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