package com.jaemin.android.remote_retrofit;

/**
 * Created by Jaemin on 2016. 10. 26..
 */

public class RemoteData
{
    private CardSubwayStatisticsService CardSubwayStatisticsService;

    public CardSubwayStatisticsService getCardSubwayStatisticsService ()
    {
        return CardSubwayStatisticsService;
    }

    public void setCardSubwayStatisticsService (CardSubwayStatisticsService CardSubwayStatisticsService)
    {
        this.CardSubwayStatisticsService = CardSubwayStatisticsService;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CardSubwayStatisticsService = "+CardSubwayStatisticsService+"]";
    }
}

class CardSubwayStatisticsService
{
    private RESULT RESULT;

    private String list_total_count;

    private Row[] row;

    public RESULT getRESULT ()
    {
        return RESULT;
    }

    public void setRESULT (RESULT RESULT)
    {
        this.RESULT = RESULT;
    }

    public String getList_total_count ()
    {
        return list_total_count;
    }

    public void setList_total_count (String list_total_count)
    {
        this.list_total_count = list_total_count;
    }

    public Row[] getRow ()
    {
        return row;
    }

    public void setRow (Row[] row)
    {
        this.row = row;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
    }
}

class Row
{
    private String COMMT;

    private String SUB_STA_NM;

    private String WORK_DT;

    private String RIDE_PASGR_NUM;

    private String LINE_NUM;

    private String USE_MON;

    private String ALIGHT_PASGR_NUM;

    public String getCOMMT ()
    {
        return COMMT;
    }

    public void setCOMMT (String COMMT)
    {
        this.COMMT = COMMT;
    }

    public String getSUB_STA_NM ()
    {
        return SUB_STA_NM;
    }

    public void setSUB_STA_NM (String SUB_STA_NM)
    {
        this.SUB_STA_NM = SUB_STA_NM;
    }

    public String getWORK_DT ()
    {
        return WORK_DT;
    }

    public void setWORK_DT (String WORK_DT)
    {
        this.WORK_DT = WORK_DT;
    }

    public String getRIDE_PASGR_NUM ()
    {
        return RIDE_PASGR_NUM;
    }

    public void setRIDE_PASGR_NUM (String RIDE_PASGR_NUM)
    {
        this.RIDE_PASGR_NUM = RIDE_PASGR_NUM;
    }

    public String getLINE_NUM ()
    {
        return LINE_NUM;
    }

    public void setLINE_NUM (String LINE_NUM)
    {
        this.LINE_NUM = LINE_NUM;
    }

    public String getUSE_MON ()
    {
        return USE_MON;
    }

    public void setUSE_MON (String USE_MON)
    {
        this.USE_MON = USE_MON;
    }

    public String getALIGHT_PASGR_NUM ()
    {
        return ALIGHT_PASGR_NUM;
    }

    public void setALIGHT_PASGR_NUM (String ALIGHT_PASGR_NUM)
    {
        this.ALIGHT_PASGR_NUM = ALIGHT_PASGR_NUM;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [COMMT = "+COMMT+", SUB_STA_NM = "+SUB_STA_NM+", WORK_DT = "+WORK_DT+", RIDE_PASGR_NUM = "+RIDE_PASGR_NUM+", LINE_NUM = "+LINE_NUM+", USE_MON = "+USE_MON+", ALIGHT_PASGR_NUM = "+ALIGHT_PASGR_NUM+"]";
    }
}

class RESULT
{
    private String MESSAGE;

    private String CODE;

    public String getMESSAGE ()
    {
        return MESSAGE;
    }

    public void setMESSAGE (String MESSAGE)
    {
        this.MESSAGE = MESSAGE;
    }

    public String getCODE ()
    {
        return CODE;
    }

    public void setCODE (String CODE)
    {
        this.CODE = CODE;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
    }
}