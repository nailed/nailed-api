package jk_5.nailed.api.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * No description given
 *
 * @author jk-5
 */
public final class ItemStack {

    private Material material;
    private int amount;
    private short damage;

    private String displayName = null;
    private final List<String> lore = new ArrayList<String>();
    private final Map<String, String> tags = new HashMap<String, String>();

    public ItemStack(Material material) {
        this(material, 1, (short) 0);
    }

    public ItemStack(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemStack(Material material, int amount, short damage) {
        this.material = material;
        this.amount = amount;
        this.damage = damage;
    }

    public short getDamage() {
        return damage;
    }

    public int getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDamage(short damage) {
        this.damage = damage;
    }

    public void addLore(String lore){
        this.lore.add(lore);
    }

    public List<String> getLore() {
        return lore;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getMaxStackSize(){
        if(this.material == null){
            return -1;
        }else{
            return this.material.getMaxStackSize();
        }
    }

    public void setTag(String key, String value){
        this.tags.put(key, value);
    }

    public String getTag(String key){
        return this.tags.get(key);
    }

    @Override
    public String toString() {
        return "ItemStack{" +
                "material=" + material +
                ", amount=" + amount +
                ", damage=" + damage +
                (displayName != null ? ", displayName='" + displayName + '\'' : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        ItemStack itemStack = (ItemStack) o;

        if(amount != itemStack.amount){
            return false;
        }
        if(damage != itemStack.damage){
            return false;
        }
        if(material != itemStack.material){
            return false;
        }

        return true;
    }

    public boolean isSimilar(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        ItemStack itemStack = (ItemStack) o;

        if(damage != itemStack.damage){
            return false;
        }
        if(material != itemStack.material){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = material != null ? material.hashCode() : 0;
        result = 31 * result + amount;
        result = 31 * result + (int) damage;
        return result;
    }
}
