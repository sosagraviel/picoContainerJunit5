package utils.context;

import java.util.HashMap;

public class ScenarioContextInfoHolder {
    private HashMap<String, Object> data = new HashMap<>();

    /**
     * Get the value associated with the specified key from the scenario context.
     *
     * @param  key   the key whose associated value is to be returned
     * @return       the value associated with the specified key
     */
    public <T> T getScenarioContext(String key) {
        return (T) data.get(key);
    }

    /**
     * Sets the value in the scenario context map with the given key.
     *
     * @param  key   the key for the data
     * @param  data  the data to be stored
     */
    public void setScenarioContext(String key, Object data) {
        this.data.put(key, data);
    }
}
