#!/usr/bin/python

import os
import requests
import csv

def get_data(mealURL, menu, custom):
    customizeable=False
    recipeHTML = requests.get(mealURL)
    recipe = recipeHTML.text
    findStr= "<div class=\"recipecontainer\">"
    findStr2="<h2>"
    findamp="&amp;"
    findapost="&#39;"
    finddquotes="&quot;"
    finde="&#233;"
    findR="&#174;"
    findN="&#241;"
    findC="&#231;"
    
    try:
        beginIndex=recipe.index(findStr)
    except ValueError:
        return -1

    recipe= recipe[ beginIndex+ len(findStr) -1:]
    recipe=recipe[recipe.index(findStr2)+len(findStr2):]
    
    nameEndIndex=recipe.find("</h2>")
    name=recipe[:nameEndIndex].replace(',','')
    name = name.replace(findamp, '&')
    name = name.replace(findapost, '\'')
    name = name.replace(finddquotes, '\"')
    name = name.replace(finde,'e')
    name = name.replace(findR,'')
    name = name.replace(findN,'n')
    name = name.replace(findC,'c')
    
    add = 0
    if (custom):
        add = 1
    
    Vege = 0
    findVege = "Vegetarian Menu Option"
    if (recipe.find(findVege) != -1):
        Vege=1

    Vegan = 0
    findVegan = "Vegan Menu Option"
    if (recipe.find(findVegan) != -1):
        Vegan = 1

    Peanuts = 0
    findPeanuts = "Contains Peanuts"
    if (recipe.find(findPeanuts) != -1):
        Peanuts = 1

    Tree = 0
    findTree = "Contains Tree Nuts"
    if (recipe.find(findTree) != -1):
        Tree = 1

    Wheat = 0
    findWheat = "Contains Wheat"
    if (recipe.find(findWheat) != -1):
        Wheat = 1

    Soy = 0
    findSoy = "Contains Soy"
    if (recipe.find(findSoy) != -1):
        Soy = 1

    Dairy = 0
    findDairy = "Contains Dairy"
    if (recipe.find(findDairy) != -1):
        Dairy = 1

    Eggs = 0
    findEggs = "Contains Eggs"
    if (recipe.find(findEggs) != -1):
        Eggs = 1

    Shellfish = 0
    findShellfish = "Contains Shellfish"
    if (recipe.find(findShellfish) != -1):
        Shellfish = 1

    Fish = 0
    findFish = "Contains Fish"
    if (recipe.find(findFish) != -1):
        Fish = 1
    
    findCalories="Calories</span> "
    caloriesIndex=recipe.find(findCalories)
    recipe=recipe[caloriesIndex+len(findCalories):]
    endCaloriesIndex=recipe.find(" ")
    calories=recipe[:endCaloriesIndex]
    calories.replace("--",'')
    if calories=="<span":
        customizeable=True
        findCalories="val_Calories\">"
        caloriesIndex=recipe.find(findCalories)
        recipe=recipe[caloriesIndex+len(findCalories):]
        endCaloriesIndex=recipe.find("<")
        calories=recipe[:endCaloriesIndex]
        calories=calories.replace("--",'')
        
    if customizeable==True:
        findFatCalories="val_CaloriesFromFat\">"
    else:
        findFatCalories="Fat Cal. "
    fatCaloriesIndex=recipe.find(findFatCalories)
    recipe=recipe[fatCaloriesIndex+len(findFatCalories):]
    endFatCaloriesIndex=recipe.find("<")
    fatCalories=recipe[:endFatCaloriesIndex]
    fatCalories=fatCalories.replace("--",'')

    if customizeable==True:
        findTotalFat="val_TotalFat\">"
    else:
        findTotalFat="Total Fat</span> "
    totalFatIndex=recipe.find(findTotalFat)
    recipe=recipe[totalFatIndex+len(findTotalFat):]
    if customizeable==True:
        endTotalFatIndex=recipe.find("<")
    else:
        endTotalFatIndex=recipe.find("g")
    totalFat=recipe[:endTotalFatIndex]
    totalFat=totalFat.replace("--",'')
    
    if customizeable==True:
        findSaturatedFat="val_SaturatedFat\">"
    else:
        findSaturatedFat="Saturated Fat "
    saturatedFatIndex=recipe.find(findSaturatedFat)
    recipe=recipe[saturatedFatIndex+len(findSaturatedFat):]
    if customizeable==True:
        endSaturatedFatIndex=recipe.find("<")
    else:
        endSaturatedFatIndex=recipe.find("g")
    saturatedFat=recipe[:endSaturatedFatIndex]
    saturatedFat=saturatedFat.replace("--",'')
    
    if customizeable==True:
        findTransFat="val_TransFat\">"
    else:
        findTransFat="Trans Fat "
    transFatIndex=recipe.find(findTransFat)
    recipe=recipe[transFatIndex+len(findTransFat):]
    if customizeable==True:
        endTransFatIndex=recipe.find("<")
    else:
        endTransFatIndex=recipe.find("g")
    transFat=recipe[:endTransFatIndex]
    transFat=transFat.replace("--",'')
    
    if customizeable==True:
        findCholesterol="val_Cholesterol\">"
    else:
        findCholesterol="Cholesterol</span> "
    cholesterolIndex=recipe.find(findCholesterol)
    recipe=recipe[cholesterolIndex+len(findCholesterol):]
    if customizeable==True:
        endCholesterolIndex=recipe.find("<")
    else:
        endCholesterolIndex=recipe.find("mg")
    cholesterol=recipe[:endCholesterolIndex]
    cholesterol=cholesterol.replace("--",'')
    
    if customizeable==True:
        findSodium="val_Sodium\">"
    else:
        findSodium="Sodium</span> "
    sodiumIndex=recipe.find(findSodium)
    recipe=recipe[sodiumIndex+len(findSodium):]
    if customizeable==True:
        endSodiumIndex=recipe.find("<")
    else:
        endSodiumIndex=recipe.find("mg")
    sodium=recipe[:endSodiumIndex]
    sodium=sodium.replace("--",'')
    
    if customizeable==True:
        findCarbs="val_TotalCarbohydrate\">"
    else:
        findCarbs="Total Carbohydrate</span> "
    carbsIndex=recipe.find(findCarbs)
    recipe=recipe[carbsIndex+len(findCarbs):]
    if customizeable==True:
        endCarbsIndex=recipe.find("<")
    else:
        endCarbsIndex=recipe.find("g")
    carbs=recipe[:endCarbsIndex]
    carbs=carbs.replace("--",'')
    
    if customizeable==True:
        findFiber="val_DietaryFiber\">"
    else:
        findFiber="Dietary Fiber "
    fiberIndex=recipe.find(findFiber)
    recipe=recipe[fiberIndex+len(findFiber):]
    if customizeable==True:
        endFiberIndex=recipe.find("<")
    else:
        endFiberIndex=recipe.find("g")
    fiber=recipe[:endFiberIndex]
    fiber=fiber.replace("--",'')
    
    if customizeable==True:
        findSugar="val_Sugars\">"
    else:
        findSugar="Sugars "
    sugarIndex=recipe.find(findSugar)
    recipe=recipe[sugarIndex+len(findSugar):]
    if customizeable==True:
        endSugarIndex=recipe.find("<")
    else:
        endSugarIndex=recipe.find("g")
    sugar=recipe[:endSugarIndex]
    sugar=sugar.replace("--",'')
    
    if customizeable==True:
        findProtein="val_Protein\">"
    else:
        findProtein="Protein</span> "
    proteinIndex=recipe.find(findProtein)
    recipe=recipe[proteinIndex+len(findProtein):]
    if customizeable==True:
        endProteinIndex=recipe.find("<")
    else:
        endProteinIndex=recipe.find("g")
    protein=recipe[:endProteinIndex]
    protein=protein.replace("--",'')
    
    if customizeable==True:
        findVitA="val_Bottom_Vitamin_A\">"
    else:
        findVitA="nfvitpct\">"
    vitAIndex=recipe.find(findVitA)
    recipe=recipe[vitAIndex+len(findVitA):]
    if customizeable==True:
        endVitAIndex=recipe.find("<")
    else:
        endVitAIndex=recipe.find("%")
    vitA=recipe[:endVitAIndex]
    vitA=vitA.replace("--", '')
    
    if customizeable==True:
        findVitC="val_Bottom_Vitamin_C\">"
    else:
        findVitC="nfvitpct\">"
    vitCIndex=recipe.find(findVitC)
    recipe=recipe[vitCIndex+len(findVitC):]
    if customizeable==True:
        endVitCIndex=recipe.find("<")
    else:
        endVitCIndex=recipe.find("%")
    vitC=recipe[:endVitCIndex]
    vitC=vitC.replace("--",'')
    
    if customizeable==True:
        findCalcium="val_Bottom_Calcium\">"
    else:
        findCalcium="nfvitpct\">"
    calciumIndex=recipe.find(findCalcium)
    recipe=recipe[calciumIndex+len(findCalcium):]
    if customizeable==True:
        endCalciumIndex=recipe.find("<")
    else:
        endCalciumIndex=recipe.find("%")
    calcium=recipe[:endCalciumIndex]
    calcium=calcium.replace("--",'')
    
    if customizeable==True:
        findIron="val_Bottom_Iron\">"
    else:
        findIron="nfvitpct\">"
    ironIndex=recipe.find(findIron)
    recipe=recipe[ironIndex+len(findIron):]
    if customizeable==True:
        endIronIndex=recipe.find("<")
    else:
        endIronIndex=recipe.find("%")
    iron=recipe[:endIronIndex]
    iron=iron.replace("--",'')

    name=name.replace(u"\u2122",'')
    
    if calories=="<span":
        calories=fatCalories=totalFat=saturatedFat=transFat=cholesterol=sodium=carbs=fiber=sugar=protein=vitA=vitC=calcium=iron="Customizeable"
    
    with open(menu,'a') as newFile:
        newFW=csv.writer(newFile)
        newFW.writerow([name,calories,fatCalories,totalFat,saturatedFat,transFat,cholesterol,sodium,carbs,fiber,sugar,protein,vitA,vitC,calcium,iron,Vege, Vegan, Peanuts, Tree, Wheat, Soy, Dairy, Eggs, Shellfish, Fish, add])

