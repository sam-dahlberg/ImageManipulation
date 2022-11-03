package model;

//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.junit.Test;

/**
 * Test for SimpleImageProcessingModel.
 */
/*
public class SimpleImageProcessingModelTest {

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

  @Test
  public void testCorrectOperation() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();

    List<Pixel> newData = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue),
        new Pixel(25, 25, 25, maxValue)
    ));
    Image newImage = new Image(newData, 5, 5, maxValue);

    for (int i = 0; i < image.getData().size(); i++) {
      assertEquals(image.getData().get(i).getRed() * .25, newImage.getData().get(i).getRed(), .0);
      assertEquals(image.getData().get(i).getGreen() * .25, newImage.getData().get(i).getGreen(),
          .0);
      assertEquals(image.getData().get(i).getBlue() * .25, newImage.getData().get(i).getBlue(), .0);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    Image nullImage = null;
    model.transform(nullImage, new TestFilter());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOperation() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.transform(image, null);
  }

}

 */
