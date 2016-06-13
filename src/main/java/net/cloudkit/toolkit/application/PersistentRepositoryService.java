package net.cloudkit.toolkit.application;

import java.util.Map;

public interface PersistentRepositoryService {

    void save(Map<String, Object> dataMap) throws Exception;
}
