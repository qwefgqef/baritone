package baritone.fabric;

import baritone.api.BaritoneAPI;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.events.WorldEvent.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class WorldLoadHello {

    private static boolean shown = false;

    public static void init() {
        BaritoneAPI.getProvider()
                .getPrimaryBaritone()
                .getGameEventHandler()
                .registerEventListener(event -> {
                    if (event instanceof WorldEvent worldEvent) {
                        if (worldEvent.getState() == EventState.POST && !shown) {
                            shown = true;
                            sendHello();
                        }
                    }
                });
    }

    private static void sendHello() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        mc.player.sendSystemMessage(
            Component.Serializer.fromJson(
                "{\"text\":\"HELLO WORLD\",\"bold\":true,\"color\":\"gold\"}"
            )
        );
    }
}
