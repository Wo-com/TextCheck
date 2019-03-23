package main;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class Star
{
    public static void main(String args[]) throws BadLocationException
    {
    	//初始化界面
        Windowm windowm = new Windowm();
        
        
        //属性设置
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        //字体大小
        StyleConstants.setFontSize(attrset,16);
        //获取JTextPane对象
        Document docs1=windowm.text1.getDocument();
        //设置初次显示文本
        docs1.insertString(docs1.getLength(), "手动输入或者选择文件打开", attrset);        
        Document docs2=windowm.text2.getDocument();
        docs2.insertString(docs2.getLength(), "手动输入输入或者选择文间打开\n点击核对试试\n红色表示错误字符\n蓝色表示多余或缺失字符", attrset);
    }
}