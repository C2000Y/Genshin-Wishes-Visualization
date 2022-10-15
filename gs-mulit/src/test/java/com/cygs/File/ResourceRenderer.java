package com.cygs.File;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public class ResourceRenderer {
    public static InputStream resourceLoader(String fileFullPath) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(fileFullPath).getInputStream();
    }
}
