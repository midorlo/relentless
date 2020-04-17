package de.midorlo.relentless.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER;
import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_PERKS;

@SuppressWarnings("rawtypes")
public class FileUtillities {

    /**
     * Gives the contents of all .yaml-Files found in <code>DIR_DAUNTLESS_BUILDER_PERKS</code> as LinkedHashMap's.
     *
     * @return List<LinkedHashMap>.
     */
    public static List<LinkedHashMap> getPerksProtoObjects() {
        return readYamlFiles(DIR_DAUNTLESS_BUILDER_PERKS);
    }

    private static List<LinkedHashMap> readYamlFiles(String directory) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return getFiles(directory)
                .stream()
                .map(f -> readYamlFile(f, mapper))
                .collect(Collectors.toList());
    }

    private static LinkedHashMap readYamlFile(File file, ObjectMapper mapper) {
        LinkedHashMap obj = null;
        try {
            obj = mapper.readValue(file, LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return obj;
    }

    private static List<File> getFiles(String absolutePath) {
        return Arrays.asList(Objects.requireNonNull(new File(DIR_DAUNTLESS_BUILDER + absolutePath).listFiles()));
    }
}
