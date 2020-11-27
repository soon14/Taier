package com.dtstack.engine.master.utils;

import com.dtstack.engine.api.domain.*;
import com.dtstack.engine.common.enums.EJobType;
import com.dtstack.engine.common.util.DateUtil;
import com.dtstack.engine.master.enums.EComponentType;
import com.dtstack.engine.master.enums.MultiEngineType;
import com.dtstack.schedule.common.enums.AppType;

import java.sql.Timestamp;

public class Template {
    public static ScheduleJob getScheduleJobTemplate() {
        ScheduleJob sj = new ScheduleJob();
        sj.setStatus(5);
        sj.setJobId("testJobId");
        sj.setTenantId(15L);
        sj.setProjectId(-1L);
        sj.setJobKey("testJobKey");
        sj.setExecStartTime(new Timestamp(System.currentTimeMillis()));
        sj.setExecEndTime(new Timestamp(System.currentTimeMillis()));
        sj.setTaskId(-1L);
        sj.setJobName("Python");
        sj.setCreateUserId(0L);
        sj.setIsDeleted(0);
        sj.setBusinessDate("20200608234500");
        sj.setCycTime(DateUtil.getUnStandardFormattedDate(System.currentTimeMillis()));
        sj.setTaskType(EJobType.SQL.getType());
        sj.setAppType(0);
        sj.setType(0);
        sj.setIsRestart(0);
        sj.setDependencyType(0);
        sj.setFlowJobId("0");
        sj.setPeriodType(0);
        sj.setMaxRetryNum(0);
        sj.setRetryNum(0);
        sj.setComputeType(1);
        sj.setLogInfo("{err: test_log_info}");
        sj.setEngineLog("{err: test_engine_log}");
        return sj;
    }

    public static EngineJobRetry getEngineJobRetryTemplate() {
        EngineJobRetry ej = new EngineJobRetry();
        ej.setEngineJobId(ValueUtils.getChangedStr());
        ej.setJobId(ValueUtils.getChangedStr());
        ej.setStatus(0);
        ej.setEngineLog("{err: test_engine_log}");
        ej.setLogInfo("{err: test_log_info}");
        ej.setApplicationId(ValueUtils.getChangedStr());
        ej.setRetryNum(2);
        ej.setRetryTaskParams("{err: test_retry_task_params}");
        return ej;
    }

    public static EngineJobCheckpoint getEngineJobCheckpointTemplate() {
        EngineJobCheckpoint jc = new EngineJobCheckpoint();
        jc.setTaskId("taskId");
        jc.setTaskEngineId("te-999");
        jc.setCheckpointId("2");
        jc.setCheckpointTrigger(Timestamp.valueOf("2020-06-14 12:12:12"));
        jc.setCheckpointSavepath("hdfs://tmp/flink/checkpoint/test");
        jc.setCheckpointCounts("2");
        return jc;
    }

    public static EngineJobCheckpoint getEngineJobSavepointTemplate() {
        EngineJobCheckpoint jc = new EngineJobCheckpoint();
        jc.setTaskId("taskId");
        jc.setTaskEngineId("te-9991");
        jc.setCheckpointId("savepointId");
        jc.setCheckpointTrigger(Timestamp.valueOf("2020-06-14 12:12:12"));
        jc.setCheckpointSavepath("hdfs://ns1/dtInsight/flink110/savepoints/savepoint-77aea4-a0b9f689989c");
        jc.setCheckpointCounts("2");
        return jc;
    }

    public static EngineJobCache getEngineJobCacheTemplate() {
        EngineJobCache engineJobCache = new EngineJobCache();
        engineJobCache.setJobId("jobId");
        engineJobCache.setJobName("test");
        engineJobCache.setEngineType("spark");
        engineJobCache.setJobPriority(10L);
        engineJobCache.setComputeType(1);
        engineJobCache.setJobInfo("{\"engineType\":\"spark\",\"taskType\":2,\"computeType\":0, \"tenantId\":9}");
        engineJobCache.setStage(2);
        engineJobCache.setIsFailover(0);
        engineJobCache.setNodeAddress("node01");
        engineJobCache.setJobResource("dtScript_dev_default_batch_Yarn");

        return engineJobCache;
    }

