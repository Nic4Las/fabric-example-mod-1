package net.fabricmc.example;

import net.fabricmc.example.OneSlotBarrel.OneSlotBarrelBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleModBlocks {

    public static Block ONE_SLOT_BARREL_BLOCK;

    public static void register(){
        ONE_SLOT_BARREL_BLOCK = register(new Identifier(ExampleMod.MOD_ID, "one_slot_barrel"), new OneSlotBarrelBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.5f).sounds(BlockSoundGroup.WOOD)));
    }


    
    private static <T extends Block> T register(Identifier path, T block) {
        return Registry.register(Registry.BLOCK, path, block);
    }
}
