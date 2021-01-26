package io.github.azura4k.ProxChat;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.TextFormat;

import java.util.Set;

public class PlayerChatListner implements Listener {

    private final ProxChat plugin;
    public PlayerChatListner(ProxChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false) //DON'T FORGET THE ANNOTATION @EventHandler
    public void PlayerMoves(PlayerChatEvent event) {

        // For every recipient of the message, it will block the message if the sender is not within the range
        for (Player recipient : plugin.getServer().getOnlinePlayers().values()) {

        // Get the distance and make positive
        double SenderX = event.getPlayer().getPosition().x;
        double recipientX = recipient.getPosition().x;
        if (SenderX < 0){SenderX = SenderX * -1;}
        if (recipientX < 0){recipientX = recipientX * -1;}
        double DistanceX = SenderX - recipientX;
        if (DistanceX < 0){DistanceX = DistanceX * -1;};

        double SenderY = event.getPlayer().getPosition().y;
        double recipientY = recipient.getPosition().y;
        if (SenderY < 0){SenderY = SenderY * -1;}
        if (recipientY < 0){recipientY = recipientY * -1;}
        double DistanceY = SenderY - recipientY;
        if (DistanceY < 0){DistanceY = DistanceY * -1;};

        double SenderZ = event.getPlayer().getPosition().z;
        double recipientZ = recipient.getPosition().z;
        if (SenderZ < 0){SenderZ = SenderZ * -1;}
        if (recipientZ < 0){recipientZ = recipientZ * -1;}
        double DistanceZ = SenderZ - recipientZ;
        if (DistanceZ < 0){DistanceZ = DistanceZ * -1;};


        //Check Player Position against the Config set radius and apply ruling
        if (DistanceX <= plugin.getConfig().getDouble("chatradius") & DistanceY <= plugin.getConfig().getDouble("chatradius") & DistanceZ < plugin.getConfig().getDouble("chatradius") & event.getPlayer().getLevel().getName() == recipient.getLevel().getName()) {
            // Player is in the same world and in range, allow message to recipient
            Set<CommandSender> r = event.getRecipients();
            r.add(recipient);
        }
        else {
            //Player is not in range or not in world, block message to recipient
            Set<CommandSender> r = event.getRecipients();
            r.remove(recipient);
        } }
    }
}
