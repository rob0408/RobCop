package de.rob0408.config;

import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;

public class ConfigObject {
    private String name;
    private String label;
    private ControlElement.IconData iconData;
    public Object value;
    private SettingsElement settingsElement;

    public ConfigObject(String name, String label, ControlElement.IconData iconData, Object value) {
        this.name = name;
        this.label = label;
        this.iconData = iconData;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public ControlElement.IconData getIconData() {
        return iconData;
    }

    public Object getValue() {
        return value;
    }

    public ConfigObject setValue(Object value) {
        this.value = value;
        return this;
    }

    public SettingsElement getSettingsElement() {
        return settingsElement;
    }

    public ConfigObject setSettingsElement(SettingsElement settingsElement) {
        this.settingsElement = settingsElement;
        return this;
    }

    public SettingsElement constructSettingsElement(Consumer consumer){
        if(settingsElement != null){
            return settingsElement;
        } else if(value instanceof String){
            return new StringElement(label, iconData, (String) value, (Consumer<String>) consumer);
        } else if(value instanceof Boolean){
            return new BooleanElement(label, iconData, (Consumer<Boolean>) consumer, (Boolean) value);
        }
        return null;
    }
}
