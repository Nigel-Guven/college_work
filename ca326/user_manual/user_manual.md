## 3rd Year Project: Innovative Cinema Listings

## User Manual



_Authors:_

Shaun Carey _16450454_

Nigel Guven _14493422_

_Supervisor:_

Charles Daly

## **Introduction**


### Application Overview

Innovative Cinema Listings is an application that is meant to solve the problem of cinema data not being confined to a single location on the internet or in an application. This Android application combines all relevant film data with the ability of a user to easily discover cinema locations on a map within a certain distance of the user. It currently operates for 109 cinemas and theatres in the Republic of Ireland and Northern Ireland.

Users can see where a cinema is on the map and can see exactly what films are showing in that cinema on a certain date and also film information such as its IMDb rating, synopsis and all of its film times for the current day. The application is meant for Android systems and operates on a goal of ease-of-usage and user-friendliness.

### Glossary

Android: Android is a mobile operating system developed by Google. It is based on a modified version of the Linux kernel and other open source software and is designed primarily for touchscreen mobile devices such as smartphones and tablets. (Wikipedia)

GUI: Graphical User Interface a device for a user to interact with an application or system.

OS – Short for operating system

Scrollview – An XML view type which is dynamic and list items in a scrollable format.

Gitlab: GitLab is a web-based DevOps lifecycle tool that provides a Git-repository manager providing wiki, issue-tracking and CI/CD pipeline features, using an open-source license, developed by GitLab Inc. (Wikipedia)

## **Application Instructions**

### 1. Main Menu
Users will first be presented with a user interface that resembles a main menu. It contains the cinema icon and two buttons. The &#39;Help&#39; button links to an interface showing links to the various sections of the project development (See 2.5).

The &#39;Cinema Map&#39; button links to a Google Maps display showing the users location and all nearby cinemas. Note that users must have turned on location and Wi-fi permissions or else the application will refuse to show the cinema locations.

### 2. Maps Interface

#### Maps Functionality


The Google Maps interface loads up and centres on the user&#39;s location. The user can view nearby cinemas by panning in and out of the maps using the touchscreen or provided zoom controls in the bottom right. Above the Zoom Controls are two buttons, &#39;Show&#39; and &#39;Sat&#39;:


#### Map Markers

At the top right of the screen, one may view the location button. If this is clicked the application will home in the user&#39;s location.

Map Markers are location identifiers on the map. Markers identity the location of a cinema on the map. Each cinema is clickable and opens an info window containing its name of the cinema and a snippet of text containing the URL. There are two options for a user here. If the user hold clicks the info window, they are taken to the URL site in question which is an RTE cinema listings website. Tapping the window will bring the user to the Cinema interface with a list of the films on show in that cinema.

### 3. Cinema Interface


Upon tapping an info window, the user may be presented with a list of films that are currently attached to the cinema. A new UI opens containing the cinema&#39;s name as well as a scrollable list of films currently being shown at that certain cinema, along with some basic information regarding the film such as genre, rating, and duration. Each film in this list is clickable and will open a new interface with the full information of that exact film in a scroll view.

### 4. Film Interface

The Film Interface opens up when a film is clicked from the list of films in the cinema. The user can view in the beginning the name, release date, film times, genre and others. If the user scrolls down on the screen, they can view further information such as the director or the synopsis of the film.

The film times section of the film list has extra functionality. It will show the next available time from the users current time. If the latest film time is less than the current time, then it will tell the user that there are no more available times for that day and to try again a different day. Also if the user is less than 30 minutes from the film they want to see then it will tell not to be late and will also inform them of how many minutes are left till that film starts as can be seen above.

### 5. Help Interface

Tapping on the &#39;Help&#39; button on the main splash page will lead the user to an information page including clickable links to the developer&#39;s blog page, tutorial video, and Gitlab repository, as well as displaying the developer&#39;s email addresses in the case of any queries.
