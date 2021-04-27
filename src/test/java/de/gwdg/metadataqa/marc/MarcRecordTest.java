package de.gwdg.metadataqa.marc;

import de.gwdg.metadataqa.api.util.FileUtils;
import de.gwdg.metadataqa.marc.dao.Control003;
import de.gwdg.metadataqa.marc.dao.Control005;
import de.gwdg.metadataqa.marc.dao.Control007;
import de.gwdg.metadataqa.marc.dao.Control008;
import de.gwdg.metadataqa.marc.dao.Leader;
import de.gwdg.metadataqa.marc.dao.MarcRecord;
import de.gwdg.metadataqa.marc.definition.controltype.Control007Category;
import de.gwdg.metadataqa.marc.utils.ReadMarc;
import de.gwdg.metadataqa.marc.utils.marcspec.legacy.MarcSpec;
import org.junit.Test;
import org.marc4j.marc.Record;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarcRecordTest {

  private static final Pattern positionalPattern = Pattern.compile("^(Leader|00[678])/(.*)$");

  @Test
  public void test() {
    Matcher matcher = positionalPattern.matcher("006/0");
    assertTrue(matcher.matches());
  }

  @Test
  public void testFromFile() throws Exception {
    Path path = FileUtils.getPath("general/0001-01.mrc");
    List<Record> records = ReadMarc.read(path.toString());
    MarcRecord marcRecord = MarcFactory.createFromMarc4j(records.get(0));

    String expected = "{\"leader\":\"00720cam a22002051  4500\"," +
      "\"001\":\"   00000002 \"," +
      "\"003\":\"DLC\"," +
      "\"005\":\"20040505165105.0\"," +
      "\"008\":\"800108s1899    ilu           000 0 eng  \"," +
      "\"010\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"   00000002 \"}}]," +
      "\"035\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"(OCoLC)5853149\"}}]," +
      "\"040\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"DLC\"," +
        "\"c\":\"DSI\"," +
        "\"d\":\"DLC\"}}]," +
      "\"050\":[{\"ind1\":\"0\",\"ind2\":\"0\",\"subfields\":{" +
        "\"a\":\"RX671\"," +
        "\"b\":\".A92\"}}]," +
      "\"100\":[{\"ind1\":\"1\",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"Aurand, Samuel Herbert,\",\"d\":\"1854-\"}}]," +
      "\"245\":[{\"ind1\":\"1\",\"ind2\":\"0\",\"subfields\":{" +
        "\"a\":\"Botanical materia medica and pharmacology;\"," +
        "\"b\":\"drugs considered from a botanical, pharmaceutical, physiological, therapeutical and toxicological standpoint.\"," +
        "\"c\":\"By S. H. Aurand.\"}}]," +
      "\"260\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"Chicago,\"," +
        "\"b\":\"P. H. Mallen Company,\"," +
        "\"c\":\"1899.\"}}]," +
      "\"300\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"406 p.\"," +
        "\"c\":\"24 cm.\"}}]," +
      "\"500\":[{\"ind1\":\" \",\"ind2\":\" \",\"subfields\":{" +
        "\"a\":\"Homeopathic formulae.\"}}]," +
      "\"650\":[" +
        "{\"ind1\":\" \",\"ind2\":\"0\",\"subfields\":{" +
          "\"a\":\"Botany, Medical.\"}}," +
        "{\"ind1\":\" \",\"ind2\":\"0\",\"subfields\":{" +
          "\"a\":\"Homeopathy\"," +
          "\"x\":\"Materia medica and therapeutics.\"}}]}";
    assertEquals(expected, marcRecord.asJson());
  }

  @Test
  public void testSelect() throws Exception {
    Path path = FileUtils.getPath("general/0001-01.mrc");
    List<Record> records = ReadMarc.read(path.toString());
    MarcRecord marcRecord = MarcFactory.createFromMarc4j(records.get(0));
    MarcSpec spec = new MarcSpec("008~0-5");
    List<String> results = marcRecord.select(spec);
    assertEquals(1, results.size());
    assertEquals("800108", results.get(0));

    spec = new MarcSpec("008~7-10");
    results = marcRecord.select(spec);
    assertEquals(1, results.size());
    assertEquals("1899", results.get(0));

    spec = new MarcSpec("008~0-1");
    results = marcRecord.select(spec);
    assertEquals(1, results.size());
    assertEquals("80", results.get(0));
  }

  @Test
  public void testMultiple007() throws Exception {
    MarcRecord marcRecord = new MarcRecord("010000011");
    marcRecord.setLeader(new Leader("00860cam a22002774a 45 0"));
    marcRecord.setControl003(new Control003("DE-627"));
    marcRecord.setControl005(new Control005("20180502143346.0"));
    marcRecord.setControl008(new Control008("861106s1985    xx |||||      10| ||ger c", marcRecord.getType()));
    marcRecord.setControl007(new Control007("tu"));
    marcRecord.setControl007(new Control007("at"));

    assertTrue(marcRecord.getControl007() instanceof List);
    assertEquals(2, marcRecord.getControl007().size());
    assertEquals("tu", marcRecord.getControl007().get(0).getContent());
    assertEquals(Control007Category.TEXT, marcRecord.getControl007().get(0).getCategory());
    assertEquals("at", marcRecord.getControl007().get(1).getContent());
    assertEquals(Control007Category.MAP, marcRecord.getControl007().get(1).getCategory());
  }

  @Test
  public void asJson() throws IOException, URISyntaxException {
    Path path = FileUtils.getPath("general/0001-01.mrc");
    List<Record> records = null;
    try {
      records = ReadMarc.read(path.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    MarcRecord marcRecord = MarcFactory.createFromMarc4j(records.get(0));
    assertNotNull(marcRecord);
    // System.err.println(record.asJson());
    assertTrue(marcRecord.asJson().contains("\"245\":[{\"ind1\":\"1\",\"ind2\":\"0\",\"subfields\":{\"a\":\"Botanical materia medica and pharmacology;\""));
  }

}
