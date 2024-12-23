# A Simple Classification Banner

This repo provides a classification banner script written in Java.

If all you want is the classification banner, then check out the [ClassificationBanner.java](./src/ClassificationBanner.java).

**Features:**
- Auto-hides every 30 seconds
- A SystemD service and example classifications for Linux
- A 15px tall rectangle at the top of your screen.


## Build

The build script provided will (once complete) create an RPM and ZST for installation.
You will be able to configure the default classification and colors in used in the service file as long as it matches the config.json.

***

## TODO Features:
- A method to upgrade/downgrade, or change classification
- A variable timeout parameter
- Transparency for a click-behind ability
- Displaying this on the login screen