    public static EngineJobCache getEngineJobCacheTemplate2() {
        EngineJobCache engineJobCache = new EngineJobCache();
        engineJobCache.setJobId("jobId2");
        engineJobCache.setJobName("test");
        engineJobCache.setEngineType("spark");
        engineJobCache.setJobPriority(10L);
        engineJobCache.setComputeType(1);
        engineJobCache.setJobInfo("{\"engineType\":\"spark\",\"taskType\":2,\"computeType\":1, \"tenantId\":9, \"maxRetryNum\":3,\"taskParams\":\"openCheckpoint:true\"," +
                "\"taskId\":\"jobId2\"}");
        engineJobCache.setStage(2);
        engineJobCache.setIsFailover(0);
        engineJobCache.setNodeAddress(null);
        engineJobCache.setJobResource("test");

        return engineJobCache;
    }

    public static ScheduleTaskShade getScheduleTaskShadeTemplate(){
        ScheduleTaskShade scheduleTaskShade = new ScheduleTaskShade();
        scheduleTaskShade.setTaskId(0L);
        scheduleTaskShade.setExtraInfo("test");
        scheduleTaskShade.setTenantId(ValueUtils.getChangedLong());
        scheduleTaskShade.setProjectId(ValueUtils.getChangedLong());
        scheduleTaskShade.setNodePid(ValueUtils.getChangedLong());
        scheduleTaskShade.setName("testJob");
        scheduleTaskShade.setTaskType(1);
        scheduleTaskShade.setEngineType(2);
        scheduleTaskShade.setComputeType(1);
        scheduleTaskShade.setSqlText("select");
        scheduleTaskShade.setTaskParams("null");
        scheduleTaskShade.setScheduleConf("{\"selfReliance\":false, \"min\":0,\"hour\":0,\"periodType\":\"2\",\"beginDate\":\"2001-01-01\",\"endDate\":\"2121-01-01\",\"isFailRetry\":true,\"maxRetryNum\":\"3\"}");
        scheduleTaskShade.setPeriodType(1);
        scheduleTaskShade.setScheduleStatus(1);
        scheduleTaskShade.setSubmitStatus(1);
        scheduleTaskShade.setGmtCreate(new Timestamp(1592559742000L));
        scheduleTaskShade.setGmtModified(new Timestamp(1592559742000L));
        scheduleTaskShade.setModifyUserId(1L);
        scheduleTaskShade.setCreateUserId(1L);
        scheduleTaskShade.setOwnerUserId(1L);
        scheduleTaskShade.setVersionId(1);
        scheduleTaskShade.setTaskDesc("null");
        scheduleTaskShade.setAppType(1);
        scheduleTaskShade.setIsDeleted(0);
        scheduleTaskShade.setMainClass("DataCollection");
        scheduleTaskShade.setExeArgs("null");
        scheduleTaskShade.setFlowId(0L);
        scheduleTaskShade.setDtuicTenantId(1L);
        scheduleTaskShade.setProjectScheduleStatus(0);

        return scheduleTaskShade;
    }

    public static Tenant getTenantTemplate(){
        Tenant tenant = new Tenant();
        tenant.setDtUicTenantId(ValueUtils.getChangedLong());
        tenant.setTenantName("testCase");
        tenant.setTenantDesc("");
        return tenant;
    }

    public static User getUserTemplate() {
        User user = new User();
        user.setDtuicUserId(-1L);
        user.setUserName("test@dtstack.com");
        user.setEmail("test@dtstack.com");
        user.setStatus(0);
        user.setPhoneNumber("");
        return user;
    }

    public static ScheduleTaskShade getCronDayTask() {

        return null;
    }

    public static Engine getDefaultEngineTemplate(){
        Engine engine = new Engine();
        engine.setId(1L);
        engine.setClusterId(1L);
        engine.setEngineName("hadoop");
        engine.setEngineType(MultiEngineType.HADOOP.getType());
        engine.setSyncType(null);
        engine.setTotalCore(10);
        engine.setTotalMemory(40960);
        engine.setTotalNode(10);
        return engine;
    }

