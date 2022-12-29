/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package testfeatures;

import one.empty3.feature.jviolajones.Detector;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectorTest {

    @Test
    public void testDetector() throws IOException {
        File img = new File("src/test/resources/lena.jpg");
        InputStream haarXml = Detector.class.getResourceAsStream("/haarcascade_frontalface_alt2.xml");

        Detector detector = new Detector(haarXml);
        List<Rectangle> res = detector.getFaces(img.getAbsolutePath(), 1, 1.25f, 0.1f, 1, true);
        Rectangle first = res.get(0);
        Rectangle second = res.get(1);

        assertEquals(2, res.size());

        assertEquals(46.0, first.getWidth(), 0.01);
        assertEquals(46.0, first.getHeight(), 0.01);
        assertEquals(284.0, first.getX(), 0.01);
        assertEquals(336.0, first.getY(), 0.01);

        assertEquals(161.0, second.getWidth(), 0.01);
        assertEquals(161.0, second.getHeight(), 0.01);
        assertEquals(237.0, second.getX(), 0.01);
        assertEquals(214.0, second.getY(), 0.01);

    }
}
