/*
 * Capturenator - a screenshot library
 *
 * Copyright (c) 2022-2025 Chris Kroells
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

import net.coobird.gui.simpleimageviewer4j.Viewer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This program demonstrates the capability of Capturenator to take images
 * around the mouse pointer.
 * <p>
 * Once the program is run, try moving the mouse cursor around the desktop.
 * This program uses Capturenator to take a screenshots of a region around
 * the mouse cursor.
 * <p>
 * Upon capturing the images, a simple image viewer window (using Swing)
 * will show the images that were taken.
 */
public class ContinuousScreenshotsAroundMousePointer {
    // Demo to take a bunch of screenshots around the mouse pointer.
    public static void main(String[] args) throws InterruptedException {
        // Initial delay, just to get ready to move the mouse.
        Thread.sleep(1000);

        final Capture capture = new Capture();

        List<BufferedImage> imgs = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            imgs.add(capture.atPointer(new Dimension(200,200)));
            Thread.sleep(30);
        }

        new Viewer(imgs).run();
    }
}
