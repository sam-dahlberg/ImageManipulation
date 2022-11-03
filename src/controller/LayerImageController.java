package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import model.Blur;
import model.Greyscale;
import model.Image;
import model.ImageProcessingModel;
import model.LayerModel;
import model.Sepia;
import model.Sharpen;
import model.SimpleImageProcessingModel;
import model.Pixel;
import model.SimpleLayerModel;
import view.IView;
import view.JFrameView;


/**
 * This class is a Simple Layer Image Controller class, able to manipulate and use layers and
 * individual images.
 */
public class LayerImageController extends AbstractController implements ImageController {

  /**
   * Main method for running the model.
   *
   * @param args Default parameter for main
   */
  public static void main(String[] args) {

    /*
    String filename = "res/Script1.txt";
    FileReader reader = new FileReader(filename);
    SimpleImageProcessingModel modelI = new SimpleImageProcessingModel();
    SimpleLayerModel model = new SimpleLayerModel(modelI);
    LayerImageController controller = new LayerImageController(model, reader);
    controller.start();

     */

    JFrameView view;
    SimpleImageProcessingModel modelI = new SimpleImageProcessingModel();
    SimpleLayerModel model = new SimpleLayerModel(modelI);

    try {
      view = new JFrameView("ImageShop");
    } catch (Exception  e) {
      throw new IllegalStateException("Could not make View");
    }
    ImageController controller = new GUIController(model, view);

  }

  private LayerModel model;
  private final Readable rd;

  /**
   * Constructor for creating a controller with the imageProcessingModel.
   *
   * @param model an inputted imageProcessingModel
   */
  public LayerImageController(SimpleLayerModel model, Readable rd) {
    super(model);
    this.rd = rd;
  }

  @Override
  public void start() throws IOException, IllegalArgumentException {
    Scanner sc;
    Scanner scan;

    try {
      scan = new Scanner(rd);
    } catch (Exception e) {
      throw new IllegalArgumentException("Cant read input");
    }

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scan.hasNextLine()) {
      StringBuilder builder = new StringBuilder();
      builder.append(scan.nextLine());
      System.out.println(builder.toString());
      sc = new Scanner(builder.toString());

      String command = sc.next().toLowerCase();
      String info;

      String fileType;
      String fileName;
      int indexOfDot;

      switch (command) {
        case ("create-layer"):
          info = sc.next().toLowerCase();
          model.createLayer(info);
          break;
        case("remove-layer"):
          info = sc.next().toLowerCase();
          model.removeLayer(info);
          break;
        case ("current"):
          info = sc.next().toLowerCase();
          model.changeCurrentLayer(info);
          break;
        case ("visible"):
        case ("invisible"):
          info = sc.next().toLowerCase();
          model.changeVisibility(info);
          break;
        case ("blur"):
          model.apply(new Blur());
          break;
        case ("sharpen"):
          model.apply(new Sharpen());
          break;
        case ("sepia"):
          model.apply(new Sepia());
          break;
        case ("greyscale"):
          model.apply(new Greyscale());
          break;
        case ("load"):
          info = sc.next();
          indexOfDot = info.indexOf(".");
          fileName = "res/" + info;
          fileType = info.substring(indexOfDot + 1);

          switch (fileType) {
            case ("png"):
              model.addImage(pngtoimage(new File(fileName)));
              break;
            case ("jpeg"):
              model.addImage(jpgtoimage(new File(fileName)));
              break;
            case ("ppm"):
              model.addImage(ppmtoimage(new File(fileName)));
              break;
            default:
              throw new IllegalArgumentException("!!NOT A VALID FILE TYPE!!");
              //.jpg files do not work but .jpeg and a ta is office hours said that was ok.
          }
          break;
        case ("save"):
          info = sc.next();
          indexOfDot = info.indexOf(".");
          fileName = info.substring(0, indexOfDot);
          fileType = info.substring(indexOfDot + 1);
          switch (fileType) {
            case ("png"):
              imageToPNG(model.getCurrentLayer(), fileName);
              break;
            case ("jpeg"):
              imageToJPG(model.getCurrentLayer(), fileName);
              break;
            case ("ppm"):
              imageToPPM(model.getCurrentLayer(), fileName);
              break;
            default:
              throw new IllegalArgumentException("!!NOT A VALID FILE TYPE!!");
              //.jpg files do not work but .jpeg and a ta is office hours said that was ok.
          }
          break;
        case ("save-all"):
          super.saveAll(sc.next());
          break;

        case "load-all":
          info = sc.next();
          indexOfDot = info.indexOf(".");
          fileName = info.substring(0, indexOfDot);
          fileType = info.substring(indexOfDot + 1);

          if (!fileType.equals("txt")) {
            throw new IllegalArgumentException("Need a text file to load in all layers.");
          }
          File layers = new File("res/" + info);

          Scanner loadIn = new Scanner(new FileInputStream(layers));

          while (loadIn.hasNextLine()) {

            String line;

            String lineFileType;
            String lineFileName;
            int lineIndexOfDot;

            line = loadIn.nextLine();
            lineIndexOfDot = line.indexOf(".");
            lineFileName = "res/" + line;
            lineFileType = line.substring(lineIndexOfDot + 1);

            if (lineIndexOfDot < 1) {
              model.createLayer(line);
            } else {
              switch (lineFileType) {
                case ("png"):
                  model.createAndAdd(pngtoimage(new File(lineFileName)),
                      line.substring(0, lineIndexOfDot));
                  break;
                case ("jpeg"):
                  model.createAndAdd(jpgtoimage(new File(lineFileName)),
                      line.substring(0, lineIndexOfDot));
                  break;
                case ("ppm"):
                  model.createAndAdd(ppmtoimage(new File(lineFileName)),
                      line.substring(0, lineIndexOfDot));
                  break;
                default:
                  throw new IllegalArgumentException("!!NOT A VALID FILE TYPE!!");
                  //.jpg files do not work but .jpeg and a ta is office hours said that was ok.
              }
            }
          }
          break;

        default:
          throw new IllegalArgumentException("Invalid Command!");

      }
    }
  }
}

