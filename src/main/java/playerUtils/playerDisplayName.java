package playerUtils;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class playerDisplayName {

    private final Chat vaultChat;
    private String prefixPlaceholder = "{% PREFIX %}";
    private String namePlaceholder = "{% PLAYERNAME %}";
    private String suffixPlaceholder = "{% SUFFIX %}";
    private String nameFormat = "{% PREFIX %}{% PLAYERNAME %}{% SUFFIX %}";
    private boolean translateColors = true;
    private char colorSymbol = '&';

    public playerDisplayName(){
        vaultChat = null;
    }

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

    private String translateColors(String name){
        if(this.translateColors){
            return ChatColor.translateAlternateColorCodes(this.colorSymbol, name);
        }
        return name;
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

        return translateColors(vaultChat.getPlayerPrefix(p) + p.getName() + vaultChat.getPlayerSuffix(p));
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

        return translateColors(vaultChat.getPlayerPrefix(world, p) + p.getName() + vaultChat.getPlayerSuffix(world, p));
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
