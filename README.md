# MQTT Example Project

A Java-based MQTT (Message Queuing Telemetry Transport) example demonstrating publish-subscribe messaging patterns.

## Overview

This project contains three main components:
- **Publisher** (`MqttPublishSample`) - Sends messages to MQTT topics
- **Subscriber** (`MqttSubscriberSample`) - Receives messages from MQTT topics  
- **Message Handler** (`SampleSubscriber`) - Processes incoming messages

## Prerequisites

- Java 7 or higher
- Maven 3.x
- MQTT Broker (e.g., Mosquitto) running on `localhost:1883`

## Quick Start

1. **Start MQTT Broker** (e.g., Mosquitto):
   ```bash
   mosquitto -p 1883
   ```

2. **Build the project**:
   ```bash
   mvn clean compile
   ```

3. **Run the subscriber** (in one terminal):
   ```bash
   mvn exec:java -Dexec.mainClass="com.learn.mqttexample.MqttSubscriberSample"
   ```

4. **Run the publisher** (in another terminal):
   ```bash
   mvn exec:java -Dexec.mainClass="com.learn.mqttexample.MqttPublishSample"
   ```

## Topics

The example uses these MQTT topics:
- `/house/power` - Power system status
- `/house/water` - Water system status  
- `/house` - General house information
- `/garden/water` - Garden water system

## Dependencies

- Eclipse Paho MQTT v3 Client Library (1.2.0)

## Project Structure

```
src/main/java/com/learn/mqttexample/
├── MqttPublishSample.java    # Publisher implementation
├── MqttSubscriberSample.java # Subscriber implementation
└── SampleSubscriber.java     # Message callback handler
```
