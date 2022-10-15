package com.cygs.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigTest {

    @Test
    public void testFile() throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource("ImageUrl.json");
        final File file = classPathResource.getFile();

        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
