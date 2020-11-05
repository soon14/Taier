package com.dtstack.lineage.impl;

import com.dtstack.engine.api.domain.LineageTableTable;
import com.dtstack.engine.api.domain.LineageTableTableUniqueKeyRef;
import com.dtstack.engine.common.util.MD5Util;
import com.dtstack.lineage.dao.LineageTableTableUniqueKeyDao;
import com.dtstack.lineage.dao.LineageTableTableDao;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chener
 * @Classname LineageTableTableService
 * @Description TODO
 * @Date 2020/10/29 15:57
 * @Created chener@dtstack.com
 */
@Service
public class LineageTableTableService {

    /**
     * 表级血缘唯一码规则inputTableId_resultTable_id
     */
    private static final String TABLE_TABLE_KEY_TMP = "%s_%s";

    @Autowired
    private LineageTableTableDao lineageTableTableDao;

    @Autowired
    private LineageTableTableUniqueKeyDao lineageTableTableUniqueKeyDao;

    /**
     * 保存表级血缘
     */
    public void saveTableLineage(List<LineageTableTable> tableTables) {
        if (CollectionUtils.isEmpty(tableTables)){
            return;
        }
        tableTables.forEach(tt->{tt.setTableLineageKey(generateTableTableKey(tt));});
        //数据插入后，id会更新
        lineageTableTableDao.batchInsertTableTable(tableTables);
        //如果uniqueKey不为空，需要删除ref表中相同uniqueKey的数据，再插入该批数据。
        if (StringUtils.isNotEmpty(tableTables.get(0).getUniqueKey())){
            lineageTableTableUniqueKeyDao.deleteByUniqueKey(tableTables.get(0).getAppType(),tableTables.get(0).getUniqueKey());
        }
        //插入新的ref
        List<LineageTableTableUniqueKeyRef> refList = tableTables.stream().map(tt -> {
            LineageTableTableUniqueKeyRef ref = new LineageTableTableUniqueKeyRef();
            ref.setAppType(tt.getAppType());
            ref.setLineageTableTableId(tt.getId());
            return ref;
        }).collect(Collectors.toList());
        lineageTableTableUniqueKeyDao.batchInsert(refList);
    }

    /**
     * 根据表和应用类型查询表级血缘上游
     */
    public List<LineageTableTable> queryTableInputLineageByAppType(Long tableId, Integer appType) {
        List<LineageTableTable> res = Lists.newArrayList();
        List<LineageTableTable> lineageTableTables = lineageTableTableDao.queryTableResultList(appType, tableId);
        res.addAll(lineageTableTables);
        if (CollectionUtils.isNotEmpty(lineageTableTables)){
            for (LineageTableTable tt:lineageTableTables){
                //TODO 未处理死循环
                List<LineageTableTable> parentList = queryTableInputLineageByAppType(tt.getInputTableId(), appType);
                res.addAll(parentList);
            }
        }
        return res;
    }

    /**
     * 根据表和应用类型查询表级血缘下游
     *
     * @param tableId
     * @param appType
     */
    public List<LineageTableTable> queryTableResultLineageByAppType(Long tableId, Integer appType) {
        List<LineageTableTable> res = Lists.newArrayList();
        List<LineageTableTable> lineageTableTables = lineageTableTableDao.queryTableInputList(appType, tableId);
        res.addAll(lineageTableTables);
        if (CollectionUtils.isNotEmpty(lineageTableTables)){
            for (LineageTableTable tt:lineageTableTables){
                //TODO 未处理死循环
                List<LineageTableTable> parentList = queryTableResultLineageByAppType(tt.getResultTableId(), appType);
                res.addAll(parentList);
            }
        }
        return res;
    }

    /**
     * 查询表血缘关系(全应用)
     */
    public void selectTableTableByTable() {
        //TODO
    }

    /**
     * 计算tableTableKey。逻辑表唯一。
     * 计算规则：
     *
     * @param tableTable
     * @return
     */
    public String generateTableTableKey(LineageTableTable tableTable) {
        return String.format(TABLE_TABLE_KEY_TMP, tableTable.getInputTableId(), tableTable.getResultTableId());
    }
}
