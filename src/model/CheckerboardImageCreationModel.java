package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Checkerboard image version of the ImageCreationModel.
 */
public class CheckerboardImageCreationModel implements ImageCreationModel<Pixel> {

  private int sizeTiles;
  private int numRows;
  private int numColumns;
  private Color color1;
  private Color color2;

  /**
   * Constructor for the CheckerboardImageCreationModel. Initializes the parameters and checks for
   * validity of parameters.
   *
   * @param sizeTiles  int representing the size of each tile in the measurement pixels
   * @param numRows    int representing the number of rows in the checkerboard
   * @param numColumns int representing the number of columns in the checkerboard
   * @param color1     Color representing the first color of the checkerboard
   * @param color2     Color representing the second color of the checkerboard
   * @throws IllegalArgumentException Throws exception if any parameters are invalid.
   */
  public CheckerboardImageCreationModel(int sizeTiles, int numRows, int numColumns, Color color1,
      Color color2) throws IllegalArgumentException {
    if (sizeTiles <= 0 || numRows <= 0 || numColumns <= 0) {
      throw new IllegalArgumentException("Invalid tile inputs");
    }
    if (color1 == null || color2 == null) {
      throw new IllegalArgumentException("Color is null");
    }
    this.sizeTiles = sizeTiles;
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.color1 = color1;
    this.color2 = color2;
  }

  @Override
  public List<Pixel> create() {
    List<Pixel> outputImage = new ArrayList<Pixel>();
    Color currentColor = color1;
    for (int row = 0; row < numRows; row++) {
      for (int i = 0; i < sizeTiles; i++) {
        for (int column = 0; column < numColumns; column++) {
          for (int j = 0; j < sizeTiles; j++) {
            outputImage.add(new Pixel(currentColor.getRed(), currentColor.getGreen(),
                currentColor.getBlue(), 255));
          }
          if (currentColor == color1) {
            currentColor = color2;
          } else {
            currentColor = color1;
          }
        }
      }
      if (currentColor == color1) {
        currentColor = color2;
      } else {
        currentColor = color1;
      }
    }
    return outputImage;
  }

  @Override
  public int getHeight() {
    return this.numRows * this.sizeTiles;
  }

  @Override
  public int getWidth() {
    return this.numColumns * this.sizeTiles;
  }

  @Override
  public int getMaxValue() {
    return 255;
  }
}
