package models.validators;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ReportValidator {

    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ReportView rv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(rv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        //出勤のチェック
        String start_timeError = validateStart_time(rv.getStart_time());
        if (!start_timeError.equals("")) {
            errors.add(start_timeError);
        }
        //退勤のチェック
        String end_timeError = validateEnd_time(rv.getEnd_time());
        if (!end_timeError.equals("")) {
            errors.add(end_timeError);
        }
        //出退勤時間を逆に書いた時にエラー
     // もし、出退勤時間が入力されていれば
        if(start_timeError.equals("") && end_timeError.equals("")){
        String timeorderError = validateTimeOrder(rv.getStart_time(), rv.getEnd_time());
        if (!timeorderError.equals("")) {
            errors.add(timeorderError);
        }
        }
        return errors;
    }



    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 出勤時間に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param start_time 出勤時間
     * @return エラーメッセージ
     */
    private static String validateStart_time(String start_time) {
        if (start_time == null || start_time.equals("")) {
            return MessageConst.E_NOSTART.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 退勤時間に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param end_time 退勤時間
     * @return エラーメッセージ
     */
    private static String validateEnd_time(String end_time) {
        if (end_time == null || end_time.equals("")) {
            return MessageConst.E_NOEND.getMessage();
        }

        return "";
    }

    // 出勤時間が入力されているか判定するメソッド
    // 出勤時間と退勤時間の順番が正しいか判定するメソッド
    private static String validateTimeOrder(String start_time, String end_time) {
        String[] start_times = start_time.split(":");
        LocalTime start_time_local = LocalTime.of(Integer.parseInt(start_times[0]), Integer.parseInt(start_times[1]));
        String[] end_times = end_time.split(":");
        LocalTime end_time_local = LocalTime.of(Integer.parseInt(end_times[0]), Integer.parseInt(end_times[1]));
        boolean d1 = start_time_local.isBefore(end_time_local);
        System.out.println(d1);
        if(d1 == false){
            return "出勤時刻と退勤時刻が逆に入力されています";
        }
        return "";
    }

}
