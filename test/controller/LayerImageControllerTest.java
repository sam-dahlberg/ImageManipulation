package controller;


//import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import model.SimpleImageProcessingModel;
import model.SimpleLayerModel;
//import org.junit.Test;

/**
 * Tests for LayerImageController.
 */
/*
public class LayerImageControllerTest {

  private LayerImageController controller;

  @Test
  public void testSimpleCommands() throws IOException {
    SimpleLayerModel model = new SimpleLayerModel(new SimpleImageProcessingModel());
    InputStream in = new ByteArrayInputStream("create-layer first\nload derpCat.jpeg".getBytes());
    controller = new LayerImageController(model, new InputStreamReader(in));
    controller.start();

    assertEquals(1, model.getAllLayer().size());
  }

  @Test
  public void testAllCommands() throws IOException {
    SimpleLayerModel model = new SimpleLayerModel(new SimpleImageProcessingModel());
    InputStream in = new ByteArrayInputStream(
        ("create-layer first\nload derpCat.jpeg\nblur\nsepia\ncreate-layer second\n"
            + "load rainbowPNG.png\nsharpen\ngreyscale\nsave-all test.jpeg")
            .getBytes());
    controller = new LayerImageController(model, new InputStreamReader(in));
    controller.start();

    assertEquals(2, model.getAllLayer().size());
  }

}

 */

