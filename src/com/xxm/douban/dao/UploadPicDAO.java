package com.xxm.douban.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import com.xxm.douban.entity.Article1;

public class UploadPicDAO {

	// 把图片存到项目的WebContent的images中
	public Article1 uploadPic(Article1 article) {

		FileOutputStream fos = null;
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = null;

		String email = article.getUser_email();
		String picUrl;// 每张图片的路径
		StringBuilder picUrls = new StringBuilder();// 每张图片的路径加起来，用逗号隔开
		String[] picsData = article.getPicture_urls().split(",");

		for (int i = 0; i < picsData.length; i++) {
			picUrl = "D:/javacode/Douban/WebContent/images/" + new Date().getTime() + email.substring(0, email.lastIndexOf("@"))
					+ ".jpg";
			picUrls.append(picUrl + ",");

			// 解码
			bytes = decoder.decode(picsData[i]);
			//存储
			try	{
				fos = new FileOutputStream(picUrl);
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
		
		article.setPicture_urls(picUrls + "");
		return article;

	}

}
