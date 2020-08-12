# GoustoProducts

### Notes
I used minSdkVersion 24 so the app could run on 73.7% of Android devices, making it easier to test my app on a wide range of physical devices if needed.

Not a 3rd party library, but I used Room because of how easy it is to set up a persistent database.

### Justification for 3rd party libraries
* Picasso - Makes downloading the images from the url much easier and I didn't want to clutter up the app code
* Mockito - Makes mocking classes for testing straight forward
* Koin - Like Dagger but better! Koin is lightweight and seemed like the clear choice for this small app

### How I would improve the app

#### Better use of available data
There's a wealth of information returned from the API. I chose to model everything out to make it easier to use that data in future.

There's a few items that are not actual products, most don't have an image or a description, so I would filter out those items and hide them from the list. 

Most of the items fall into 7 or 8 categories, so I would make a few category tabs so the user could quickly navigate the products on offer.

There's a lot of information stored in the title of each product. Volumes, weight, dairy-free, gluten free, discounts, RRP. I would try and extract that information and present it in a more interesting way. e.g. displaying the RRP or non-discounted price next to the listPrice of the product. I could also have badges on each products denoting whether it's dairy free or gluten free (I could also split GF and DF into separate categories)

I could also improve the search functionality, implementing fuzzy string searching to make it easier to find products. I could also write the search function to find things that are tagged as gifts (gifts could be a separate category too).

There's also some allergen information of certain products, so I could add information to the product details. If this app was expanded to have user accounts, I would let the user indicate their dietary preferences and automatically filter out things that don't fit (maybe not hide them, but grey those options out and put them at the bottom of the product list).

#### Better architecture
There's some heavy stuff in ProductsFragment, which feels more natural to have in the ViewModel and work with LiveData between fragment-VM, however unit testing the VM with the Room database became very complicated, and I couldn't figure it out in time, so I had to revert back to doing a lot of heavy lifting in the fragment.

Some Databinding would be a nice to have, has an added benefit of making the code a bit leaner.

