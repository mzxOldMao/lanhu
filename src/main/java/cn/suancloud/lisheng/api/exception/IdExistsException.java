package cn.suancloud.lisheng.api.exception;

/**
 * Created by admin on 2018/4/26.
 * id存在异常.
 */
public class IdExistsException extends RuntimeException {
  public IdExistsException(String message) {
    super(message);
  }

  public IdExistsException() {
  }
}
