package com.ltsh.wx.web.req;

import com.ltsh.wx.web.common.req.AppContext;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Random on 2017/11/23.
 */
@Data
public class PageReq extends AppContext {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
