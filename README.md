# GoustoProducts

### Notes
I used minSdkVersion 24 so the app could run on 73.7% of Android devices, making it easier to test my app on a wide range of physical devices if needed.

Not a 3rd party library, but I used Room because of how easy it is to set up a persistant database.

### Justification for 3rd party libraries
* Picasso - Makes downloading the images from the url much easier and I didn't want to clutter up the app code
* Mockito - Makes mocking classes for testing straight forward
* Koin - Like Dagger but better! Koin is lightweight and seemed like the clear choice for this small app
