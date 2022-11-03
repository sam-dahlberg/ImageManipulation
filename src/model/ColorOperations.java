package model;


/**
 * Abstract Class representing all color operations.
 */
public abstract class ColorOperations implements Operations<Pixel> {

  /**
   * Represents the math need to apply a Color kernel to an image.
   *
   * @param image  a 2D array of pixels that represent an image
   * @param kernel a Matrix that is applied to pixels to perform the specific operation
   * @return a 2D array of pixels that represent an image.
   */
  protected Pixel[][] math(Pixel[][] image, double[][] kernel) {
    Pixel[][] newImage = new Pixel[image.length][image[1].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        newImage[i][j] = matrixMultiply(image[i][j], kernel);
      }
    }
    return newImage;
  }

  /**
   * Helper method for the math method. Applies the kernel to each provided pixel.
   *
   * @param pixel  Provided pixel to apply the kernel
   * @param kernel Provided kernel to apply to the pixel
   * @return Returns the pixel after kernel has been applied to it
   */
  private Pixel matrixMultiply(Pixel pixel, double[][] kernel) {
    int newRed;
    int newGreen;
    int newBlue;

    newRed = (int) ((kernel[0][0] * pixel.getRed()) + (kernel[0][1] * pixel.getGreen()) + (
        kernel[0][2] * pixel.getBlue()));
    if (newRed > pixel.getMaxValue()) {
      newRed = 255;
    }
    if (newRed < 0) {
      newRed = 0;
    }
    newGreen = (int) ((kernel[1][0] * pixel.getRed()) + (kernel[1][1] * pixel.getGreen()) + (
        kernel[1][2] * pixel.getBlue()));
    if (newGreen > pixel.getMaxValue()) {
      newGreen = 255;
    }
    if (newGreen < 0) {
      newGreen = 0;
    }
    newBlue = (int) ((kernel[2][0] * pixel.getRed()) + (kernel[2][1] * pixel.getGreen()) + (
        kernel[2][2] * pixel.getBlue()));
    if (newBlue > pixel.getMaxValue()) {
      newBlue = 255;
    }
    if (newBlue < 0) {
      newBlue = 0;
    }

    return new Pixel(newRed, newGreen, newBlue, pixel.getMaxValue());
  }


}
