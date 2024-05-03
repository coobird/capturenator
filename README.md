# What is Capturenator?

_Capturenator_ is a Java library for taking screenshots.

# Why use Capturenator?

_Capturenator_ offers a very simple API for taking screenshots:

```java
// Take a full-screen, screenshot.
BufferedImage screenshot = Capture.fullScreen();

// Take a screenshot of a region of the screen. 
BufferedImage screenShotOfARegion = Capture.of(new Rectangle(0, 0, 100, 100));

// Take a screenshot centered around the mouse pointer.
BufferedImage screenshotAtThePointer = Capture.atPointer(new Dimension(100, 100));
```

# How do I use Capturenator?

The `test/java` directory contains a couple of example programs that use _Capturenator_.
