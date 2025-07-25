# PvP Toggle
PvP Toggle is a plugin is built for Beta 1.7.3 Minecraft that allows players to toggle pvp.

[![GitHub Release](https://img.shields.io/github/v/release/Garsooon/PVPToggle?label=release)](https://github.com/Garsooon/PVPToggle/releases/latest)  
[![Downloads](https://img.shields.io/github/downloads/Garsooon/PVPToggle/total.svg?style=flat)](https://github.com/Garsooon/PVPToggle/releases)  
[![GitHub Stars](https://img.shields.io/github/stars/Garsooon/PVPToggle?style=social)](https://github.com/Garsooon/PVPToggle/stargazers)

---

## Features

- Toggleable pvp
- Configurable "Anarachy" worlds
- Configurable default pvp state on log in

---

## Installation

1. Download `PVPToggle.jar` from the [releases](https://github.com/Garsooon/PVPToggle/releases).
2. Drop it into your `plugins` folder.
3. Make sure [Poseidon](https://github.com/retromcorg/Project-Poseidon) is installed. (Other forks of CB1060 untested)
4. Restart your server.

---

## Usage

- **Toggle PvP**:  
  `/pvptoggle`  or `/pvp` will toggle your pvp status
 
---

## Configuration

The config file (`plugins/PVPToggle/config.yml`) lets you adjust:

- Default PVP status on log in 
- Reset PVP status to default on log in  
- Always PVP on worlds list

---

## Building

You can build the plugin using Maven:
`mvn clean install`
