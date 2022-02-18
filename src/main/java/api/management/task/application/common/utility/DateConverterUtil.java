package api.management.task.application.common.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 日付関連情報の変換用共通クラス(DI上での管理はしない)
 */
public class DateConverterUtil {

    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String EMPTY_STR = "";

    /**
     * LocalDateからISO形式の文字列に変換する
     *
     * @param date 日付情報
     * @return yyyy-MM-dd形式の文字列 nullが引数にわたってきた場合は空文字を返却
     */
    public static String isoDate2Str(LocalDate date) {
        if (ObjectUtils.isEmpty(date)) {
            return EMPTY_STR;
        }
        return date.format(ISO_DATE_FORMATTER);
    }
}
