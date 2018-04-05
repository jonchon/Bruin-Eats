# BruinWats

BruinWats is a dietary tracker Android application that allows users to track their dietary nutritional intake specifically from the UCLA dining facilities that are present on The Hill and record them to allow users to eat healthier and succeed in their fitness and health goals.

Check howtorun.txt on how to run app.

Demo Run of the Application (Click for youtube video)


[![Demo](https://img.youtube.com/vi/dRvKhcs8IdM/0.jpg)](https://www.youtube.com/watch?v=dRvKhcs8IdM)


## Backend Design

##### CSV Files for Frontend display:
- Python Scripts parseDH.py and parseTakeout.py
- Automated the process of crawling HTML of all dining halls and takeout restaurant menus
  - Recognized patterns in the HTML to isolate the nutritional data and extract into CSV files
  - parseDH.py implemented to run everyday using batch file to run every 86400 seconds to fetch dail data

## Frontend Design

##### Dashboard:
- Displays all the previous food items added during the day
- Pie chart detailing the amount of protein, calories, and carbs consumed proportionally
- Button on bottom right brings user to add food item activity

##### Navigation Bar:
- Contains tab to history and settings pages on the app
##### Adding New Food Item:
- Once button is clicked, whole list of available dining halls are listed
- Each dining hall is a gateway to specific dining menu 
  - List of food items as highlightable text so that user can input what he/she has eaten that day
- Dashboard should update what food items have been selected
##### Displaying Menus:
- Each menu is stored in CSV files given by backend code
- A function named readMacros() parses through CSV files and then stores data into FoodItem object
- Each FoodItem object’s name is then listed out 


