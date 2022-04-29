package board;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ArticleVO {
	private String art_ID;
	private int art_No;
	private String title;
	private String content;
	private String category;
	private String image;
	private Date writeDate;
	
	public ArticleVO() {
		
	}
	public ArticleVO(String art_ID, int art_No, String title, 
			String content,String category, String image, Date writeDate) {
		super();
		this.art_ID = art_ID;
		this.art_No = art_No;
		this.title = title;
		this.content = content;
		this.category = category;
		this.image = image;
		this.writeDate = writeDate;
	}

	public String getArt_ID() {
		return art_ID;
	}

	public void setArt_ID(String art_ID) {
		this.art_ID = art_ID;
	}

	public int getArt_No() {
		return art_No;
	}

	public void setArt_No(int art_No) {
		this.art_No = art_No;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		try {
			if (image != null && image.length() != 0) {
				image = URLDecoder.decode(image, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(String image) {
		try {
			if(image !=null && image.length()!=0) {
				this.image = URLEncoder.encode(image, "UTF-8");  //파일이름에 특수문자가 있을 경우 인코딩합니다.
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
}
