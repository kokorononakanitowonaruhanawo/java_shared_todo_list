package settings;

/**
 * メッセージ設定クラス
 */
public class MessageSettings {

	/** すでに登録されているので使用できません。 */
	public static final String MSG_ER_DUP_KEYNAME = "すでに登録されているので使用できません。";

	/** 申し訳ございません。エラーが発生しました。 */
	public static final String MSG_ERROR_OCCURRED = "申し訳ございません。エラーが発生しました。";

	/** 不正な処理が行われました */
	public static final String MSG_INVALID_PROCESS = "不正な処理が行われました。";

	/** E-mailアドレス、または、パスワードが間違っています。 */
	public static final String MSG_LOGIN_FAILURE = "E-mailアドレス、または、パスワードが間違っています。";

	/** 正しいE−mailアドレスを入力してください。 */
	public static final String MSG_EMAIL_FAILURE = "正しいE−mailアドレスを入力してください。";

	/** パスワードは、半角英字大文字小文字と半角英数字を取り混ぜて、8文字以上20文字以内で入力してください。 */
	public static final String MSG_PASSWORD_FAILURE = "パスワードは、半角英字大文字小文字と半角英数字を取り混ぜて、8文字以上20文字以内で入力してください。";

	/** パスワードが一致しません	 */
	public static final String MSG_PASSWORD_NOT_CONSENSUS = "パスワードが一致しません";
	
	/** %sは%d文字以上で入力してください。*/
	public static final String MSG_LENGTH_SHORT = "%sは%d文字以上で入力してください。";

	/** %sは%d文字以下で入力してください。*/
	public static final String MSG_LENGTH_LONG = "50文字以下で入力してください。";

	/** %sは必ず入力してください。 */
	public static final String MSG_REQUIRED = "必ず入力してください。";

	/** 形式が正しくありません。*/
	public static final String MSG_INVALID_FORMAT = "形式が正しくありません。";
	
	/** 形式が正しいです */
	public static final String MSG_VALID = "形式が正しいです";

	/** %sが正しくありません。 */
	public static final String MSG_INVALID_VALUE = "%sが正しくありません。";
	
	public static final int EXECUTION_SUCCESS = 1;

	public static final int EXECUTION_FAILURE = 0;
	
	/** 作業項目はありません */
	public static final String MSG_NO_TODOITEMS = "作業項目はありません";
	
	/** 作業項目はありません */
	public static final String MSG_NO_SEARCH = "該当する作業項目は見つかりませんでした";
}
