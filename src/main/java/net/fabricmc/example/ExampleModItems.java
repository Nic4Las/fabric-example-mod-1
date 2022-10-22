package net.fabricmc.example;


import net.fabricmc.example.TestPick.TestPick;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleModItems {
    public static Item EXAMPLE_TOOLS;

    public static void register(){
        EXAMPLE_TOOLS = register(new Identifier(ExampleMod.MOD_ID, "pickaxe"), (Item) new TestPick(Items.DIAMOND_PICKAXE ,ToolMaterials.DIAMOND, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS)));
    }

    private static <T extends Item> T register(Identifier path, T item) {
        return Registry.register(Registry.ITEM, path, item);
    }
}
