/**
 * AbyssalCraft
 * Copyright 2012-2015 Shinoow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shinoow.abyssalcraft.client.gui;

import org.lwjgl.opengl.GL11;
import com.shinoow.abyssalcraft.common.inventory.ContainerCrystalBag;
import com.shinoow.abyssalcraft.common.inventory.InventoryCrystalBag;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiCrystalBag extends GuiContainer
{

	private static final ResourceLocation iconLocation = new ResourceLocation("abyssalcraft:textures/gui/container/crystalbag.png");

	private final InventoryCrystalBag inventory;

	private int invRows;

	public GuiCrystalBag(ContainerCrystalBag container)
	{
		super(container);
		inventory = container.getBagInventory();
		short short1 = 256;
		int i = short1 - 138;
		invRows = inventory.getSizeInventory() / 9;
		ySize = i + invRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = inventory.hasCustomInventoryName() ? inventory.getInventoryName() : I18n.format(inventory.getInventoryName());
		fontRendererObj.drawString(s, 6, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory"), 6, ySize - 102 + 4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, invRows * 18 + 17);
		drawTexturedModalRect(k, l + invRows * 18 + 17, 0, 160, xSize, 96);
	}
}