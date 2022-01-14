package com.bdxUtils.playerUtils;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class playerDisplayName {

    private Chat vaultChat;
    private String prefixPlaceholder = "{% PREFIX %}";
    private String namePlaceholder = "{% PLAYERNAME %}";
    private String suffixPlaceholder = "{% SUFFIX %}";
    private String nameFormat = "{% PREFIX %}{% PLAYERNAME %}{% SUFFIX %}";
    private boolean translateColors = true;
    private char colorSymbol = '&';

    /**
     * Creates an empty instance of playerDisplayName. Will not support any vault features.
     */
    public playerDisplayName(){
        vaultChat = null;
    }

    /**
     * Can create an instance using an instance of the Plugin calling the method. Will gather Vault chat when called
     * @param plugin JavaPlugin
     */
    public playerDisplayName(JavaPlugin plugin){
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            vaultChat = rsp.getProvider();
        }
    }

    /**
     * Creates a new instance of the playerDisplayName class using default formatting
     * @param chat Chat
     */
    public playerDisplayName(Chat chat){
        this.vaultChat = chat;
    }

    /**
     * Creates a new instance of the playerDisplayName class using custom Vault name formatting
     *
     * @param chat Chat
     * @param nameFormat String
     */
    public playerDisplayName(Chat chat, String nameFormat){
        this.vaultChat = chat;
        this.nameFormat = nameFormat;
    }

    /**
     * Converts the colors within prefixes to Bukkit ChatColors
     * @param name String
     * @return name String
     */
    private String translateColors(String name){
        if(this.translateColors){
            return ChatColor.translateAlternateColorCodes(this.colorSymbol, name);
        }
        return name;
    }

    /**
     * Creates a player displayname using the name format
     * @param prefix String
     * @param name String
     * @param suffix String
     * @return formattedName String
     */
    private String formatName(String prefix, String name, String suffix){
        String formattedName = nameFormat;

        if(formattedName.contains(prefixPlaceholder)){
            formattedName = formattedName.replace(prefixPlaceholder, prefix);
        }

        if(formattedName.contains(namePlaceholder)){
            formattedName = formattedName.replace(namePlaceholder, name);
        }

        if(formattedName.contains(suffixPlaceholder)){
            formattedName = formattedName.replace(suffixPlaceholder, suffix);
        }

        return formattedName;
    }

    /**
     * Gets the vault current DisplayName formatted as Prefix Username Suffix
     * @param p Player
     * @return String
     */
    public String getPlayerFormattedName(Player p){
        if(vaultChat == null){
            return p.getDisplayName();
        }
        return translateColors(formatName(vaultChat.getPlayerPrefix(p),p.getName(),vaultChat.getPlayerSuffix(p)));
    }

    /**
     * Gets the essentials/vault current displayname
     * @param p OfflinePlayer
     * @param world String The world where the prefix should be acquired
     * @return String
     */
    public String getPlayerFormattedName(OfflinePlayer p, String world){
        if(vaultChat == null){
            return p.getName();
        }

        return translateColors(formatName(vaultChat.getPlayerPrefix(world, p), p.getName(), vaultChat.getPlayerSuffix(world, p)));
    }

    /**
     * Gets the Vault chat prefix of the given player if vault chat instance exists.
     * @param p Player
     * @return player Prefix or empty String if vault instance is null
     */
    public String getPlayerPrefix(Player p){
        if(vaultChat != null){
            return vaultChat.getPlayerPrefix(p);
        }
        return "";
    }

    /**
     * Gets the prefix for the given OfflinePlayer on the given world
     * @param p Player
     * @param world String
     * @return prefix String
     */
    public String getPlayerPrefix(OfflinePlayer p, String world){
        if(vaultChat != null){
            return vaultChat.getPlayerPrefix(world, p);
        }
        return "";
    }

    /**
     * Gets the player prefix for the player with the Given UUID on the first avaliable world
     * @param uuid UUID
     * @return prefix String
     */
    public String getPlayerPrefix(UUID uuid){
        return getPlayerPrefix(Bukkit.getOfflinePlayer(uuid), String.valueOf(Bukkit.getServer().getWorlds().get(0)));
    }

    public void setPrefixPlaceholder(String prefixPlaceholder) {
        this.prefixPlaceholder = prefixPlaceholder;
    }

    public void setColorSymbol(char colorSymbol) {
        this.colorSymbol = colorSymbol;
    }

    public void setTranslateColors(boolean translateColors) {
        this.translateColors = translateColors;
    }

    public void setNamePlaceholder(String namePlaceholder) {
        this.namePlaceholder = namePlaceholder;
    }

    public void setSuffixPlaceholder(String suffixPlaceholder) {
        this.suffixPlaceholder = suffixPlaceholder;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public String getPrefixPlaceholder() {
        return prefixPlaceholder;
    }

    public String getNamePlaceholder() {
        return namePlaceholder;
    }

    public String getSuffixPlaceholder() {
        return suffixPlaceholder;
    }
}
