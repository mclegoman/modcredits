package com.mclegoman.modcredits.gui;

import com.mclegoman.modcredits.ModCredits;
import com.mojang.blaze3d.vertex.BufferBuilder;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ModCreditsScreen extends Screen {
	private final Screen parent;
	private int time;
	private final List<ModCredit> credits = new ArrayList<>();
	public ModCreditsScreen(Screen screen) {
		this.parent = screen;
	}
	public void init() {
		credits.add(new ModCredit("Mod" + (ModCredits.mods.size() > 1 ? "s" : ""), 16755200));
		credits.add(new ModCredit(" ", 0));
		credits.add(new ModCredit(" ", 0));

		for (ModContainer modContainer : ModCredits.mods) {
			ModMetadata mod = modContainer.getMetadata();
			String version = "v" + (mod.getVersion().getFriendlyString().startsWith("v") ? mod.getVersion().getFriendlyString().substring(1) : mod.getVersion().getFriendlyString());

			credits.add(new ModCredit(mod.getName() + " (" + mod.getId() + " " + version + ")", 16777215));
			if (!mod.getAuthors().isEmpty()) {
				credits.add(new ModCredit("Author" + (mod.getAuthors().size() > 1 ? "s" : ""), 11184810));
				for (Person author : mod.getAuthors()) {
					credits.add(new ModCredit(author.getName(), 8421504));
				}
			}
			if (!mod.getContributors().isEmpty()) {
				credits.add(new ModCredit("Contributor" + (mod.getContributors().size() > 1 ? "s" : ""), 11184810));
				for (Person contributor : mod.getContributors()) {
					credits.add(new ModCredit(contributor.getName(), 8421504));
				}
			}
			if (!mod.getLicense().isEmpty() || mod.getId().equals("minecraft")) {
				credits.add(new ModCredit("License" + (mod.getLicense().size() > 1 ? "s" : ""), 11184810));
				if (mod.getId().equals("minecraft")) {
					credits.add(new ModCredit("Minecraft EULA", 8421504));
				} else {
					for (String license : mod.getLicense()) {
						credits.add(new ModCredit(license, 8421504));
					}
				}
			}
			if (!mod.getDescription().isEmpty() || mod.getId().equals("minecraft") || mod.getId().equals("java")) {
				credits.add(new ModCredit("Description", 11184810));
				String description = mod.getId().equals("minecraft") ? "The base game." : (mod.getId().equals("java") ? "The Java runtime environment." : mod.getDescription());
				credits.add(new ModCredit(description, 8421504));
			}
			credits.add(new ModCredit(" ", 0));
		}
	}
	public final void render(int i, int j) {
		if (ModCredits.minecraft.f_5854988 != null) fillGradient(0, 0, this.width, this.height, 1610941696, -1607454624);
		else {
			BufferBuilder var4 = BufferBuilder.INSTANCE;
			int var5 = ModCredits.minecraft.f_9413506.load("/dirt.png");
			int var8 = ModCredits.minecraft.f_0545414 * 240 / ModCredits.minecraft.f_5990000;
			int var3 = ModCredits.minecraft.f_5990000 * 240 / ModCredits.minecraft.f_5990000;
			GL11.glBindTexture(3553, var5);
			var4.start();
			var4.color(4210752);
			var4.vertex(0.0F, (float)var3, 0.0F, 0.0F, (float)var3 / 32.0F);
			var4.vertex((float)var8, (float)var3, 0.0F, (float)var8 / 32.0F, (float)var3 / 32.0F);
			var4.vertex((float)var8, 0.0F, 0.0F, (float)var8 / 32.0F, 0.0F);
			var4.vertex(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			var4.end();
		}
		credits.forEach(credit -> textRenderer.drawWithShadow(credit.getString(), (this.width / 2) - (textRenderer.getWidth(credit.getString()) / 2), (this.height + (credits.indexOf(credit) * 10)) - time, credit.getColor()));
		super.render(i, j);
	}
	public void keyPressed(char chr, int key) {
		if (key == 1) ModCredits.minecraft.m_6408915(this.parent);
	}
	public void tick() {
		if (time > ((this.credits.size() * 10) + this.height)) ModCredits.minecraft.m_6408915(this.parent);
		else time += (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) ? (Keyboard.isKeyDown(Keyboard.KEY_UP) ? -4 : 4) : (Keyboard.isKeyDown(Keyboard.KEY_UP) ? -1 : 1);
	}

	public static class ModCredit {
		private final String string;
		private final Integer color;
		ModCredit(String string, Integer color) {
			this.string = string;
			this.color = color;
		}
		public String getString() {
			return string;
		}
		public Integer getColor() {
			return color;
		}
	}
}
