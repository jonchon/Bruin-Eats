#!/usr/bin/python

import requests
import sys
import os
import csv

def parseRecipe(url,menu):
    
    customizeable=False
    recipeHTML = requests.get(url)
    recipe = recipeHTML.text
    findStr= "<div class=\"recipecontainer\">"
    findStr2="<h2>"
    findamp="&amp;"
    findapost="&#39;"
    finddquotes="&quot;"
    finde="&#233;"
    findR="&#174;"
    try:
        beginIndex=recipe.index(findStr)
    except ValueError:
        return -1

    recipe= recipe[ beginIndex+ len(findStr) -1:]
    recipe=recipe[recipe.index(findStr2)+len(findStr2):]
    
    nameEndIndex=recipe.find("</h2>")
    name=recipe[:nameEndIndex]
    name = name.replace(findamp, '&')
    name = name.replace(findapost, '\'')
    name = name.replace(finddquotes, '\"')
    name = name.replace(finde,'e')
    name = name.replace(findR,'')
    name = name.replace(',','')
    
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
        newFW.writerow([name,calories,fatCalories,totalFat,saturatedFat,transFat,cholesterol,sodium,carbs,fiber,sugar,protein,vitA,vitC,calcium,iron,Vege, Vegan, Peanuts, Tree, Wheat, Soy, Dairy, Eggs, Shellfish, Fish, "0"])
    


def parseMenu(url):
        
    websiteHTML=requests.get(url)
    webText=websiteHTML.text
  
    findMenu="<title>"
    menuIndex=webText.find(findMenu)
    webText=webText[menuIndex+len(findMenu):]
    endMenuIndex=webText.find(" Menu")
    menu=webText[:endMenuIndex]+".csv"
    menu=menu.lower()
    menu=menu.replace(' ','_')
    finde="&#233;"
    findamp="&amp;"
    findapost="&#39;"
    menu = menu.replace(findamp, 'and')
    menu = menu.replace(findapost, '')
    menu = menu.replace(finde,'e')
    if os.path.isfile(menu):
      os.remove(menu)
    with open(menu,'a') as newFile:
      newFW=csv.writer(newFile)
      newFW.writerow(["Menu Item","Calories","Fat Cal.","Total Fat (g)","Saturated Fat (g)","Trans Fat (g)","Cholesterol (mg)","Sodium (mg)","Total Carbohydrate (g)","Dietary Fiber (g)","Sugars (g)","Protein (g)","Vitamin A (%)","Vitamin C (%)","Calcium (%)","Iron (%)","Vegetarian","Vegan","Contains Peanuts","Contains Tree Nuts","Contains Wheat","Contains Soy","Contains Dairy","Contains Eggs","Contains Shellfish","Contains Fish","Add-on"])
      
    while True:
      findStr="recipelink\" href=\""
      recipeBeginLink=webText.find(findStr)
      if recipeBeginLink == -1:
          print("end of data set for "+menu)
          break
      webText=webText[recipeBeginLink+len(findStr):]
      recipeEndLink=webText.find("\"")   #start of "http..."
      recipeLink=webText[:recipeEndLink]
      if recipeLink[0:5]!="http:":
        recipeLink="http://menu.dining.ucla.edu"+recipeLink
        
      webText=webText[recipeEndLink:]  
      parseRecipe(recipeLink,menu)
#      if parseRecipe(recipeLink,menu) == -1:
 #       failedEndLink=webText.find("<")
  #      failedName=webText[2:failedEndLink]
   #     str1="No Data Given"
    #    failString=str1+"\t"+failedName
     #   print(failString)
              

def main():

  BCafe="http://menu.dining.ucla.edu/Menus/BruinCafe"
  Cafe1919="http://menu.dining.ucla.edu/Menus/Cafe1919"
  Rende="http://menu.dining.ucla.edu/Menus/Rendezvous"
  Study="http://menu.dining.ucla.edu/Menus/HedrickStudy"
  DeNeveGrab="http://menu.dining.ucla.edu/Menus/DeNeveGrabNGo"
  DeNeveLate="http://menu.dining.ucla.edu/Menus/DeNeveLateNight"
  BPlateGrab="http://menu.dining.ucla.edu/Menus/BruinPlateGrabNGoBreakfast"
  urlList=[BCafe,Cafe1919,Rende,Study,DeNeveGrab,DeNeveLate,BPlateGrab]
  for i in range(0,7):
    parseMenu(urlList[i])
    
main()