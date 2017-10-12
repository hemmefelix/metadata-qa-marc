package de.gwdg.metadataqa.marc.definition.controlsubfields;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.ControlSubfield;

/**
 * Target audience
 * https://www.loc.gov/marc/bibliographic/bd008m.html
 */
public class Tag008music22 extends ControlSubfield {
	private static Tag008music22 uniqueInstance;

	private Tag008music22() {
		initialize();
		extractValidCodes();
	}

	public static Tag008music22 getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Tag008music22();
		return uniqueInstance;
	}

	private void initialize() {
		label = "Target audience";
		id = "tag008music22";
		mqTag = "targetAudience";
		positionStart = 22;
		positionEnd = 23;
		descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd008m.html";
		codes = Utils.generateCodes(
			" ", "Unknown or unspecified",
			"a", "Preschool",
			"b", "Primary",
			"c", "Pre-adolescent",
			"d", "Adolescent",
			"e", "Adult",
			"f", "Specialized",
			"g", "General",
			"j", "Juvenile",
			"|", "No attempt to code"
		);
	}
}