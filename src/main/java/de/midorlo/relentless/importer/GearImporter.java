package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.gear.Gear;
import de.midorlo.relentless.repository.yaml.YamlRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class GearImporter extends YamlFileImporter<Gear> {

    public GearImporter(YamlRepository<Gear> repository) {
        super(repository);
    }

    @Override
    protected String getYamlsPath() {
        return null;
    }

    @Override
    protected Gear parseGameObject(LinkedHashMap<Object, Object> map) {
        return null;
    }

    protected static List<CellSocket> parseCellSockets(ArrayList<String> stringList, Gear parent) {
        List<CellSocket> cellSockets = new ArrayList<>();
        AtomicLong i = new AtomicLong(0);
        if (stringList != null) {
            cellSockets.addAll(stringList.stream().map(e -> {
                CellSocket cellSocket = new CellSocket();
                cellSocket.setType(CellImporter.parseCellType(e));
                cellSocket.setId(Math.abs(i.getAndIncrement() + parent.hashCode() + cellSocket.getType().hashCode()));
                return cellSocket;
            }).collect(Collectors.toList()));
        }
        return cellSockets;
    }
}