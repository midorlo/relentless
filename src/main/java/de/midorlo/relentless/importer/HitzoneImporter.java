package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.repository.Repository;

import java.util.LinkedHashMap;
import java.util.List;

public class HitzoneImporter extends AbstractImporter<Hitzone>{

    public HitzoneImporter(Repository<Hitzone> repository) {
        super(repository);
    }

    @Override
    public void importGameObjects(List<LinkedHashMap<Object, Object>> map) {
        for (String name : new String[]{"Head", "Horn"}) {
            repository.save(new Hitzone(name));
        }
    }

    @Override
    public Hitzone parseGameObject(LinkedHashMap map, Object extraData) {
        throw new RuntimeException("no source datamap");
    }
}
