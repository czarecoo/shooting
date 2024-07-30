package com.czareg.shooting.file;

import lombok.experimental.UtilityClass;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
class FilePathProvider {

    static Path provide() throws URISyntaxException {
        URL resource = FileOperations.class.getProtectionDomain().getCodeSource().getLocation();
        Path path = Paths.get(resource.toURI());

        // If running from a JAR file use target/SHO.IN (copied from example directory during packaging (see pom)
        if (path.toString().endsWith(".jar")) {
            return path.getParent();
        }

        // If running from an IDE, use example/SHO.IN
        return Paths.get("example").toAbsolutePath();
    }
}