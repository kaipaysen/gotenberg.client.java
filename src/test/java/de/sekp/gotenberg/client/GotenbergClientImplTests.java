package de.sekp.gotenberg.client;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import de.sekp.gotenberg.client.dto.GotenbergFile;

public class GotenbergClientImplTests {
   
    final String GOTENBERG_BASEURL = "http://localhost:3000";
    final GotenbergClient gotenbergClient = new GotenbergClientImpl(GOTENBERG_BASEURL);

    @Test
    public void testConvertOfficeFile() {

        // Load Sample File
        var input = getTestFile01();

        // Convert File
        var output = gotenbergClient.convertOfficeFile(input);

        // Save Output
        toFile(output);
        
    }

    private GotenbergFile getTestFile01() {
        try {
            URL inputUrl = this.getClass().getResource("/samples/LoremIpsum.docx");
            var fileData = Files.readAllBytes(Paths.get(inputUrl.toURI()));
            return new GotenbergFile(fileData, "LoremIpsum.docx");
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void toFile(GotenbergFile output) {
        try {
            Files.write(Paths.get(output.getName()), output.getData());
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}