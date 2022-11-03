package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a PPM image format version of the ImageProcessingModel.
 */
public class SimpleImageProcessingModel implements ImageProcessingModel<Pixel> {

  /**
   * Empty constructor to initialize object.
   */
  public SimpleImageProcessingModel() {
    //Constructor needed for controller. Nothing needs to be initialized.
  }

  @Override
  public Image transform(Image image, Operations<Pixel> operation) throws IllegalArgumentException {
    if (operation == null) {
      throw new IllegalArgumentException("Null Operation");
    }
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }

    Pixel[][] array2D = imageTo2DArray(image);
    Pixel[][] transformedArray = operation.apply(array2D);

    return new Image(convertToImage(transformedArray), image.getHeight(), image.getWidth(),
        image.getMaxRGB());
  }

  private List<Pixel> convertToImage(Pixel[][] image) {
    List<Pixel> returnImage = new ArrayList<Pixel>();

    for (Pixel[] pixels : image) {
      for (Pixel pixel : pixels) {
        returnImage.add(pixel);
      }
    }
    return returnImage;
  }

  private Pixel[][] imageTo2DArray(Image image) {
    List<Pixel> data = new ArrayList<Pixel>(image.getData());
    int height = image.getHeight();
    int width = image.getWidth();

    Pixel[][] array2D = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        array2D[i][j] = data.get(0);
        data.remove(0);
      }
    }
    return array2D;
  }
}
