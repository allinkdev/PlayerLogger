package me.allink.playerlogger.client.mixin;

import com.mojang.authlib.properties.Property;
import me.allink.playerlogger.client.PlayerLoggerClient;
import me.allink.playerlogger.client.property.SkinProperty;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayMixin {
    @Inject(method = "onPlayerList(Lnet/minecraft/network/packet/s2c/play/PlayerListS2CPacket;)V", at = @At("TAIL"))
    public void onPlayerList(PlayerListS2CPacket packet, CallbackInfo ci) {
        if(packet.getAction().equals(PlayerListS2CPacket.Action.ADD_PLAYER)) {
            for(PlayerListS2CPacket.Entry entry: packet.getEntries()) {
                Collection<Property> properties = entry.getProfile().getProperties().get("textures");
                String skinValue = null;
                String skinSignature = null;

                for(Property property: properties) {
                    skinValue = property.getValue();
                    skinSignature = property.getValue();
                }

                if(skinValue != null && skinSignature != null) {
                    PlayerLoggerClient.addSkinProperty(new SkinProperty(skinValue, skinSignature));
                }

                PlayerLoggerClient.addUsername(entry.getProfile().getName());
            }
        }
    }
}
