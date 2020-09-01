package com.wl.many_steps.handler;

import com.wl.many_steps.exception.JsonException;
import com.wl.many_steps.exception.PageException;
import com.wl.many_steps.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/6 11:12
 * desc   : 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 统一 json 异常处理
     *
     * @param exception JsonException
     * @return 统一返回 json 格式
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(Exception exception) {
        log.error("【Exception】:{}", exception.getMessage());
        return ApiResponse.of(999, exception.getMessage(), null);
    }
    /**
     * 统一 json 异常处理
     *
     * @param exception JsonException
     * @return 统一返回 json 格式
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException exception) {
        log.error("【JsonException】:{}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }

    /**
     * 统一 页面 异常处理
     *
     * @param exception PageException
     * @return 统一跳转到异常页面
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException exception) {
        log.error("【DemoPageException】:{}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

    /**
     * 校验单个参数
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handle(ConstraintViolationException exception) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            if (!violations.isEmpty()){
                StringBuilder msgBuilder = new StringBuilder();
                for (ConstraintViolation<?> item : violations) {
                    //打印验证不通过的信息
//                    System.out.println(item.getMessage());
                    msgBuilder.append(item.getMessage()).append(",");
                }
                String errorMessage = msgBuilder.toString();
                if (errorMessage.length() > 1) {
                    errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
                }
                log.error("【ConstraintViolationException】:{}", errorMessage);
                return ApiResponse.of(999, errorMessage, null);
            }
//        System.out.println("【ConstraintViolationException】" + exception.getMessage());
        return ApiResponse.of(999, exception.getMessage(), null);
    }

    /**
     * 校验jsonBean 组合参数
     * @param ex
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handle(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            log.error("【MethodArgumentNotValidException】:{}", errorMessage);
            return ApiResponse.of(999, errorMessage, null);
        }
//        System.out.println("【MethodArgumentNotValidException】" + ex.getMessage());
        return ApiResponse.of(999, ex.getMessage(), null);
    }


}
