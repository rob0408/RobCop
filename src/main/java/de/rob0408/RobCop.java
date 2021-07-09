package de.rob0408;

import de.rob0408.config.ConfigManager;
import de.rob0408.config.ConfigObject;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.List;

public class RobCop extends LabyModAddon {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        setConfigValues();
        RobCopListeners robCopListeners = new RobCopListeners(configManager);
        getApi().getEventManager().register(robCopListeners);
        getApi().registerForgeListener(robCopListeners);
    }

    private void setConfigValues(){
        /*
         * Setzt Standartwerte in den Addon-Settings
         */
        configManager.addObject(new ConfigObject("enable", "Aktiviert", new ControlElement.IconData(Material.PISTON_MOVING_PIECE), true));
        configManager.addObject(new ConfigObject("prefix", "Prefix", new ControlElement.IconData(Material.MAP), "&r  &f["));
        configManager.addObject(new ConfigObject("content", "Content", new ControlElement.IconData(Material.MAP), "&dKopieren"));
        configManager.addObject(new ConfigObject("suffix", "Suffix", new ControlElement.IconData(Material.MAP), "&f]"));
    }

    @Override
    public void loadConfig() {
        configManager.loadConfig();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        configManager.fillSettings(list);
    }
}
