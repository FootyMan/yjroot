package com.service.utils;
//package com.myErp.utils;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.text.DecimalFormat;
//import java.util.Properties;
//import javax.imageio.ImageIO;
//public class ImgUtil {
//	public static String CONVERT_PROG = Resources.getConfigString("ImageMagick_path");
//	  private int startX;
//	  private int startY;
//	  private int width;
//	  private int height;
//	  private String srcPath;
//	  private String tarPath;
//
//	  public static void main(String[] args)
//	  {
//	    ImgUtil imgT = new ImgUtil();
//	    imgT.setHeight(170);
//	    imgT.setWidth(170);
//	    imgT.setSrcPath("d:/390390.png");
//	    imgT.setTarPath("d:/170170.png");
//
//	    imgT.scale();
//	  }
//
//	  public void setCutSize()
//	  {
//	    try
//	    {
//	      File f = new File(this.srcPath);
//	      BufferedImage bi = ImageIO.read(f);
//	      int width2 = bi.getWidth();
//	      int height2 = bi.getHeight();
//	      if (width2 > height2) {
//	        this.startY = 0;
//	        this.height = height2;
//	        this.startX = ((width2 - height2) / 2);
//	        this.width = this.height;
//	      } else {
//	        this.startX = 0;
//	        this.width = width2;
//	        this.startY = ((height2 - width2) / 2);
//	        this.height = this.width;
//	      }
//	    }
//	    catch (IOException e) {
//	      e.printStackTrace();
//	    }
//	  }
//
//	  public void setSize()
//	  {
//	    try
//	    {
//	      File f = new File(this.srcPath);
//	      BufferedImage bi = ImageIO.read(f);
//	      int width2 = bi.getWidth();
//	      int height2 = bi.getHeight();
//	      this.width = width2;
//	      this.height = height2;
//	    }
//	    catch (IOException e) {
//	      e.printStackTrace();
//	    }
//	  }
//
//	  public String getPicRate() {
//	    String picRatio = "1.00";
//	    try {
//	      File f = new File(this.srcPath);
//	      BufferedImage bi = ImageIO.read(f);
//	      int width2 = bi.getWidth();
//	      int height2 = bi.getHeight();
//	      float size = width2 / height2;
//	      DecimalFormat df = new DecimalFormat("0.00");
//	      picRatio = df.format(size);
//	    }
//	    catch (IOException e) {
//	      e.printStackTrace();
//	    }
//	    return picRatio;
//	  }
//
//	  public boolean scale()
//	  {
//	    int quality = 100;
//	    if (this.height > 300) {
//	      quality = 60;
//	    }
//	    File in = new File(this.srcPath);
//	    File out = new File(this.tarPath);
//	    if ((quality < 0) || (quality > 100)) {
//	      quality = 75;
//	    }
//
//	    String property = System.getProperties().getProperty("os.name");
//	    StringBuffer command = new StringBuffer();
//	    if (property.indexOf("Windows") >= 0)
//	      command.append(CONVERT_PROG);
//	    else {
//	      command.append("convert ");
//	    }
//	    command.append(" ");
//	    command.append("-geometry");
//	    command.append(" ");
//	    command.append(this.width + "x" + this.height);
//	    command.append(" ");
//	    command.append("-quality");
//	    command.append(" ");
//	    command.append("" + quality);
//	    command.append(" ");
//	    command.append(in.getAbsolutePath());
//	    command.append(" ");
//	    command.append(out.getAbsolutePath());
//	    command.append(" ");
//	    System.out.println("srcPath:" + this.srcPath);
//	    System.out.println("tarPath:" + this.tarPath);
//	    System.out.println("command:" + command.toString());
//	    boolean exec = exec(command.toString());
//	    System.out.println("exec:" + exec);
//	    in.delete();
//	    return exec;
//	  }
//
//	  public boolean scale(int isDelSrcFile) {
//	    int quality = 100;
//	    if (this.height > 300) {
//	      quality = 60;
//	    }
//	    File in = new File(this.srcPath);
//	    File out = new File(this.tarPath);
//	    if ((quality < 0) || (quality > 100)) {
//	      quality = 75;
//	    }
//
//	    String property = System.getProperties().getProperty("os.name");
//	    StringBuffer command = new StringBuffer();
//	    if (property.indexOf("Windows") >= 0)
//	      command.append(CONVERT_PROG);
//	    else {
//	      command.append("convert ");
//	    }
//	    command.append(" ");
//	    command.append("-geometry");
//	    command.append(" ");
//	    command.append(this.width + "x" + this.height);
//	    command.append(" ");
//	    command.append("-quality");
//	    command.append(" ");
//	    command.append("" + quality);
//	    command.append(" ");
//	    command.append(in.getAbsolutePath());
//	    command.append(" ");
//	    command.append(out.getAbsolutePath());
//	    command.append(" ");
//	    System.out.println("srcPath:" + this.srcPath);
//	    System.out.println("tarPath:" + this.tarPath);
//	    System.out.println("command:" + command.toString());
//	    boolean exec = exec(command.toString());
//	    System.out.println("exec:" + exec);
//	    if (isDelSrcFile == 1) {
//	      in.delete();
//	    }
//	    return exec;
//	  }
//
//	  public void cut()
//	  {
//	    try
//	    {
//	      File file = new File(this.srcPath);
//	      BufferedImage bufImg = ImageIO.read(file);
//	      bufImg = bufImg.getSubimage(this.startX, this.startY, this.width, this.height);
//	      ImageIO.write(bufImg, getImgFormat(), new File(this.tarPath));
//	      file.delete();
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  }
//
//	  public void cut(int isDelSrcFile)
//	  {
//	    try
//	    {
//	      File file = new File(this.srcPath);
//	      BufferedImage bufImg = ImageIO.read(file);
//	      bufImg = bufImg.getSubimage(this.startX, this.startY, this.width, this.height);
//	      ImageIO.write(bufImg, getImgFormat(), new File(this.tarPath));
//	      if (isDelSrcFile == 1)
//	        file.delete();
//	    }
//	    catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  }
//
//	  public String getImgFormat() {
//	    int lastIndexOf = this.srcPath.lastIndexOf(".");
//	    if (lastIndexOf >= 0) {
//	      return this.srcPath.substring(lastIndexOf + 1);
//	    }
//	    return null;
//	  }
//
//	  private static boolean exec(String command)
//	  {
//	    try
//	    {
//	      proc = Runtime.getRuntime().exec(command);
//	    }
//	    catch (IOException e)
//	    {
//	      Process proc;
//	      System.out.println("IOException while trying to execute " + command);
//
//	      return false;
//	    }
//	    while (true)
//	      try
//	      {
//	        Process proc;
//	        exitStatus = proc.waitFor();
//	      }
//	      catch (InterruptedException e)
//	      {
//	        int exitStatus;
//	        System.out.println("Interrupted: Ignoring and waiting");
//	      }
//	    int exitStatus;
//	    if (exitStatus != 0) {
//	      System.out.println("Error executing command: " + exitStatus);
//	    }
//	    return exitStatus == 0;
//	  }
//
//	  public int getStartX()
//	  {
//	    return this.startX;
//	  }
//	  public void setStartX(int startX) {
//	    this.startX = startX;
//	  }
//	  public int getStartY() {
//	    return this.startY;
//	  }
//	  public void setStartY(int startY) {
//	    this.startY = startY;
//	  }
//	  public int getWidth() {
//	    return this.width;
//	  }
//	  public void setWidth(int width) {
//	    this.width = width;
//	  }
//	  public int getHeight() {
//	    return this.height;
//	  }
//	  public void setHeight(int height) {
//	    this.height = height;
//	  }
//	  public String getSrcPath() {
//	    return this.srcPath;
//	  }
//	  public void setSrcPath(String srcPath) {
//	    this.srcPath = srcPath;
//	  }
//	  public String getTarPath() {
//	    return this.tarPath;
//	  }
//	  public void setTarPath(String tarPath) {
//	    this.tarPath = tarPath;
//	  }
//}
