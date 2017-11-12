package net.ourteam.service;

import net.ourteam.dao.MemberAuthLogDataAccess;
import net.ourteam.framework.service.AbstractDataAccessProxyService;
import net.ourteam.mapping.MemberAuthLog;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhizoo on 2016/6/27.
 */
@Service
@Transactional(rollbackFor = java.lang.Exception.class )
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemberAuthLogService extends AbstractDataAccessProxyService<MemberAuthLog, Long, MemberAuthLogDataAccess<?>> {

    public MemberAuthLogService() {
        super();
    }

    @Override
    public MemberAuthLogDataAccess getDataAccess() {
        return getDataAccess(MemberAuthLogDataAccess.class);
    }

    public List<MemberAuthLog> getListByMemberId(Long memberId){
        return (List<MemberAuthLog>)getDataAccess().getListByMemberId(memberId);
    }

    public MemberAuthLog  createMemberAuthLog(MemberAuthLog memberAuthLog){
        memberAuthLog = (MemberAuthLog)getDataAccess().saveOrModifyOne(memberAuthLog);
        return memberAuthLog;
    }

}
