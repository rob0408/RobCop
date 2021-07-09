package de.rob0408.config;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
    private LabyModAddon addon;
    private Map<String, ConfigObject> configObjects = new HashMap<String, ConfigObject>();

    public ConfigManager(LabyModAddon addon) {
        this.addon = addon;
    }

    public void addObject(ConfigObject configObject){
        configObjects.put(configObject.getName(), configObject);
    }

    public void loadConfig(){

        /*
         * lädt die in den Addon-Settings einstellbare Config
         */

        JsonObject jsonObject = addon.getConfig();
        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()){
            if(configObjects.containsKey(entry.getKey())){
                try {
                    JsonPrimitive jsonPrimitive = entry.getValue().getAsJsonPrimitive();
                    Field field = JsonPrimitive.class.getDeclaredField("value");
                    field.setAccessible(true);
                    configObjects.get(entry.getKey()).setValue(field.get(jsonPrimitive));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public ConfigObject getObject(String name){
        return configObjects.get(name);
    }

    public void saveObject(String name){
        saveObject(getObject(name));
    }

    public void saveObject(ConfigObject configObject){
        try {
            Constructor constructor = JsonPrimitive.class.getDeclaredConstructor(Object.class);
            constructor.setAccessible(true);
            addon.getConfig().add(configObject.getName(), (JsonElement) constructor.newInstance(configObject.getValue()));
            addon.saveConfig();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void fillSettings(List<SettingsElement> list){
        for(final ConfigObject configObject : configObjects.values()){
            SettingsElement settingsElement = configObject.constructSettingsElement(new Consumer() {
                @Override
                public void accept(Object o) {
                    configObject.setValue(o);
                    saveObject(configObject);
                }
            });
            if(settingsElement != null){
                list.add(settingsElement);
            }
        }
    }
}
