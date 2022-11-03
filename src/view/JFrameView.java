package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFrameView extends JFrame implements IView{


  JPanel topPanel;
  JPanel leftPanel;
  JPanel centerPanel;
  JPanel rightPanel;
  JPanel bottomPanel;

  JPanel layerButtonPanel;
  JPanel listOfLayers;

  JButton blurButton;
  JButton sharpenButton;
  JButton sepiaButton;
  JButton greyscaleButton;

  JButton createLayerButton;
  JButton removeLayerButton;

  JButton saveButton;
  JButton saveAllButton;
  JButton loadButton;
  JButton loadAllButton;

  private JLabel fileOpenDisplay;

  JScrollPane scrollListOfLayers;
  JScrollPane scrollPanel;

  JLabel newPicLabel;
  JFrame imageFrame;

  List<String> layers;


  public JFrameView(String windowName) {
    super(windowName);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

    setSize(1050, 550);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    layers = new ArrayList<String>();


    //Panels
    topPanel = new JPanel(new FlowLayout());
    topPanel.setBackground(Color.DARK_GRAY);

    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
    leftPanel.setBackground(Color.GRAY);


    centerPanel = new JPanel();
    centerPanel.setBackground(Color.GRAY);

    // NEED TO BE MOVED TO A PUBLIC METHOD LATER
    scrollPanel = new JScrollPane(centerPanel);
    centerPanel.setLayout(new FlowLayout());




    rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
    rightPanel.setBackground(Color.YELLOW);

    layerButtonPanel = new JPanel(new FlowLayout());
    layerButtonPanel.setBackground(Color.GRAY);

    layerButtonPanel.setMaximumSize(new Dimension(300, 200));

    listOfLayers = new JPanel();
    listOfLayers.setLayout(new GridLayout(0,1));
    scrollListOfLayers = new JScrollPane(listOfLayers);


    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.DARK_GRAY);



    // Making of the Buttons
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    blurButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    leftPanel.add(blurButton, Component.CENTER_ALIGNMENT);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    leftPanel.add(sharpenButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    leftPanel.add(sepiaButton);

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    leftPanel.add(greyscaleButton);

    loadButton = new JButton("Load Image");
    loadButton.setActionCommand("load");
    topPanel.add(loadButton);

    saveButton = new JButton("Save as Image");
    saveButton.setActionCommand("save");
    topPanel.add(saveButton);

    loadAllButton = new JButton("Load Layers");
    loadAllButton.setActionCommand("load-all");
    topPanel.add(loadAllButton);

    saveAllButton = new JButton("Save Layers");
    saveAllButton.setActionCommand("save-all");
    topPanel.add(saveAllButton);

    createLayerButton = new JButton("Create Layer");
    createLayerButton.setActionCommand("create-layer");

    layerButtonPanel.add(createLayerButton);

    removeLayerButton = new JButton("Remove Layer");
    removeLayerButton.setActionCommand("remove-layer");
    layerButtonPanel.add(removeLayerButton);

    rightPanel.add(layerButtonPanel);
    rightPanel.add(scrollListOfLayers);
    add(topPanel, BorderLayout.PAGE_START);
    add(leftPanel, BorderLayout.LINE_START);
    add(rightPanel, BorderLayout.LINE_END);
    add(scrollPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.PAGE_END);

  }

  @Override
  public void setListener(ActionListener listener) {
    this.createLayerButton.addActionListener(listener);
    this.removeLayerButton.addActionListener(listener);
    this.loadButton.addActionListener(listener);
    this.saveButton.addActionListener(listener);
    this.saveAllButton.addActionListener(listener);
    this.blurButton.addActionListener(listener);
    this.sharpenButton.addActionListener(listener);
    this.sepiaButton.addActionListener(listener);
    this.greyscaleButton.addActionListener(listener);
  }

  public void currentDisplayImage(BufferedImage rawImage) {


    try {
      Component[] tempList = centerPanel.getComponents();
      for (Component c : tempList) {
        centerPanel.remove(c);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    JLabel displayImage = new JLabel(new ImageIcon(rawImage));
    centerPanel.add(displayImage);
    centerPanel.repaint();
    validate();
  }


  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void renderView() {
    validate();
  }

  @Override
  public void renderMessage(String title, String message) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void renderError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.PLAIN_MESSAGE);
  }



  @Override
  public java.io.File loadFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, & PPM Images", "jpeg", "png", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(JFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      System.out.println(f.getName());
      return f;
    } else {
      throw new IllegalArgumentException("File Chooser Failure");
    }
  }

  @Override
  public String saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(JFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      throw new IllegalArgumentException("Could not save properly");
    }
  }

  public String createLayer(ActionListener listener) {
    JPanel tempPan = new JPanel(new FlowLayout());
    JButton buttonToBeCreated = new JButton("Default");
    String text = JOptionPane.showInputDialog("Layer Name");

    boolean dup = false;
    Component[] tempList = listOfLayers.getComponents();

    for(Component c: tempList) {
      JPanel temp;
      if (c instanceof JPanel) {
        temp = (JPanel) c;
        if (temp.getName().equals(text)) {
          dup = true;
          break;
        }
      } else {
        throw new IllegalArgumentException("Bad Panel");
      }
    }

    if (text.equals("") || text.equals(" ")){
      JOptionPane.showMessageDialog(this, "NOT A VALID LAYER NAME!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    else if (dup) {
      JOptionPane.showMessageDialog(this, text.toUpperCase() + " is already a layer!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    else {
      tempPan.setName(text);

      buttonToBeCreated.setText(text);
      buttonToBeCreated.setPreferredSize(new Dimension(190, 75));
      buttonToBeCreated.setName(text);
      buttonToBeCreated.setActionCommand("current");
      buttonToBeCreated.addActionListener(listener);

      tempPan.add(buttonToBeCreated);


      JToggleButton toggle = new JToggleButton("Visible");
      toggle.setActionCommand("visible");
      toggle.addActionListener(listener);
      toggle.setName(text);

      tempPan.add(toggle);
      tempPan.setAlignmentX(JButton.CENTER_ALIGNMENT);
      listOfLayers.add(tempPan);

    }
    validate();

    return text;
  }

  public String removeLayer() {
    String layerName = JOptionPane.showInputDialog("Layer Name");
    Component[] tempList = listOfLayers.getComponents();
    JPanel panelToBeRemoved = null;

    for(Component c: tempList) {
      JPanel temp;
      if (c instanceof JPanel) {
        temp = (JPanel) c;
      }
      else {
        throw new IllegalArgumentException("Bad Panel");
      }
      Component[] buttonTemp = temp.getComponents();
      JButton buttonToBeRemove;
      if (buttonTemp[0] instanceof JButton) {
        buttonToBeRemove = (JButton) buttonTemp[0];

        if (buttonToBeRemove.getText().equals(layerName)) {
          panelToBeRemoved = temp;
        }
      }
      else {
        throw new IllegalArgumentException("Not a Button for some reason");
      }
    }

    if (panelToBeRemoved == null) {
      JOptionPane.showMessageDialog(this, "NO LAYERS NAMED " + layerName.toUpperCase() +"!", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      listOfLayers.remove(panelToBeRemoved);
    }
    validate();

    return layerName;
  }

  public void debugText(String text) {
    JOptionPane.showMessageDialog(this, text, "Debugger", JOptionPane.PLAIN_MESSAGE);
  }
}
