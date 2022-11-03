package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import model.Blur;
import model.Greyscale;
import model.Image;
import model.Pixel;
import model.Sepia;
import model.Sharpen;
import model.SimpleLayerModel;
import view.IView;
import view.JFrameView;

public class GUIController extends AbstractController implements ImageController, ActionListener {

  IView view;
  Image workingImage;

  public GUIController(SimpleLayerModel model, JFrameView view) {
    super(model);

    this.view = view;
    view.setListener(this);
    view.display();
  }

  @Override
  public void start() throws IOException, IllegalArgumentException {

  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "create-layer": {
        String layerName = view.createLayer(this);
        createLayer(layerName);
      }
      break;

      case "remove-layer": {
        String layerName = view.removeLayer();
        removeLayer(layerName);
      }
      break;

      case "blur":
        transformLayer(new Blur());
        break;

      case "sharpen":
        transformLayer(new Sharpen());
        break;

      case "sepia":
        transformLayer(new Sepia());
        break;

      case "greyscale":
        transformLayer(new Greyscale());
        break;

      case "load": {
        if(model.getAllLayer().size() == 0) {
          view.renderMessage("No Layers!", "There are no layers to load image into.");
          break;
        }
        File f;
        String tempFile;

        try {
          f = view.loadFile();
          System.out.println(f.getName());
          tempFile = f.getName();
        } catch(Exception error) {
          throw new IllegalArgumentException("Error loading in file");
        }
        String fileType = tempFile.substring(tempFile.indexOf(".") + 1);
        String fileName = tempFile.substring(0, tempFile.indexOf("."));
        Image tempImage;
        switch (fileType) {
          case "ppm":
            System.out.println("ppm type");
            tempImage = ppmtoimage(f);
            System.out.println("ppm succesful");
            break;
          case "png":
            System.out.println("png type");
            tempImage = pngtoimage(f);
            System.out.println("png succesful");
            break;
          case "jpeg":
            System.out.println("jpeg type");
            tempImage = jpgtoimage(f);
            System.out.println("jpeg succesful");
            break;
          default:
            throw new IllegalArgumentException("Failure to load file");
        }
        model.addImage(tempImage);
      }
      break;
      case "save": {
        String saveFile = view.saveFile();
        String saveFileType = saveFile.substring(saveFile.indexOf(".") + 1);
        String saveFileName = saveFile.substring(0, saveFile.indexOf("."));
        switch (saveFileType) {
          case ("png"):
            imageToPNG(model.getCurrentLayer(), saveFile);
            break;
          case ("jpeg"):
            imageToJPG(model.getCurrentLayer(), saveFile);
            break;
          case ("ppm"):
            imageToPPM(model.getCurrentLayer(), saveFile);
            break;
          default:
            throw new IllegalArgumentException("!!NOT A VALID FILE TYPE!!");
            //.jpg files do not work but .jpeg and a ta is office hours said that was ok.
        }
      }
        break;
      case "save-all": {
        try {
          super.saveAll(view.saveFile());
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        break;
      }

      case("current"): {
        String layerName;
        if (e.getSource() instanceof JButton) {
          JButton tempButton = (JButton) e.getSource();
          layerName = tempButton.getName();
        } else {
          view.renderError("ERROR. Did not click a button. ERROR");
          throw new IllegalArgumentException("No button found");
        }
        super.changeCurrentLayer(layerName);
      }
        break;
      case "visible": {
        String layerName;
        if (e.getSource() instanceof JToggleButton) {
          JToggleButton tempButton = (JToggleButton) e.getSource();
          layerName = tempButton.getName();
        } else {
          view.renderError("ERROR. Did not click a button. ERROR");
          throw new IllegalArgumentException("No button found");
        }
        super.changeVisiblity(layerName);
      }
    }



    Image current = model.getCurrentLayer();
    view.currentDisplayImage(renderImage(current));
  }




  private BufferedImage renderImage(Image image) {

    if (image == null) {
      return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> data = image.getData();
    BufferedImage displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


      int index = 0;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {

        Pixel currentPixel = data.get(index);
        int red = currentPixel.getRed();
        int green = currentPixel.getGreen();
        int blue = currentPixel.getBlue();

        int outputRGB = new Color(red, green, blue).getRGB();

        displayImage.setRGB(column, row, outputRGB);
        index++;
      }
    }
    return displayImage;
  }





}
