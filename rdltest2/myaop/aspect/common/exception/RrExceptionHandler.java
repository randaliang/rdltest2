package aspect.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import aspect.common.model.Code;
import aspect.common.model.Response;
import aspect.common.model.Result;

import  org.springframework.validation.BindException;



@RestControllerAdvice
public class RrExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(AccountException.class)
	public Result handleRRException(AccountException e){
		Result r = new Result();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Result.error("数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		logger.error(e.getMessage(), e);
		return Result.error();
	}
	
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Response<String> bindExceptionErrorHandler(BindException ex) throws Exception {
         logger.error("bindExceptionErrorHandler info:{}",ex.getMessage());
         Response<String> r = new Response<>();
         StringBuilder sb = new StringBuilder();
         FieldError fieldError = ex.getFieldError();
         sb.append(fieldError.getDefaultMessage());
         r.setMsg(sb.toString());
         r.setCode(Code.FAILED);
         return r;
    }


   @ExceptionHandler(value = MyException.class)
   @ResponseBody
   public Response<String> myExceptionErrorHandler(MyException ex) throws Exception {
       logger.error("myExceptionErrorHandler info:{}",ex.getMessage());
       Response<String> r = new Response<>();
       r.setMsg(ex.getMsg());
       r.setCode(ex.getCode());
       return r;
   }
   
}
