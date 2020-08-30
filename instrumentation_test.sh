#!/bin/bash

#create avd
emulator_name="test_device"
emulator_path="/home/rahulkumarlohra-xps/AndroidStudioProjects/GqlDeveloperApp/avds/$emulator_name"
AVD_MANAGER="$ANDROID_HOME/tools/bin/avdmanager"
echo "no" | $AVD_MANAGER --verbose create avd --force --name $emulator_name --device "pixel" --package "system-images;android-29;google_apis;x86" --tag "google_apis" --abi "x86" -p $emulator_path

#Start the emulator
$ANDROID_HOME/emulator/emulator -avd $emulator_name -netdelay none -netspeed full -wipe-data & EMULATOR_PID=$!

# Wait for Android to finish booting
WAIT_CMD="$ANDROID_HOME/platform-tools/adb wait-for-device shell getprop init.svc.bootanim"
until $WAIT_CMD | grep -m 1 stopped; do
  echo "Waiting..."
  sleep 1
done

# Unlock the Lock Screen
$ANDROID_HOME/platform-tools/adb shell input keyevent 82

# Clear and capture logcat
$ANDROID_HOME/platform-tools/adb logcat -pid=$EMULATOR_PID -c
$ANDROID_HOME/platform-tools/adb logcat -pid=$EMULATOR_PID > build/logcat.log &
LOGCAT_PID=$!

# Run the tests
./gradlew connectedAndroidTest -i

# Stop the background processes
kill $LOGCAT_PID
kill $EMULATOR_PID