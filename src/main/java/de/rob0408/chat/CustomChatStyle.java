package de.rob0408.chat;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.util.HashMap;
import java.util.Map;

public class CustomChatStyle extends ChatStyle {
    private ChatStyle chatStyle;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public CustomChatStyle(IChatComponent iChatComponent) {
        this(iChatComponent.getChatStyle());
        iChatComponent.setChatStyle(this);
    }

    public CustomChatStyle(ChatStyle chatStyle) {
        this.chatStyle = chatStyle;
    }

    public void setProperty(String key, Object value){
        properties.put(key, value);
    }

    public void removeProperty(String key){
        properties.remove(key);
    }

    public Object getProperty(String key){
        return properties.get(key);
    }

    public Map<String, Object> getProperties(){
        return new HashMap<String, Object>(properties);
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public ChatStyle getChatStyle() {
        return chatStyle;
    }

    @Override
    public EnumChatFormatting getColor() {
        return chatStyle.getColor();
    }

    @Override
    public boolean getBold() {
        return chatStyle.getBold();
    }

    @Override
    public boolean getItalic() {
        return chatStyle.getItalic();
    }

    @Override
    public boolean getStrikethrough() {
        return chatStyle.getStrikethrough();
    }

    @Override
    public boolean getUnderlined() {
        return chatStyle.getUnderlined();
    }

    @Override
    public boolean getObfuscated() {
        return chatStyle.getObfuscated();
    }

    @Override
    public boolean isEmpty() {
        return chatStyle.isEmpty();
    }

    @Override
    public ClickEvent getChatClickEvent() {
        return chatStyle.getChatClickEvent();
    }

    @Override
    public HoverEvent getChatHoverEvent() {
        return chatStyle.getChatHoverEvent();
    }

    @Override
    public String getInsertion() {
        return chatStyle.getInsertion();
    }

    @Override
    public ChatStyle setColor(EnumChatFormatting color) {
        return chatStyle.setColor(color);
    }

    @Override
    public ChatStyle setBold(Boolean boldIn) {
        return chatStyle.setBold(boldIn);
    }

    @Override
    public ChatStyle setItalic(Boolean italic) {
        return chatStyle.setItalic(italic);
    }

    @Override
    public ChatStyle setStrikethrough(Boolean strikethrough) {
        return chatStyle.setStrikethrough(strikethrough);
    }

    @Override
    public ChatStyle setUnderlined(Boolean underlined) {
        return chatStyle.setUnderlined(underlined);
    }

    @Override
    public ChatStyle setObfuscated(Boolean obfuscated) {
        return chatStyle.setObfuscated(obfuscated);
    }

    @Override
    public ChatStyle setChatClickEvent(ClickEvent event) {
        return chatStyle.setChatClickEvent(event);
    }

    @Override
    public ChatStyle setChatHoverEvent(HoverEvent event) {
        return chatStyle.setChatHoverEvent(event);
    }

    @Override
    public ChatStyle setInsertion(String insertion) {
        return chatStyle.setInsertion(insertion);
    }

    @Override
    public ChatStyle setParentStyle(ChatStyle parent) {
        return chatStyle.setParentStyle(parent);
    }

    @Override
    public String getFormattingCode() {
        return chatStyle.getFormattingCode();
    }

    @Override
    public String toString() {
        return chatStyle.toString();
    }

    @Override
    public ChatStyle createShallowCopy() {
        CustomChatStyle customChatStyle = new CustomChatStyle(super.createShallowCopy());
        customChatStyle.properties = this.properties;
        return customChatStyle;
    }

    @Override
    public ChatStyle createDeepCopy() {
        CustomChatStyle customChatStyle = new CustomChatStyle(super.createDeepCopy());
        customChatStyle.setProperties(this.getProperties());
        return customChatStyle;
    }
}
