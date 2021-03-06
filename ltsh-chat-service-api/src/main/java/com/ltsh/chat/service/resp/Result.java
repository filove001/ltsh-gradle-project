package com.ltsh.chat.service.resp;

import com.ltsh.chat.service.enums.ResultCodeEnum;
import lombok.Data;
import com.ltsh.common.entity.BaseResult;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class Result<T> extends BaseResult<T> {
    public Result(){
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }
    public Result(String code, String message) {
        super(code, message);
    }
    public Result(ResultCodeEnum resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }
    public Result(ResultCodeEnum resultCode, String message) {
        super(resultCode.getCode(), String.format(resultCode.getMessage(), message));
    }
    public Result(T content) {
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), content);
    }

}
