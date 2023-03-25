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

package net.coobird.gui.capturenator.impl.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public final class MouseDragDetector {
    private boolean isDragged = false;
    private MouseEvent pressEvent;
    private MouseEvent releaseEvent;
    private final List<MouseDragListener> listeners = new ArrayList<>();

    public void addListener(MouseDragListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        listeners.forEach(listener -> listener.mouseDragCompleted(pressEvent, releaseEvent));
        pressEvent = null;
        releaseEvent = null;
    }

    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            isDragged = false;
            pressEvent = e;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            releaseEvent = e;
            if (isDragged) {
                notifyListeners();
            }
        }
    };

    private final MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            isDragged = true;
            listeners.forEach(listener -> listener.mouseDragProgress(pressEvent, e));
        }
    };

    public MouseListener getMouseListener() {
        return mouseAdapter;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionAdapter;
    }
}