package net.fabricmc.example.TestPick;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.ExampleModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestPick extends PickaxeItem implements PolymerItem  {

    private final PolymerModelData model;

    public TestPick(Item polymerItem, ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.model = PolymerRPUtils.requestModel(polymerItem, new Identifier( ExampleMod.MOD_ID, "item/pickaxe"));
    }

    @Override
    public Item getPolymerItem(ItemStack stack, ServerPlayerEntity player) {
        return this.model.item();
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, ServerPlayerEntity player) {
        tooltip.add(0, Text.literal("Hello"));
        tooltip.add(Text.literal("World!"));
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return model.value();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity player) {
            if(hand == Hand.MAIN_HAND){
                openGui(player, player.getMainHandStack());
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand), true);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        // TODO Auto-generated method stub
        int curModLv = 1;
        NbtCompound nbt = stack.getNbt();
        if(nbt.contains("modLv")){ // && nbt.getNbtType() instanceof NbtInt
            curModLv = stack.getNbt().getInt("modLv");
        } else {
            nbt.putInt("modLv", curModLv);
        }

        return curModLv;
    }

    public void openGui(ServerPlayerEntity player, ItemStack Tool) {
        new Gui(player, Tool);
    }

    private class Gui extends SimpleGui {
        public Gui(ServerPlayerEntity player, ItemStack Tool) {
            super(ScreenHandlerType.GENERIC_3X3, player, false);
            this.setTitle(Text.of("Modify Tool"));

            
            int tempCurModLv = 1;
            NbtCompound tempNbt = Tool.getNbt();
            if(tempNbt.contains("modLv")){ // && nbt.getNbtType() instanceof NbtInt
                tempCurModLv = Tool.getNbt().getInt("modLv");
            } else {
                tempNbt.putInt("modLv", tempCurModLv);
            }


            this.setSlot(0, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));

            this.setSlot(1, new GuiElementBuilder(Items.REDSTONE).setName(Text.of("SpeedMod +"))
                .setCallback((x,y,z)->{

                    int curModLv = 1;
                    NbtCompound nbt = Tool.getNbt();
                    if(nbt.contains("modLv")){ // && nbt.getNbtType() instanceof NbtInt
                        curModLv = Tool.getNbt().getInt("modLv");
                    } else {
                        nbt.putInt("modLv", curModLv);
                    }

                    nbt.put("modLv", NbtInt.of(curModLv+1));
                    this.setSlot(4, new GuiElementBuilder(ExampleModItems.EXAMPLE_TOOLS)
                        .setName(Text.of("ModLv = " + curModLv)));
                }));

            this.setSlot(2, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));
            this.setSlot(3, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));

            this.setSlot(4, new GuiElementBuilder(ExampleModItems.EXAMPLE_TOOLS)
                .setName(Text.of("ModLv = " + tempCurModLv)));

            this.setSlot(5, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));
            this.setSlot(6, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));

            this.setSlot(7, new GuiElementBuilder(Items.GUNPOWDER).setName(Text.of("SpeedMod -"))
                .setCallback((x,y,z)->{

                    int curModLv = 1;
                    NbtCompound nbt = Tool.getNbt();
                    if(nbt.contains("modLv")){ // && nbt.getNbtType() instanceof NbtInt
                        curModLv = Tool.getNbt().getInt("modLv");
                    } else {
                        nbt.putInt("modLv", curModLv);
                    }

                    nbt.put("modLv", NbtInt.of(curModLv>=1 ? curModLv-1 : 0));
                    this.setSlot(4, new GuiElementBuilder(ExampleModItems.EXAMPLE_TOOLS)
                        .setName(Text.of("ModLv = " + curModLv)));
                }));

            this.setSlot(8, new GuiElementBuilder(Items.AIR).setName(Text.translatable( "filler")));
            
            this.open();
        }

    
    }
}
