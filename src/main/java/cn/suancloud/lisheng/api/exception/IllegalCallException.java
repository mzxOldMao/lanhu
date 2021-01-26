package cn.suancloud.lisheng.api.exception;

/**
 * Created by teruo on 2018/4/26.
 * 非法调用异常.
 */
public class IllegalCallException extends RuntimeException {
  public IllegalCallException() {
  }

  public IllegalCallException(String message) {
    super(message);
  }
}
