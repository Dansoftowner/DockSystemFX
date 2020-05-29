# ![DockSystemFX](documentation/images/logo.png)

<hr>
<b align="center">Create advanced GUIs easily with this library!</b>
<hr>

## Some screenshots

<i>Light style + <a href="https://github.com/JFXtras/jfxtras-styles/tree/master/src/jmetro">JMetro</a>'s light style</i>
![DockSystemFX w dark style](documentation/images/light-example.jpg)

<i>Dark Style + <a href="https://github.com/JFXtras/jfxtras-styles/tree/master/src/jmetro">JMetro</a>'s dark style</i>
![DockSystemFX w dark style](documentation/images/dark-example.jpg)

## Features
* Supports <b>8</b> dock-positions:
    * `TOP>LEFT`
    * `TOP>RIGHT`
    * `LEFT>TOP`
    * `LEFT>BOTTOM`
    * `RIGHT>TOP`
    * `RIGHT>BOTTOM`
    * `BOTTOM>LEFT`
    * `BOTTOM>RIGHT`

* Supports <b>3</b> view modes:
    * `PINNED` - the dock-panel is docked normally inside the dock-system
    * `FLOAT` - the dock-panel is displayed inside a floating window
    * `WINDOW` - the dock-panel is displayed inside a normal window

* Supports internationalization
* Supports the javaFX <b>CSS</b> based styling

## How to include it into your project

#### 1. Manually
You can download a jar file from the releases. Every release has two jar files: one is a single jar without
the dependencies and the other one is a fat jar that includes all dependencies. 

#### 2. Using with Maven, Gradle etc ...

<b>Maven example:</b><br>

```xml
<repositories>
   <repository>
     <id>jitpack.io</id>
     <url>https://jitpack.io</url>
   </repository>
</repositories>
```
```xml
<dependency>
  <groupId>com.github.goxr3plus</groupId>
  <artifactId>FX-BorderlessScene</artifactId>
  <version>4.4.0</version>
</dependency>
```


## Quick example