    public static Component getDefaultHdfsComponentTemplate() {
        Component component = new Component();
        component.setEngineId(1L);
        component.setComponentName(EComponentType.HDFS.name());
        component.setComponentTypeCode(EComponentType.HDFS.getTypeCode());
        component.setComponentConfig("{\"fs.defaultFS\":\"hdfs://ns1\",\"hadoop.proxyuser.admin.groups\":\"*\",\"javax.jdo.option.ConnectionDriverName\":\"com.mysql.jdbc.Driver\",\"dfs.replication\":\"2\",\"dfs.ha.fencing.methods\":\"sshfence\",\"dfs.client.failover.proxy.provider.ns1\":\"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider\",\"typeName\":\"yarn2-hdfs2-hadoop2\",\"dfs.ha.fencing.ssh.private-key-files\":\"~/.ssh/id_rsa\",\"dfs.nameservices\":\"ns1\",\"javax.jdo.option.ConnectionURL\":\"jdbc:mysql://kudu2:3306/ide?useSSL=false\",\"dfs.safemode.threshold.pct\":\"0.5\",\"dfs.ha.namenodes.ns1\":\"nn1,nn2\",\"dfs.journalnode.rpc-address\":\"0.0.0.0:8485\",\"dfs.journalnode.http-address\":\"0.0.0.0:8480\",\"dfs.namenode.rpc-address.ns1.nn2\":\"kudu2:9000\",\"dfs.namenode.rpc-address.ns1.nn1\":\"kudu1:9000\",\"hive.metastore.warehouse.dir\":\"/user/hive/warehouse\",\"hive.server2.webui.host\":\"172.16.10.34\",\"dfs.namenode.shared.edits.dir\":\"qjournal://kudu1:8485;kudu2:8485;kudu3:8485/namenode-ha-data\",\"hive.metastore.schema.verification\":\"false\",\"javax.jdo.option.ConnectionUserName\":\"dtstack\",\"hive.server2.support.dynamic.service.discovery\":\"true\",\"javax.jdo.option.ConnectionPassword\":\"abc123\",\"hive.metastore.uris\":\"thrift://kudu1:9083\",\"hive.server2.thrift.port\":\"10000\",\"hive.exec.dynamic.partition.mode\":\"nonstrict\",\"ha.zookeeper.session-timeout.ms\":\"5000\",\"hadoop.tmp.dir\":\"/data/hadoop_${user.name}\",\"dfs.journalnode.edits.dir\":\"/data/dtstack/hadoop/journal\",\"hive.server2.zookeeper.namespace\":\"hiveserver2\",\"hive.server2.enable.doAs\":\"/false\",\"dfs.namenode.http-address.ns1.nn2\":\"kudu2:50070\",\"dfs.namenode.http-address.ns1.nn1\":\"kudu1:50070\",\"hadoop.proxyuser.admin.hosts\":\"*\",\"md5zip\":\"47a9d897ea8fbf51265cecd30536ebad\",\"hive.exec.scratchdir\":\"/user/hive/warehouse\",\"hive.server2.webui.max.threads\":\"100\",\"hive.zookeeper.quorum\":\"kudu1:2181,kudu2:2181,kudu3:2181\",\"datanucleus.schema.autoCreateAll\":\"true\",\"hive.exec.dynamic.partition\":\"true\",\"hive.server2.thrift.bind.host\":\"kudu1\",\"ha.zookeeper.quorum\":\"kudu1:2181,kudu2:2181,kudu3:2181\",\"hive.server2.thrift.min.worker.threads\":\"200\",\"hive.server2.webui.port\":\"10002\",\"dfs.ha.automatic-failover.enabled\":\"true\"}");
        component.setClusterId(1L);
        component.setHadoopVersion("hadoop3");
        component.setComponentTemplate("[]");
        component.setUploadFileName("conf.zip");
        component.setKerberosFileName("kb.zip");
        component.setStoreType(EComponentType.HDFS.getTypeCode());
        return component;
    }

