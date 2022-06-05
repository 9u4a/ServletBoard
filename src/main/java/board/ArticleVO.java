package board;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ArticleVO {
	private int level;
	private String art_ID;
	private int art_No;
	private int par_No;
	private String title;
	private String content;
	private String image;
	private Date writeDate;
	
	public ArticleVO() {
		
	}
	public ArticleVO(int level, String art_ID, int art_No, int par_No, String title, 
			String content, String image, Date writeDate) {
		super();
		this.level = level;
		this.art_ID = art_ID;
		this.art_No = art_No;
		this.par_No = par_No;
		this.title = title;
		this.content = content;
		this.image = image;
		this.writeDate = writeDate;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
	
	public int getPar_No() {
		return par_No;
	}

	public void setPar_No(int par_No) {
		this.par_No = par_No;
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

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
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
			if(image!=null && image.length()!=0) {
				this.image = URLEncoder.encode(image, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
