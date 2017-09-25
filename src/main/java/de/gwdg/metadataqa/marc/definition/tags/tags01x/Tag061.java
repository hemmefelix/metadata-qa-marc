package de.gwdg.metadataqa.marc.definition.tags.tags01x;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.Indicator;

/**
 * National Library of Medicine Copy Statement
 * http://www.loc.gov/marc/bibliographic/bd061.html
 */
public class Tag061 extends DataFieldDefinition {

	private static Tag061 uniqueInstance;

	private Tag061() {
		initialize();
	}

	public static Tag061 getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Tag061();
		return uniqueInstance;
	}

	private void initialize() {

		tag = "061";
		label = "National Library of Medicine Copy Statement";
		mqTag = "NlmCopy";
		cardinality = Cardinality.Repeatable;

		ind1 = new Indicator();
		ind2 = new Indicator();

		setSubfieldsWithCardinality(
			"a", "Classification number", "R",
			"b", "Item number", "NR",
			"c", "Copy information", "NR",
			"8", "Field link and sequence number", "R"
		);

		getSubfield("a").setMqTag("classificationPortion").setMqTag("classification");;
		getSubfield("b").setMqTag("itemPortion").setMqTag("item");;
		getSubfield("c").setMqTag("copy");
		getSubfield("8").setMqTag("fieldLink");
	}
}