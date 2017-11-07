package de.gwdg.metadataqa.marc.definition;

public enum ValidationErrorType {

	Obsolete("obsolete", "obsolete value"),
	HasInvalidValue("hasInvalidValue", "invalid value"),
	ContainsInvalidCode("containsInvalidCode", "contains invalid code"),
	UndefinedSubfield("undefinedSubfield", "undefined subfield"),
	InvalidLength("invalidLength", "invalid lengh"),
	MissingSubfield("missingSubfield", "missing subfield"),
	InvalidReference("invalidReference", "invalid reference"),
	NonEmptyIndicator("nonEmptyIndicator", "non-empty indicator"),
	PatternMismatch("patternMismatch", "does not match any patterns"),
	NonrepeatableSubfield("NonrepeatableSubfield", "non-repeatable subfield");

	private String code;
	private String message;

	ValidationErrorType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
