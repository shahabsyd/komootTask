# Web App Automation

## Overview
This repository contains scripts which perform automation testing on web application - Komoot.

## Technology
For scripting, following technology and framework has been used:
* JAVA  (_Scripting language_)
* TestNG
* JUnit framework
* Maven  (_Build automation tool for JAVA_)


## Instruction to execute test
The instruction will explain how to execute test on MAC OS X or linux platform.

### Pre-requirement
>
> * JDK 13 (preferred version )
> * Maven
> * Selenide
> * Homebrew (for MAC OS X or linux)
> * Latest version of chrome and firefox browser
>
> #### Required installation
>
> * Install chromedriver for running the test on chrome browser
> ```sh
> brew install chromedriver
> ```
> * Install geckodriver for running the test on firefox browser
> ```sh
> brew install geckodriver
> ```

### Time to execute test
>
> #### To run the test
>
> * Clone the project
> * Go to project directory.
> * Then run one of the following command according to your required browser:
>```
>mvn clean test -Dbrowser=chrome -Durl=https://www.komoot.com/        // to run test on chrome browser
>mvn clean test -Dbrowser=firefox -Durl=https://www.komoot.com/      // to run test on firefox browser
>```

### Where can I see report

Once you run the test successfully, go to project directory and open directory named "target". You will find report.html



