package net.fabricmc.example;

import eu.pb4.polymer.api.block.PolymerBlockUtils;
import net.fabricmc.example.OneSlotBarrel.OneSlotTileEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleModBlockEntities {
    public static BlockEntityType<OneSlotTileEntity> ONE_SLOT_BARREL_ENTITY;

    public static void register() {
        ONE_SLOT_BARREL_ENTITY = register(new Identifier(ExampleMod.MOD_ID, "one_slot_barrel_block_entity"), FabricBlockEntityTypeBuilder.create(OneSlotTileEntity::new, ExampleModBlocks.ONE_SLOT_BARREL_BLOCK).build());
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(Identifier path, BlockEntityType<T> block) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, path, block);
        PolymerBlockUtils.registerBlockEntity(block);
        return block;
    }
}
