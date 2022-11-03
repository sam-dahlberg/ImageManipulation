package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.Image;
import model.LayerModel;
import model.Operations;
import model.Pixel;
import model.SimpleLayerModel;
import view.IView;
import view.JFrameView;

public abstract class AbstractController implements ImageController {

  protected LayerModel model;
  protected IView view;

  public AbstractController (SimpleLayerModel model/*, JFrameView view*/) {
    this.model = model;
    //this.view = view;
  }


  protected void createLayer(String layerName) {
    model.createLayer(layerName);
  }

  protected void removeLayer(String layerName) {
    model.removeLayer(layerName);
  }

  protected void changeCurrentLayer(String layerName) {
    model.changeCurrentLayer(layerName);
  }

  protected void changeVisiblity(String layerName) {
    model.changeVisibility(layerName);
  }

  protected void transformLayer(Operations operation) {
    model.apply(operation);
  }

  protected void saveAll(String info) throws IOException {
    System.out.println(info);
    int indexOfSlash = info.lastIndexOf("\\");
    int indexOfDot = info.indexOf(".");
    String fileType = info.substring(indexOfDot + 1);
    String filePath = info.substring(0, indexOfSlash + 1);
    String fileName = info.substring(indexOfSlash + 1, indexOfDot);
    System.out.println(filePath);
    List<Image> images = model.getAllLayer();
    List<String> names = model.getOrder();
    int i = 0;

    switch (fileType) {
      case ("png"):
        for (Image image : images) {
          if (image != null) {
            imageToPNG(image, filePath + names.get(i) + ".png");
          }
          i++;
        }
        break;
      case ("jpeg"):
        for (Image image : images) {
          if (image != null) {
            imageToJPG(image, filePath + names.get(i) + ".jpeg");
          }
          i++;
        }
        break;
      case ("ppm"):
        for (Image image : images) {
          if (image != null) {
            imageToPPM(image, filePath + names.get(i) + ".ppm");
          }
          i++;
        }
        break;
      default:
        throw new IllegalArgumentException("!!NOT A VALID FILE TYPE!!");
        //.jpg files do not work but .jpeg and a ta is office hours said that was ok.
    }
    File outputFile = new File(info);
    try {
      FileWriter writer = new FileWriter(filePath + fileName + ".txt");

      int check = 0;
      for (String name : names) {
        if (check != 0) {
          writer.write("\n");
        }
        if (model.getLayer(name) != null) {
          writer.write(name + "." + fileType);
        } else {
          writer.write(name);
        }
        check++;
      }
      writer.close();

    } catch (IOException e) {
      throw new IOException("Could not write text file for multi-layered image.");
    }
  }

  protected Image pngtoimage(File inputFile) {
    List<Pixel> data = new ArrayList<Pixel>();
    BufferedImage inputBuffer;

    int height;
    int width;

    try {
      inputBuffer = ImageIO.read(inputFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Could not read PNG File");
    }

    height = inputBuffer.getHeight();
    width = inputBuffer.getWidth();

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        int rgb = inputBuffer.getRGB(column, row);
        int red = (rgb >> 16) & 255;
        int green = (rgb >> 8) & 255;
        int blue = (rgb) & 255;

        data.add(new Pixel(red, green, blue, 255));
      }
    }

    return new Image(data, height, width, 255);
  }

  protected void imageToPNG(Image image, String fileName) {
    List<Pixel> data = image.getData();
    int height = image.getHeight();
    int width = image.getWidth();

    //**** Image Type is DIFFERENT for png and jpg ********
    BufferedImage outputBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    int index = 0;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {

        Pixel currentPixel = data.get(index);
        int red = currentPixel.getRed();
        int green = currentPixel.getGreen();
        int blue = currentPixel.getBlue();

        int outputRGB = new Color(red, green, blue).getRGB();

        outputBuffer.setRGB(column, row, outputRGB);
        index++;
      }
    }

    File outputFile = new File(fileName);

    try {
      ImageIO.write(outputBuffer, "PNG", outputFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected Image jpgtoimage(File inputFile) {
    List<Pixel> data = new ArrayList<Pixel>();
    BufferedImage inputBuffer;

    int height;
    int width;

    try {
      inputBuffer = ImageIO.read(inputFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Could not read JPG File");
    }

    height = inputBuffer.getHeight();
    width = inputBuffer.getWidth();

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        int rgb = inputBuffer.getRGB(column, row);
        Color color = new Color(rgb);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        data.add(new Pixel(red, green, blue, 255));

      }
    }
    return new Image(data, height, width, 255);
  }


  protected void imageToJPG(Image image, String fileName) {
    List<Pixel> data = image.getData();
    int height = image.getHeight();
    int width = image.getWidth();

    //**** Image Type is DIFFERENT for png and jpg ********
    BufferedImage outputBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int index = 0;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {

        Pixel currentPixel = data.get(index);
        int red = currentPixel.getRed();
        int green = currentPixel.getGreen();
        int blue = currentPixel.getBlue();

        int outputRGB = new Color(red, green, blue).getRGB();

        outputBuffer.setRGB(column, row, outputRGB);
        index++;
      }
    }

    File outputFile = new File(fileName);

    try {
      ImageIO.write(outputBuffer, "JPEG", outputFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Takes in a file name for a file of type ppm, imports the ppm file, and converts it to a list.
   *
   * @param inputFile The file for the intended ppm file
   */
  protected Image ppmtoimage(File inputFile) {
    List<Pixel> data = new ArrayList<Pixel>();
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(inputFile));
    } catch (FileNotFoundException e) {
      System.out.println("Cant read ppm File");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        data.add(new Pixel(r, g, b, maxValue));

      }
    }
    return new Image(data, height, width, maxValue);
  }


  /**
   * Takes in a list of pixels and exported it to a file of type ppm in the location of the inputted
   * file name.
   *
   * @param image    The image being exported
   * @param fileName the file name for the created ppm file
   */
  protected void imageToPPM(Image image, String fileName) {
    List<Pixel> data = image.getData();
    StringBuilder builder = new StringBuilder();
    int height = image.getHeight();
    int width = image.getWidth();
    int maxValue = image.getMaxRGB();

    builder.append("P3" + System.lineSeparator());
    builder.append(width + System.lineSeparator());
    builder.append(height + System.lineSeparator());
    builder.append(maxValue + System.lineSeparator());

    for (Pixel pixel : data) {
      builder.append(pixel.toString() + System.lineSeparator());
    }

    String path = fileName;

    try (FileWriter writer = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(writer)) {

      bw.write(String.valueOf(builder));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }



}
