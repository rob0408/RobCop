package de.rob0408.chat;

import net.labymod.ingamechat.GuiChatCustom;
import net.minecraft.util.IChatComponent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ChatGui extends GuiChatCustom {
    @Override
    protected boolean handleComponentClick(IChatComponent chatComponent) {
        /*
         * Legt die ChatMessage in der Zwischenablage ab.
         */

        if(chatComponent == null) return false;

        if(chatComponent.getChatStyle() instanceof CustomChatStyle){
            CustomChatStyle customChatStyle = (CustomChatStyle) chatComponent.getChatStyle();
            if(customChatStyle.getProperties().containsKey("copy")){
                Object obj = customChatStyle.getProperties().get("copy");
                if(obj instanceof IChatComponent){
                    IChatComponent copyComponent = (IChatComponent) obj;
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(copyComponent.getUnformattedText()), null);
                }
            }
        }
        return super.handleComponentClick(chatComponent);
    }
}
