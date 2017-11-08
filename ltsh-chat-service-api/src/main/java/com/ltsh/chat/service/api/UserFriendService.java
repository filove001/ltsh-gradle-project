package com.ltsh.chat.service.api;

import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.FriendQueryResp;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;

/**
 * 好友api
 * Created by Random on 2017/10/23.
 */
public interface UserFriendService {
    public Result<PageResult<FriendQueryResp>> page(PageReq req);

}
