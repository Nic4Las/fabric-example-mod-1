package net.fabricmc.example.OneSlotBarrel;

import eu.pb4.polymer.api.block.PolymerBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OneSlotBarrelBlock extends Block implements PolymerBlock, BlockEntityProvider  {

    public OneSlotBarrelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OneSlotTileEntity(pos, state);
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.BARREL;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        var blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof OneSlotTileEntity){
            ItemStack stack = ((OneSlotTileEntity)blockEntity).inventory.get(0);
            return (int)(((float)stack.getCount() / (float)(stack.getItem().getMaxCount()))*15);
        }

        return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (hand == Hand.MAIN_HAND) {
            var blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof OneSlotTileEntity barrelBlock && barrelBlock.canPlayerUse(player)) {
                barrelBlock.openGui((ServerPlayerEntity) player);
                world.playSound(null,
                        barrelBlock.getPos().getX() + 0.5,
                        barrelBlock.getPos().getY() + 0.5,
                        barrelBlock.getPos().getZ() + 0.5, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

}
