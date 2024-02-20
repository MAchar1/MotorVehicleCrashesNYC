# MotorVehicleCrashesNYC

The dataset I will be working with contains Motor Vehicle Collisions in New York City. Within the dataset there is information on the time and place of every accident, as well as the number of injured/killed, the types of vehicles involved, and the contributing factors to the accident. The [Pareto principle](https://en.wikipedia.org/wiki/Pareto_principle) states that a majority (~80%) of the consequences come from the minority (~20%) of the causes. I want to see if this applies to streets that car accidents take place in NYC. My hypothesis is that around 20% of the streets of NYC will be the location of 80% of the accidents.

I found this dataset through data.gov and the city of new york data website. Since this dataset is quite large you must download git lfs (large file storage). 
After downloading git lfs you must install git lfs in the repo by using:<br/>
`git lfs install`

Then track csv files with:<br/>
`git lfs track "*.csv"`

Finally to download the dataset:<br/> 
`curl 'https://data.cityofnewyork.us/api/views/h9gi-nx95/rows.csv?accessType=DOWNLOAD` > MVCollisions.csv

Using head on the file resulted in:
[img](https://imgur.com/R7dX9t2)

next I created a file with all of the on streets of the datasets:
`awk -F ',' '{print $8}' MVCollisions.csv > streets.csv`

Another file I created is the a file with all of the date and location data for each collision.
`awk -F ',' '{print $1, $7}' MVCollision.csv > date_location.csv`