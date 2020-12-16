package de.gwdg.metadataqa.marc.definition.tags.bltags;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.Indicator;

/**
 * Serial Acquisitions System Number
 */
public class TagAQN extends DataFieldDefinition {

  private static TagAQN uniqueInstance;

  private TagAQN() {
    initialize();
    postCreation();
  }

  public static TagAQN getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new TagAQN();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "AQN";
    label = "Acquisitions Notes Field";
    mqTag = "acquisitionsNotes";
    cardinality = Cardinality.Repeatable;
    // descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd037.html";
    // setCompilanceLevels("O");
    obsolete = true;

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Note", "NR"
    );

    getSubfield("a")
      .setMqTag("note");
  }
}
