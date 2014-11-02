/**AbyssalCraft
 *Copyright 2012-2014 Shinoow
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package com.shinoow.abyssalcraft.common.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.blocks.*;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AbyssalCraftEventHooks {

	//Bonemeal events
	@SubscribeEvent
	public void bonemealUsed(BonemealEvent event) {
		if (event.block == AbyssalCraft.DLTSapling) {
			if (!event.world.isRemote)
				((BlockDLTSapling)AbyssalCraft.DLTSapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
			event.setResult(Result.ALLOW);
		}

		if (event.block == AbyssalCraft.dreadsapling) {
			if (!event.world.isRemote)
				((BlockDreadSapling)AbyssalCraft.dreadsapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
			event.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public void populateChunk(PopulateChunkEvent.Pre event) {
		Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
		for (ExtendedBlockStorage storage : chunk.getBlockStorageArray())
			if (storage != null && storage.getYLocation() >= 60)
				for (int x = 0; x < 16; ++x)
					for (int y = 0; y < 16; ++y)
						for (int z = 0; z < 16; ++z)
							if(chunk.getBiomeGenForWorldCoords(x, z, event.world.getWorldChunkManager()) == AbyssalCraft.DarklandsMountains)
								if (storage.getBlockByExtId(x, y, z) == Blocks.stone)
									storage.func_150818_a(x, y, z, AbyssalCraft.Darkstone);
	}

	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.Darkstone_cobble))
			event.entityPlayer.addStat(AbyssalCraft.mineDS, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.abyore))
			event.entityPlayer.addStat(AbyssalCraft.mineAby, 1);
		if(event.item.getEntityItem().getItem() == AbyssalCraft.Coralium)
			event.entityPlayer.addStat(AbyssalCraft.mineCorgem, 1);
		if(event.item.getEntityItem().getItem() == AbyssalCraft.Cchunk)
			event.entityPlayer.addStat(AbyssalCraft.mineCor, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.DGhead))
			event.entityPlayer.addStat(AbyssalCraft.ghoulhead, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.Phead))
			event.entityPlayer.addStat(AbyssalCraft.petehead, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.Whead))
			event.entityPlayer.addStat(AbyssalCraft.wilsonhead, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.Ohead))
			event.entityPlayer.addStat(AbyssalCraft.orangehead, 1);
		if(event.item.getEntityItem().getItem() == AbyssalCraft.devsword)
			event.entityPlayer.addStat(AbyssalCraft.secret1, 1);
		if(event.item.getEntityItem().getItem() == AbyssalCraft.portalPlacer)
			event.entityPlayer.addStat(AbyssalCraft.GK1, 1);
		if(event.item.getEntityItem().getItem() == AbyssalCraft.portalPlacerDL)
			event.entityPlayer.addStat(AbyssalCraft.GK2, 1);
		if(event.item.getEntityItem().getItem() == Item.getItemFromBlock(AbyssalCraft.PSDL))
			event.entityPlayer.addStat(AbyssalCraft.findPSDL, 1);
	}

	/**@SubscribeEvent
	public void darkRealm(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayerMP){
			WorldServer worldServer = (WorldServer)event.entityLiving.worldObj;
			EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
			if(player.dimension == AbyssalCraft.configDimId3 && player.posY <= 0){
				player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, 255));
				player.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 20));
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, AbyssalCraft.configDimId4, new TeleporterOmothol(worldServer));
			}
		}
		if(event.entityLiving.worldObj.isRemote && event.entityLiving.dimension == AbyssalCraft.configDimId4){
			Random rand = new Random();
			event.entityLiving.worldObj.spawnParticle("largesmoke", event.entityLiving.posX + (rand.nextDouble() - 0.5D) * event.entityLiving.width,
					event.entityLiving.posY + rand.nextDouble() * event.entityLiving.height,
					event.entityLiving.posZ + (rand.nextDouble() - 0.5D) * event.entityLiving.width, 0,0,0);
		}
	}*/

	@SubscribeEvent
	public void enchantmentEffects(LivingAttackEvent event){
		if(event.source instanceof EntityDamageSource){
			Entity entity = ((EntityDamageSource)event.source).getEntity();
			if(entity instanceof EntityLivingBase){
				ItemStack item = ((EntityLivingBase)entity).getHeldItem();
				if(item != null && item.hasTagCompound()){
					NBTTagList enchTag = item.getEnchantmentTagList();
					for(int i = 0; i < enchTag.tagCount(); i++)
						if(enchTag.getCompoundTagAt(i).getInteger("id") == AbyssalCraft.coraliumE.effectId)
							event.entityLiving.addPotionEffect(new PotionEffect(AbyssalCraft.Cplague.id, 100));
						else if(enchTag.getCompoundTagAt(i).getInteger("id") == AbyssalCraft.dreadE.effectId)
							event.entityLiving.addPotionEffect(new PotionEffect(AbyssalCraft.Dplague.id, 100));
				}
			}
		}
	}

	/**@SubscribeEvent
	public void ironWall(LivingHurtEvent event){
		ItemStack item = event.entityLiving.getEquipmentInSlot(3);
		if(item != null && item.hasTagCompound()){
			NBTTagList enchTag = item.getEnchantmentTagList();
			for(int i = 0; i < enchTag.tagCount(); i++){
				if(enchTag.getCompoundTagAt(i).getInteger("id") == AbyssalCraft.ironWall.effectId)
					event.entityLiving.setVelocity(0, 0, 0);
			}
		}
	}

	/**@SubscribeEvent
	public void renderDepthsHelmetOverlay(RenderGameOverlayEvent event) {
		final ResourceLocation coraliumBlur = new ResourceLocation("abyssalcraft:textures/misc/coraliumblur.png");

		ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);
		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && helmet != null && helmet.getItem() == AbyssalCraft.Depthshelmet) {
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

			Tessellator t = Tessellator.instance;

			ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int width = scale.getScaledWidth();
			int height = scale.getScaledHeight();

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			Minecraft.getMinecraft().renderEngine.bindTexture(coraliumBlur);

			t.startDrawingQuads();
			t.addVertexWithUV(0.0D, (double)height, 90.0D, 0.0D, 1.0D);
			t.addVertexWithUV((double)width, (double)height, 90.0D, 1.0D, 1.0D);
			t.addVertexWithUV((double)width, 0.0D, 90.0D, 1.0D, 0.0D);
			t.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
			t.draw();

			GL11.glPopAttrib();
		}
	}*/
}