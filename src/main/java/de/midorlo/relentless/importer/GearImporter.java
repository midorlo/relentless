package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Gear;
import de.midorlo.relentless.importer.yaml.YamlRepository;

import java.util.LinkedHashMap;

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

//    protected static List<CellSocket> parseCellSockets(ArrayList<String> stringList, Gear parent) {
//        List<CellSocket> cellSockets = new ArrayList<>();
//        AtomicLong i = new AtomicLong(0);
//        if (stringList != null) {
//            cellSockets.addAll(stringList.stream().map(e -> {
//                CellSocket cellSocket = new CellSocket();
//                cellSocket.setType(parseCellType(e));
//                cellSocket.setId(Math.abs(i.getAndIncrement() + parent.hashCode() + cellSocket.getType().hashCode()));
//                return cellSocket;
//            }).collect(Collectors.toList()));
//        }
//        return cellSockets;
//    }
}
