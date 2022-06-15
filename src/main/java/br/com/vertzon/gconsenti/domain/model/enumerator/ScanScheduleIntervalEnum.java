package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum ScanScheduleIntervalEnum {
	UNICO("UNICO"), DIARIO("DIARIO"), SEMANAL("SEMANAL"), QUINZENAL("QUINZENAL"), MENSAL("MENSAL");

	private final String label;

	private ScanScheduleIntervalEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static ScanScheduleIntervalEnum getValueOfLabel(String label) {
		for (ScanScheduleIntervalEnum scanScheduleIntervalEnumValue : values()) {
			if (scanScheduleIntervalEnumValue.label.equals(label)) {
				return scanScheduleIntervalEnumValue;
			}
		}
		return null;
	}
}
