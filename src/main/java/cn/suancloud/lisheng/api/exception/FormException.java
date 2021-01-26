package cn.suancloud.lisheng.api.exception;

/**
 * Created by admin on 2018/4/26.
 * 表单异常.
 */
public class FormException extends RuntimeException {
  public FormException(String message) {
    super(message);
  }

  public FormException() {
  }
}
