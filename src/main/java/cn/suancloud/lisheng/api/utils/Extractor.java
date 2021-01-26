package cn.suancloud.lisheng.api.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by teruo on 2018/1/31.
 * 提取器.
 */
public class Extractor {
  private Extractor() {
  }

  public static String[] extractErrorMsg(BindingResult result) {
    List<ObjectError> errors = result.getAllErrors();
    String[] errorMsg = new String[errors.size()];
    for (int i = 0, len = errors.size(); i < len; i++) {
      errorMsg[i] = errors.get(i).getDefaultMessage();
    }
    return errorMsg;
  }
}
