package cn.suancloud.lisheng.api.exception;

/**
 * Created by admin on 2018/4/26.
 * id不存在异常.
 */
public class IdNotExistsException extends RuntimeException {
  public IdNotExistsException(String message) {
    super(message);
  }

  public IdNotExistsException() {
  }
}
