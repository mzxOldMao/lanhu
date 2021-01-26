package cn.suancloud.lisheng.api.controller;

import cn.suancloud.lisheng.api.exception.BusinessException;
import cn.suancloud.lisheng.api.exception.FormException;
import cn.suancloud.lisheng.api.exception.IdExistsException;
import cn.suancloud.lisheng.api.exception.IdNotExistsException;
import cn.suancloud.lisheng.api.utils.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * Created by snail on 2019/05/19.
 */
public class BaseController {

  @Autowired
  ApplicationEventPublisher eventPublisher;

  @InitBinder
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
    binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
    binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
    binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
  }


  
  protected String getPath(HttpServletRequest request) {
    return (String) request.getAttribute("path");
  }

  protected void hasErrors(BindingResult result) {
    if (result.hasErrors()) {
      String error = Arrays.toString(Extractor.extractErrorMsg(result));
      throw new FormException(error.substring(1,error.length()-1));
    }
  }



  @ExceptionHandler(SQLException.class)
  public ResponseEntity sqlExceptionHandler(SQLException e) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
  }

  @ExceptionHandler(
          {
                  FormException.class,
                  HttpMessageNotReadableException.class,
                  BusinessException.class,
                  IllegalArgumentException.class
          }
  )
  public ResponseEntity formExceptionHandler(Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }


}
