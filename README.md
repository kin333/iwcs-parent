# iwcs


## iwcs管理系统产品主分支
### 分支介绍
 该分支用于维护产品最新代码，项目相关定制需求严禁合并到该分支。
 可在案例模块，将具有复用或示范价值的定制代码迁入，但禁止破坏产品主体模型的修改。

prd_base
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