package com.hn.multitenancy.share.request.query;

import lombok.Data;

import java.util.List;

@Data
public class UserQuery extends BaseQuery {
    private static final long serialVersionUID = -3508389226104879668L;
    private String userNameKeywords;
    private List<Long> userIdList;
}
