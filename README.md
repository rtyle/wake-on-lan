# wake-on-lan
Use any SmartThings switch (including a virtual one) to wake up a device on the LAN of your SmartThings hub.

### 2019 Samsung QLED Q90R TVs</h3>

2019 Samsung QLED Q90R TVs cannot be turned on through their SmartThings integration.
This is probably true of all 2019 and earlier Samsung TVs.

While the TV thing in the SmartThings App is presented with an on/off switch, turning on the TV here will not work unless it was very recently turned off.
When Google Home is asked to turn on the TV (through its SmartThings integration) it complains: "It looks like the TV isn't available right now".

A solution is to deploy an instance of this SmartApp.
The instance will need to be configured with a switch to trigger it and the target MAC address of the TV on your SmartThings hub's LAN.
When the trigger switch is turned on, the SmartApp will ask your SmartThings hub to use [Wake-on-LAN](https://en.wikipedia.org/wiki/Wake-on-LAN) to turn on the TV.

For lack of a smart physical switch to use as a trigger, one can use a virtual switch.

In Google Home, one can import the trigger switch and the TV from SmartThings.
One should give them names so that "Turn on NAME" and "Turn off NAME" voice commands get resolved to both.
For example, naming them both "TV" seems to work.
Turning on the TV will still result in the "It looks like the TV isn't available right now" response from the SmartThings TV thing but the SmartThings trigger switch and its **Wake on LAN** SmartApp will still work (the TV will be turned on).

After turning on the TV this way, both TV things in SmartThings will report as being on. However, if the TV is turned on another way or if the TV is turned off in any way, the TV trigger switch thing may not report the correct state. If this bothers you, you can use the SmartThings Smart Lighting SmartApp to automatically have the trigger switch mirror the TV. This will result in an unneeded (but harmless) Wake-on-LAN request if the TV is turned on without using the trigger switch.
