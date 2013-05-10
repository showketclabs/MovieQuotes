package awesome.app.moviequotes;

public class Picture {
	private String picName;
	private String picType;
	private String thirdline;
	private int picDrawable;
	
	public Picture(String picName,String picType,String thirdline)
	{ 
		 this.picName=picName;
	     this.picType=picType;
	     this.thirdline=thirdline;
//	     this.picDrawable=picDrawable;
		
	}
	
	public String getPicName()
	{
		return this.picName;
	}
    public String getPicType()
    {
    	return this.picType;
    }
    public String getThirdLine()
    {
    	return this.thirdline;
    }
//    public int getPicSource()
//    {
//    	return this.picDrawable;
//    }
}
