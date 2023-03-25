/*
 * Capturenator - a screenshot library
 *
 * Copyright (c) 2022-2023 Chris Kroells
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.coobird.gui.capturenator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * Main class for taking screenshots.
 */
public final class Capture {
    private final Robot robot;

    public Capture() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Could not instantiate Capturenator.", e);
        }
    }

    // Get full-screen bounds spanning multiple monitors
    private static Rectangle getBounds() {
        Rectangle bounds = new Rectangle();
        for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            bounds.add(gd.getDefaultConfiguration().getBounds());
        }
        return bounds;
    }

    /**
     * Take a screen capture of the entire screen.
     * @return full screen capture.
     */
    public BufferedImage fullScreen() {
        return robot.createScreenCapture(getBounds());
    }

    /**
     * Take a screenshot of a region of the screen.
     * @param r The region of the screen to take a screenshot of.
     * @return A screenshot.
     */
    public BufferedImage of(Rectangle r) {
        return robot.createScreenCapture(r);
    }

    /**
     * Take screenshot of given dimensions, centered at the mouse pointer.
     *
     * The screenshot will be un-centered if the screenshot will go beyond the
     * visible dimensions of the screen.
     *
     * @param d Dimensions of the screenshot.
     * @return A screenshot, centered around the mouse pointer.
     */
    public BufferedImage atPointer(Dimension d) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        int x = p.x - Math.round(d.width / 2.0f);
        int y = p.y - Math.round(d.height / 2.0f);

        Rectangle bounds = getBounds();
        int maxX = bounds.width - d.width;
        int maxY = bounds.height - d.height;
        x = Math.min(Math.max(0, x), maxX);
        y = Math.min(Math.max(0, y), maxY);

        Rectangle r = new Rectangle(x, y, d.width, d.height);
        return robot.createScreenCapture(r);
    }
}
