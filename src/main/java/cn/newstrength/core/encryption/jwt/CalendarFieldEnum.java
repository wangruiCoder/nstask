package cn.newstrength.core.encryption.jwt;

import java.util.Calendar;

/**
 * 定义 jwt token 有效时间可以支持的单位
 * @author 王瑞
 *
 */
public enum CalendarFieldEnum {
    /**
     * 默认分钟单位 1<= calendarInterval <=1440
     */
    DEFAULT(Calendar.MINUTE,1,1440),
    /**
     * 分钟单位 1<= calendarInterval <=1440
     */
    MINUTE(Calendar.MINUTE,1,1440),
    /**
     * 小时单位 1<= calendarInterval <=48
     */
    HOUR(Calendar.HOUR,1,48),
    /**
     * 天单位 1<= calendarInterval <=30
     */
    DATE(Calendar.DATE,1,30);

    private int calendarField;
    private int minValue;
    private int maxValue;

    CalendarFieldEnum(int calendarField, int minValue, int maxValue){
        this.calendarField = calendarField;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getCalendarField() {
        return calendarField;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
