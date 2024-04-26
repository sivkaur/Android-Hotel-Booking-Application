# Android Hotel Reservation System

## Project Overview
This Android application is a hotel reservation system that allows users to book hotels online. The application features four user interface screens and utilizes two API service calls.

## Features
- **Date and Guest Input**: Users can input check-in and check-out dates along with the number of guests.
- **Hotel Selection**: A list of hotels is displayed based on the input dates and guest count. Users can select a hotel and proceed to reservation details.
- **Guest Details Entry**: For the selected hotel, users enter details for each guest and finalize the booking.
- **Booking Confirmation**: The system confirms the reservation with a unique number displayed to the user.

## Technical Components
- **Constraint Layouts**: Utilized for designing UI components.
- **RecyclerView**: Implemented to list hotels and manage guest details dynamically.
- **Retrofit**: Used for API service calls.
- **Intents and Fragments**: Manage data passing between activities and fragments.
- **Shared Preferences**: Store user inputs like name and number of guests.

## Screens
1. **Home Screen**: Date and guest input.
2. **Hotel List Screen**: Displays hotels fetched via API.
3. **Guest Detail Screen**: Input details for all guests.
4. **Confirmation Screen**: Shows the reservation number and a thank-you message.

## Setup and Installation
Clone this repository, then open the project in Android Studio:
