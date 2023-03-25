# MOVIE LIST APP

This is a simple movie list application built using the MVVM architecture pattern, data binding, and the ROOM database library. With this app, users can add new movies to the list, edit existing movies, delete movies, search for movies by name, and sort the movie list by release date.

## Getting Started

To get started with the app, clone this repository to your local machine and import the project into Android Studio. You can then build and run the app on an emulator or a physical device.

## HOW TO USE APP

When you open the app, you will be taken to the main screen, which displays a list of all the movies currently in the database. To add a new movie, click on the floating action button in the bottom right corner of the screen. This will take you to the Add Movie screen, where you can enter the movie's details, including the title, release date, director, description, and image.

<img width="326" alt="Ekran Resmi 2023-03-25 17 53 09" src="https://user-images.githubusercontent.com/61374255/227724948-71880b35-d44b-4c30-ba1e-6acddb155152.png">

To edit an existing movie, simply click on the movie in the list that you want to edit. This will take you to the Edit Movie screen, where you can modify the movie's details and save the changes.

<img width="330" alt="Ekran Resmi 2023-03-25 17 53 17" src="https://user-images.githubusercontent.com/61374255/227724954-f9b27177-a2e3-4c95-a716-163142efdbd6.png">

<img width="334" alt="Ekran Resmi 2023-03-25 17 53 30" src="https://user-images.githubusercontent.com/61374255/227724959-e5cb1ff6-89a0-42a2-931d-4e2c623ff769.png">

To delete a movie, simply swipe left on the movie in the list and click the Delete button.

To search for a movie by name, enter the movie's title in the search bar at the top of the screen. The app will filter the movie list based on your search query.

To sort the movie list by release date, click on the Sort button in the top right corner of the screen. This will sort the movies by release date in ascending order. Click the Sort button again to reverse the sort order and sort the movies by release date in descending order.

## Architecture and Libraries Used

The app is built using the MVVM architecture pattern, which separates the business logic and presentation layers of the app. Data binding is used to bind the UI elements to the data in the ViewModel, reducing boilerplate code and making the code easier to read and maintain. The ROOM database library is used to persist the movie data to a local database on the device.

Other libraries used in the app include:
* LiveData: for observing changes in the data and updating the UI accordingly


