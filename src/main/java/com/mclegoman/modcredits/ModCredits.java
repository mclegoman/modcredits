package com.mclegoman.modcredits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.C_5664496;

import java.util.*;

public class ModCredits implements ClientModInitializer {
	// We store the Resources.minecraft (C_9029783.f_6145320) variable here, so it's easier to access.
	public static C_5664496 minecraft;
	public static List<ModContainer> mods = new ArrayList<>();
	public void onInitializeClient() {
		// Add all mod ID's and corresponding container to an unsorted map.
		Map<String, ModContainer> unprocessedMods = new HashMap<>();
		for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) unprocessedMods.put(modContainer.getMetadata().getName().toLowerCase(), modContainer);
		// Sort mod ID's alphabetically and add the corresponding ModContainer to the 'mods' list.
		SortedSet<String> keys = new TreeSet<>(unprocessedMods.keySet());
		for (String key : keys) mods.add(unprocessedMods.get(key));
	}
}
