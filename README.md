springboot-tools
====

springboot 项目中常用的工具集



## 1. 模块描述

 模块中文名|模块英文名|模块内容
 ---- | ----- | ------ 
 公用模块|common|通用工具-基本对象、工具类
 缓存|cache|缓存内容-springcache redis guava caffeine
 数据源动态路由|dynamic-data-source|常遇到的多数据源、或相同表结构不同库等情况
 事务扩展|transaction-extension|需要在核心事物提交前、后做某些操作（强一致性操作or最终一致）
 逻辑扩展|logic-extension|针对代码中多条件（if switch 等情况）减少逻辑代码增强扩展性
 逻辑扩展增强|logic-extension-starter|减少代码中逻辑判断使用自定义条件对象、函数式调用方法、支持配置
 代码生成组件|generator|使用模板与myabtis扩展组件根据库表生成代码已经页面相关
 ...持续添加


## 2. 使用的框架方案

* spring-boot 2.2.6.RELEASE
* lombok