def find_meals(mealURL, hall):
    #used to get rid of weird characters
    mealHTML = requests.get(mealURL)
    meal = mealHTML.text
    meal = meal.replace("&amp;", '&')
    meal = meal.replace("&#39;", '\'')
    meal = meal.replace("&quot;", '\"')
    meal = meal.replace("&#233;", 'e')

    #Used for naming .csv files
    findMenu="<option value=\"/Menus/Yesterday/"
    menuIndex=meal.find(findMenu)
    webText=meal[menuIndex+len(findMenu):]
    endMenuIndex=webText.find("\">")

    #used for find the hall
    find_text = "col-header\">" + hall
    curr_hall = meal.find(find_text) + 12
    next_hall = meal.find("col-header", curr_hall)
    curr_hall_last_ind = meal.find("<", curr_hall)
    curr_name = meal[curr_hall:curr_hall_last_ind]
    curr_name = curr_name.replace(' ', '-')

    #finds all of the recipes
    recipe_ind = curr_hall
    recipe_ind = meal.find("recipelink", recipe_ind) + 1
    
    #creates the .csv file and adds some information into it
    menu = webText[:endMenuIndex].lower() + "_" + curr_name.lower().replace('-', '_') + ".csv"
    #print(menu)
    if os.path.isfile(menu):
        os.remove(menu)
    with open(menu,'a') as newFile:
        newFW=csv.writer(newFile)
        newFW.writerow(["Menu Item","Calories","Fat Cal.","Total Fat (g)","Saturated Fat (g)","Trans Fat (g)","Cholesterol (mg)","Sodium (mg)","Total Carbohydrate (g)","Dietary Fiber (g)","Sugars (g)","Protein (g)","Vitamin A (%)","Vitamin C (%)","Calcium (%)","Iron (%)", "Vegetarian", "Vegan", "Contains Peanuts", "Contains Tree Nuts", "Contains Wheat", "Contains Soy", "Contains Dairy", "Contains Eggs", "Contains Shellfish", "Contains Fish", "Add-On"])
        
    while (recipe_ind < next_hall or (next_hall == -1 and recipe_ind != 0)):
        link_ind = recipe_ind     #for the nutrition facts
        recipe_ind = meal.find(">", recipe_ind) + 1
        recipe_last_ind = meal.find("<", recipe_ind)
        recipe_name = meal[recipe_ind : recipe_last_ind]
        #print (recipe_name + " This is a recipe for " + curr_name)
        #used to identify add-on itmes
        custom = 0
        if (meal.find("&", link_ind - 60, link_ind) != -1 or meal.find("w/", link_ind - 60, link_ind) != -1):
            custom = 1
        #used to find the link to get nutrition facts
        link_ind = meal.find("http:", link_ind)
        link_last_ind = meal.find("\"", link_ind)
        link = meal[link_ind:link_last_ind]

        get_data(link, menu, custom)
        recipe_ind = meal.find("recipelink", recipe_ind) + 1
            
def dining_hall_meals(mealURL):
    mealHTML = requests.get(mealURL)
    meal = mealHTML.text

    hall_name_last_ind = 1
    while True:
        hall_name_identifier = meal.find("col-header", hall_name_last_ind) #col-header proceeds hall name
        if hall_name_identifier == -1:
            return 0
        hall_name_ind = hall_name_identifier + 12 #12 extra characters
        hall_name_last_ind = meal.find("<",hall_name_ind)
        hall_name = meal[ hall_name_ind : hall_name_last_ind ]
        find_meals(mealURL, hall_name)
    return hall_name_last_ind

def main():
    result = 1
    breakfastURL = "http://menu.dining.ucla.edu/Menus/Today/breakfast" 
    lunchURL = "http://menu.dining.ucla.edu/Menus/Today/lunch"
    dinnerURL = "http://menu.dining.ucla.edu/Menus/Today/Dinner"

    dining_hall_meals(breakfastURL)
    dining_hall_meals(lunchURL)
    dining_hall_meals(dinnerURL)

main()
