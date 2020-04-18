package de.midorlo.relentless.importer;

import de.midorlo.relentless.repository.Repository;

import java.util.*;
import java.util.stream.Collectors;


@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractImporter<T> {

    protected Repository<T> repository;

    public AbstractImporter(Repository<T> repository) {
        this.repository = repository;
    }

    /**
     * Does all the magic.
     * @param map sourcemap.
     */
    public void importGameObjects(List<LinkedHashMap> map) {
        repository.save(parseGameObjects(map));
    }

    /**
     * Parses a collection of Game Objects as <T>
     *
     * @param map the map.
     * @return List of parsed Objects.
     */
    public List<T> parseGameObjects(List<LinkedHashMap> map) {
        List<T> parsedItems = new ArrayList<>();
        for (LinkedHashMap oMap : map) {
            parsedItems.add(parseGameObject(oMap));
        }
        return parsedItems;
    }

    /**
     * Parses a Game Object as <T>
     *
     * @param map       sourcemap
     * @param extraData additional data for "exotic" implementations.
     * @return T
     */
    public abstract T parseGameObject(LinkedHashMap map, Object extraData);

    /**
     * Parses a Game Object as <T>
     *
     * @param map sourcemap.
     * @return GameObject
     */
    private T parseGameObject(LinkedHashMap map) {
        return parseGameObject(map, null);
    }

    /**
     * Utillity Method to be able to parse heterogenic yaml-fields.
     * Needed because the source data has (k,v) pairs with
     * (String, Array), (String, null), (String, String)
     * for the same struct :-(
     *
     * @param src will be String, Double, ArrayLists of those.
     * @param tar src values will be added to.
     */
    protected static void parseMixedContents(Object src, List tar) {
        if (src instanceof ArrayList) {
            src = ((ArrayList) src).stream().filter(Objects::nonNull).collect(Collectors.toList());
            tar.addAll((ArrayList) src);
        } else if (src != null) {
            tar.add(src);
        }
    }

    protected static Double parseMixedNumerics(Object o) {
        double d = 0d;
        if (o instanceof Integer) {
            d += ((Integer)o);
        } else if (o instanceof Double){
            d += ((Double)o);
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
    protected static List<LinkedHashMap> unwrapListInMap(LinkedHashMap map) {
        List<LinkedHashMap> list = new ArrayList<>();
        int index = 1;
        while (map.get("" + index) != null) {
            list.add((LinkedHashMap) map.get("" + (index++)));
        }
        return list;
    }

    /**
     * Utillity Method. Gives (hashcode-)distinct <T>'s of a List.
     * @param items list of <T>'s
     * @return distinct list of <T>'s
     */
    protected List<T> distinct(List<T> items) {
        return new ArrayList<>(new HashSet<>(items));
    }
}
