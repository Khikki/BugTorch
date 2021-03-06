package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

@Mixin(Block.class)
public class MixinBlock_ShearsDupeFix {

    /**
     * @author jss2a98aj
     * @reason Keeps shearable items from still giving normal drops when sheared (item dupe fix)
     */
    @Inject(method = "harvestBlock", at = @At("HEAD"), cancellable = true)
    private void onHarvestBlock(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata, CallbackInfo ci) {
        if(this instanceof IShearable && playerIn.getHeldItem() != null && playerIn.getHeldItem().getItem() instanceof ItemShears) {
            playerIn.addExhaustion(0.025F);
            ci.cancel();
        }
    }

}
