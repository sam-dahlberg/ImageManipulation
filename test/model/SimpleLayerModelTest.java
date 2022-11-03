package model;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.junit.Test;


/**
 * Test for SimpleImageProcessingModel.
 */
/*

public class SimpleLayerModelTest {

  private SimpleLayerModel model = new SimpleLayerModel(new SimpleImageProcessingModel());
  private int maxValue = 255;
  private List<Pixel> data = new ArrayList<Pixel>(Arrays.asList(
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue),
      new Pixel(100, 100, 100, maxValue)
  ));
  private Image image = new Image(data, 5, 5, maxValue);

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    SimpleLayerModel nullModel = new SimpleLayerModel(null);
  }

  @Test
  public void testCreateLayer() {
    model.createLayer("First");
    assertEquals(1, model.getAllLayer().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCreateLayer() {
    model.createLayer("");
  }

  @Test
  public void testCreateAndAdd() {
    model.createAndAdd(image, "First");
    assertEquals(1, model.getAllLayer().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCreateAndAdd() {
    model.createAndAdd(image, "");
  }

  @Test
  public void testChangedCurrentLayer() {
    model.createLayer("First");
    model.changeCurrentLayer("First");
    List<String> oldOrder = model.getOrder();

    model.createLayer("Second");
    model.changeCurrentLayer("Second");
    List<String> newOrder = model.getOrder();

    assertNotEquals(oldOrder, newOrder);
  }

  @Test
  public void testVisibility() {
    model.createAndAdd(new Image(image.getData(), image.getHeight(), image.getWidth(),
        image.getMaxRGB()), "First");
    model.changeVisibility("First");

    assertEquals(false, model.getLayer("First").getVisible());
  }
}

 */
