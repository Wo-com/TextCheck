package main;

import algorithm.DNASequence;
import open_file.Read_File;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

class Windowm extends JFrame
{
	String path1;//第一个文件目录
	String path2;//第二个文件目录
	String File1;//第一个文件
	String File2;//第二个文件
	int point;//保存当前活动窗口
	
	private static final long serialVersionUID = 1L;
	JPanel myPanel1 = new JPanel();//面板1.1
    JPanel myPanel2 =new JPanel();//面板2.1
    JPanel myPanel3 =new JPanel();//面板3
    JPanel myPanel4 =new JPanel();//面板4
    JTextPane text1=new JTextPane();
    JTextPane text2=new JTextPane();
    
    JButton bt1 = new JButton("打开文档1");
    JButton bt2 = new JButton("打开文档2");
    JButton bt3 = new JButton("核对");
   
    JPopupMenu jm = new JPopupMenu();//右键菜单
    JMenuItem copy = new JMenuItem("复制");//菜单项
    JMenuItem path = new JMenuItem("粘贴");  
    JMenuItem cut = new JMenuItem("剪切");
    JMenuItem help = new JMenuItem("帮助");
    JMenuItem about = new JMenuItem("关于");
    
    JScrollPane scro1=new JScrollPane(text1);//添加滚动条
    JScrollPane scro2=new JScrollPane(text2);//添加滚动条
    
    JSplitPane jSplitPane =new JSplitPane();//设定为拆分布局
    JSplitPane jSplitPane2 =new JSplitPane();//设定为拆分布局
    JSplitPane jSplitPane3 =new JSplitPane();//设定为拆分布局
    
