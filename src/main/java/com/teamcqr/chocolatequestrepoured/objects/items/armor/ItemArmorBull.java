package com.teamcqr.chocolatequestrepoured.objects.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;
import com.teamcqr.chocolatequestrepoured.client.init.ModArmorModels;
import com.teamcqr.chocolatequestrepoured.util.ItemUtil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorBull extends ArmorCQRBase {

	private AttributeModifier strength;

	public ItemArmorBull(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);

		this.strength = new AttributeModifier("BullArmorModifier", 1D, 0);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EntityLiving.getSlotForItemStack(stack)) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), this.strength);
		}

		return multimap;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			tooltip.add(TextFormatting.BLUE + I18n.format("description.bull_armor.name"));
		} else {
			tooltip.add(TextFormatting.BLUE + I18n.format("description.click_shift.name"));
		}
	}

	@Override
	public void onArmorTick(World worldIn, EntityPlayer player, ItemStack stack) {
		if (ItemUtil.hasFullSet(player, ItemArmorBull.class)) {
			if (player.isSprinting()) {
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 1, false, false));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getBipedArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot) {
		return armorSlot == EntityEquipmentSlot.LEGS ? ModArmorModels.bullArmorLegs : ModArmorModels.bullArmor;
	}

}
