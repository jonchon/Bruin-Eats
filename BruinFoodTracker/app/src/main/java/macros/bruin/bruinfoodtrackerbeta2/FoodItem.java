package macros.bruin.bruinfoodtrackerbeta2;

/**
 * Created by jjcgo on 3/9/2018.
 * Modified by Junhyuck Lee on 3/15/2018.
 */

class FoodItem {
    String food_item;
    private double calories;
    private double fat_calories;
    private double total_fat;
    private double saturated_fat;
    private double trans_fat;
    private double cholesterol;
    private double sodium;
    private double total_carbohydrate;
    private double dietary_fiber;
    private double sugars;
    private double protein;
    private int vitamin_a;
    private int vitamin_c;
    private int calcium;
    private int iron;
    private boolean vegetarian;
    private boolean vegan;
    private boolean peanuts;
    private boolean tree_nuts;
    private boolean wheat;
    private boolean soy;
    private boolean dairy;
    private boolean eggs;
    private boolean shellfish;
    private boolean fish;
    private boolean add_on;

    public String getFood_item() {
        return food_item;
    }

    public void setFood_item(String food_item) {
        this.food_item = food_item;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat_calories() {
        return fat_calories;
    }

    public void setFat_calories(double fat_calories) {
        this.fat_calories = fat_calories;
    }

    public double getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(double total_fat) {
        this.total_fat = total_fat;
    }

    public double getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(double saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getTotal_carbohydrate() {
        return total_carbohydrate;
    }

    public void setTotal_carbohydrate(double total_carbohydrate) {
        this.total_carbohydrate = total_carbohydrate;
    }

    public double getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(double dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public int getVitamin_a() {
        return vitamin_a;
    }

    public void setVitamin_a(int vitamin_a) {
        this.vitamin_a = vitamin_a;
    }

    public int getVitamin_c() {
        return vitamin_c;
    }

    public void setVitamin_c(int vitamin_c) {
        this.vitamin_c = vitamin_c;
    }

    public int getCalcium() {
        return calcium;
    }

    public void setCalcium(int calcium) {
        this.calcium = calcium;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isPeanuts() {
        return peanuts;
    }

    public void setPeanuts(boolean peanuts) {
        this.peanuts = peanuts;
    }

    public boolean isTree_nuts() {
        return tree_nuts;
    }

    public void setTree_nuts(boolean tree_nuts) {
        this.tree_nuts = tree_nuts;
    }

    public boolean isWheat() {
        return wheat;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isDairy() {
        return dairy;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public boolean isEggs() {
        return eggs;
    }

    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isShellfish() {
        return shellfish;
    }

    public void setShellfish(boolean shellfish) {
        this.shellfish = shellfish;
    }

    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public boolean isAdd_on() {
        return add_on;
    }

    public void setAdd_on(boolean add_on) {
        this.add_on = add_on;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "food_item='" + food_item + '\'' +
                ", calories=" + calories +
                ", fat_calories=" + fat_calories +
                ", total_fat=" + total_fat +
                ", saturated_fat=" + saturated_fat +
                ", trans_fat=" + trans_fat +
                ", cholesterol=" + cholesterol +
                ", sodium=" + sodium +
                ", total_carbohydrate=" + total_carbohydrate +
                ", dietary_fiber=" + dietary_fiber +
                ", sugars=" + sugars +
                ", protein=" + protein +
                ", vitamin_a=" + vitamin_a +
                ", vitamin_c=" + vitamin_c +
                ", calcium=" + calcium +
                ", iron=" + iron +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", peanuts=" + peanuts +
                ", tree_nuts=" + tree_nuts +
                ", wheat=" + wheat +
                ", soy=" + soy +
                ", dairy=" + dairy +
                ", eggs=" + eggs +
                ", shellfish=" + shellfish +
                ", fish=" + fish +
                ", add_on=" + add_on +
                '}';
    }

    public String toMacroString() {
        return food_item + "|" +
                calories + "|" +
                fat_calories + "|" +
                total_fat + "|" +
                saturated_fat + "|" +
                trans_fat + "|" +
                cholesterol + "|" +
                sodium + "|" +
                total_carbohydrate + "|" +
                dietary_fiber + "|" +
                sugars + "|" +
                protein + "|" +
                vitamin_a + "|" +
                vitamin_c + "|" +
                calcium + "|" +
                iron + "|" +
                "1" + "|"
                ;
    }
}