    public Windowm()
    {
    	setVisible(true);
    	jm.add(copy);
        jm.add(path);
        jm.add(cut);
        jm.add(help);
        jm.add(about);
         
        myPanel3.add(bt1);
        myPanel3.add(bt2);
        myPanel4.add(bt3);
         
    	this.setTitle("欢迎使用文本比较软件");
    	this.setBounds(100, 100, 600, 500);
        jSplitPane.setContinuousLayout(true);//操作箭头，重绘图形
        jSplitPane2.setContinuousLayout(true);//操作箭头，重绘图形
        jSplitPane3.setContinuousLayout(true);//操作箭头，重绘图形
        
        jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);//垂直方向
        jSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//水平方向
        jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);//垂直方向
        
        myPanel1.setBorder(BorderFactory.createLineBorder(Color.green));
        myPanel2.setBorder(BorderFactory.createLineBorder(Color.red));
        myPanel3.setBorder(BorderFactory.createLineBorder(Color.yellow));
        myPanel4.setBorder(BorderFactory.createLineBorder(Color.blue));

        jSplitPane.setLeftComponent(scro1);//左右布局中添加组件 ，面板1
        jSplitPane.setRightComponent(scro2);//左右布局中添加组件 ，面板2

        jSplitPane2.setTopComponent(myPanel3);//上下布局中添加组件 ，面板1
        jSplitPane2.setBottomComponent(jSplitPane);//上下布局中添加组件 ，面板1
        
        jSplitPane3.setTopComponent(jSplitPane2);
        jSplitPane3.setBottomComponent(myPanel4);
        
        jSplitPane.setDividerSize(5);//设置分割线的宽度
        jSplitPane2.setDividerSize(5);//设置分割线的宽度
        jSplitPane3.setDividerSize(5);//设置分割线的宽度
        setContentPane(jSplitPane3);//设置为父模块
  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        copy.addActionListener(new ActionListener()//窗口监听
    		{
    			public void actionPerformed(ActionEvent e4)//菜单项
    			{
   					try{	
    					text1.copy();
    					text2.copy();
    				}catch(Exception e1){
    				}
    			}
   			}
    	);
        path.addActionListener(new ActionListener()//窗口监听
    		{
    			public void actionPerformed(ActionEvent e4)//菜单项
    			{
   					try{	
   						if(point==1)//由于有两个窗口，因此设计point来确定粘贴在某个窗口
   							text1.paste();
   						else
    						text2.paste();
    				}catch(Exception e1){
    				}
    			}
   			}
    	);
        cut.addActionListener(new ActionListener()//窗口监听
    		{
    			public void actionPerformed(ActionEvent e4)//菜单项
    			{
    				try{	
   						text1.cut();
   						text2.cut();
    				}catch(Exception e1){
    				}
    			}
    		}
    	);
        help.addActionListener(new ActionListener()//窗口监听
    		{
    			public void actionPerformed(ActionEvent e4)//菜单项
    			{
    				JOptionPane.showMessageDialog(null,"使用方法：输入或者点击打开两个文本，按核对键进行比较\n红色表示匹配失败，蓝色表示多余，黑色为正常匹配文本","使用指南",JOptionPane.PLAIN_MESSAGE); 			
    			}
    		}
    	);
        about.addActionListener(new ActionListener()//窗口监听
    		{
    			public void actionPerformed(ActionEvent e4)//菜单项
    			{
    				JOptionPane.showMessageDialog(null,"原创：将军\nQQ 2910001378@qq.com \n人生苦短，欢迎转载","将军原创",JOptionPane.PLAIN_MESSAGE); 			
    			}
    		}
    	);
        
        text1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {      
                    jm.show(text1, e.getX(), e.getY()); // 弹出菜单
                    point=1;
                }
            }
        });
        
        text2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {      
                    jm.show(text2, e.getX(), e.getY()); // 弹出菜单
                    point=2;
                }
            }
        });
        
        jSplitPane.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {  
            	jSplitPane.setDividerLocation(jSplitPane3.getWidth()/2-7);//设置第一条宽度  
            }  
        }); 
       jSplitPane2.setDividerLocation(50);//设定分割线的距离左边的位置
       jSplitPane3.addComponentListener(new ComponentAdapter() {//拖动窗口监听 
           public void componentResized(ComponentEvent e) {  
           	jSplitPane3.setDividerLocation(jSplitPane3.getHeight()-50);//设置第三条高度 
           }  
       }); 
       
	bt1.addActionListener(new ActionListener()//窗口监听
		{
			public void actionPerformed(ActionEvent e4)//菜单项
			{
				try{	
					text1.setText("");
				    JFileChooser jfc=new JFileChooser();
				    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
				    jfc.showDialog(new JLabel(), "选择");
				    File file=jfc.getSelectedFile();
				    path1=file.getAbsolutePath();//获取文件绝对地址	
				    new Read_File(path1);
				    File1= Read_File.getFile();
				    SimpleAttributeSet attrset = new SimpleAttributeSet();
				    StyleConstants.setFontSize(attrset,16);//设置字号
			        Document docs=text1.getDocument();
			        docs.insertString(docs.getLength(), File1, attrset);
				}catch(Exception e1){
				}
			}
		}
	);
	bt2.addActionListener(new ActionListener()//窗口监听
		{
			public void actionPerformed(ActionEvent e4)//菜单项
			{
				try{	
					text2.setText("");
				    JFileChooser jfc=new JFileChooser();
				    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
				    jfc.showDialog(new JLabel(), "选择");
				    File file=jfc.getSelectedFile();
				    path2=file.getAbsolutePath();//获取文件绝对地址		
				    new Read_File(path2);
					File2= Read_File.getFile();
					SimpleAttributeSet attrset = new SimpleAttributeSet();
					StyleConstants.setFontSize(attrset,16);//设置字号
			        Document docs=text2.getDocument();
			        docs.insertString(docs.getLength(), File2, attrset);
				}catch(Exception e1){
					System.out.println("选择文件出错");
				}
			}
		}
	);
	bt3.addActionListener(new ActionListener()//窗口监听
		{
			public void actionPerformed(ActionEvent e4)//菜单项
			{
				try{
					String dnas1;//算法处理之后的字符串1
					String dnas2;//算法处理之后的字符串2
					String jtp1;//JTextpane的内容1
					String jtp2;//JTextpane的内容2
					int len=0;	//处理后的字符串长度
								
					jtp1=text1.getText();//获取窗口文本
					jtp2=text2.getText();
					text1.setText("");//清空之前内容
					text2.setText("");
			        Document docs1=text1.getDocument();
			        Document docs2=text2.getDocument();        					
					DNASequence dna=new DNASequence(jtp1,jtp2);//通过构造方法传递参数
					dna.runAnalysis();
					dna.traceback();
					dnas1=dna.getString1();//获取处理后的字符串
					dnas2=dna.getString2();
					char[] s = dnas1.toCharArray();//字符串转Char数组
					char[] p = dnas2.toCharArray();
					len=dnas1.length();
					SimpleAttributeSet set2 = new SimpleAttributeSet();//设置一个属性
					StyleConstants.setFontSize(set2,16);//设置字号
					for(int i=0;i<len;i++){
						if(s[i]=='-'){
							StyleConstants.setForeground(set2,Color.BLUE);//设置文字颜色
						    docs2.insertString(docs2.getLength(),String.valueOf(p[i]), set2);
						}else if(p[i]=='-'){
							StyleConstants.setForeground(set2,Color.BLUE);//设置文字颜色
						    docs1.insertString(docs1.getLength(),String.valueOf(s[i]), set2);
						}else if(s[i]==p[i]){
							StyleConstants.setForeground(set2,Color.black);//设置文字颜色
						    docs1.insertString(docs1.getLength(),String.valueOf(s[i]), set2);
						    docs2.insertString(docs2.getLength(),String.valueOf(p[i]), set2);
						}else if(s[i]!=p[i]){								StyleConstants.setForeground(set2,Color.red);//设置文字颜色
							docs1.insertString(docs1.getLength(),String.valueOf(s[i]), set2);
						    docs2.insertString(docs2.getLength(),String.valueOf(p[i]), set2);
						}else{
							System.out.print("考虑更多颜色");
						}
					}
					
				}catch(Exception e1){		
					System.out.println("选择文件2出错");
				}
			}
		}
	);     
    }
}

