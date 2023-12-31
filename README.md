﻿# weatherApp
A basic weather app using https://www.weatherapi.com/.

![Image of daily view of weather cards](https://github.com/ranjotsd/weatherApp/blob/main/app/images/daily_image.jpg)
![Image of hourly view of weather cards](https://github.com/ranjotsd/weatherApp/blob/main/app/images/hourly_image.jpg)

Demo Video: https://photos.app.goo.gl/yMVoD3MU6bu46VRVA

Future work:
- Add custom location option
- Add more thorough tests
- Create a theme
- Make a single request for the week instead of 7 for each day.
- Add more information (such as percipitation, or historical weather)
- Add app icon
- Add cacheing and daily background tasks so data can be collected and loaded in cases where there is no internet access.

The Weather API provides lots of imformation, such as daily and hourly conditions, and a Url to an image.
\
This was all in the form a JsonObject, so I parsed the results and added it to an autobuilder to simplify accessing the data.
\
The weekly and hourly forecasts were displayed using a recylcer view because it makes it easy to efficiently display data.
\
I used espresso to create a screenshot test to verify the UI (TODO for hour cards), and unit tests to verify the JsonObject is parsed correctly.
\
The project was created with Android studio using gradle, and used other APIs, such as Picasso to retrieve images and LocalDateTime.
