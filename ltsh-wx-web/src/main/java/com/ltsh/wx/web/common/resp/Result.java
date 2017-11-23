package com.ltsh.wx.web.common.resp;

import com.ltsh.wx.web.common.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/11/23.
 */
@Data
public class Result<T> implements Serializable{
    private String code;
    private String message;
    private T content;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T content) {
        this.code = ResultCodeEnum.SUCCEED.getCode();
        this.message = ResultCodeEnum.SUCCEED.getMessage();
        this.content = content;
    }
    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public Result(ResultCodeEnum resultCodeEnum, String ... params) {
        this.code = resultCodeEnum.getCode();
        this.message = String.format(resultCodeEnum.getMessage(), params);

    }
    public Result(ResultCodeEnum resultCodeEnum, T content) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.content = content;
    }
    public Result(String code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public boolean isSucceed() {
        if(this.code.equals(ResultCodeEnum.SUCCEED.getCode()) &&
                this.content != null) {
            return true;
        }
        return false;
    }
}
