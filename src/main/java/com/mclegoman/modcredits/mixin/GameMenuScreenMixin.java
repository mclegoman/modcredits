package com.mclegoman.modcredits.mixin;

import com.mclegoman.modcredits.ModCredits;
import com.mclegoman.modcredits.gui.ModCreditsScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
	@Inject(method = "init", at = @At(value = "TAIL"))
	private void save$init(CallbackInfo ci) {
		this.buttons.add(new ButtonWidget(900, this.width / 2 - 100, this.height / 4 + 96, "Mods"));
	}
	@Inject(method = "buttonClicked", at = @At(value = "TAIL"))
	private void save$buttonClicked(net.minecraft.client.gui.widget.ButtonWidget button, CallbackInfo ci) {
		if (button.active && button.id == 900) ModCredits.minecraft.m_6408915(new ModCreditsScreen(ModCredits.minecraft.f_0723335));
	}
}
