package com.jaemin.android.remote_retrofitwithokhttp;

/**
 * Created by Jaemin on 2016. 11. 3..
 */

public class BoxOfficeResult
{
    private String showRange;

    private DailyBoxOfficeList[] dailyBoxOfficeList;

    private String boxofficeType;

    public String getShowRange ()
    {
        return showRange;
    }

    public void setShowRange (String showRange)
    {
        this.showRange = showRange;
    }

    public DailyBoxOfficeList[] getDailyBoxOfficeList ()
    {
        return dailyBoxOfficeList;
    }

    public void setDailyBoxOfficeList (DailyBoxOfficeList[] dailyBoxOfficeList)
    {
        this.dailyBoxOfficeList = dailyBoxOfficeList;
    }

    public String getBoxofficeType ()
    {
        return boxofficeType;
    }

    public void setBoxofficeType (String boxofficeType)
    {
        this.boxofficeType = boxofficeType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [showRange = "+showRange+", dailyBoxOfficeList = "+dailyBoxOfficeList+", boxofficeType = "+boxofficeType+"]";
    }
}
