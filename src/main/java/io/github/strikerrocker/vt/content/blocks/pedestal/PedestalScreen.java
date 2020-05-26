package io.github.strikerrocker.vt.content.blocks.pedestal;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PedestalScreen extends ContainerScreen<PedestalContainer> {

    private static final Identifier BG_TEXTURE = new Identifier(VanillaTweaks.MODID, "textures/gui/pedestal.png");

    public PedestalScreen(PedestalContainer container, PlayerInventory playerInv, Text title) {
        super(container, playerInv, title);
    }

    @Override
    protected void drawForeground(int mouseX, int mouseY) {
        String name = title.asFormattedString();
        font.draw(name, containerWidth / 2 - font.getStringWidth(name) / 2, 6, 0x404040);
        font.draw(playerInventory.getDisplayName().asFormattedString(), 8, 40, 0x404040);
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1, 1, 1, 1);
        minecraft.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - containerWidth) / 2;
        int y = (height - containerHeight) / 2;
        blit(x, y, 0, 0, containerWidth, containerHeight);
    }
}