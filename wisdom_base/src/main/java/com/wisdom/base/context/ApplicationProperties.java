package com.wisdom.base.context;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


/**
 * Properties specific to JHipster.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Async async = new Async();

    private final Http http = new Http();

    private final Cache cache = new Cache();

    private final Mail mail = new Mail();

    private final Security security = new Security();

    private final Swagger swagger = new Swagger();

    private final Metrics metrics = new Metrics();

    private final CorsConfiguration cors = new CorsConfiguration();
    private final Glusterfs glusterfs = new Glusterfs();

    private final Wbcode wbcode = new Wbcode();

    private final HikParam hikParam = new HikParam();

    private final Outstock outstock = new Outstock();

    private final Rabbitmq rabbitmq = new Rabbitmq();

    private final Warn warn = new Warn();

    private final Netty netty = new Netty();

    public Async getAsync() {
        return async;
    }

    public Http getHttp() {
        return http;
    }

    public Cache getCache() {
        return cache;
    }

    public Mail getMail() {
        return mail;
    }

    public Security getSecurity() {
        return security;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public Glusterfs getGlusterfs() {
        return glusterfs;
    }

    public Wbcode getWbcode() {
        return wbcode;
    }

    public HikParam getHikParam() {
        return hikParam;
    }

    public Outstock getOutstock() {
        return outstock;
    }

    public Rabbitmq getRabbitmq() {
        return rabbitmq;
    }

    public Warn getWarn() {
        return warn;
    }

    public Netty getNetty(){
        return netty;
    }

    public static class Async {

        private int corePoolSize = 2;

        private int maxPoolSize = 50;

        private int queueCapacity = 10000;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Http {

        private final Cache cache = new Cache();

        public Cache getCache() {
            return cache;
        }

        public static class Cache {

            private int timeToLiveInDays = 1461;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Cache {

        private int timeToLiveSeconds = 3600;

        private final Ehcache ehcache = new Ehcache();

        public int getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(int timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public static class Ehcache {

            private String maxBytesLocalHeap = "16M";

            public String getMaxBytesLocalHeap() {
                return maxBytesLocalHeap;
            }

            public void setMaxBytesLocalHeap(String maxBytesLocalHeap) {
                this.maxBytesLocalHeap = maxBytesLocalHeap;
            }
        }
    }

    public static class Mail {

        private String from = "yz@localhost";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
    }

    public static class Security {

        private final Authentication authentication = new Authentication();

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private final Jwt jwt = new Jwt();

            public Jwt getJwt() {
                return jwt;
            }

            public static class Jwt {

                private String secret;

                private long tokenValidityInSeconds = 60 * 60;
                private long tokenValidityInSecondsForRememberMe = 2592000;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }
    }

    public static class Swagger {

        private String title = "wisdom iwcs API";

        private String description = "wisdom iwcs API documentation";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName;

        private String contactUrl;

        private String contactEmail;

        private String license;

        private String licenseUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
    }

    public static class Metrics {

        private final Jmx jmx = new Jmx();

        private final Spark spark = new Spark();

        private final Graphite graphite = new Graphite();

        private final Logs logs = new Logs();

        public Jmx getJmx() {
            return jmx;
        }

        public Spark getSpark() {
            return spark;
        }

        public Graphite getGraphite() {
            return graphite;
        }

        public Logs getLogs() {
            return logs;
        }


        public static class Jmx {

            private boolean enabled = true;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Spark {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 9999;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }
        }

        public static class Graphite {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 2003;

            private String prefix = "yz";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }

        public static class Logs {

            private boolean enabled = false;

            private long reportFrequency = 60;

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(int reportFrequency) {
                this.reportFrequency = reportFrequency;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    private final Logging logging = new Logging();

    public Logging getLogging() {
        return logging;
    }

    public static class Logging {

        private final Logstash logstash = new Logstash();

        public Logstash getLogstash() {
            return logstash;
        }

        public static class Logstash {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 5000;

            private int queueSize = 512;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getQueueSize() {
                return queueSize;
            }

            public void setQueueSize(int queueSize) {
                this.queueSize = queueSize;
            }
        }
    }

    public static class Glusterfs {
        private String accessSubPath;
        private String accessKeyID;
        private String secretKey;
        private String bucketName;
        private String endpoint;

        public String getAccessSubPath() {
            return accessSubPath;
        }

        public void setAccessSubPath(String accessSubPath) {
            this.accessSubPath = accessSubPath;
        }

        public String getAccessKeyID() {
            return accessKeyID;
        }

        public void setAccessKeyID(String accessKeyID) {
            this.accessKeyID = accessKeyID;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }
    }

    public static class Wbcode {

        private boolean openWbCode;

        public boolean isOpenWbCode() {
            return openWbCode;
        }

        public void setOpenWbCode(boolean openWbCode) {
            this.openWbCode = openWbCode;
        }

    }

    public static class HikParam {

        private String returnPodUrl;

        private String getOutPodUrl;

        private String endTaskUrl;

        private String rotatePodUrl;

        private String movePodUrl;

        private String tokenCode;

        private String genAgvSchedulingTaskUrl;

        private String continueTaskUrl;

        private String cancelTaskUrl;

        private String clientCode;

        private String returnMapOrPodUrl;

        private String returnTaskStatus;

        private String finiTask;

        public String getFiniTask() {
            return finiTask;
        }

        public void setFiniTask(String finiTask) {
            this.finiTask = finiTask;
        }

        public String getReturnTaskStatus() {
            return returnTaskStatus;
        }

        public void setReturnTaskStatus(String returnTaskStatus) {
            this.returnTaskStatus = returnTaskStatus;
        }

        public String getReturnMapOrPodUrl() {
            return returnMapOrPodUrl;
        }

        public void setReturnMapOrPodUrl(String returnMapOrPodUrl) {
            this.returnMapOrPodUrl = returnMapOrPodUrl;
        }

        public String getGenAgvSchedulingTaskUrl() {
            return genAgvSchedulingTaskUrl;
        }

        public void setGenAgvSchedulingTaskUrl(String genAgvSchedulingTaskUrl) {
            this.genAgvSchedulingTaskUrl = genAgvSchedulingTaskUrl;
        }

        public String getContinueTaskUrl() {
            return continueTaskUrl;
        }

        public void setContinueTaskUrl(String continueTaskUrl) {
            this.continueTaskUrl = continueTaskUrl;
        }

        public String getCancelTaskUrl() {
            return cancelTaskUrl;
        }

        public void setCancelTaskUrl(String cancelTaskUrl) {
            this.cancelTaskUrl = cancelTaskUrl;
        }


        public String getMovePodUrl() {
            return movePodUrl;
        }

        public void setMovePodUrl(String movePodUrl) {
            this.movePodUrl = movePodUrl;
        }

        public String getClientCode() {
            return clientCode;
        }

        public void setClientCode(String clientCode) {
            this.clientCode = clientCode;
        }

        public String getTokenCode() {
            return tokenCode;
        }

        public void setTokenCode(String tokenCode) {
            this.tokenCode = tokenCode;
        }

        public String getReturnPodUrl() {
            return returnPodUrl;
        }

        public void setReturnPodUrl(String returnPodUrl) {
            this.returnPodUrl = returnPodUrl;
        }

        public String getGetOutPodUrl() {
            return getOutPodUrl;
        }

        public void setGetOutPodUrl(String getOutPodUrl) {
            this.getOutPodUrl = getOutPodUrl;
        }

        public String getEndTaskUrl() {
            return endTaskUrl;
        }

        public void setEndTaskUrl(String endTaskUrl) {
            this.endTaskUrl = endTaskUrl;
        }

        public String getRotatePodUrl() {
            return rotatePodUrl;
        }

        public void setRotatePodUrl(String rotatePodUrl) {
            this.rotatePodUrl = rotatePodUrl;
        }
    }

    public static class Netty {

        private String lineClientHost;
        private String lineClientPort;
        private String elevatorClientHost;
        private String elevatorClientPort;
        private String nettyServerHost;
        private String nettyServerPort;

        public String getLineClientHost() {
            return lineClientHost;
        }

        public void setLineClientHost(String lineClientHost) {
            this.lineClientHost = lineClientHost;
        }

        public String getLineClientPort() {
            return lineClientPort;
        }

        public void setLineClientPort(String lineClientPort) {
            this.lineClientPort = lineClientPort;
        }

        public String getElevatorClientHost() {
            return elevatorClientHost;
        }

        public void setElevatorClientHost(String elevatorClientHost) {
            this.elevatorClientHost = elevatorClientHost;
        }

        public String getElevatorClientPort() {
            return elevatorClientPort;
        }

        public void setElevatorClientPort(String elevatorClientPort) {
            this.elevatorClientPort = elevatorClientPort;
        }

        public String getNettyServerHost() {
            return nettyServerHost;
        }

        public void setNettyServerHost(String nettyServerHost) {
            this.nettyServerHost = nettyServerHost;
        }

        public String getNettyServerPort() {
            return nettyServerPort;
        }

        public void setNettyServerPort(String nettyServerPort) {
            this.nettyServerPort = nettyServerPort;
        }
    }

    public static class Outstock {

        private boolean noticeOutstockProcess;

        private boolean allowHandOutstockSupplyCall;

        private boolean forceKeepOutstockProcess;

        public boolean isForceKeepOutstockProcess() {
            return forceKeepOutstockProcess;
        }

        public void setForceKeepOutstockProcess(boolean forceKeepOutstockProcess) {
            this.forceKeepOutstockProcess = forceKeepOutstockProcess;
        }

        public boolean isAllowHandOutstockSupplyCall() {
            return allowHandOutstockSupplyCall;
        }

        public void setAllowHandOutstockSupplyCall(boolean allowHandOutstockSupplyCall) {
            this.allowHandOutstockSupplyCall = allowHandOutstockSupplyCall;
        }

        public boolean isNoticeOutstockProcess() {
            return noticeOutstockProcess;
        }

        public void setNoticeOutstockProcess(boolean noticeOutstockProcess) {
            this.noticeOutstockProcess = noticeOutstockProcess;
        }
    }

    public static class Rabbitmq {
        private String host;
        private Integer port;
        private String username;
        private String password;
        private String virtualHost;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVirtualHost() {
            return virtualHost;
        }

        public void setVirtualHost(String virtualHost) {
            this.virtualHost = virtualHost;
        }
    }

    public static class Warn {
        private String checkName;

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }
    }
}