    public static Component getDefaultYarnComponentTemplate() {
        Component component = new Component();
        component.setEngineId(1L);
        component.setComponentName(EComponentType.YARN.name());
        component.setComponentTypeCode(EComponentType.YARN.getTypeCode());
        component.setComponentConfig("{\"yarn.resourcemanager.zk-address\":\"kudu1:2181,kudu2:2181,kudu3:2181\",\"yarn.resourcemanager.admin.address.rm1\":\"kudu1:8033\",\"yarn.resourcemanager.webapp.address.rm2\":\"kudu2:8088\",\"yarn.log.server.url\":\"http://kudu3:19888/jobhistory/logs/\",\"yarn.resourcemanager.admin.address.rm2\":\"kudu2:8033\",\"yarn.resourcemanager.webapp.address.rm1\":\"kudu1:8088\",\"yarn.resourcemanager.ha.rm-ids\":\"rm1,rm2\",\"yarn.resourcemanager.ha.automatic-failover.zk-base-path\":\"/yarn-leader-election\",\"yarn.client.failover-proxy-provider\":\"org.apache.hadoop.yarn.client.ConfiguredRMFailoverProxyProvider\",\"yarn.resourcemanager.scheduler.address.rm1\":\"kudu1:8030\",\"yarn.resourcemanager.scheduler.address.rm2\":\"kudu2:8030\",\"yarn.nodemanager.delete.debug-delay-sec\":\"600\",\"yarn.resourcemanager.address.rm1\":\"kudu1:8032\",\"yarn.log-aggregation.retain-seconds\":\"2592000\",\"yarn.nodemanager.resource.memory-mb\":\"8000\",\"yarn.resourcemanager.ha.enabled\":\"true\",\"yarn.resourcemanager.address.rm2\":\"kudu2:8032\",\"yarn.resourcemanager.cluster-id\":\"yarn-rm-cluster\",\"yarn.scheduler.minimum-allocation-mb\":\"512\",\"yarn.nodemanager.aux-services\":\"mapreduce_shuffle\",\"yarn.resourcemanager.resource-tracker.address.rm1\":\"kudu1:8031\",\"yarn.nodemanager.resource.cpu-vcores\":\"10\",\"yarn.resourcemanager.resource-tracker.address.rm2\":\"kudu2:8031\",\"yarn.nodemanager.pmem-check-enabled\":\"false\",\"yarn.nodemanager.remote-app-log-dir\":\"/tmp/logs\",\"yarn.resourcemanager.ha.automatic-failover.enabled\":\"true\",\"yarn.nodemanager.vmem-check-enabled\":\"false\",\"yarn.resourcemanager.hostname.rm2\":\"kudu2\",\"yarn.nodemanager.webapp.address\":\"kudu1:8042\",\"yarn.resourcemanager.hostname.rm1\":\"kudu1\",\"yarn.nodemanager.aux-services.mapreduce_shuffle.class\":\"org.apache.hadoop.mapred.ShuffleHandler\",\"yarn.resourcemanager.recovery.enabled\":\"true\",\"yarn.log-aggregation-enable\":\"true\",\"yarn.resourcemanager.store.class\":\"org.apache.hadoop.yarn.server.resourcemanager.recovery.ZKRMStateStore\",\"yarn.nodemanager.vmem-pmem-ratio\":\"4\",\"yarn.resourcemanager.zk-state-store.address\":\"kudu1:2181,kudu2:2181,kudu3:2181\",\"ha.zookeeper.quorum\":\"kudu1:2181,kudu2:2181,kudu3:2181\"}");
        component.setClusterId(1L);
        component.setHadoopVersion("hadoop3");
        component.setComponentTemplate("[]");
        component.setUploadFileName("conf.zip");
        component.setKerberosFileName("kb.zip");
        component.setStoreType(EComponentType.HDFS.getTypeCode());
        return component;
    }

    public static Component getDefaultSftpComponentTemplate() {
        Component component = new Component();
        component.setEngineId(1L);
        component.setComponentName(EComponentType.SFTP.name());
        component.setComponentTypeCode(EComponentType.SFTP.getTypeCode());
        component.setComponentConfig("{\"path\":\"/data/sftp\",\"password\":\"abc123\",\"auth\":\"1\",\"port\":\"22\",\"host\":\"172.16.100.115\",\"username\":\"root\"}");
        component.setClusterId(1L);
        component.setHadoopVersion("hadoop3");
        component.setComponentTemplate("[]");
        component.setUploadFileName("");
        component.setKerberosFileName("");
        component.setStoreType(EComponentType.SFTP.getTypeCode());
        return component;
    }

