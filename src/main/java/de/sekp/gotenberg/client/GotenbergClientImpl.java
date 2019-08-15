package de.sekp.gotenberg.client;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import de.sekp.gotenberg.client.dto.GotenbergFile;

public class GotenbergClientImpl implements GotenbergClient {
    
    private String baseUrl;

    public GotenbergClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public GotenbergFile convertOfficeFile(GotenbergFile officeFile) {

        try {

            var serverUrl = baseUrl + "/convert/office";
            HttpPost post = new HttpPost(serverUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody("file", officeFile.getData(), ContentType.APPLICATION_OCTET_STREAM, officeFile.getName());
    
            HttpEntity entity = builder.build();
            post.setEntity(entity);
    
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);

            var out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            return new GotenbergFile(out.toByteArray(), "out.pdf");
            
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public GotenbergFile mergePdfFils(GotenbergFile[] pdfFiles) {
		return null;
	}
    
}