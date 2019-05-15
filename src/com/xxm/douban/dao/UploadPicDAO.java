package com.xxm.douban.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;

/**
 * 用io流处理图片，存进本地文件夹和删除
 */
public class UploadPicDAO {

	// 把图片存到项目的WebContent的images中
	public String uploadPic(String picture_urls, String email) {

		FileOutputStream fos = null;
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = null;

		String picUrl;// 每张图片的路径
		StringBuilder picUrls = new StringBuilder();// 每张图片的路径加起来，用逗号隔开
		String[] picsData = picture_urls.split(",");

		for (int i = 0; i < picsData.length; i++) {
			//为每张图片生成路径
			picUrl = new Date().getTime() + email.substring(0, email.lastIndexOf("@"))
					+ ".jpg";
			picUrls.append("/file/" + picUrl + ",");

			// 解码
			bytes = decoder.decode(picsData[i]);
			//存储
			try	{
				fos = new FileOutputStream("D:/javacode/Douban/WebContent/images/" + picUrl);
				fos.write(bytes);
			} catch (IOException e)	{
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}		
		return picUrls + "";
	}
	
	//删除图片
	public Msg deletePic (String pic_urls) {
		String[] pics = pic_urls.split(",");
		String pic =null;
		File file = null;
		for (int i = 0; i < pics.length; i++) {
			pic = pics[i].split("/")[2];
			file = new File("D:/javacode/Douban/WebContent/images/" + pic);
			
			if (file.exists()) {
				file.delete();
			}
		}		
		return new Msg("删除图片成功", null);
	}

}
