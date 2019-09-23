// vim: ts=4:sw=4
/**
 * Wake on LAN
 *
 * Copyright 2019 Ross Tyler
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations under the License.
 *
**/
definition(
	name			: 'Wake on LAN',
	namespace		: 'rtyle',
	author			: 'Ross Tyler',
	description		: 'When triggered by an associated switch turning on, turn on another device by targeting it with a Wake on LAN (WoL) magic packet.',
	category		: 'Convenience',
	singleInstance	: false,
	iconUrl			: "https://raw.githubusercontent.com/rtyle/wake-on-lan/master/resources/icons/app.png",
	iconX2Url		: "https://raw.githubusercontent.com/rtyle/wake-on-lan/master/resources/icons/app@2x.png",
	iconX3Url		: "https://raw.githubusercontent.com/rtyle/wake-on-lan/master/resources/icons/app@3x.png",
)

preferences {
	section("Trigger") {
		input "triggerSwitch", "capability.switch", required: true, title: "Switch"
	}
	section("Target") {
		input "targetMacAddress", "text", required: true, title: "Target MAC Address, in hex, without delimiters"
	}
}

def triggerSwitchOnHandler(event) {
	log.debug "WoL"
	sendHubCommand(
		new physicalgraph.device.HubAction(
			"wake on lan $targetMacAddress",
			physicalgraph.device.Protocol.LAN,
			null
		)
	)
}

def initialize() {
	subscribe(triggerSwitch, "switch.on", triggerSwitchOnHandler)
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}
