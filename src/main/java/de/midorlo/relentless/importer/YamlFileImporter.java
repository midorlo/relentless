package de.midorlo.relentless.importer;

import de.midorlo.relentless.repository.Repository;
import de.midorlo.relentless.util.FileUtillities;

import java.util.*;
import java.util.stream.Collectors;


public abstract class YamlFileImporter<T> {

    protected Repository<T> repository;

    public YamlFileImporter(Repository<T> repository) {
        this.repository = repository;
    }

    protected abstract String getYamlsPath();

    /**
     * Does all the magic.
     *
     * @param map sourcemap.
     */
    protected Repository<T> importGameObjects(List<LinkedHashMap<Object, Object>> map) {
        repository.save(parseGameObjects(map));
        return repository;
    }

    /**
     * Creates <T>'s from yaml-files in a directory.
     */
    public Repository<T> importGameObjects() {
        return importGameObjects(FileUtillities.readYamlFiles(getYamlsPath()));
    }

    /**
     * Parses a collection of Game Objects as <T>
     *
     * @param map the map.
     * @return List of parsed Objects.
     */
    protected List<T> parseGameObjects(List<LinkedHashMap<Object, Object>> map) {
        List<T> parsedItems = new ArrayList<>();
        for (LinkedHashMap<Object, Object> oMap : map) {
            parsedItems.add(parseGameObject(oMap));
        }
        return parsedItems;
    }

    /**
     * Parses a Game Object as <T>
     *
     * @param map sourcemap
     * @return T
     */
    protected abstract T parseGameObject(LinkedHashMap<Object, Object> map);

    /**
     * Utillity Method to be able to parse heterogenic yaml-fields.
     * Needed because the source data has (k,v) pairs with
     * (String, Array), (String, null), (String, String)
     * for the same struct :-(
     *
     * @param src will be String, Double, ArrayLists of those.
     * @param tar src values will be added to.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected static void parseMixedContents(Object src, List tar) {
        if (src instanceof ArrayList) {
            src = ((ArrayList) src).stream().filter(Objects::nonNull).collect(Collectors.toList());
            tar.addAll((ArrayList) src);
        } else if (src != null) {
            tar.add(src);
        }
    }

    /**
     * Utillity Method. Asserts an Integer/Double/null as Double at compile time.
     *
     * @param o Numeric..
     * @return double
     */
    protected static Double parseMixedNumerics(Object o) {
        double d = 0d;
        if (o instanceof Integer) {
            d += ((Integer) o);
        } else if (o instanceof Double) {
            d += ((Double) o);
        } else {
            d = -1d; //todo warn
        }
        return d;
    }

    /**
     * Utillity Method. Converts a Map(k,v) to an ArrayList, sorted by ((int)k)
     *
     * @param map the map.
     * @return the list.
     */
    @SuppressWarnings("unchecked")
    protected static List<LinkedHashMap<Object, Object>> unwrapListInMap(LinkedHashMap<Object, Object> map) {
        List<LinkedHashMap<Object, Object>> list = new ArrayList<>();
        int index = 1;
        while (map.get("" + index) != null) {
            list.add((LinkedHashMap<Object, Object>) map.get("" + (index++)));
        }
        return list;
    }

    /**
     * Utillity Method. Gives (hashcode-)distinct <T>'s of a List.
     *
     * @param items list of <T>'s
     * @return distinct list of <T>'s
     */
    protected List<T> distinct(List<T> items) {
        return new ArrayList<>(new HashSet<>(items));
    }
}
