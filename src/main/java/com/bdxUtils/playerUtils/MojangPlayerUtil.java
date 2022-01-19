package com.bdxUtils.playerUtils;

import java.util.UUID;
import java.util.regex.Pattern;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;

public class MojangPlayerUtil {

    private static final Mojang mojang = new Mojang();

    private static final Pattern UUID_PATTERN = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

    /**
     * Changes the UUID format and parses UUID from String
     * @param uuid String
     * @return uuid UUID
     */
    public static UUID formatUUID(String uuid) {
        return UUID.fromString(UUID_PATTERN.matcher(uuid.replace("-", "")).replaceAll("$1-$2-$3-$4-$5"));
    }

    /**
     * Using the MojangAPI gets the UUID for the given username
     * @param username String
     * @return UUID
     */
    public static UUID getUniqueId(String username){

        String uuid = mojang.getUUIDOfUsername(username);
        return formatUUID(uuid);
    }

    /**
     * Converts the given UUID to a UtilPlayer instance
     * @param uuid UUID
     * @return UtilPlayer
     */
    public static UtilPlayer getPlayer(UUID uuid){
        PlayerProfile p = mojang.getPlayerProfile(String.valueOf(uuid));
        return new UtilPlayer(uuid, p.getUsername());
    }

}
