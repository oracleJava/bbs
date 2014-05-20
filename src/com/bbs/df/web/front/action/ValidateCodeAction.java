package com.bbs.df.web.front.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ValidateCodeAction extends ActionSupport{
    private ByteArrayInputStream inputStream;
    public ByteArrayInputStream getInputStream() {
    	return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
    	this.inputStream = inputStream;
    }
    /**
     * 生成随机的五个字符，并将这些字符添加到session作用域内。
     * */
	public String generateCode(){
	   Random rand = new Random();
	   StringBuffer waitRandomStr = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
	   int codeLength = 5;
	   StringBuffer code = new StringBuffer();
	   for(int i=0;i<codeLength;i++){
		   int select = rand.nextInt(waitRandomStr.length());
		   code.append(waitRandomStr.charAt(select));
		   waitRandomStr.deleteCharAt(select);
	   }
	   ServletActionContext.getRequest().getSession().setAttribute("ValidateCode",code.toString());
	   return code.toString();
   }
   /**
    * 绘制背景
    * */
   private void drawbg(Graphics g){
	  Random rand = new Random();
	  int randx,randy;
	  for(int i=0;i<rand.nextInt(100)+500;i++){
		 g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
		 randx = rand.nextInt(100);
		 randy = rand.nextInt(20);
		 g.drawLine(randx,randy, randx,randy);
	  }
   }
   private void drawValidateCode(Graphics g){
	   String code = generateCode();
	   Random rand = new Random();
	   int x = 0;
	   Font font = new Font("Times New Roman",Font.PLAIN,18);
	   g.setFont(font);
	   for(int i=0;i<code.length();i++){
		   int y = 20-rand.nextInt(4);
		   g.setColor(new Color(rand.nextInt(150),rand.nextInt(150),rand.nextInt(150)));
		   g.drawString(code.substring(i,i+1), x, y);
		   x += 20;
	   }
   }
   /**
    * 生成验证码
    * */
   private ByteArrayInputStream generateImage()throws IOException{
	   BufferedImage image = new BufferedImage(100,20,BufferedImage.TYPE_INT_RGB);
	   Graphics g = image.getGraphics();
	   g.setColor(Color.WHITE);
	   g.fillRect(0, 0, 100, 20);
	   drawbg(g);
	   drawValidateCode(g);
	   ByteArrayInputStream input = null;
	   ByteArrayOutputStream bos = new ByteArrayOutputStream();
	   ImageIO.write(image,"JPEG",bos);
	   byte[] buf = bos.toByteArray();
	   input = new ByteArrayInputStream(buf);
	   return input;
   }
  
   public String generateValidateCode(){
		try {
			setInputStream(generateImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}

