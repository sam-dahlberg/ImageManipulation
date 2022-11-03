package model;


import java.util.List;

/**
 * Interface that represents layers of images.
 */

public interface LayerModel {

  /**
   * Creates a new layer.
   *
   * @param layerName String representing the name of the layer
   */

  void createLayer(String layerName);

  /**
   * adds an image to the current layer.
   *
   * @param image Image object to be added
   */

  void addImage(Image image);

  /**
   * Creates a layer and adds an image at the same time.
   *
   * @param image     Image object to be added
   * @param layerName Name of layer to be added
   */

  void createAndAdd(Image image, String layerName);

  /**
   * Removes a layer from the model.
   *
   * @param layerName The name of the layer to be removed
   */

  void removeLayer(String layerName);

  /**
   * Changes the current layer of the model.
   *
   * @param layerName Name of the layer to be made current
   */

  void changeCurrentLayer(String layerName);

  /**
   * Applies an operation to the current layer.
   *
   * @param operation The operation to be applied
   */

  void apply(Operations operation);

  /**
   * Changes the visibility of the a given layer.
   *
   * @param layerName the name of the layer to be changed.
   */

  void changeVisibility(String layerName);

  /**
   * Gets the image at the current layer.
   *
   * @return An image object representing above
   */

  Image getCurrentLayer();

  /**
   * Returns the image at the specific layer inputted.
   *
   * @param layerName name of layer to return
   * @return Image object representing the return image
   */

  Image getLayer(String layerName);

  /**
   * returns all layers in the model.
   *
   * @return a list of images representing all layers
   */

  List<Image> getAllLayer();

  /**
   * returns the list of all layers order.
   *
   * @return A list of string with the order of the layers
   */

  List<String> getOrder();

}
