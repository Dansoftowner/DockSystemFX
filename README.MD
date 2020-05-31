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

#### 2. Using with Maven, Gradle etc...

<b>Maven example:</b><br>

Add JitPack.io to your repositories :
```xml
<repositories>
   <repository>
     <id>jitpack.io</id>
     <url>https://jitpack.io</url>
   </repository>
</repositories>
```
Add the dependency:
```xml
<dependency>
    <groupId>com.github.Dansoftowner</groupId>
    <artifactId>DockSystemFX</artifactId>
    <version>v1.0</version>
</dependency>
```

<b>Gradle example</b>

Add the repository:
```groovy
repositories {
    //...
    maven { url 'https://jitpack.io' }
}
```

Add the dependency:
```groovy
dependencies {
    //...
    implementation 'com.github.Dansoftowner:DockSystemFX:v1.0'
}
```

## Documentation resources

* [The structure](documentation/structure.md)
* [How to use it in practice](documentation/usage.md)