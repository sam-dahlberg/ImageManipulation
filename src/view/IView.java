package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public interface IView {

  void renderView();

  void renderMessage(String title, String message);

  void renderError(String message);

  java.io.File loadFile();

  String saveFile();

  String createLayer(ActionListener listener);

  void currentDisplayImage(BufferedImage rawImage);

  String removeLayer();


  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();



  //DELETE
  void debugText(String s);
}
