package net.fabricmc.example.OneSlotBarrel;

import eu.pb4.sgui.api.elements.GuiElement;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.ExampleModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class OneSlotTileEntity extends LootableContainerBlockEntity {
    public DefaultedList<ItemStack> inventory;


    public OneSlotTileEntity(BlockPos blockPos, BlockState blockState) {
        super(ExampleModBlockEntities.ONE_SLOT_BARREL_ENTITY, blockPos, blockState);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
        
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("modid.one_slot_barrel");
    }

    @Override
    protected ScreenHandler createScreenHandler(int var1, PlayerInventory var2) {
        return null;
    }

    public void openGui(ServerPlayerEntity player) {
        new Gui(player);
    }

    

    private class Gui extends SimpleGui {
        public Gui(ServerPlayerEntity player) {
            super(ScreenHandlerType.GENERIC_3X3, player, false);
            this.setTitle(OneSlotTileEntity.this.getDisplayName());

            this.setSlot(0, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(1, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(2, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(3, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));

            this.setSlotRedirect(4, new Slot(OneSlotTileEntity.this, 0, 0, 0) {});

            this.setSlot(5, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(6, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(7, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            this.setSlot(8, new GuiElementBuilder(Items.BARRIER).setName(Text.translatable( "filler")));
            
            this.open();
        }
    }
}
