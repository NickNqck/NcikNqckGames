package fr.games.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

/**
 * Easily create itemstacks, without messing your hands.
 * <i>Note that if you do use this in one of your projects, leave this notice.</i>
 * <i>Please do credit me if you do use this in one of your projects.</i>
 * @author NonameSL
 * @author Kuosai (V1 Remix)
 */
public class ItemBuilder {
    protected ItemStack is;
    /**
     * Create a new ItemBuilder from scratch.
     * @param m The material to create the ItemBuilder with.
     */
    public ItemBuilder(Material m){
        this(m, 1);
    }
    /**
     * Create a new ItemBuilder over an existing itemstack.
     * @param is The itemstack to create the ItemBuilder over.
     */
    public ItemBuilder(ItemStack is){
        this.is=is;
    }
    /**
     * Create a new ItemBuilder from scratch.
     * @param m The material of the item.
     * @param amount The amount of the item.
     */
    public ItemBuilder(Material m, int amount){
        is= new ItemStack(m, amount);
    }
    /**
     * Create a new ItemBuilder from scratch.
     * @param m The material of the item.
     * @param amount The amount of the item.
     * @param durability The durability of the item.
     */
    public ItemBuilder(Material m, int amount, int durability){
        is = new ItemStack(m, Math.min(amount, 64), (byte) durability);
    }
    /**
     * Clone the ItemBuilder into a new one.
     * @return The cloned instance.
     */
    public ItemBuilder clone(){
        return new ItemBuilder(is);
    }
    /**
     * Change the durability of the item.
     * @param dur The durability to set it to.
     */
    public ItemBuilder setDurability(short dur){
        is.setDurability(dur);
        return this;
    }
    public ItemBuilder setDurability(int dur) {
        is.setDurability((short)dur);
        return this;
    }
    /**
     * Set the displayname of the item.
     * @param name The name to change it to.
     */
    public ItemBuilder setName(String name){
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Add an unsafe enchantment.
     * @param ench The enchantment to add.
     * @param level The level to put the enchant on.
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level){
        is.addUnsafeEnchantment(ench, level);
        return this;
    }
    /**
     * Remove a certain enchant from the item.
     * @param ench The enchantment to remove
     */
    public ItemBuilder removeEnchantment(Enchantment ench){
        is.removeEnchantment(ench);
        return this;
    }
    /**
     * Set the skull owner for the item. Works on skulls only.
     * @param owner The name of the skull's owner.
     */
    public ItemBuilder setSkullOwner(String owner){
        try{
            SkullMeta im = (SkullMeta)is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        }catch(ClassCastException ignored){}
        return this;
    }

    public ItemBuilder setSkullOwner(String texture, String signature){

        SkullMeta headMeta = (SkullMeta) is.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {

        }
        is.setItemMeta(headMeta);
        return this;

    }
    /**
     * Add an enchant to the item.
     * @param ench The enchant to add
     * @param level The level
     */
    public ItemBuilder addEnchant(Enchantment ench, int level){
        if(level < 0) return this;
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Add multiple enchants at once.
     * @param enchantments The enchants to add.
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        is.addEnchantments(enchantments);
        return this;
    }
    /**
     * Sets infinity durability on the item by setting the durability to Short.MAX_VALUE.
     */
    public ItemBuilder setInfinityDurability(){
        is.setDurability(Short.MAX_VALUE);
        return this;
    }
    /**
     * Re-sets the lore.
     * @param lore The lore to set it to.
     */
    public ItemBuilder setLore(String... lore){
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }
    /**
     * Re-sets the lore.
     * @param lore The lore to set it to.
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Remove a lore line.
     * @param line The lore to remove.
     */
    public ItemBuilder removeLoreLine(String line){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if(!lore.contains(line))return this;
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Remove a lore line.
     * @param index The index of the lore line to remove.
     */
    public ItemBuilder removeLoreLine(int index){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if(index<0||index>lore.size())return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Add a lore line.
     * @param line The lore line to add.
     */
    public ItemBuilder addLoreLine(String line){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(im.hasLore())lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Add a lore line.
     * @param line The lore line to add.
     * @param pos The index of where to put it.
     */
    public ItemBuilder addLoreLine(String line, int pos){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    /**
     * Sets the dye color on an item.
     * <b>* Notice that this doesn't check for item type, sets the literal data of the dyecolor as durability.</b>
     * @param color The color to put.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDyeColor(DyeColor color){
        this.is.setDurability(color.getData());
        return this;
    }
    /**
     * Sets the dye color of a wool item. Works only on wool.
     * @deprecated As of version 1.2 changed to setDyeColor.
     * @see ItemBuilder@setDyeColor(DyeColor)
     * @param color The DyeColor to set the wool item to.
     */
    @Deprecated
    public ItemBuilder setWoolColor(DyeColor color){
        if(!is.getType().equals(Material.WOOL))return this;
        this.is.setDurability(color.getData());
        return this;
    }
    /**
     * Sets the armor color of a leather armor piece. Works only on leather armor pieces.
     * @param color The color to set it to.
     */
    public ItemBuilder setLeatherArmorColor(Color color){
        try{
            LeatherArmorMeta im = (LeatherArmorMeta)is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        }catch(ClassCastException ignored){}
        return this;
    }
    /**
     * Retrieves the itemstack from the ItemBuilder.
     * @return The itemstack created/modified by the ItemBuilder instance.
     */
    public ItemStack toItemStack(){
        return is;
    }

    public ItemBuilder setUnbreakable(boolean b){
        ItemMeta itemMeta = this.is.getItemMeta();
        itemMeta.spigot().setUnbreakable(b);
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder hideAllAttributes() {
        ItemMeta itemMeta = this.is.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        this.is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder hideEnchantAttributes() {
        ItemMeta itemMeta = this.is.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.is.setItemMeta(itemMeta);
        return this;
    }
    public ItemBuilder hideAttributes(ItemFlag... a) {
        ItemMeta itemMeta = this.is.getItemMeta();
        itemMeta.addItemFlags(a);
        this.is.setItemMeta(itemMeta);
        return this;
    }
    public ItemBuilder setAmount(int amount) {
        is.setAmount(amount);
        return this;
    }
    public ItemBuilder addPattern(Pattern pattern){
        try{
            BannerMeta meta = (BannerMeta) is.getItemMeta();
            meta.addPattern(pattern);
            is.setItemMeta(meta);
        }catch (ClassCastException ignored){}
        return this;
    }

    public ItemBuilder setPattern(int i, Pattern pattern){
        try{
            BannerMeta meta = (BannerMeta) is.getItemMeta();
            meta.setPattern(i, pattern);
            is.setItemMeta(meta);
        }catch (ClassCastException ignored){}
        return this;
    }

    public ItemBuilder addStoredEnchantment(Enchantment ench, int i) {
        try{
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) is.getItemMeta();
            meta.addStoredEnchant(ench, i, true);
            is.setItemMeta(meta);
        }catch (ClassCastException ignored){}
        return this;
    }

    public ItemBuilder setPotionEffect(PotionEffect potionEffect){
        try{
            PotionMeta meta = (PotionMeta) is.getItemMeta();
            meta.addCustomEffect(potionEffect, true);
            is.setItemMeta(meta);
        }catch (ClassCastException ignored){}
        return this;
    }

    public ItemBuilder setSplash(){
        try{
            Potion potion = new Potion(1);
            potion.setSplash(true);
            potion.apply(is);
        }catch (ClassCastException ignored){}
        return this;
    }
}