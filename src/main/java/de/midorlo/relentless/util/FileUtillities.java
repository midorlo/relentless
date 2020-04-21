package de.midorlo.relentless.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class FileUtillities {

    public static List<LinkedHashMap<Object, Object>> readYamlFiles(String directory) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return getFiles(directory)
                .stream()
                .map(f -> readYamlFile(f, mapper))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static LinkedHashMap<Object, Object> readYamlFile(File file, ObjectMapper mapper) {
        LinkedHashMap obj = null;
        try {
            obj = mapper.readValue(file, LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return ((LinkedHashMap<Object, Object>)obj);
    }

    private static List<File> getFiles(String absolutePath) {
        List<File> files = new ArrayList<>();
        File[] filesToCheck = Objects.requireNonNull(new File(absolutePath).listFiles());
        for (File file :filesToCheck) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file.getAbsolutePath()));
            } else {
                files.add(file);
            }
        }
        return files;
    }
}
