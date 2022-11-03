package model;

/**
 * Abstract Class representing all filtering operations.
 */

public abstract class FilteringOperations implements Operations<Pixel> {


  /**
   * Represents the math need to apply a filter kernel to an image.
   *
   * @param image  a 2D array of pixels that represent an image
   * @param kernel a Matrix that is applied to pixels to perform the specific operation
   * @return A 2d array of pixels that represents the image after kernel has been applied
   */
  protected Pixel[][] math(Pixel[][] image, double[][] kernel) {
    int redInt;
    int greenInt;
    int blueInt;
    int kernalSize = (kernel.length - 1) / 2;
    Pixel[][] newImage = new Pixel[image.length][image[1].length];

    for (int row = 0; row < image.length; row++) {    //The rows
      for (int column = 0; column < image[row].length; column++) {      //The columns of each row

        redInt = (int) (image[row][column].getRed() * kernel[kernalSize][kernalSize]);
        greenInt = (int) (image[row][column].getGreen() * kernel[kernalSize][kernalSize]);
        blueInt = (int) (image[row][column].getBlue() * kernel[kernalSize][kernalSize]);

        for (int rowLayerCounter = 1; rowLayerCounter <= kernalSize; rowLayerCounter++) {

          if (row
              >= rowLayerCounter) { //If the current pixel is low enough to add the above neighbors
            for (int columnLayerCounter = 1; columnLayerCounter <= kernalSize;
                columnLayerCounter++) { //For loop to look at each above neighbor

              if (column >= 0
                  + columnLayerCounter) {
                //if current pixel is right enough, then for loop for the top left corner neighbors

                redInt +=
                    image[row - rowLayerCounter][column - columnLayerCounter].getRed() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize - columnLayerCounter];
                greenInt +=
                    image[row - rowLayerCounter][column - columnLayerCounter].getGreen() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize - columnLayerCounter];
                blueInt +=
                    image[row - rowLayerCounter][column - columnLayerCounter].getBlue() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize - columnLayerCounter];
              }
              if (column < image[row].length
                  - columnLayerCounter) {
                //If current pixel is left enough, then for loop for the top right corner neighbors

                redInt +=
                    image[row - rowLayerCounter][column + columnLayerCounter].getRed() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize + columnLayerCounter];
                greenInt +=
                    image[row - rowLayerCounter][column + columnLayerCounter].getGreen() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize + columnLayerCounter];
                blueInt +=
                    image[row - rowLayerCounter][column + columnLayerCounter].getBlue() * kernel[
                        kernalSize - rowLayerCounter][
                        kernalSize + columnLayerCounter];
              }
            }

            //one for loop called "kernelcounter"
            //then the ifs in that take in kernelcounter.

            //Adding the middle section of neighbors in the above section
            redInt +=
                image[row - rowLayerCounter][column].getRed() * kernel[kernalSize
                    - rowLayerCounter][kernalSize];
            greenInt +=
                image[row - rowLayerCounter][column].getGreen() * kernel[kernalSize
                    - rowLayerCounter][kernalSize];
            blueInt +=
                image[row - rowLayerCounter][column].getBlue() * kernel[kernalSize
                    - rowLayerCounter][kernalSize];
          }

          if (row < image.length
              - rowLayerCounter) { //If the current pixel is high enough to add the below neighbors
            for (int columnLayerCounter = 1; columnLayerCounter <= kernalSize;
                columnLayerCounter++) { //For loop to look at each below neighbor
              if (column >= 0
                  + columnLayerCounter) {
                //if current pixel is right enough, then for loop the bottom left corner neighbors
                redInt +=
                    image[row + rowLayerCounter][column - columnLayerCounter].getRed() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize - columnLayerCounter];
                greenInt +=
                    image[row + rowLayerCounter][column - columnLayerCounter].getGreen() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize - columnLayerCounter];
                blueInt +=
                    image[row + rowLayerCounter][column - columnLayerCounter].getBlue() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize - columnLayerCounter];
              }
              if (column < image[row].length
                  - columnLayerCounter) {
                //If current pixel is left enough, then for loop the bottom right corner neighbors
                redInt +=
                    image[row + rowLayerCounter][column + columnLayerCounter].getRed() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize + columnLayerCounter];
                greenInt +=
                    image[row + rowLayerCounter][column + columnLayerCounter].getGreen() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize + columnLayerCounter];
                blueInt +=
                    image[row + rowLayerCounter][column + columnLayerCounter].getBlue() * kernel[
                        kernalSize + rowLayerCounter][
                        kernalSize + columnLayerCounter];
              }
            }
            //Adding the middle section of neighbors in the below section
            redInt += image[row + rowLayerCounter][column].getRed() * kernel[kernalSize
                + rowLayerCounter][kernalSize];
            greenInt += image[row + rowLayerCounter][column].getGreen() * kernel[kernalSize
                + rowLayerCounter][kernalSize];
            blueInt += image[row + rowLayerCounter][column].getBlue() * kernel[kernalSize
                + rowLayerCounter][kernalSize];
          }
        }

        for (int columnLayerCounter = 1; columnLayerCounter <= kernalSize;
            columnLayerCounter++) {
          if (column >= 0
              + kernalSize) { //If the current pixel is right enough to add the left middle section
            redInt += image[row][column - columnLayerCounter].getRed() * kernel[kernalSize][
                kernalSize
                    - columnLayerCounter];
            greenInt +=
                image[row][column - columnLayerCounter].getGreen() * kernel[kernalSize][
                    kernalSize
                        - columnLayerCounter];
            blueInt +=
                image[row][column - columnLayerCounter].getBlue() * kernel[kernalSize][
                    kernalSize
                        - columnLayerCounter];
          }
          if (column < image[row].length
              - kernalSize) { //If the current pixel is left enough to add the right middle section
            redInt += image[row][column + columnLayerCounter].getRed() * kernel[kernalSize][
                kernalSize
                    + columnLayerCounter];
            greenInt +=
                image[row][column + columnLayerCounter].getGreen() * kernel[kernalSize][
                    kernalSize
                        + columnLayerCounter];
            blueInt +=
                image[row][column + columnLayerCounter].getBlue() * kernel[kernalSize][
                    kernalSize
                        + columnLayerCounter];
          }
        }

        if (redInt > image[row][column]
            .getMaxValue()) { //If redInt is higher than the max pixel value, set it to the max
          redInt = image[row][column].getMaxValue();
        }
        if (redInt < 0) {
          redInt = 0;
        }
        if (greenInt > image[row][column]
            .getMaxValue()) { //If greenInt is higher than the max pixel value, set it to the max
          greenInt = image[row][column].getMaxValue();
        }
        if (greenInt < 0) {
          greenInt = 0;
        }
        if (blueInt > image[row][column]
            .getMaxValue()) { //If blueInt is higher than the max pixel value, set it to the max
          blueInt = image[row][column].getMaxValue();
        }
        if (blueInt < 0) {
          blueInt = 0;
        }
        //new pixel value = rolling integer
        Pixel newPixel = new Pixel(redInt, greenInt, blueInt, image[row][column].getMaxValue());
        newImage[row][column] = newPixel;
      }
    }
    return newImage;
  }

}
