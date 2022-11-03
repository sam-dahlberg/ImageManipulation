package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a simple layer model.
 */

public class SimpleLayerModel implements LayerModel {

  private ImageProcessingModel model;
  private List<String> keyList;
  private Map<String, Image> layers;


  /**
   * Constructor for SimpleLayerModel.
   *
   * @param model SimpleImageProcessingModel
   */
  public SimpleLayerModel(SimpleImageProcessingModel model) {
    if (model == null) {
      throw new IllegalArgumentException("empty model");
    }
    this.model = model;
    keyList = new ArrayList<String>();
    layers = new HashMap<String, Image>();
  }

  @Override
  public void createLayer(String layerName) {
    if (layerName.equals("")) {
      throw new IllegalArgumentException("empty string given for name");
    }
    keyList.add(0, layerName);
    layers.put(layerName, null);
  }

  @Override
  public void addImage(Image image) {
    layers.replace(keyList.get(0), image);
  }

  @Override
  public void createAndAdd(Image image, String layerName) {
    if (layerName.equals("")) {
      throw new IllegalArgumentException("empty string given for name");
    }
    keyList.add(0, layerName);
    layers.put(layerName, image);
  }

  @Override
  public void removeLayer(String layerName) {
    validLayerName(layerName);
    layers.remove(layerName);
    keyList.remove(layerName);
  }

  @Override
  public void changeCurrentLayer(String layerName) {
    validLayerName(layerName);
    keyList.remove(layerName);
    keyList.add(0, layerName);
  }

  @Override
  public void apply(Operations operation) {
    int index = 0;
    while (!layers.get(keyList.get(index)).getVisible()) {
      index++;
    }

    if (index >= keyList.size()) {
      throw new IllegalArgumentException("All Layers are invisible");
    }
    layers.replace(keyList.get(index), model.transform(layers.get(keyList.get(index)), operation));

  }

  @Override
  public void changeVisibility(String layerName) {
    validLayerName(layerName);
    Image tempImage = layers.get(layerName);
    tempImage.setVisible(!tempImage.getVisible());
  }

  @Override
  public Image getCurrentLayer() {

    if ((layers.get(keyList.get(0)) == null)) {
      return null;
    }

    int index = 0;
    while (!layers.get(keyList.get(index)).getVisible()) {
      index++;
    }

    if (index >= keyList.size()) {
      throw new IllegalArgumentException("All Layers are invisible");
    }


    return layers.get(keyList.get(index));
  }

  @Override
  public Image getLayer(String layerName) {
    validLayerName(layerName);
    return layers.get(layerName);
  }

  @Override
  public List<Image> getAllLayer() {
    List<Image> listOfImages = new ArrayList<Image>();
    for (int i = 0; i < keyList.size(); i++) {
      listOfImages.add(layers.get(keyList.get(i)));
    }
    return listOfImages;
  }

  @Override
  public List<String> getOrder() {
    return new ArrayList<String>(keyList);
  }


  private void validLayerName(String layerName) {
    if (!keyList.contains(layerName)) {
      throw new IllegalArgumentException("Not a valid Layer name");
    }
  }
}
