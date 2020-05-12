
## 3rd Year Project: Innovative Cinema Listings

### Technical Manual



##### _Authors:_

##### Shaun Carey _16450454_

##### Nigel Guven _14493422_

##### _Supervisor:_

##### Charles Daly

##### **Introduction**

**Overview**

The project is developed around a mobile application developed solely for Android operating systems. The application displays cinema listings in a user-friendly guided format. These cinemas are in Ireland and Northern Ireland only. Users are told where the nearest cinema is and what time the next film will start at and if the user will be late. Requirements necessary for a smooth performance are the enabling of Wi-Fi and user location from within their Android mobile settings menu. The system will prevent the user from progressing further if neither of these requirements are met, both of which are mandatory. The system involves scraping of HTML web data and using the Java library JSoup to parse it into a readable string which can then be manipulated by the system or displayed to the user and solid information.

There is a link to the Google Maps API wherein the user&#39;s location is displayed and with their nearest local cinemas displayed as accessible map markers. The web scraping scripts are initialized at runtime so the user can be presented with the latest cinema-listings information. Picasso is another library for Android which is built in to the application to download film images from a URL and cache them into the system database. The web scripts are initialized when the user clicks a button but are ran asynchronously to prevent a clogging of memory or &#39;lagging&#39; on the UI thread. The application does not coordinate with other systems although it does require an active connection to the websites that it is scraping from.

**Glossary**

**JSoup** : A Java library purpose-built for interacting with HTML. Operates by making a connection to a webpage and grabbing the HTML within which can be extracted.

**Asynchronous** : Asynchronous execution is performed when other tasks are finished processing. Tasks that are asynchronous operate at different times to regular computation

**Picasso** : Picasso is an Android library that is used to download and display images with no loss of data and with full resolution of the picture.

**HTML** : Hypertext Markup Language or HTML is a prime languages for creating web pages and web applications. It defines the text and order but not the structure or interaction of a document

**API** : An Application Programming Interface or API allows the creation of applications that access the features or data of an operating system and application.

**SDK:** A Software Development Kit or SDK is a tool that allows for the creation of applications for certain software package, platform or computer system.

**Gradle:** Gradle is a build-automation system which configures Android applications at runtime by downloading necessary libraries and setting the API level.

**ADB:** Android Debug Bridge is a tool used to read Android mobile data from a computer which is not necessarily able to utilise Android apps. Essentially a comms platform between a computer and an android device.

**IDE:** Integrated Development Environments are software applications with comprehensive facilities for allowing its users to develop other pieces of software.

**Android Code Version:** The value represents the version of the applications code.

**Android Activity Lifecycle:** Android operates by an activity lifecycle. Transitions between different interfaces and themselves are controlled by methods like onCreate, onStop, and OnResume.

**Android Manifest:** The Android Manifest is an XML file which contains all the activities in an Android app as well as setting the permissions which the Android app has access to.

**Listview:** Listview is an XML type which contains data and displays it in a list

**Scrollview:** Scrollview is an XML type which allows for dynamic scrolling of an activity where not all data might fit into a screen.

**Kotlin:** Kotlin is a programming language that is primarily used for developing Android apps. It can be interoperated alongside Java in any application.

**ChaquoPy:** ChaquoPy is a library which enables a user to manipulate Python on Android Studio where the IDE doesn&#39;t support Python. It allows for cross-compatibility with Java and Kotlin in the IDE.

**ArrayList:** Arraylists are data types which are lists of data. Unlike arrays which are simple, Arraylists can be expanded dynamically.

**LogCat:** LogCat is the internal debugging terminal which retrieves all data from an Android device and displays its information inside of Android Studio.

**onCreate:** onCreate is the initialization function of an activity. It calls an XML layout file and runs a set of functions which display on the UI Thread.



### **Installation Guide**

**System Specifications**

_Developed on HUAWEI PRA-LX1 and LG-X Venture Android phones_

**Platform Support:** Android Mobile devices only

