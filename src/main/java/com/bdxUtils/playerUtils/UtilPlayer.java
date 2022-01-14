package com.bdxUtils.playerUtils;

import com.bdxUtils.exceptions.PlayerNotFound;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UtilPlayer {

    /**
     * This class serves as an intermediary between the Spigot and the Bungeecord Player instances, allowing classes
     * to utilize UtilPlayer as an intermediary
     */
    private final UUID uuid;
    private final String name;
    private String displayName;
    private Player playerInstance = null;
    private ProxiedPlayer proxiedPlayer = null;


    /**
     * Create a new UtilPlayer instance
     * @param uuid UUID
     * @param name String
     */
    public UtilPlayer (UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        this.displayName = name;
    }

    /**
     * Create a new UtilPlayer
     * @param uuid UUID
     * @param name String
     * @param displayName String
     */
    public UtilPlayer (UUID uuid, String name, String displayName){
        this.uuid = uuid;
        this.name = name;
        this.displayName = displayName;
    }

    /**
     * Get the Unique id of the UtilPlayer
     * @return uuid UUID
     */
    public UUID getUniqueId() {
        return uuid;
    }

    /**
     * Get the name of the UtilPlayer
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the displayName of the UtilPlayer
     * @return String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the displayName of the UtilPlayer
     * @param displayName String
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the instance of the current player or null if not defined
     * @return playerInstance
     */
    public Player getPlayerInstance() {
        return playerInstance;
    }

    /**
     * Sets the playerInstance to the given Player
     * @param playerInstance Player
     */
    public void setPlayerInstance(Player playerInstance) {
        this.playerInstance = playerInstance;
    }

    /**
     * Gets the instance of the current ProxiedPlayer or null if not defined
     * @return proxiedPlayer
     */
    public ProxiedPlayer getProxiedPlayer() {
        return proxiedPlayer;
    }

    /**
     * Sets the proxiedPlayer to the given ProxiedPlayer
     * @param proxiedPlayer ProxiedPlayer
     */
    public void setProxiedPlayer(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
    }

    /**
     * Allows a message to be sent directly to the player instance
     * @param message String
     * @throws PlayerNotFound PlayerNotFound
     */
    public void sendMessage(String message) throws PlayerNotFound {

        //Sends the Message to the Spigot player instance
        if(playerInstance != null){
            playerInstance.sendMessage(message);
        }

        //Sends the Message to the Bungee ProxiedPlayer instance
        else if(proxiedPlayer != null){
            proxiedPlayer.sendMessage(new TextComponent(message));
        }

        //If no player instance has been defined, PlayerNotFound error is thrown
        else{
            throw new PlayerNotFound("No playerInstance defined for player " + this.getName());
        }

    }

    /**
     * Allows TextComponent messages to be sent directly to player instance
     * @param message TextComponent
     * @throws PlayerNotFound PlayerNotFound
     */
    public void sendMessage(TextComponent message) throws PlayerNotFound {

        //Sends the Message to the Spigot player instance
        if(playerInstance != null){
            playerInstance.spigot().sendMessage(message);
        }

        //Sends the Message to the Bungee ProxiedPlayer instance
        else if(proxiedPlayer != null){
            proxiedPlayer.sendMessage(message);
        }

        //If no player instance has been defined, PlayerNotFound error is thrown
        else{
            throw new PlayerNotFound("No playerInstance defined for player " + this.getName());
        }

    }

}
