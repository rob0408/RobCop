package de.rob0408;

import de.rob0408.chat.ChatGui;
import de.rob0408.chat.CustomChatStyle;
import de.rob0408.config.ConfigManager;
import net.labymod.api.events.MessageModifyChatEvent;
import net.labymod.core.LabyModCore;
import net.labymod.ingamechat.GuiChatCustom;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RobCopListeners implements MessageModifyChatEvent {
    private ConfigManager robCop;

    public RobCopListeners(ConfigManager robCop) {
        this.robCop = robCop;
    }

    private boolean hasEnable(){
        return (Boolean) robCop.getObject("enable").getValue();
    }

    private void addCopyText(IChatComponent chatComponent){

        /*
         * Fügt jeder ChatMessage am Ende die Kopier-Option an.
         */

        IChatComponent prefixComponent = createChatComponent((String) robCop.getObject("prefix").getValue());
        IChatComponent suffixComponent = createChatComponent((String) robCop.getObject("suffix").getValue());
        IChatComponent contentComponent = createChatComponent((String) robCop.getObject("content").getValue());

        CustomChatStyle customChatStyle = new CustomChatStyle(contentComponent);

        customChatStyle.setProperty("copy", chatComponent.createCopy());

        chatComponent.appendSibling(prefixComponent);
        chatComponent.appendSibling(contentComponent);
        chatComponent.appendSibling(suffixComponent);
    }

    private IChatComponent createChatComponent(String text){
        return new ChatComponentText(text.replace("&", "\u00A7")); // "\u00A7" == "§"
    }

    @Override
    public Object onModifyChatMessage(Object o) {
        if(hasEnable() && o instanceof IChatComponent) addCopyText((IChatComponent) o);
        return o;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onOpenGui(GuiOpenEvent event){
        GuiScreen guiScreen = LabyModCore.getForge().getGuiOpenEventGui(event);
        if(!hasEnable() || guiScreen == null) return;

        if (guiScreen.getClass().equals(GuiChatCustom.class)) {
            LabyModCore.getForge().setGuiOpenEventGui(event, new ChatGui());
        }
    }
}