**Android OS Minimum API/SDK Version:** API14/Android 4.0 (Ice Cream Sandwich)

**Android OS Maximum API/SDK Version:** API27/Android 8.1 (Oreo)

_Runnable on 100% of Android devices._

**Android Code Version:** 1.0

**External Requirements:**

**       ** Google Maps API Key

Google Play Services v16.0.0

Google Maps Services v16.1.0

Square Picasso v2.5.2

JSoup v1.11.3 :_download via_ https://jsoup.org/

Android Gradle v3.3.1

**Installation Instructions**

Prerequisites:
  Android Studio is installed on the user&#39;s device.
  ADB is installed on the users device.

Download the Project Repository from its repository located on Gitlab at the link: [https://gitlab.computing.dcu.ie/careys26/2019-ca326-innovative-cinema-listings](https://gitlab.computing.dcu.ie/careys26/2019-ca326-innovative-cinema-listings).

Find the downloaded project on the users file system (_by default in the Downloads folder__on Windows, Mac or Linux operating systems_).

Open the application in the Android Studio IDE. This can be found by looking for the Android Studio icon in the applications folder. This icon called app is the root of the entire application.

Wait for the program to build and execute its tasks.

A Run option should become available at the top.

Click the Run button. In the case where the user has not downloaded ADB, an alternative option to download a virtual device is available.

The application will appear on the selected device either through the ADB or the virtual device.

Google will ask the user to sign in to an email (_This is necessary so that Google can get the users location for the app to function correctly_).

Once logging in the app will display on the home screen as a shortcut.

Click on the application icon to start it.

See the User Manual for further information about how to use the application.

### **Problems and Resolutions**

The following sections are dedicated to some of the major bugs that arose in the application and how we managed to solve them.

 **BeautifulSoup on Android Studio**

Our original plan was to use BeautifulSoup, a Python library for scraping web data. Once we had the script written we had to examine how we would incorporate it into Android Studio. Some helper libraries like ChaquoPy would enable us to use Python on Android Studio which is primarily a Java/Kotlin based IDE. When we tried to incorporate BeautifulSoup, the application refused to accept it and ChaquoPy itself was causing problems.

**Resolution:** We decided to rewrite the script to scrape web data in Java. JSoup is the alternative to BeautifulSoup and we installed this as a library in the Android application. It worked much better albeit with a bigger hit to performance.

**Film Data displaying twice in Cinema List View**

The Film Data had been placed in a listview in the cinema activity. Each cinema has a certain amount of films on show. Films would display a small amount of information in the listview. However, we ran into a problem whereby when a cinema activity was opened, it displayed its films twice on the list.

**Resolution:** We realised that if a user clicked into a cinema then into another cinema, the arraylist of film data would append more information onto it without clearing information from the previous cinema. We created a check method to examine if an arraylist already contained data and if so to remove this data before adding another cinema&#39;s data.

 **Web Scraper crashing when run on UIThread**

We placed the web scraper in the beginning to the onCreate function of the activity it was in. The onCreate activity would run the script and its regular data. Everytime the activity was activated, it would crash with a fatal error in LogCat.

**Resolution:** We realised that a massive web scraper would not work in the onCreate method. We created a thread to run asynchronously in between each activity. When a user clicked button, it would fire the script into action. This solved the problem of the fatal crash and hanging on the UI thread.

**ListView not working within a Scrollview**

In the Film Activity, there contains all the film data which is inside of an XML Scrollview. The scrollview is also expandable. We wanted to display the film times inside of the scrollview in an intuitive way. Our original design was a listview with a film time and a highlighted object to display the nearest time to the users current time.

Upon entering the film activity interface, the listview was not visible and would not expand inside the scrollview. The XML was causing a problem. One solution was to make the listview a separate object but this did not make it intuitive for a user to view.

**Resolution:** We decided to create a function which displayed rows and columns of film data. The film times were made to appear orderly. This was successful in that it appeared in a correct format while also appearing dynamically on the interface.
