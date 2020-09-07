#! /usr/bin/env python
import sys
import os
import requests
import json
import time

files = ['fakeresponse/src/main/java/com/','module2/src/main/java/com/']

#check if test-app module is included

moduleList = []
for item in files:
    if '/src/' in item:
        moduleName = item.split('/src/')
        moduleList.append(moduleName[0])

moduleForTesting = []
for item in moduleList:
    with open(item+'/build.gradle', 'r') as r:
        for line in r:
            if("testapp" in line and not("\\" in line) and not("*" in line)):
                moduleForTesting.append(item)
                break

append_modules_command = ""
instrumentationText = ":connectedDebugAndroidTest"

for module_path in moduleForTesting:
    append_modules_command = module_path + instrumentationText +" "

# print(append_modules_command)

#check what all classes used annotation @UiAnalyticsTest without comments
strictModuleForTesting = []
for item in moduleList:
    PATH = item + '/src/androidTest'    
    for path, dirs, files in os.walk(PATH):
        for filename in files:
            with open(path+"/"+filename, 'r') as r: 
                for line in r:                                            
                    if("@UiAnalyticsTest" in line and not("\\" in line) and not("*" in line)):
                        strictModuleForTesting.append(item)                        
                        break


print("strictModuleForTesting found = ",len(strictModuleForTesting))

if(len(strictModuleForTesting)!=0):    
    print("Strict modules for testing:")
    strict_append_modules_command = ""
    for module_path in strictModuleForTesting:
        strict_append_modules_command = module_path + instrumentationText +" "
    
    print(strict_append_modules_command)
else:
    print("No classes found using @UiAnalyticsTest")