package de.sekp.gotenberg.client.dto;

import java.io.InputStream;

public class GotenbergFile {
    
    private byte[] data;
    private String name;

    public GotenbergFile(byte[] data, String name) {
        this.data = data;
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public String getName() {
        return name;
    }
    
}