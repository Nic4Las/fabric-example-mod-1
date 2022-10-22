package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.Bucket.TestBucket;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");


		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "custom_item"), new TestBucket(Fluids.WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(16), Items.WATER_BUCKET));
		ExampleModItems.register();
		ExampleModBlocks.register();
		ExampleModBlockEntities.register();
	}
}
