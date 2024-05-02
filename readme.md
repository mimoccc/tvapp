# Android TV test app

[![Build & Publish Release APK](https://github.com/mimoccc/tvapp/actions/workflows/build-gradle-project.yml/badge.svg)](https://github.com/mimoccc/tvapp/actions/workflows/build-gradle-project.yml)
[![GitHub all releases](https://img.shields.io/github/downloads/mimoccc/tvapp/total)](https://github.com/mimoccc/tvapp/releases)

## Download
[<img src="./design/pictures/android-app-button.png" title="Apk Download" width="215"/>](https://github.com/mimoccc/tvapp/releases/download/release-1.2.9/app-release.apk)

## Stats

[![Last commit](https://img.shields.io/github/last-commit/mimoccc/tvapp?color=FFC877)](#)
[![Lang](https://img.shields.io/github/languages/top/mimoccc/tvapp?color=FFC877)](#)
[![Platform](https://img.shields.io/badge/Platform-Android-FFC877.svg)](#)
[![Min sdk](https://img.shields.io/badge/minSdkVersion-21-FFC877.svg)](#)
[![Languages](https://img.shields.io/github/languages/count/mimoccc/tvapp?color=FFC877)](#)
[![Issues](https://img.shields.io/github/issues-raw/mimoccc/tvapp?color=FFC877)](#)
[![PR's](https://img.shields.io/badge/PRs-welcome-FFC877.svg)](#)
[![Code size](https://img.shields.io/github/languages/code-size/mimoccc/tvapp?color=FFC877)](#)
[![Total lines](https://img.shields.io/tokei/lines/github/mimoccc/tvapp?color=FFC877)](#)
[![Dependencies](https://img.shields.io/librariesio/github/mimoccc/tvapp?color=FFC877)](#)

## Table of Contents

- [Documentation](#project-documentation)
- [Screenshots](#screenshots)
- [Description](#description)
- [Compilation](#compilation)
- [Usage](#usage)
- [Credits](#credits)
- [License](#license)

## Project documentation

- [App code documentation](documentation/app/index.md)
- [Lib code documentation](documentation/lib/index.md)

## Screenshots

![](screenshots/Screenshot_20230718_020504.png)
![](screenshots/Screenshot_20230718_020440.png)
![](screenshots/Screenshot_20230718_020340.png)

## Description

All in one, tablet, mobile and tv android app.

App is divided to two modules, tvlib which is main tv library for easier tv app developement 
and a demo app in app module.

Library and demo app is still under development.

Just an test app as showcase of coding skills, using modern frameworks  
and a new unstable tv compose framework for tv apps with the diferrence  
that components in this example should be touchable and therefore this  
example can be used on any device include tv, tablet or any mobile phone.

Components should be responsive to display size.

Technologies that will be used:

- Compose
- Dagger/Hilt
- ViewModels MVVM pattern
- Glide
- Kotlin
- LifeCycle
- Solve problems with compose previews
- Coroutines
- Flow
- Navigation
- Retrofit
- Json serialization (Moshi)
- Gradle scripting / configuration
- MarkDown editing
- Github actions
- Shell scripting
- Yaml scripting
- CI/CD
- SyncAdapter
- Automatic documentation
- Automatic release
- Toml dependencies
- Gradle plugins
- Screenshots auto creation (coming soon)
- AI test auto creation (coming soon)

## Compilation

Use Android studio or use command:

- debug app version :
  ./gradlew assembleDebug
- release app version :
  ./gradlew assembleRelease

## Usage

- Usable on any android device > SDK V 21 - Lollipop
- Use as you want. Freeware

## Credits

Milan Jurkulak

## License

No licenced.