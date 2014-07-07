package cn.v5cn.v5cms.dao.impl;

import cn.v5cn.v5cms.dao.SiteDao;
import cn.v5cn.v5cms.entity.Site;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZYW on 2014/6/30.
 */
@Repository("siteDao")
public class SiteDaoImpl implements SiteDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public ImmutableList<Site> findSite(int siteId) {
        List<Site> temp = sqlSession.selectList(Site.class.getName()+".selectSite",siteId);
        ImmutableList<Site> result = ImmutableList.<Site>builder().addAll(temp).build();
        return result;
    }

    @Override
    public ImmutableList<Site> findRunableSite(int isclosesite) {
        List<Site> temp = sqlSession.selectList(Site.class.getName()+".selectRunableSite",isclosesite);
        ImmutableList<Site> result = ImmutableList.<Site>builder().addAll(temp).build();
        return result;
    }

    @Override
    public int addSite(Site site) {
        int result =  sqlSession.insert(Site.class.getName() + ".addSite",site);

        if(result > 0){
            return site.getTbId();
        }
        return 0;
    }

    @Override
    public int updateSite(Site site) {
        return sqlSession.update(Site.class.getName() + ".updateSite",site);
    }

    @Override
    public int deleteSite(int siteId) {
        return sqlSession.update(Site.class.getName() + ".deleteSite",siteId);
    }
}
