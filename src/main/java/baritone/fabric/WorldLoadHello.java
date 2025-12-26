package baritone.fabric;

import baritone.api.event.events.WorldEvent;
import baritone.api.event.listener.IGameEventListener;
import baritone.api.event.listener.IEventBus;
import baritone.api.BaritoneAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class WorldLoadHello implements IGameEventListener {

    private static boolean shown = false;

    public static void init() {
        IEventBus bus = BaritoneAPI.getProvider()
                .getPrimaryBaritone()
                .getGameEventHandler();

        bus.registerEventListener(new WorldLoadHello());
    }

    @Override
    public void onWorldEvent(WorldEvent event) {
        if (shown) return;

        // WorldEvent fires when world becomes non-null
        if (Minecraft.getInstance().level != null) {
            shown = true;
            sendHello();
        }
    }

    private static void sendHello() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        mc.player.sendSystemMessage(
            Component.literal("HELLO WORLD")
                .withStyle(style -> style
                    .withBold(true)
                )
        );
    }
}
