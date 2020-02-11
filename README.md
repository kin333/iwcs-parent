# iwcs


## iwcs管理系统产品  长春分支
### 分支介绍

changchun
### 运行开发步骤
#### 把socket依赖加到本地仓库
```shell
mvn install:install-file -Dfile=libs/socketio-1.0.0.jar -Dpackaging=jar -DpomFile=libs/pom.xml
```



#### 把license依赖加到本地仓库
```shell
mvn install:install-file -Dfile=libs/license-manage-1.0.0.jar -Dpackaging=jar -DpomFile=libs/license-manage-1.0.0.pom
```
### 正式上线手动开启
#### 1.历史数据迁移功能上线待配：
    http://IP:port/quartz/job/save
    {
        "beanName": "HisDataMigrationService", 
        "methodName": "dataMigration",
        "cronExpression": ""
    }