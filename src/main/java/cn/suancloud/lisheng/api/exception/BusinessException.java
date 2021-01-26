package cn.suancloud.lisheng.api.exception;

/**
 * Created by admin on 2018/4/26.
 * 业务异常.
 */
public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }

  public BusinessException() {
  }
}
