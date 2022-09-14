import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Options {
    // Load
    private boolean loadEnabled = false;
    private String loadFileName = "basket.json";
    private String loadFormat = "json";
    // Save
    private boolean saveEnabled = true;
    private String saveFileName = "basket.json";
    private String saveFormat = "json";
    // Logs
    private boolean logEnabled = true;
    private String logFileName = "client.csv";

    public Options(){

    }

    public Options(File xmlFile){
        try {
            Options options = loadDataFromXML(xmlFile);

            if(options != null) {
                setLoadEnabled(options.isLoadEnabled());
                setLoadFileName(options.getLoadFileName());
                setLoadFormat(options.getLoadFormat());

                setSaveEnabled(options.isSaveEnabled());
                setSaveFileName(options.getSaveFileName());
                setSaveFormat(options.getSaveFormat());

                setLogEnabled(options.isLogEnabled());
                setLogFileName(options.getLogFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Options loadDataFromXML(File xmlFile) throws IOException {
        Options option = new Options();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            Node configList = doc.getDocumentElement(); // doc.getElementsByTagName("config").item(0);

            Node loadNodesList = ((Element)configList).getElementsByTagName("load").item(0);
            Node saveNodesList = ((Element)configList).getElementsByTagName("save").item(0);
            Node logNodesList = ((Element)configList).getElementsByTagName("log").item(0);

            // Load
            Node loadEnabled = ((Element) loadNodesList).getElementsByTagName("enabled").item(0);
            option.setLoadEnabled(Boolean.parseBoolean(loadEnabled.getTextContent()));

            Node loadFileName = ((Element) loadNodesList).getElementsByTagName("fileName").item(0);
            option.setLoadFileName(loadFileName.getTextContent());

            Node loadFormat = ((Element) loadNodesList).getElementsByTagName("format").item(0);
            option.setLoadFormat(loadFormat.getTextContent());

            // Save
            Node saveEnabled = ((Element) saveNodesList).getElementsByTagName("enabled").item(0);
            option.setSaveEnabled(Boolean.parseBoolean(saveEnabled.getTextContent()));

            Node saveFileName = ((Element) saveNodesList).getElementsByTagName("fileName").item(0);
            option.setSaveFileName(saveFileName.getTextContent());

            Node saveFormat = ((Element) saveNodesList).getElementsByTagName("format").item(0);
            option.setSaveFormat(saveFormat.getTextContent());

            // Log
            Node logEnabled = ((Element) logNodesList).getElementsByTagName("enabled").item(0);
            option.setLogEnabled(Boolean.parseBoolean(logEnabled.getTextContent()));

            Node logFileName = ((Element) logNodesList).getElementsByTagName("fileName").item(0);
            option.setLogFileName(logFileName.getTextContent());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        return option;
    }

    public boolean isLoadEnabled() {
        return loadEnabled;
    }

    public void setLoadEnabled(boolean loadEnabled) {
        this.loadEnabled = loadEnabled;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public void setLoadFileName(String loadFileName) {
        this.loadFileName = loadFileName;
    }

    public String getLoadFormat() {
        return loadFormat;
    }

    public void setLoadFormat(String loadFormat) {
        this.loadFormat = loadFormat;
    }

    public boolean isSaveEnabled() {
        return saveEnabled;
    }

    public void setSaveEnabled(boolean saveEnabled) {
        this.saveEnabled = saveEnabled;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getSaveFormat() {
        return saveFormat;
    }

    public void setSaveFormat(String saveFormat) {
        this.saveFormat = saveFormat;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public void showOptions(){
        System.out.println("LOAD:");
        System.out.println("load enabled: " + isLoadEnabled());
        System.out.println("load filename: " + getLoadFileName());
        System.out.println("load format: " + getLoadFormat());
        System.out.println("SAVE:");
        System.out.println("save enabled: " + isSaveEnabled());
        System.out.println("save filename: " + getSaveFileName());
        System.out.println("save format: " + getSaveFormat());
        System.out.println("LOG:");
        System.out.println("log enabled: " + isLogEnabled());
        System.out.println("log filename: " + getLogFileName());
    }
}
