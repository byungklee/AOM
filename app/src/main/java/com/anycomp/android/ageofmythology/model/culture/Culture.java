package com.anycomp.android.ageofmythology.model.culture;

public class Culture {
	private int imagePath;
	private String name;
	public Culture() {}
    private int permanentAttackCardImage;
    private int permanentBuildCardImage;
    private int permanentExploreCardImage;
    private int permanentGatherCardImage;
    private int permanentNextCardImage;
    private int permanentRecruitCardImage;
    private int permanentTradeCardImage;



	public void setImagePath(int imagePath) {
		this.imagePath = imagePath;
	}
	public int getImagePath() {
		return imagePath;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

    public int getPermanentAttackCardImage() {
        return permanentAttackCardImage;
    }

    protected void setPermanentAttackCardImage(int permanentAttackCardImage) {
        this.permanentAttackCardImage = permanentAttackCardImage;
    }

    public int getPermanentBuildCardImage() {
        return permanentBuildCardImage;
    }

    protected void setPermanentBuildCardImage(int permanentBuildCardImage) {
        this.permanentBuildCardImage = permanentBuildCardImage;
    }

    public int getPermanentExploreCardImage() {
        return permanentExploreCardImage;
    }

    protected void setPermanentExploreCardImage(int permanentExploreCardImage) {
        this.permanentExploreCardImage = permanentExploreCardImage;
    }

    public int getPermanentGatherCardImage() {
        return permanentGatherCardImage;
    }

    protected void setPermanentGatherCardImage(int permanentGatherCardImage) {
        this.permanentGatherCardImage = permanentGatherCardImage;
    }

    public int getPermanentNextCardImage() {
        return permanentNextCardImage;
    }

    protected void setPermanentNextCardImage(int permanentNextCardImage) {
        this.permanentNextCardImage = permanentNextCardImage;
    }

    public int getPermanentRecruitCardImage() {
        return permanentRecruitCardImage;
    }

    protected void setPermanentRecruitCardImage(int permanentRecruitCardImage) {
        this.permanentRecruitCardImage = permanentRecruitCardImage;
    }

    public int getPermanentTradeCardImage() {
        return permanentTradeCardImage;
    }

    protected void setPermanentTradeCardImage(int permanentTradeCardImage) {
        this.permanentTradeCardImage = permanentTradeCardImage;
    }
}
