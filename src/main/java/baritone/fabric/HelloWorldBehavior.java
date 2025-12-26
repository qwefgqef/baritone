package baritone.fabric;

import baritone.api.behavior.IBehavior;
import baritone.api.event.events.WorldEvent;
import baritone.api.utils.IPlayerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class HelloWorldBehavior implements IBehavior {

    private boolean shown = false;

    @Override
    public void onWorldEvent(WorldEvent event) {
        if (shown) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        shown = true;

        // Client-side chat message (tellraw-style formatting possible)
        mc.player.displayClientMessage(
            Component.literal("HELLO WORLD")
                .withStyle(style -> style.withBold(true)),
            false
        );
    }

    /* -------- Required IBehavior methods (no-op) -------- */

    @Override
    public void onTick(boolean ingame) {}

    @Override
    public void onPlayerTick(IPlayerContext ctx) {}

    @Override
    public void onLostControl() {}

    @Override
    public void onGainControl() {}

    @Override
    public void onPlayerDeath() {}

    @Override
    public String name() {
        return "HelloWorldBehavior";
    }

    @Override
    public String description() {
        return "Shows a hello world message when a world loads";
    }
}