    public static Component getDefaultK8sComponentTemplate(){
        Component component = new Component();
        component.setEngineId(1L);
        component.setComponentName(EComponentType.KUBERNETES.name());
        component.setComponentTypeCode(EComponentType.KUBERNETES.getTypeCode());
        component.setComponentConfig("{\"kubernetes.context\":\"{\\\"kubernetes.context\\\":\\\"apiVersion: v1\\\\nclusters:\\\\n- cluster:\\\\n    certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJd01ETXlOakF6TkRJeU1sb1hEVE13TURNeU5EQXpOREl5TWxvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBS1Q5CjB3bGhxdzVPdWM2ZHBkRUMxaUovNys1SUE3QmZ5c0o4QzExREVnT25PMlJIRFk1S010Z2pPVitYMDZJNmF2ck8KdjgrTVZ2dkxGOEppSndkN0p5UWYrUVEwTjJ0UXU3d3h5R0Vla0Z3OUJMaGpicldRL0s2R2lHcEFiUVllbE5ibwpwZ1dFYUxpU3VJbnhUWGtZU2ltNW15dThVWkY4cFlwcnNSL3VIbHZBOFFIc004TjNrT243THppTFhXd1BBZlhvCnorRXhxeVEzc3JJVFZWdHlGakl5djFIME50RWxQODV0R2JrdGh6S2k4UVJzcDRJZTRQM3dHN01mZnNGdFF3Rm8KRnA4WWVkekZtYU9RTzI4dzgxQTFkcS8zRjdYNWhHK054T3hFTkR4cGU0QkQvZmwwWUM4eXI1UUxoZ3RkV29tegpSanU0MldJeHNsVU9pc1hJOExrQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFBVXYwTi9GRkxEeVBQMnpEaFdKSGVyS25VNHoKeGxiYVNFMjkrZk5jWml5Q0UvQXVkc0RFbk1vZ25rRDg0WndNS1pXT0xXRkI5aUo5Z1VFcTFIT0ZZMlIyZzhiMgpHVko5dmdTVm9nZHhlQ1c3KzBDZ2JWQlEva1hiaU1SRTdPLy90V2lxQTdXOFBaSnc2Q01reXhNS1FFYUR2RFoxCnlydGE1dnM4cGJlcUIvakUrRUVWL0hDdWIvK3VxdjFhVWtWVHZ2N1ZHYllXU0hMN1Z2eitSOUlGSlp5dTI0dGsKVnBPVGRTbFNyY2Fqb3l0eXdMZTF6VzR4bENNd3FRMkRHaDZFeGl2WHBnSHBXVEVvM3Z1Z3VnUTY2S2RUdXpKaApEWDdZKzg3TTFrV1BDZFJmbW02emp5Zk5sbEhQRWhPWGxsanliSzNxRW1qM2FHSkZaUG1lSXptbWVXOD0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=\\\\n    server: https://172.16.101.208:6443\\\\n  name: kubernetes\\\\ncontexts:\\\\n- context:\\\\n    cluster: kubernetes\\\\n    user: kubernetes-admin\\\\n  name: kubernetes-admin@kubernetes\\\\ncurrent-context: kubernetes-admin@kubernetes\\\\nkind: Config\\\\npreferences: {}\\\\nusers:\\\\n- name: kubernetes-admin\\\\n  user:\\\\n    client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJUzZ2VTI2eUxLSzh3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TURBek1qWXdNelF5TWpKYUZ3MHlNVEF6TWpZd016UXlNalZhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXRuVTJZd1hFbFF6cEZZRTgKUGdzajA0azM3QVQ4alI0ajF4cDFEdjNYbHlMSHU5N0JSbURUaVpwTnZmK2lmRHQ2eEVWMVliREYwRHU1NTIrdAplcGt6OVMzK2ZTS1JCTGtpbXhHeHhQQ2xBVUFIMHU4dlRBWWFmeCs5WndUcHdWYU5oY0NWdlRpWnI1Vlp2WEhrCmRkbDgydHFsa29yWVdzeWp2eXdzalFGZEgwNkpyMEw0KzdMaVpPem40YzdDdnBkTWIwamlCRG9uWFFocHBCb1gKNmFFQVE3YUg2WUozaWFtd1lKSTJWdDdITDZ4THFJRU5nU0RNMzRIUm01N2xxNHNIVEYxeEIvTlVKRXRIa3VzbwpsclFYRmpITmtKTWR0bU9lK2JKUWVnL1ZCUjNueWgrVXQ3eGdqV1dMd1pLaUhwSnFKWFZwSFA4dkRudUh4UVA5CmJZYlVrd0lEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFDRXFJWS9TRUd3RXlUUTZWSm03RDM1b0ZmVXljTnEzQTNISwpsTis1bXovTVUzUTZxSGQyenRKcTVZOUs5Tlh6OEk3Y0RDajNtbGFhVjZMeUp0MkcweHBiRDV6RUY2T09xcXg5Cnp1cUpacUtjVmxadmRva2FFNUdmdDA2dkxWN2pjQU5wVDVPditrTWRMSWQ1WkZlNU50REh5N01mMzQwNmJSSXMKamlHTWJiWjVVbXdCSThnZVdha3o0YXY4YVY0akZyZkxtbkRvUUhRMENYNFpQT1pPcmZwRC9wK21tUTBTQ1dFZgpyZ1hKTk02OUozY0xES0tUbGgyQ3FiYnNVcmt0UWhNcHdFNEFzL1A2RjIwUWk3eHpiTDhQa3B4OFB6cGhLSUdPCjR6TGE0akU5U2Rtd1Q0NGJwYUVWNEd0dlQxVGRMUXN1VWVTMm9WNjhpZ1RQWVNlSWpaYz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=\\\\n    client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBdG5VMll3WEVsUXpwRllFOFBnc2owNGszN0FUOGpSNGoxeHAxRHYzWGx5TEh1OTdCClJtRFRpWnBOdmYraWZEdDZ4RVYxWWJERjBEdTU1Mit0ZXBrejlTMytmU0tSQkxraW14R3h4UENsQVVBSDB1OHYKVEFZYWZ4Kzlad1Rwd1ZhTmhjQ1Z2VGlacjVWWnZYSGtkZGw4MnRxbGtvcllXc3lqdnl3c2pRRmRIMDZKcjBMNAorN0xpWk96bjRjN0N2cGRNYjBqaUJEb25YUWhwcEJvWDZhRUFRN2FINllKM2lhbXdZSkkyVnQ3SEw2eExxSUVOCmdTRE0zNEhSbTU3bHE0c0hURjF4Qi9OVUpFdEhrdXNvbHJRWEZqSE5rSk1kdG1PZStiSlFlZy9WQlIzbnloK1UKdDd4Z2pXV0x3WktpSHBKcUpYVnBIUDh2RG51SHhRUDliWWJVa3dJREFRQUJBb0lCQUVmVXczR2Vqck1EWHl3SgpNZmRYR1dhcFNldlFWc0VUMFpaWW95Y2d4bVNJMjh0WnVndUVDU1BPTExjVlVobklyTjlpWFFEMXdCcm51SnVsCnVzMWVUVGRFUVVGd2YxazFyYXNRLzBTQ1hPT3VHcVp2WmRadlBMVTVnSzV6SDdmdTVFNTQ4RHFMY3UzT1JZTXcKdUhteEF0ZUNadGJYZEsvaWlzQ3ptbUowMi8xN21xNzlpOThBWUY2aHc1WlowMENkQXhMWUR5akJqMDZqL0pTNApUZmNKZjRYY1lhTzlTenNUWkRQQUxQbkx3UnZ5TTRVNzVzL0pTTSt6cnA3cHdvUFhNTzUzVVRkRGhNZnBFMlJNCmZVSlJHNk1JcTliVTBLWGVMR1crQzNlTkF1Q1FsWGM4MEZ5cmRSK1NESFRpZ0V2V1pDQWhHMTFrVW1qYmpCRUUKdnZUMGNSRUNnWUVBMElSbVhsaXF5T20zTjZDZXlhdFlyaUJDcWsxdDhyQmpIQXRoNDVyUGdSRFdUemZmTXBvZwo3TmpET0t0cnd1ZU8zb3o0bWloVUtqV25LOEg0VVhSeXUyb3MxcnJuaFc3cENiQ0RCREVITTdNY1pGaU9QeURMCllGeXRWVVRFMlhpQ3orU2VkQndSQUx0MlFTaDNVSlpFZG1COERPVzJDYkhnRTY5VEVYR2tMV2tDZ1lFQTRBR3MKUHNDM1VmQWNTN0ZzQzVYZ1ZQSTJNT0VxOWczVzgzb2hnREJOcFlFZldjMS82c291YVJIQWNCbitlUVNrUU1CZgo0UENFWllpWnFqTTVQajZGQVZLSkNCZldLSUpZamdWK0ZaWGRBc2QyWVlPb21XR0JacnF1b0pCYzhvRW1zbXBDClg4Unc3TjlPMmRCejNJbWpIU3RPNmQ1b0hXSHlIU0c5QnVwYjVwc0NnWUVBbmE4Q0d1YkNnQno5eUx0V1dQdVMKbkZzWkR1QnUvTkFXb3VhWXFCNHlQVkFXUU9Ibmo4U3VrVzE2ZENodDNYNXV0QzIyOGh6OVNNNDZGUVVpVzdiTAo3SjVtT2h3dGFPSnVxRDByVnNnY3dpUDRuSW03U0ZIc2VucWJPWmcvcEpWVmx4RTBJbW4zRWE2eHhxUnJWaTNNCnFCaGV0d0lmbjBVOFJxYVhFdUgxWGNFQ2dZRUEyWGJtUjdtQmZvdFNmTzFPVGVUL2RwZjVvZlJHWjc3QnlYYnMKWk96L3hFZVpMdTVBVzZoUjYvQ3UyR1Z6MVBwN2x0enJkNDBuaXdaVTM1V0E0ZnVCMWVuUlhFai93QzNpV0dYZQpwSWZybWxJWGk4MXI5Uk5pczE5U1BsQkgyNmtqN3hzWE9xK1RUWEh3czZZWmhLVWQ5Q2hpSU1xb1dyWUdmTitQCkNkS2t5emNDZ1lBeU1USEY1LzQrNkMwVlIzWFFOb0F6bmh4RmtBV1MyeXB2Rk9zMW15V3RXaExXd0xpVFVQdTIKZ3lUWUFWYlpGU2xpVkZtRThKcjNJWURIL1lKamtFOTJNdHFaeW8rTGM5bFllR0xTZC9HeTdxaEJ0WmJNeEtVMwpMWk1reE1yNkRyMktWNEJlTldnWHI0b2p2aXVTMEtxWk94MlkyalBxcnpvNnkxbENJS2cyWUE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=\\\\n\\\"}\"}");
        component.setClusterId(1L);
        component.setHadoopVersion("");
        component.setComponentTemplate("[]");
        component.setUploadFileName("");
        component.setKerberosFileName("");
        component.setStoreType(0);
        return component;
    }

    public static ScheduleFillDataJob getDefaultScheduleFillDataJobTemplate(){
        ScheduleFillDataJob scheduleFillDataJob = new ScheduleFillDataJob();
        scheduleFillDataJob.setRunDay("2020-11-26");
        scheduleFillDataJob.setFromDay("2020-11-26");
        scheduleFillDataJob.setToDay("2020-11-26");
        scheduleFillDataJob.setCreateUserId(1L);
        scheduleFillDataJob.setAppType(AppType.RDOS.getType());
        scheduleFillDataJob.setDtuicTenantId(1L);
        scheduleFillDataJob.setJobName("test");
        scheduleFillDataJob.setTenantId(1L);
        scheduleFillDataJob.setProjectId(1L);
        return scheduleFillDataJob;
    }
}
